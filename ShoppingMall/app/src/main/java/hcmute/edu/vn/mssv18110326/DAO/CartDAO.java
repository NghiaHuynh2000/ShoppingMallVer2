package hcmute.edu.vn.mssv18110326.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;

import hcmute.edu.vn.mssv18110326.Data.DatabaseManager;
import hcmute.edu.vn.mssv18110326.Model.Cart;

public class CartDAO {
    Context context;
    private DatabaseManager data;

    ProductDAO productDAO=new ProductDAO(data);

    public CartDAO( DatabaseManager data) {

        this.data = data;
    }

    public void AddCart(String email, int id_pro,String color, String size,int quantity){
        int id_color=GetID_Color(color);
        int id_size=GetID_Size(size);

        SQLiteDatabase db = data.getWritableDatabase();
        String sql="Insert into cart(email,id_product,id_color,id_size,quantity) values (?,?,?,?,?)";
        SQLiteStatement statement = db.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1,email);
        statement.bindLong(2,id_pro);
        statement.bindLong(3,id_color);
        statement.bindLong(4,id_size);
        statement.bindLong(5,quantity);
        statement.execute();
    }

    public void UpdateCart(String email, int id_pro,String color, String size,int quantity){
        int id_color=GetID_Color(color);
        int id_size=GetID_Size(size);

        SQLiteDatabase db = data.getWritableDatabase();
        String sql="Update cart set quantity=? where email=? and id_product=? and id_color=? and id_size=?";
        SQLiteStatement statement = db.compileStatement(sql);
        statement.clearBindings();
        statement.bindLong(1,quantity);
        statement.bindString(2,email);
        statement.bindLong(3,id_pro);
        statement.bindLong(4,id_color);
        statement.bindLong(5,id_size);
        statement.execute();
    }

    public void DeleteCart(String email, int id_pro,String color, String size){
        int id_color=GetID_Color(color);
        int id_size=GetID_Size(size);

        SQLiteDatabase db = data.getWritableDatabase();
        String sql="Delete from cart where email=? and id_product=? and id_color=? and id_size=?";
        SQLiteStatement statement = db.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1,email);
        statement.bindLong(2,id_pro);
        statement.bindLong(3,id_color);
        statement.bindLong(4,id_size);
        statement.execute();

    }

    public ArrayList<Cart> GetCart(String email){
        ArrayList<Cart> cartArrayList = new ArrayList<Cart>();
        Cart cart;
        SQLiteDatabase db = data.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from cart where email=?",new String[]{email});
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            cart=new Cart();
            cart.setId(cursor.getInt(1));
            Cursor cursorProduct=GetProduct(cursor.getInt(1));

            cursorProduct.moveToFirst();
            while (!cursorProduct.isAfterLast()) {
                cart.setName(cursorProduct.getString(1));
                cart.setPrice(cursorProduct.getInt(2));
                cart.setImage(cursorProduct.getBlob(3));
                cursorProduct.moveToNext();
            }

            cart.setColor(Get_NameColor(cursor.getInt(2)));
            cart.setSize(Get_NameSize(cursor.getInt(3)));
            cart.setQty(cursor.getInt(4));

            cartArrayList.add(cart);
            cursor.moveToNext();
        }
        return cartArrayList;
    }

    public int GetID_Color(String name){
        SQLiteDatabase db = data.getWritableDatabase();
        int id_color=0;
        Cursor cursor = db.rawQuery("select * from color where name=?",new String[]{name});
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            id_color=cursor.getInt(0);
            cursor.moveToNext();
        }
        return id_color;
    }

    public int GetID_Size(String name){
        int id_size=0;
        SQLiteDatabase db = data.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from size where name=?",new String[]{name});
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            id_size=cursor.getInt(0);
            cursor.moveToNext();
        }
        return id_size;
    }

    public String Get_NameColor(int id_color){
        String name_color="";
        SQLiteDatabase db = data.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from color where id_color=?",new String[]{String.valueOf(id_color)});
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            name_color=cursor.getString(1);
            cursor.moveToNext();
        }
        return name_color;
    }

    public String Get_NameSize(int id_size){
        String name_size="";
        SQLiteDatabase db = data.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from size where id_size=?",new String[]{String.valueOf(id_size)});
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            name_size=cursor.getString(1);
            cursor.moveToNext();
        }
        return name_size;
    }

    public Cursor GetProduct(int id){
        SQLiteDatabase db = data.getWritableDatabase();
        Cursor cursor = db.rawQuery( "select * from product where id=?",new String[]{String.valueOf(id)});
        return  cursor;

    }
}
