package cuongnguyen.app.com.day8.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

import cuongnguyen.app.com.day8.Adapter.AdapterSong;
import cuongnguyen.app.com.day8.AsynTask.SongHot;
import cuongnguyen.app.com.day8.Model.Song;
import cuongnguyen.app.com.day8.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button song,youtube,github;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getControls();
    }
    public void getControls(){
        song= (Button) findViewById(R.id.song);
        youtube= (Button) findViewById(R.id.youtube);
        github= (Button) findViewById(R.id.github);
        song.setOnClickListener(this);
        youtube.setOnClickListener(this);
        github.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.song:
                intent=new Intent(MainActivity.this,SongActivity.class);
                break;
            case R.id.youtube:
                intent=new Intent(MainActivity.this,SongActivity.class);
                break;
            case R.id.github:
                intent=new Intent(MainActivity.this,GithubActivity.class);
                break;
        }
        startActivity(intent);
    }
}
