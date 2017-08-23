package cuongnguyen.app.com.day6.AsyncTask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

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
import java.util.HashMap;
import java.util.List;

import cuongnguyen.app.com.day6.Model.DirectionsJSONParser;

import static com.google.android.gms.internal.zzs.TAG;

/**
 * Created by CuongNguyen on 07/13/17.
 */

public class AsynFindDirection extends AsyncTask<String,Void,String> {
    private Activity activity;
    private DownloadFinish downloadFinish;
    private ProgressDialog dialog;
    public AsynFindDirection(Activity activity, DownloadFinish downloadFinish) {
        this.activity = activity;
        this.downloadFinish = downloadFinish;
    }

    @Override
    protected String doInBackground(String... params) {
        String result="";
        try {
            URL url=new URL(params[0]);
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            int responseCode=conn.getResponseCode();
            if(responseCode==HttpURLConnection.HTTP_OK){
                InputStream stream=conn.getInputStream();
                InputStreamReader inputStreamReader=new InputStreamReader(stream);
                BufferedReader reader=new BufferedReader(inputStreamReader);
                String data="";
                while((data=reader.readLine())!=null){
                    result+=data;
                }
                return result;
            }else{
                Log.i("TrangThai","Co loi");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog=new ProgressDialog(activity);
        dialog.setMessage("Loading...");
        dialog.show();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        dialog.dismiss();
        DirectionsJSONParser parser=new DirectionsJSONParser();
        JSONObject jObject = null;
        try {
            jObject = new JSONObject(s);
            List<List<HashMap<String,String>>> list=parser.parse(jObject);
            PolylineOptions polylineOptions=parserPoly(list);
            downloadFinish.finish(polylineOptions,parser.getDistance(),parser.getDuration());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public PolylineOptions parserPoly(List<List<HashMap<String, String>>> result){
        ArrayList<LatLng> points = null;
        PolylineOptions lineOptions = null;
        MarkerOptions markerOptions = new MarkerOptions();
        for(int i=0;i<result.size();i++){
            points = new ArrayList<LatLng>();
            lineOptions = new PolylineOptions();
            List<HashMap<String, String>> path = result.get(i);
            for(int j=0;j<path.size();j++){
                HashMap<String,String> point = path.get(j);

                double lat = Double.parseDouble(point.get("lat"));
                double lng = Double.parseDouble(point.get("lng"));
                LatLng position = new LatLng(lat, lng);

                points.add(position);
            }
            lineOptions.addAll(points);
            lineOptions.width(5);
            lineOptions.color(Color.RED);
        }
        return lineOptions;
    }
}
