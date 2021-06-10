package hcmute.edu.vn.mssv18110326.Adapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


import hcmute.edu.vn.mssv18110326.Activity.HDDFragment;
import hcmute.edu.vn.mssv18110326.Activity.SSDFragment;


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
                return new SSDFragment();
            case 1:
                return new HDDFragment();

            default:
                return new SSDFragment();
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