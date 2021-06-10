package hcmute.edu.vn.mssv18110326.Activity;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import hcmute.edu.vn.mssv18110326.Adapter.ShopViewAdapter;
import hcmute.edu.vn.mssv18110326.R;


public class ShopFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private  View mView;
    Context context;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        context = container.getContext();
        tabLayout = view.findViewById(R.id.tablayout);
        viewPager = view.findViewById(R.id.orderpage);
        ShopViewAdapter shopViewAdapter = new ShopViewAdapter(getChildFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(shopViewAdapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

}