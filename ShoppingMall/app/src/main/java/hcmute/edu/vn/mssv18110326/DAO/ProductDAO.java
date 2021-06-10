package hcmute.edu.vn.mssv18110326.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import hcmute.edu.vn.mssv18110326.Data.DatabaseManager;


public class ProductDAO {
    Context thiscontext;
    private DatabaseManager data;

    public ProductDAO( DatabaseManager data) {

        this.data = data;
    }
    public Cursor GetSSD(){
        SQLiteDatabase db = data.getWritableDatabase();
        Cursor cursor = db.rawQuery( "select * from product where id_cate = 1",null);
        return  cursor;

    }

    public Cursor GetDetailsProduct(int id){
        SQLiteDatabase db = data.getWritableDatabase();
        Cursor cursor = db.rawQuery( "select * from product where id = ?",new String[]{String.valueOf(id)});
        return  cursor;

    }
}
