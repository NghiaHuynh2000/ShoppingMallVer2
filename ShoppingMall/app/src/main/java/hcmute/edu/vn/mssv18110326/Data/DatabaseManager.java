package hcmute.edu.vn.mssv18110326.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import hcmute.edu.vn.mssv18110326.DAO.ProductDAO;
import hcmute.edu.vn.mssv18110326.Model.Bill;
import hcmute.edu.vn.mssv18110326.Model.Cart;
import hcmute.edu.vn.mssv18110326.Model.Details;


public class DatabaseManager extends SQLiteOpenHelper {
    // tạo db
    public  static  final String DATABASE_NAME = "db_andorid.db";

    //   tạo bảng user
    private  static final String USER = "users";
    private  static  final String ID = "id";
    private  static final String NAME = "name";
    private  static final String EMAIL = "email";
    private  static final String PHONE = "phone";
    private  static final String PASSWORD = "password";
    private static final String ADDRESS = "address";
    private static final String IMAGE_USER = "image";
    private static final String PERMISSION = "permission";
    private static final String ACTIVE="active";

    // tạo bảng category
    private  static final String CATEGORY = "category";
    private  static  final String ID_CATE = "id";
    private  static  final String NAME_CATE = "name";

    // tạo bản sản phẩm
    private  static final String PRODUCT = "product";
    private  static  final String ID_PRO = "id";
    private  static  final String NAME_PRO = "name";
    private  static  final String PRICE_PRODUCT = "price";
    private  static  final String IMAGE_PRO = "image";
    private  static  final String ID_CATEGORY = "id_cate";
    private  static  final String DESC_PRO = "description";
    private static final String QUANTITY_PRO="quantity";

    //cart
    private  static final String CART = "cart";
    private  static  final String EMAIL_USER = "email";
    private  static  final String ID_PRODUCT = "id_product";
    private static final String ID_COLOR_CART="id_color";
    private static final String ID_SIZE_CART="id_size";
    private  static  final String QTY = "quantity";

    // tạo bản bill
    private  static final String BILL = "bill";
    private  static final String ID_BILL = "id";
    private  static final String DATE = "date";
    private  static final String YOUR_ADDRESS = "you_address";
    private  static final String TOTAL = "total";
    private  static final String ID_USER = "id_user";
    private  static final String STATUS = "status";

   // details
   private  static final String DETAILS = "details";
   private  static final String ID_DETAIL = "id";
   private  static  final String BILL_ID = "id_bill";
   private  static  final String PRODUCT_ID = "id_product";
   private  static  final String QUANTITY = "quantity";


   // color
    private static final String COLOR = "color";
    private static final String ID_COLOR = "id_color";
    private static final String NAME_COLOR = "name";

    // size
    private static final String SIZE = "size";
    private static final String ID_SIZE = "id_size";
    private static final String NAME_SIZE = "name";

    private final Context context;

