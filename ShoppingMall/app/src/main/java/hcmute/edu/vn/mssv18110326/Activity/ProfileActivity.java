package hcmute.edu.vn.mssv18110326.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import hcmute.edu.vn.mssv18110326.DAO.UsersDAO;
import hcmute.edu.vn.mssv18110326.Data.DatabaseManager;
import hcmute.edu.vn.mssv18110326.R;

import static android.app.Activity.RESULT_OK;


public class ProfileActivity extends Fragment {
    Context thiscontext;
    DatabaseManager db;
    TextView email,name;
    UsersDAO usersDAO;
    Button btnLogin, btnLogout, btnChat,btnEditProfile, btnEditPassword;

    CircleImageView user_profile_photo;
    private static final int PICK_IMAGE=1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        thiscontext = container.getContext();
        db = new DatabaseManager(thiscontext);
        usersDAO = new UsersDAO(db);
        email = view.findViewById(R.id.user_email);
        name = view.findViewById(R.id.user_namme);
        user_profile_photo=(CircleImageView) view.findViewById(R.id.user_profile_photo);

 //       usersDAO = new UsersDAO(db);

        btnChat = view.findViewById(R.id.btnChat);
        btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2();
            }
        });


        btnLogin = view.findViewById(R.id.btnLogin);
        btnLogout = view.findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logout();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity1();
            }
        });
        String names = GetSessionUser();
        try {
//            usersDAO=new UsersDAO(db);
            if (!(usersDAO.GetImageAvt(names).equals(null))) {

                byte[] imageAvt = usersDAO.GetImageAvt(names);
                Bitmap bitmap = BitmapFactory.decodeByteArray(imageAvt, 0, imageAvt.length);
                user_profile_photo.setImageBitmap(bitmap);
            }
        }
        catch (Exception e){

        }


        if(names.equals("")){
            btnLogout.setVisibility(View.GONE);
        }else {
            btnLogin.setVisibility(View.GONE);
        }
        Cursor cursor = db.Profile_User(names);
        if(cursor.getCount()!=0){
            if(cursor.moveToFirst()){
                name.setText(cursor.getString(1));
                email.setText(cursor.getString(2));
            }
        }

        btnEditProfile = (Button) view.findViewById(R.id.btnEditProfile);
        btnEditPassword = (Button) view.findViewById(R.id.btnEditPassWord);

        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfile();
            }
        });
        btnEditPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUpdatePassword();
            }
        });

        user_profile_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(gallery,"Chọn hình ảnh"),PICK_IMAGE);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK){
            Uri imageUri;
            imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(thiscontext.getContentResolver(),imageUri);
                user_profile_photo.setImageBitmap(bitmap);

                CircleImageView updateAvt = user_profile_photo;
                byte[] image = usersDAO.ConverttoArrayByte(updateAvt);
                String email = GetSessionUser();
                usersDAO.UpdateAvt(email, image);
                Toast.makeText(thiscontext,"Cập nhật ảnh thành công!", Toast.LENGTH_LONG).show();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public String GetSessionUser(){

        SharedPreferences sharedPreferences = this.thiscontext.getSharedPreferences("user_check", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("user_email","");
        return name;
    }
    public void openActivity1(){
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
    }
    public void  Logout(){
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user_check", Context.MODE_PRIVATE);
        SharedPreferences.Editor  editor =sharedPreferences.edit();
        editor.remove("user_email");
        editor.apply();

    }
    public void openActivity2(){
        Intent intent = new Intent(getContext(), ChatMainActivity.class);
        startActivity(intent);
    }

    public void openProfile(){
        Intent intent = new Intent(getContext(), EditProfileActivity.class);
        startActivity(intent);
    }

    public void openUpdatePassword(){
        Intent intent = new Intent(getContext(), UpdatePasswordActivity.class);
        startActivity(intent);
    }


}

