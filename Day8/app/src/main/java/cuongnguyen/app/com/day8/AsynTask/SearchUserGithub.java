package cuongnguyen.app.com.day8.AsynTask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import cuongnguyen.app.com.day8.Model.UserGithub;

/**
 * Created by CuongNguyen on 07/14/17.
 */

public class SearchUserGithub extends AsyncTask<String,Void,String> {
    private Activity activity;
    private ProgressDialog dialog;
    private SearchFinish searchFinish;

    public SearchUserGithub(Activity activity, SearchFinish searchFinish) {
        this.activity = activity;
        this.searchFinish = searchFinish;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog=new ProgressDialog(activity);
        dialog.setMessage("Loading...");
        dialog.show();
    }

    @Override
    protected String doInBackground(String... params) {
        String result="";
        try {
            URL url=new URL("https://api.github.com/users/"+params[0]);
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            if(conn.getResponseCode()==HttpURLConnection.HTTP_OK){
                InputStreamReader inputStreamReader=new InputStreamReader(conn.getInputStream());
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
        dialog.dismiss();
        try {
            searchFinish.finish(parserUser(s));
        } catch (JSONException e) {
            searchFinish.finish(null);
            e.printStackTrace();
        }
    }
    public UserGithub parserUser(String s) throws JSONException {
        UserGithub userGithub = null;
        JSONObject object=new JSONObject(s);
        if(object.get("login")==null)
            return null;
        else{
            userGithub=new UserGithub();
            userGithub.setAvatar(object.getString("avatar_url"));
            userGithub.setCreated(object.getString("created_at"));
            userGithub.setUpdated(object.getString("updated_at"));
        }
        return userGithub;
    }
    public interface SearchFinish{
        public void finish(UserGithub user);
    }
}
