package cuongnguyen.app.com.day5.Activity;

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

import java.util.ArrayList;

import cuongnguyen.app.com.day5.Adapter.AdapterNavigationMusic;
import cuongnguyen.app.com.day5.Model.MenuDrawerMusic;
import cuongnguyen.app.com.day5.R;

public class MusicActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ListView listMenu;
    AdapterNavigationMusic adapter;
    ArrayList<MenuDrawerMusic> list;
    ActionBarDrawerToggle toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        getControls();
    }
    public void getControls(){
        drawerLayout= (DrawerLayout) findViewById(R.id.drawer_layout);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listMenu= (ListView) findViewById(R.id.listMenu);
        list=new ArrayList<>();
        list.add(new MenuDrawerMusic("Editor",R.drawable.ic_edit,0));
        list.add(new MenuDrawerMusic("Starters",R.drawable.ic_star,0));
        list.add(new MenuDrawerMusic("Albums",true));
        list.add(new MenuDrawerMusic("Camerarol",R.drawable.icon_camera_24dp,1568));
        list.add(new MenuDrawerMusic("Instagram",R.drawable.instagram,89));
        list.add(new MenuDrawerMusic("Snapspeed",R.drawable.ic_menu_profile,1));
        list.add(new MenuDrawerMusic("Photo Streams",true));
        list.add(new MenuDrawerMusic("Địch Lệ Nhiệt Ba",R.drawable.dichle,7));
        list.add(new MenuDrawerMusic("Địch Lệ Nhiệt Ba",R.drawable.dichle,3));
        list.add(new MenuDrawerMusic("More",true));
        list.add(new MenuDrawerMusic("Facebooks Photos",R.drawable.facebook,0));
        list.add(new MenuDrawerMusic("Camera",R.drawable.icon_camera_24dp,0));
        adapter=new AdapterNavigationMusic(this,list);
        listMenu.setAdapter(adapter);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_drawer,R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        listMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(!list.get(position).isGroup()){
                    drawerLayout.closeDrawer(Gravity.START);
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
