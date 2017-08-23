package cuongnguyen.app.com.day5.Activity;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import cuongnguyen.app.com.day5.Adapter.AdapterNavigation;
import cuongnguyen.app.com.day5.Model.MenuDrawer;
import cuongnguyen.app.com.day5.R;

public class NavigationOne extends AppCompatActivity{
    DrawerLayout drawerLayout;
    AdapterNavigation adapter;
    ArrayList<MenuDrawer> list;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    ListView listMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_one);
        getControls();
    }
    public void getControls(){
        drawerLayout= (DrawerLayout) findViewById(R.id.drawer_layout);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        listMenu= (ListView) findViewById(R.id.left_drawer);
        setSupportActionBar(toolbar);
        list=new ArrayList<>();
        list.add(new MenuDrawer("My Profile",R.drawable.ic_menu_profile));
        list.add(new MenuDrawer("Notification",R.drawable.ic_menu_notifi));
        list.add(new MenuDrawer("Find Friend",R.drawable.ic_menu_add_friend));
        list.add(new MenuDrawer("Setting",R.drawable.ic_menu_setting));
        adapter=new AdapterNavigation(this,list);
        listMenu.setAdapter(adapter);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_drawer,R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        listMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(!list.get(position).isGroup()){
                    Toast.makeText(NavigationOne.this,"Clicked "+list.get(position).getName(),Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggle.onOptionsItemSelected(item))return true;
        return super.onOptionsItemSelected(item);
    }
}
