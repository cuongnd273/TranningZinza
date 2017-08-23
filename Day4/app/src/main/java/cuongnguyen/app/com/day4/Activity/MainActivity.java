package cuongnguyen.app.com.day4.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cuongnguyen.app.com.day4.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button demo_1,demo_2,demo_3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getControls();
    }
    public void getControls(){
        demo_1= (Button) findViewById(R.id.demo_1);
        demo_2= (Button) findViewById(R.id.demo_2);
        demo_3= (Button) findViewById(R.id.demo_3);
        demo_1.setOnClickListener(this);
        demo_2.setOnClickListener(this);
        demo_3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.demo_1:
                Intent intentDemo1=new Intent(MainActivity.this,Demo1Activity.class);
                startActivity(intentDemo1);
                break;
            case R.id.demo_2:
                Intent intentDemo2=new Intent(MainActivity.this,Demo2Activity.class);
                startActivity(intentDemo2);
                break;
            case R.id.demo_3:
                Intent intentDemo3=new Intent(MainActivity.this,Demo3Activity.class);
                startActivity(intentDemo3);
                break;
        }
    }
}
