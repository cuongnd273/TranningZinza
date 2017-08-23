package cuongnguyen.app.com.day4.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import cuongnguyen.app.com.day4.Adapter.AdapterGroupProduct;
import cuongnguyen.app.com.day4.R;

public class Demo3Activity extends AppCompatActivity {
    ListView listGroup;
    ArrayList<String> list;
    AdapterGroupProduct adapterGroupProduct;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo3);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listGroup= (ListView) findViewById(R.id.list_group_product);
        list=new ArrayList<>();
        list.add("Jeans");
        list.add("Dress");
        list.add("T-shirts");
        list.add("Shoes");
        list.add("Hats");
        list.add("Jeans");
        list.add("Dress");
        list.add("T-shirts");
        list.add("Shoes");
        list.add("Hats");
        adapterGroupProduct=new AdapterGroupProduct(this,list);
        listGroup.setAdapter(adapterGroupProduct);
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
