package hcmute.edu.vn.mssv18110326.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import hcmute.edu.vn.mssv18110326.Data.DatabaseManager;
import hcmute.edu.vn.mssv18110326.Model.Users;

public class UsersDAO {


    private  static final String USER = "users";
    private  static  final String ID = "id";
    private  static final String NAME = "name";
    private  static final String EMAIL = "email";
    private  static final String PASSWORD = "password";
    private static final String ADDRESS="address";
    Context thiscontext;
    private DatabaseManager dbb;
    public UsersDAO(DatabaseManager dbb) {
        this.dbb = dbb;

    }

    public  boolean login(String email, String password){


        SQLiteDatabase db = dbb.getWritableDatabase();
        Cursor cursor = db.rawQuery(" select * from users where email = ? and password = ?", new String[]{email , password});
        if( cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public void addUsers(Users users){
        SQLiteDatabase db = dbb.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, users.getmName());
        values.put(EMAIL, users.getmEmail());
        values.put(PASSWORD, users.getmPassword());
        db.insert(USER,null,values);
        db.close();
    }


}
