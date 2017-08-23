package cuongnguyen.app.com.day6.AsyncTask;

import android.os.AsyncTask;

import com.google.android.gms.maps.model.LatLng;

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

import cuongnguyen.app.com.day6.Model.MyLocation;
import cuongnguyen.app.com.day6.Model.Restaurant;

/**
 * Created by CuongNguyen on 07/13/17.
 */

public class AsynGetRestaurant extends AsyncTask<String,Void,String> {
    private GetRestaurantFinish downloadFinish;

    public AsynGetRestaurant(GetRestaurantFinish downloadFinish) {
        this.downloadFinish = downloadFinish;
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
        ArrayList<Restaurant> list=new ArrayList<>();
        try {
            JSONObject object=new JSONObject(s);
            JSONArray results=object.getJSONArray("results");
            for(int i=0;i<results.length();i++){
                String name=results.getJSONObject(i).getString("name");
                String address=results.getJSONObject(i).getString("vicinity");
                JSONObject geometry=results.getJSONObject(i).getJSONObject("geometry");
                JSONObject location=geometry.getJSONObject("location");
                LatLng latLng=new LatLng(location.getDouble("lat"),location.getDouble("lng"));
                list.add(new Restaurant(name,address,latLng));
            }
            downloadFinish.finish(list);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
