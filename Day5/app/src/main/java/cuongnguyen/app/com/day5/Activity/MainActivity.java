package cuongnguyen.app.com.day5.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import cuongnguyen.app.com.day5.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView youtube,facebook,instagram,shop,food,music;
    Button navigationOne,navigationTwo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getControls();
    }
    public void getControls(){
        youtube= (ImageView) findViewById(R.id.youtube);
        facebook= (ImageView) findViewById(R.id.facebook);
        instagram= (ImageView) findViewById(R.id.instagram);
        shop= (ImageView) findViewById(R.id.clothes);
        food= (ImageView) findViewById(R.id.food);
        music= (ImageView) findViewById(R.id.music);
        youtube.setOnClickListener(this);
        facebook.setOnClickListener(this);
        instagram.setOnClickListener(this);
        shop.setOnClickListener(this);
        food.setOnClickListener(this);
        music.setOnClickListener(this);
        navigationOne= (Button) findViewById(R.id.navigationOne);
        navigationTwo= (Button) findViewById(R.id.navigationTwo);
        navigationOne.setOnClickListener(this);
        navigationTwo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.youtube:
                Intent intentYoutube=new Intent(MainActivity.this,YoutubeActivity.class);
                startActivity(intentYoutube);
                break;
            case R.id.facebook:
                Intent intentFacebook=new Intent(MainActivity.this,FacebookActivity.class);
                startActivity(intentFacebook);
                break;
            case R.id.instagram:
                Intent intentInstagram=new Intent(MainActivity.this,InstagramActivity.class);
                startActivity(intentInstagram);
                break;
            case R.id.clothes:
                Intent intentShop=new Intent(MainActivity.this,ShopActivity.class);
                startActivity(intentShop);
                break;
            case R.id.food:
                Intent intentFood=new Intent(MainActivity.this,FoodActivity.class);
                startActivity(intentFood);
                break;
            case R.id.music:
                Intent intentMusic=new Intent(MainActivity.this,MusicActivity.class);
                startActivity(intentMusic);
                break;
            case R.id.navigationOne:
                Intent intentNO=new Intent(MainActivity.this,NavigationOne.class);
                startActivity(intentNO);
                break;
            case R.id.navigationTwo:
                Intent intentNT=new Intent(MainActivity.this,NavigationTwo.class);
                startActivity(intentNT);
                break;
        }
    }
}
