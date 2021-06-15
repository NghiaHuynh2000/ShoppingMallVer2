package hcmute.edu.vn.mssv18110326.Adapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


import hcmute.edu.vn.mssv18110326.Activity.QuanFragment;
import hcmute.edu.vn.mssv18110326.Activity.AoFragment;


public class ShopViewAdapter extends FragmentStatePagerAdapter {
    public ShopViewAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position)
    {
        switch (position){
            case 0:
                return new AoFragment();
            case 1:
                return new QuanFragment();

            default:
                return new AoFragment();
        }
        //return  null;
    }

    @Override
    public int getCount()
    {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Áo Chất";
            case 1:
                return "Quần Xịn";
            default:
                return "Áo Chất";
        }

    }
}