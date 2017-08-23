package cuongnguyen.app.com.day4.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

import cuongnguyen.app.com.day4.Adapter.AdapterGrid;
import cuongnguyen.app.com.day4.R;

public class Demo1Activity extends AppCompatActivity {
    GridView gridView;
    AdapterGrid adapterGrid;
    ArrayList<Integer> data;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo1);
        getControls();
    }
    public void getControls(){
        gridView= (GridView) findViewById(R.id.listImage);
        data=new ArrayList<>();
        data.add(R.drawable.image_1);
        data.add(R.drawable.image_2);
        data.add(R.drawable.image_3);
        data.add(R.drawable.image_4);
        data.add(R.drawable.image_5);
        data.add(R.drawable.image_6);
        data.add(R.drawable.image_7);
        data.add(R.drawable.image_8);
        adapterGrid=new AdapterGrid(this,data);
        gridView.setAdapter(adapterGrid);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(Demo1Activity.this,ShowActivity.class);
                intent.putExtra("id",data.get(position));
                startActivity(intent);
            }
        });
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_demo_2,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)finish();
        return super.onOptionsItemSelected(item);
    }
}
