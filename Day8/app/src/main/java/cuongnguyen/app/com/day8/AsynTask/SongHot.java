package cuongnguyen.app.com.day8.AsynTask;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import cuongnguyen.app.com.day8.Model.Song;

/**
 * Created by CuongNguyen on 07/14/17.
 */

public class SongHot extends AsyncTask<String,Void,String> {
    private GetSong getSong;

    public SongHot(GetSong getSong) {
        this.getSong = getSong;
    }

    @Override
    protected String doInBackground(String... params) {
        String result="";
        try {
            URL url=new URL(params[0]);
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            if(conn.getResponseCode()==HttpURLConnection.HTTP_OK){
                InputStream inputStream=conn.getInputStream();
                InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
                BufferedReader reader=new BufferedReader(inputStreamReader);
                String data="";
                while((data=reader.readLine())!=null){
                    result=result+data;
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            ArrayList<Song> list=parserSong(s);
            getSong.finish(list);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Song> parserSong(String s) throws JSONException {
        ArrayList<Song> list=new ArrayList<>();
        JSONObject object=new JSONObject(s);
        JSONArray songs=object.getJSONArray("songs");
        for(int i=0;i<songs.length();i++){
            String name=songs.getJSONObject(i).getString("name");
            String url=songs.getJSONObject(i).getString("url");
            Boolean favorite=new Random().nextBoolean();
            Song song=new Song(name,url,favorite);
            list.add(song);
        }
        return list;
    }
    public interface GetSong{
        public void finish(ArrayList<Song> list);
    }
}
