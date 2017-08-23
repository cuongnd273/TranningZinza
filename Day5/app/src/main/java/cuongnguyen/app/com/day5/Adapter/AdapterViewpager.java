package cuongnguyen.app.com.day5.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CuongNguyen on 07/10/17.
 */

public class AdapterViewpager extends FragmentPagerAdapter {
    List<Fragment> list=new ArrayList<>();
    public AdapterViewpager(FragmentManager fm) {
        super(fm);
    }
    public void addFragment(Fragment fragment){
        list.add(fragment);
    }
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
