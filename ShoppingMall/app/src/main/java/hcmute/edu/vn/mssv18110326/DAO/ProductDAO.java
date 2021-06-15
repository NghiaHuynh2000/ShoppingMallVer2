package hcmute.edu.vn.mssv18110326.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

import de.hdodenhof.circleimageview.CircleImageView;
import hcmute.edu.vn.mssv18110326.Data.DatabaseManager;


public class ProductDAO {
    Context context;
    private DatabaseManager data;

    public ProductDAO( DatabaseManager data) {

        this.data = data;
    }

    public ProductDAO( DatabaseManager data, Context context) {

        this.data = data;
        this.context = context;
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

    public void AddProduct(String name,int price, byte[] image, int id_cate, String description){
        SQLiteDatabase db = data.getWritableDatabase();
        String sql="Insert into product(name,price,image,id_cate,description) values (?,?,?,?,?)";
        SQLiteStatement statement = db.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1,name);
        statement.bindLong(2,price);
        statement.bindBlob(3,image);
        statement.bindLong(4,id_cate);
        statement.bindString(5,description);
        statement.execute();
    }

    public void AddProductImgString(String name,int price, String image, int id_cate, String description){

        int resourceId = context.getResources().getIdentifier(image, "drawable", context.getPackageName() );

        ImageView imageView = new ImageView(context);
        imageView.setImageResource(resourceId);

        byte[] imageProduct = ConverttoArrayByte(imageView);

        SQLiteDatabase db = data.getWritableDatabase();
        String sql="Insert into product(name,price,image,id_cate,description) values (?,?,?,?,?)";
        SQLiteStatement statement = db.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1,name);
        statement.bindLong(2,price);
        statement.bindBlob(3,imageProduct);
        statement.bindLong(4,id_cate);
        statement.bindString(5,description);
        statement.execute();
    }

    public byte[] ConverttoArrayByte(ImageView image){
        BitmapDrawable bitmapDrawable = (BitmapDrawable) image.getDrawable();
        Bitmap bitmap=bitmapDrawable.getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        return stream.toByteArray();
    }
}
