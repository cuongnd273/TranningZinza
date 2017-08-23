package cuongnguyen.app.com.day8.Activity;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

import cuongnguyen.app.com.day8.Adapter.AdapterSong;
import cuongnguyen.app.com.day8.AsynTask.SongHot;
import cuongnguyen.app.com.day8.Model.Song;
import cuongnguyen.app.com.day8.R;

public class SongActivity extends AppCompatActivity {

    ArrayList<Song> list;
    ListView listSong;
    AdapterSong adapter;
    ProgressDialog dialog;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
        listSong= (ListView) findViewById(R.id.listSong);
        list=new ArrayList<>();
        dialog=new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();
        SongHot songHot= (SongHot) new SongHot(new SongHot.GetSong() {
            @Override
            public void finish(ArrayList<Song> listS) {
                list=listS;
                adapter=new AdapterSong(SongActivity.this,list);
                listSong.setAdapter(adapter);
                dialog.dismiss();
            }
        }).execute("http://tranningzinza.esy.es/songhot.php");
        listSong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    if(mediaPlayer!=null){
                        mediaPlayer.stop();
                        mediaPlayer=null;
                    }
                    mediaPlayer=new MediaPlayer();
                    mediaPlayer.setDataSource(list.get(position).getUrl());
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
