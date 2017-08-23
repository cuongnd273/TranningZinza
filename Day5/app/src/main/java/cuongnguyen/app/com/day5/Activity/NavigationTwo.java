package cuongnguyen.app.com.day5.Activity;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import cuongnguyen.app.com.day5.Adapter.AdapterNavigationGroup;
import cuongnguyen.app.com.day5.Model.MenuDrawer;
import cuongnguyen.app.com.day5.R;

public class NavigationTwo extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    AdapterNavigationGroup adapter;
    ArrayList<MenuDrawer> list;
    ListView listMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_two);
        getControls();
    }
    public void getControls(){
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        listMenu= (ListView) findViewById(R.id.left_drawer);
        setSupportActionBar(toolbar);
        drawerLayout= (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_drawer,R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        list=new ArrayList<>();
        list.add(new MenuDrawer("Trần Kiều Ân",R.drawable.ic_menu_profile));
        list.add(new MenuDrawer("Friend",R.drawable.ic_menu_add_friend));
        list.add(new MenuDrawer("Notifications",R.drawable.ic_menu_notifi));
        list.add(new MenuDrawer("Messages",R.drawable.icon_message_24dp));
        list.add(new MenuDrawer("DISCOVER",true));
        list.add(new MenuDrawer("Setting",R.drawable.ic_menu_setting));
        list.add(new MenuDrawer("OTHER",true));
        list.add(new MenuDrawer("Sign out",R.drawable.ic_lock_outline_black_24dp));
        adapter=new AdapterNavigationGroup(this,list);
        listMenu.setAdapter(adapter);
        listMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(!list.get(position).isGroup()){
                    Toast.makeText(NavigationTwo.this,"Clicked "+list.get(position).getName(),Toast.LENGTH_SHORT).show();
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
