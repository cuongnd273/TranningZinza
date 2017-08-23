package cuongnguyen.app.com.day6.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cuongnguyen.app.com.day6.R;

public class MainActivity extends AppCompatActivity {
    Button btMap,btLocation,btRestaurant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btMap= (Button) findViewById(R.id.map1);
        btLocation= (Button) findViewById(R.id.map2);
        btRestaurant= (Button) findViewById(R.id.map3);
        btMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MapsPhotoActivity.class);
                startActivity(intent);
            }
        });
        btLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MapsLocationActivity.class);
                startActivity(intent);
            }
        });
        btRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MapsRestaurantActivity.class);
                startActivity(intent);
            }
        });
    }
}
