package cuongnguyen.app.com.day5.Activity;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import cuongnguyen.app.com.day5.Adapter.AdapterViewpager;
import cuongnguyen.app.com.day5.Fragment.FragmentDemo;
import cuongnguyen.app.com.day5.R;

public class YoutubeActivity extends AppCompatActivity {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    AdapterViewpager adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);
        getControls();
    }
    public void getControls(){
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        tabLayout= (TabLayout) findViewById(R.id.tablayout);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Account");
        toolbar.setTitleTextColor(Color.WHITE);
        viewPager= (ViewPager) findViewById(R.id.viewpager);
        adapter=new AdapterViewpager(getSupportFragmentManager());
        adapter.addFragment(new FragmentDemo());
        adapter.addFragment(new FragmentDemo());
        adapter.addFragment(new FragmentDemo());
        adapter.addFragment(new FragmentDemo());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home_black_24dp);
        tabLayout.getTabAt(0).getIcon().setColorFilter(Color.WHITE,PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(1).setIcon(R.drawable.icon_fire_24dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.icon_album_24dp);
        tabLayout.getTabAt(3).setIcon(R.drawable.icon_user_24dp);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_youtube,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this,"Clicked "+item.getTitle(),Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }
}