    public DatabaseManager( Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String SQLQueryUser = "CREATE TABLE " + USER + " (" + ID + " integer primary  key AUTOINCREMENT , " +
                NAME + " TEXT, " +
                EMAIL + " TEXT, " +
                PHONE + " TEXT, " +
                PASSWORD + " TEXT, " +
                ADDRESS + " TEXT, " +
                IMAGE_USER + " BLOB, " +
                PERMISSION + " integer, " +
                ACTIVE + " integer) ";

        String SQLQueryCategory = "CREATE TABLE " + CATEGORY + " (" + ID_CATE + " integer primary  key AUTOINCREMENT , " +
                NAME_CATE + " TEXT)";

        String SQLQueryProduct = "CREATE TABLE " + PRODUCT + " (" + ID_PRO + " integer primary  key AUTOINCREMENT , " +
                NAME_PRO + " TEXT, " +
                PRICE_PRODUCT + " integer, " +
                IMAGE_PRO + " BLOB, " +
                ID_CATEGORY + " integer , " +
                DESC_PRO + " TEXT, " +
                QUANTITY_PRO + " integer ) ";

        String SQLQueryCart = "CREATE TABLE " + CART + " (" +
                EMAIL_USER + " TEXT, " +
                ID_PRODUCT + " integer, " +
                ID_COLOR_CART + " integer, " +
                ID_SIZE_CART + " integer, " +
                QTY + " integer, " +
                "PRIMARY key(" + EMAIL_USER+", "+ID_PRODUCT+", "+ID_COLOR_CART+", "+ID_SIZE_CART+"))";

        String SQLQueryBill = "CREATE TABLE " + BILL + " (" + ID_BILL + " integer primary  key AUTOINCREMENT , " +
                DATE + " TEXT , " +
                YOUR_ADDRESS + " TEXT , " +
                PHONE + " integer, " +
                EMAIL + " TEXT, " +
                NAME + " TEXT, " +
                TOTAL + " integer, " +
                ID_USER + " integer, " +
                STATUS + " integer ) ";

        String SQLQueryDetails= "CREATE TABLE " + DETAILS + " (" + ID_DETAIL + " integer primary  key AUTOINCREMENT , " +
                BILL_ID + " integer , " +
                PRODUCT_ID + " integer, " +
                ID_COLOR + " integer, " +
                ID_SIZE + " integer, " +
                QUANTITY + " integer ) ";

        String SQLQueryColor = "CREATE TABLE " + COLOR + " (" + ID_COLOR + " integer primary  key AUTOINCREMENT , " +
                NAME_COLOR + " TEXT ) ";

        String SQLQuerySize = "CREATE TABLE " + SIZE + " (" + ID_SIZE + " integer primary  key AUTOINCREMENT , " +
                NAME_SIZE + " TEXT ) ";

        sqLiteDatabase.execSQL(SQLQueryUser);
        sqLiteDatabase.execSQL(SQLQueryCategory);
        sqLiteDatabase.execSQL(SQLQueryProduct);
        sqLiteDatabase.execSQL(SQLQueryCart);
        sqLiteDatabase.execSQL(SQLQueryBill);
        sqLiteDatabase.execSQL(SQLQueryColor);
        sqLiteDatabase.execSQL(SQLQuerySize);
        sqLiteDatabase.execSQL(SQLQueryDetails);

        // Add Color
        String SqlColor1 = " insert into color(name) values ('Trắng')";
        String SqlColor2 = " insert into color(name) values ('Vàng')";
        String SqlColor3 = " insert into color(name) values ('Đỏ')";
        String SqlColor4 = " insert into color(name) values ('Xanh')";
        String SqlColor5 = " insert into color(name) values ('Đen')";

        sqLiteDatabase.execSQL(SqlColor1);
        sqLiteDatabase.execSQL(SqlColor2);
        sqLiteDatabase.execSQL(SqlColor3);
        sqLiteDatabase.execSQL(SqlColor4);
        sqLiteDatabase.execSQL(SqlColor5);

        // Add Size
        String SqlSize1 = " insert into size(name) values ('S')";
        String SqlSize2 = " insert into size(name) values ('M')";
        String SqlSize3 = " insert into size(name) values ('L')";
        String SqlSize4 = " insert into size(name) values ('XL')";
        String SqlSize5 = " insert into size(name) values ('XXL')";

        sqLiteDatabase.execSQL(SqlSize1);
        sqLiteDatabase.execSQL(SqlSize2);
        sqLiteDatabase.execSQL(SqlSize3);
        sqLiteDatabase.execSQL(SqlSize4);
        sqLiteDatabase.execSQL(SqlSize5);


        // Add Category
        String SqlCate1="insert into category(name) values ('Áo')";
        String SqlCate2="insert into category(name) values ('Quần')";
        String SqlCate3="insert into category(name) values ('Phụ kiện')";

        sqLiteDatabase.execSQL(SqlCate1);
        sqLiteDatabase.execSQL(SqlCate2);
        sqLiteDatabase.execSQL(SqlCate3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+USER);
        db.execSQL("DROP TABLE IF EXISTS "+CATEGORY);
        db.execSQL("DROP TABLE IF EXISTS "+PRODUCT);
        db.execSQL("DROP TABLE IF EXISTS "+CART);
        db.execSQL("DROP TABLE IF EXISTS "+BILL);
        db.execSQL("DROP TABLE IF EXISTS "+DETAILS);
        onCreate(db);



    }
    public Cursor GetBills(int id_user){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery( "select * from bill where id_user = ?",new String[]{String.valueOf(id_user)});
        return  cursor;

    }
    public void AddCart(Cart cart){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID_PRODUCT, cart.getId());
//        values.put(NAME_PRODUCT, cart.getName());
        values.put(QTY, cart.getQty());
//        values.put(SUB_PRICE, cart.getPrice());
//        values.put(IMG, cart.getImage());
        db.insert(CART,null,values);
        db.close();
    }

    /////

    public void AddProduct(){
        SQLiteDatabase db = this.getWritableDatabase();

        ProductDAO productDAO= new ProductDAO(this,context);

        productDAO.AddProductImgString("Áo thun con mèo",150000,"aothun1",1,"- In ấn với công nghệ tốt nhất.\n- Chất lượng thun mịn, giặt không nhăn.\n- Màu sắc đẹp hài hòa, không bay màu, không nhăn.\n- Sản phẩm với thiết kế ưu chuộng nhất hiện nay.\n- Được người tiêu dùng lựa chọn nhiều nhất.\n- Mẫu mã đa dạng, dễ dàng chọn lựa.");

        productDAO.AddProductImgString("Áo thun đen trơn",120000,"aothun2",1,"- In ấn với công nghệ tốt nhất.\n- Chất lượng thun mịn, giặt không nhăn.\n- Màu sắc đẹp hài hòa, không bay màu, không nhăn.\n- Sản phẩm với thiết kế ưu chuộng nhất hiện nay.\n- Được người tiêu dùng lựa chọn nhiều nhất.\n- Mẫu mã đa dạng, dễ dàng chọn lựa.");

        productDAO.AddProductImgString("Áo thun trắng trơn",120000,"aothun3",1,"- In ấn với công nghệ tốt nhất.\n- Chất lượng thun mịn, giặt không nhăn.\n- Màu sắc đẹp hài hòa, không bay màu, không nhăn.\n- Sản phẩm với thiết kế ưu chuộng nhất hiện nay.\n- Được người tiêu dùng lựa chọn nhiều nhất.\n- Mẫu mã đa dạng, dễ dàng chọn lựa.");

        productDAO.AddProductImgString("Áo Thun Off Đen",180000,"aothun4",1,"- In ấn với công nghệ tốt nhất.\n- Chất lượng thun mịn, giặt không nhăn.\n- Màu sắc đẹp hài hòa, không bay màu, không nhăn.\n- Sản phẩm với thiết kế ưu chuộng nhất hiện nay.\n- Được người tiêu dùng lựa chọn nhiều nhất.\n- Mẫu mã đa dạng, dễ dàng chọn lựa.");

        productDAO.AddProductImgString("NAM UI Áo Thun Ngắn Tay",150000,"aothun5",1,"- In ấn với công nghệ tốt nhất.\n- Chất lượng thun mịn, giặt không nhăn.\n- Màu sắc đẹp hài hòa, không bay màu, không nhăn.\n- Sản phẩm với thiết kế ưu chuộng nhất hiện nay.\n- Được người tiêu dùng lựa chọn nhiều nhất.\n- Mẫu mã đa dạng, dễ dàng chọn lựa.");

        productDAO.AddProductImgString("Áo Sơ mi Lụa Tay Ngắn Trơn Basic",280000,"somi1",1,"- In ấn với công nghệ tốt nhất.\n- Chất lượng vải mịn, giặt không nhăn.\n- Màu sắc đẹp hài hòa, không bay màu, không nhăn.\n- Sản phẩm với thiết kế ưu chuộng nhất hiện nay.\n- Được người tiêu dùng lựa chọn nhiều nhất.\n- Mẫu mã đa dạng, dễ dàng chọn lựa.");

        productDAO.AddProductImgString("Áo Sơ Mi Thom Browne Grosgrain Armbands",310000,"somi2",1,"- In ấn với công nghệ tốt nhất.\n- Chất lượng vải mịn, giặt không nhăn.\n- Màu sắc đẹp hài hòa, không bay màu, không nhăn.\n- Sản phẩm với thiết kế ưu chuộng nhất hiện nay.\n- Được người tiêu dùng lựa chọn nhiều nhất.\n- Mẫu mã đa dạng, dễ dàng chọn lựa.");

        productDAO.AddProductImgString("Sơ Mi Tay Dài Đơn Giản M24",350000,"somi3",1,"- In ấn với công nghệ tốt nhất.\n- Chất lượng vải mịn, giặt không nhăn.\n- Màu sắc đẹp hài hòa, không bay màu, không nhăn.\n- Sản phẩm với thiết kế ưu chuộng nhất hiện nay.\n- Được người tiêu dùng lựa chọn nhiều nhất.\n- Mẫu mã đa dạng, dễ dàng chọn lựa.");

        productDAO.AddProductImgString("Sơ Mi Tay Dài Đơn Giản N01",360000,"somi4",1,"- In ấn với công nghệ tốt nhất.\n- Chất lượng vải mịn, giặt không nhăn.\n- Màu sắc đẹp hài hòa, không bay màu, không nhăn.\n- Sản phẩm với thiết kế ưu chuộng nhất hiện nay.\n- Được người tiêu dùng lựa chọn nhiều nhất.\n- Mẫu mã đa dạng, dễ dàng chọn lựa.");

        productDAO.AddProductImgString("Sơ Mi Tay Dài Đơn Giản M22",320000,"somi5",1,"- In ấn với công nghệ tốt nhất.\n- Chất lượng vải mịn, giặt không nhăn.\n- Màu sắc đẹp hài hòa, không bay màu, không nhăn.\n- Sản phẩm với thiết kế ưu chuộng nhất hiện nay.\n- Được người tiêu dùng lựa chọn nhiều nhất.\n- Mẫu mã đa dạng, dễ dàng chọn lựa.");

//----------------------------------------------------------------------------------------------------------------

        productDAO.AddProductImgString("Quần Short Dị Biệt M2",320000,"quanshort1",2,"- Thiết kế thời trang, phù hợp cho giới trẻ.\n- Sản phẩm với chất lượng cao.\n- Khả năng độ bền và màu sắc rất tốt.\n- Nhiều năm liền được chọn là những sản phẩm ưa chuộng.\n- Đứng đầu trong ngành sản xuất về chất lượng cao.");

        productDAO.AddProductImgString("Quần Short Đơn Giản Y Nguyên Bản M3",340000,"quanshort2",2,"- Thiết kế thời trang, phù hợp cho giới trẻ.\n- Sản phẩm với chất lượng cao.\n- Khả năng độ bền và màu sắc rất tốt.\n- Nhiều năm liền được chọn là những sản phẩm ưa chuộng.\n- Đứng đầu trong ngành sản xuất về chất lượng cao.");

        productDAO.AddProductImgString("Quần Short Đơn Giản B2KM01",310000,"quanshort3",2,"- Thiết kế thời trang, phù hợp cho giới trẻ.\n- Sản phẩm với chất lượng cao.\n- Khả năng độ bền và màu sắc rất tốt.\n- Nhiều năm liền được chọn là những sản phẩm ưa chuộng.\n- Đứng đầu trong ngành sản xuất về chất lượng cao.");

        productDAO.AddProductImgString("[SOO04] SHT Quần Short Đặc Biệt B1ST13",390000,"quanshort4",2,"- Thiết kế thời trang, phù hợp cho giới trẻ.\n- Sản phẩm với chất lượng cao.\n- Khả năng độ bền và màu sắc rất tốt.\n- Nhiều năm liền được chọn là những sản phẩm ưa chuộng.\n- Đứng đầu trong ngành sản xuất về chất lượng cao.");

        productDAO.AddProductImgString("[SOO04] Quần Short Đặc Biệt C02",390000,"quanshort5",2,"- Thiết kế thời trang, phù hợp cho giới trẻ.\n- Sản phẩm với chất lượng cao.\n- Khả năng độ bền và màu sắc rất tốt.\n- Nhiều năm liền được chọn là những sản phẩm ưa chuộng.\n- Đứng đầu trong ngành sản xuất về chất lượng cao.");

        productDAO.AddProductImgString("Quần Dài Tây Đơn Giản HG09",450000,"quandai1",2,"- Thiết kế thời trang, phù hợp cho giới trẻ.\n- Sản phẩm với chất lượng cao.\n- Khả năng độ bền và màu sắc rất tốt.\n- Nhiều năm được chọn là sản phẩm ưa chuộng.\n- Đứng đầu trong ngành sản xuất về chất lượng cao.");

        productDAO.AddProductImgString("Quần Dài Jogger Đặc Biệt Drama Ver1",460000,"quandai2",2,"- Thiết kế thời trang, phù hợp cho giới trẻ.\n- Sản phẩm với chất lượng cao.\n- Khả năng độ bền và màu sắc rất tốt.\n- Nhiều năm được chọn là sản phẩm ưa chuộng.\n- Đứng đầu trong ngành sản xuất về chất lượng cao.");

        productDAO.AddProductImgString("[SOO04] SHT Quần Dài Jogger Đặc Biệt A02",430000,"quandai3",2,"- Thiết kế thời trang, phù hợp cho giới trẻ.\n- Sản phẩm với chất lượng cao.\n- Khả năng độ bền và màu sắc rất tốt.\n- Nhiều năm được chọn là sản phẩm ưa chuộng.\n- Đứng đầu trong ngành sản xuất về chất lượng cao.");

        productDAO.AddProductImgString("[SOO04] Quần Dài Jogger Đặc Biệt G02",490000,"quandai4",2,"- Thiết kế thời trang, phù hợp cho giới trẻ.\n- Sản phẩm với chất lượng cao.\n- Khả năng độ bền và màu sắc rất tốt.\n- Nhiều năm được chọn là sản phẩm ưa chuộng.\n- Đứng đầu trong ngành sản xuất về chất lượng cao.");

        productDAO.AddProductImgString("[SOO04] Quần Dài Jogger Đặc Biệt E03",450000,"quandai5",2,"- Thiết kế thời trang, phù hợp cho giới trẻ.\n- Sản phẩm với chất lượng cao.\n- Khả năng độ bền và màu sắc rất tốt.\n- Nhiều năm được chọn là sản phẩm ưa chuộng.\n- Đứng đầu trong ngành sản xuất về chất lượng cao.");

    }

    public void AddBill(Bill bill){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, bill.getName());
        values.put(DATE, bill.getDate());
        values.put(YOUR_ADDRESS, bill.getAddress());
        values.put(PHONE, bill.getPhone());
        values.put(EMAIL, bill.getEmail());
        values.put(ID_USER, bill.getId_user());
        values.put(TOTAL, bill.getTotal());
        values.put(STATUS, bill.getStt());
        db.insert(BILL,null,values);
        db.close();
    }
    public void AddDetails(Details details){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(BILL_ID, details.getId_bill());
        values.put(PRODUCT_ID, details.getId_product());
        values.put(ID_COLOR,GetID_Color(details.getColor()));
        values.put(ID_SIZE,GetID_Size(details.getSize()));
        values.put(QUANTITY, details.getQuantity());
        db.insert(DETAILS,null,values);
        db.close();
    }
    public Cursor GetMaxIdBill(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(BILL,null,null,null,null,null,"id Desc","1");
        return  cursor;

    }
    public  void UpdateTotal(int  bill, int total){
        SQLiteDatabase db = this.getWritableDatabase();
        String Sql = " UPDATE bill SET total = "+total+ " WHERE id = " + bill + "";
        db.execSQL(Sql);
    }
    public Cursor GetDetailsBill(int id_bill){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery( "select details.id, details.id_bill, product.name, product.price, details.quantity, product.image,details.id_color,details.id_size from details, product where details.id_product == product.id and details.id_bill = ?",new String[]{String.valueOf(id_bill)});
        return  cursor;
    }
    //////////////////////////////////////////////////////////

    public boolean checkmail(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(" select * from users where email = ? ", new String[]{email});
        if( cursor.getCount() > 0)
            return false;
        else
            return true;

    }


    public Cursor Profile_User(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(" select * from users where email = ?", new String[]{email });
        return cursor;
    }

    public Cursor getAllProducts(){
        SQLiteDatabase db = this.getWritableDatabase();
        String qry = " select * from product";
        Cursor cursor = db.rawQuery(qry, null);
        return cursor;
    }

    public Cursor getSearchProducts(String search){
        SQLiteDatabase db = this.getWritableDatabase();
        String qry = " select * from product where name like ?";
        Cursor cursor = db.rawQuery(qry,new String[]{"%"+search+"%"});
        return cursor;
    }

    public Cursor GetSSD(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery( "select * from product where id_cate = 1",null);
        return  cursor;

    }
    public Cursor GetDetailsProduct(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery( "select * from product where id = ?",new String[]{String.valueOf(id)});
        return  cursor;

    }
    public Cursor GetHDD(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery( "select * from product where id_cate = 2",null);
        return  cursor;

    }

    public Cursor Colors(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from color",null);
        return cursor;
    }

    public Cursor Sizes(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from size",null);
        return cursor;
    }

    public void UpdateProfile(String name, String phone, String emailNew, String address,String emailOld){
        SQLiteDatabase db = this.getWritableDatabase();
        String updateProfile="update users set name=\""+name+"\" , email=\""+emailNew+"\" , phone=\""+phone+"\" , address=\""+address+"\" where email=\""+emailOld+"\"";
        db.execSQL(updateProfile);
    }

    public void UpdatePassword(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        String updatePassword = "update users set password=\""+password+"\" where email=\""+email+"\"";
        db.execSQL(updatePassword);
    }

    public int GetID_Color(String name){
        SQLiteDatabase db = this.getWritableDatabase();
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
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from size where name=?",new String[]{name});
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            id_size=cursor.getInt(0);
            cursor.moveToNext();
        }
        return id_size;
    }
}
