package hcmute.edu.vn.mssv18110326.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;


import hcmute.edu.vn.mssv18110326.DAO.ProductDAO;
import hcmute.edu.vn.mssv18110326.Data.DatabaseManager;
import hcmute.edu.vn.mssv18110326.Model.Cart;
import hcmute.edu.vn.mssv18110326.R;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {

    Integer id, quantity,id_pro, newprice,pricee, itemcart;
    TextView txtid, price,description;
    String name,img, test;
    ImageView image;
    Spinner color;
    Spinner size;
    Button btnAddCart, btnMyCart;
    Context context;

    private ArrayList<Cart> ListCarts;
    DatabaseManager db = new DatabaseManager(this);
    ProductDAO productDAO = new ProductDAO(db);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        btnAddCart = (Button)findViewById(R.id.AddCart) ;
        btnAddCart.setOnClickListener(AddToCart);
        GetDetails();
    }
    private void GetDetails(){
        try {
            Intent intent = getIntent();
            txtid = (TextView)findViewById(R.id.TxtName);
            price = (TextView)findViewById(R.id.Price);
            image = (ImageView)findViewById(R.id.ImgDetails);
            color = (Spinner)findViewById(R.id.spnColor);
            size = (Spinner)findViewById(R.id.spnSize);
            description = (TextView)findViewById(R.id.description);

            Bundle bundle = intent.getExtras();
            name = bundle.getString("Name");
            id = bundle.getInt("ID",0);
            //txtid.setText(String.valueOf(id));
            Cursor cursor = db.GetDetailsProduct(id);

            if(cursor.getCount()!=0){
                if(cursor.moveToFirst()) {
                    String txt = cursor.getString(2) + "VND";
                    id_pro = cursor.getInt(0);
                    txtid.setText(name);
                    price.setText(txt);
                    pricee = Integer.parseInt(cursor.getString(2));
                    description.setText(cursor.getString(6));
                    Resources res = getResources();
                    img = cursor.getString(3);

                    int resourceId = getResources().getIdentifier(img, "drawable", getPackageName() );
                    image.setImageResource( resourceId );
                }
            }

            // Đổ vào Spinner Color
            List<String> arrayColor = new ArrayList<String>();
            Cursor cursorColor = db.Colors();
            cursorColor.moveToFirst();
            while(!cursorColor.isAfterLast()) {
                arrayColor.add(cursorColor.getString(1));
                cursorColor.moveToNext();
            }
            ArrayAdapter<String> arrayAdapterColor = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, arrayColor);
            arrayAdapterColor.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            cursorColor.close();
            color.setAdapter(arrayAdapterColor);

            // Đổ vào Spinner Size
            ArrayList<String> arraySize = new ArrayList<String>();
            Cursor cursorSize = db.Sizes();
            cursorSize.moveToFirst();
            while(!cursorSize.isAfterLast()) {
                arraySize.add(cursorSize.getString(1));
                cursorSize.moveToNext();
            }
            ArrayAdapter<String> arrayAdapterSize = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, arraySize);
            arrayAdapterSize.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            cursorSize.close();
            size.setAdapter(arrayAdapterSize);

        }catch (Exception e){
            Toast.makeText(this,"ERROR", Toast.LENGTH_SHORT).show();
        }

    }
    private View.OnClickListener AddToCart = new View.OnClickListener() {
        public void onClick(View v) {
            if(MainActivity.cart_main.size()>0){
                boolean exists = false;
                for(int i =0; i< MainActivity.cart_main.size(); i++){
                    if(MainActivity.cart_main.get(i).getId() == id){
                        MainActivity.cart_main.get(i).setQty(MainActivity.cart_main.get(i).getQty() + 1);
                        MainActivity.cart_main.get(i).setPrice(pricee * MainActivity.cart_main.get(i).getQty());
                        exists = true;
                    }
                }
                if (exists  == false){
                    MainActivity.cart_main.add(new Cart(id,name,pricee,img,color.getSelectedItem().toString(),size.getSelectedItem().toString(),1));
                }
            }else {
                MainActivity.cart_main.add(new Cart(id,name,pricee,img,color.getSelectedItem().toString(),size.getSelectedItem().toString(),1));
            }
            Toast.makeText(getApplicationContext(), name + " Added", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(DetailsActivity.this, MainActivity.class);
            startActivity(intent);

        }
    };
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
    public void Restart() {
        Intent intent = new Intent(DetailsActivity.this, CartFragment.class);
        finish();
        startActivity(intent);
    }
}