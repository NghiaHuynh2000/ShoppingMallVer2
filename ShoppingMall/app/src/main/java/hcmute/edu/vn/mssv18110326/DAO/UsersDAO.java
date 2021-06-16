package hcmute.edu.vn.mssv18110326.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

import de.hdodenhof.circleimageview.CircleImageView;
import hcmute.edu.vn.mssv18110326.Data.DatabaseManager;
import hcmute.edu.vn.mssv18110326.Model.Users;

public class UsersDAO {


    private  static final String USER = "users";
    private  static  final String ID = "id";
    private  static final String NAME = "name";
    private  static final String EMAIL = "email";
    private  static final String PASSWORD = "password";
    private static final String PHONE="phone";
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
        values.put(PHONE,users.getmPhone());
        values.put(EMAIL, users.getmEmail());
        values.put(PASSWORD, users.getmPassword());
        db.insert(USER,null,values);
        db.close();
    }

    public byte[] ConverttoArrayByte(CircleImageView image){
        BitmapDrawable bitmapDrawable = (BitmapDrawable) image.getDrawable();
        Bitmap bitmap=bitmapDrawable.getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        return stream.toByteArray();
    }

    public void UpdateAvt(String email, byte[] image){
        SQLiteDatabase db = dbb.getWritableDatabase();
        String sql="Update users set image=? where email=?";
        SQLiteStatement statement = db.compileStatement(sql);
        statement.clearBindings();
        statement.bindBlob(1,image);
        statement.bindString(2,email);
        statement.execute();
    }

    public byte[] GetImageAvt(String email){
        byte[] image=null;
        SQLiteDatabase db = dbb.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where email=?",new String[]{email});
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            image= cursor.getBlob(6);
            cursor.moveToNext();
        }
        return image;
    }

}
