package cuongnguyen.app.com.day4.Activity;

import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import java.util.ArrayList;

import cuongnguyen.app.com.day4.Adapter.AdapterProduct;
import cuongnguyen.app.com.day4.Model.Product;
import cuongnguyen.app.com.day4.R;

public class Demo2Activity extends AppCompatActivity {
    GridView listProduct;
    AdapterProduct adapter;
    ArrayList<Product> list;
    AppBarLayout appBarLayout;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo2);
        getControls();
    }
    public void getControls(){
        listProduct= (GridView) findViewById(R.id.list_product);
        list=new ArrayList<>();
        list.add(new Product("T-shirt",R.drawable.product_1,1500,20));
        list.add(new Product("Dress",R.drawable.product_2,1600,13));
        list.add(new Product("Suit",R.drawable.product_3,1670,10));
        list.add(new Product("Jean",R.drawable.product_4,1800,16));
        list.add(new Product("Flower",R.drawable.product_5,2300,24));
        adapter=new AdapterProduct(this,list);
        listProduct.setAdapter(adapter);
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
