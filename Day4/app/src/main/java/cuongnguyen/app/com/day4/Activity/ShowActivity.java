package cuongnguyen.app.com.day4.Activity;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import cuongnguyen.app.com.day4.R;

public class ShowActivity extends AppCompatActivity {
    ImageView image;
    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        image= (ImageView) findViewById(R.id.image);
        back= (Button) findViewById(R.id.back);
        Intent intent=getIntent();
        image.setImageResource(intent.getIntExtra("id",0));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
