package hcmute.edu.vn.mssv18110326.Activity.Admin;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

//import hcmute.edu.vn.mssv18110326.Fragment.HoaDonFragment;
//import hcmute.edu.vn.mssv18110326.Fragment.TaiKhoanFragment;
//import hcmute.edu.vn.mssv18110326.Fragment.ThongKeFragment;
import hcmute.edu.vn.mssv18110326.Admin_Fragment.SanPhamFragment;
import hcmute.edu.vn.mssv18110326.R;

public class AdminHomeActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        bottomNavigationView = findViewById(R.id.nav_bottom_bar);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_big, new SanPhamFragment()).commit();

    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //click bottom menu
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.sanPhamFragment:
                            selectedFragment = new SanPhamFragment();
                            break;
                        case R.id.hoaDonFragment:
 //                           selectedFragment = new HoaDonFragment();
                            break;
                        case R.id.thongKeFragment:
 //                           selectedFragment = new ThongKeFragment();
                            break;
                        case R.id.taiKhoanFragment:
 //                           selectedFragment = new TaiKhoanFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.right_to_left, R.anim.exit_rtl, R.anim.left_to_right, R.anim.exit_ltr).replace(R.id.layout_big, selectedFragment).commit();
                    return true;
                }
            };
}
