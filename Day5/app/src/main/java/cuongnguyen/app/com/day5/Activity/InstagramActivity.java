package cuongnguyen.app.com.day5.Activity;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import cuongnguyen.app.com.day5.Adapter.AdapterViewpager;
import cuongnguyen.app.com.day5.Fragment.FragmentDemo;
import cuongnguyen.app.com.day5.R;

public class InstagramActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewPager viewPager;
    TabLayout tabLayout;
    AdapterViewpager adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instagram);
        getControls();
    }
    public void getControls(){
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        viewPager= (ViewPager) findViewById(R.id.viewpager);
        tabLayout= (TabLayout) findViewById(R.id.tablayout);
        adapter=new AdapterViewpager(getSupportFragmentManager());
        adapter.addFragment(new FragmentDemo());
        adapter.addFragment(new FragmentDemo());
        adapter.addFragment(new FragmentDemo());
        adapter.addFragment(new FragmentDemo());
        adapter.addFragment(new FragmentDemo());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home_black_24dp);
        tabLayout.getTabAt(0).getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(1).setIcon(R.drawable.icon_search_24dp);
        tabLayout.getTabAt(1).getIcon().setColorFilter(Color.parseColor("#979798"), PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(2).setIcon(R.drawable.icon_camera_24dp);
        tabLayout.getTabAt(2).getIcon().setColorFilter(Color.parseColor("#979798"), PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(3).setIcon(R.drawable.icon_message_24dp);
        tabLayout.getTabAt(3).getIcon().setColorFilter(Color.parseColor("#979798"), PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(4).setIcon(R.drawable.icon_login_24dp);
        tabLayout.getTabAt(4).getIcon().setColorFilter(Color.parseColor("#979798"), PorterDuff.Mode.SRC_IN);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(Color.parseColor("#979798"), PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_instagram,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this,"Clicked "+item.getTitle(),Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }
}
