package cuongnguyen.app.com.day6.AsyncTask;

import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

import cuongnguyen.app.com.day6.Model.Restaurant;

/**
 * Created by CuongNguyen on 07/13/17.
 */

public interface DownloadFinish {
    void finish(PolylineOptions polylineOptions,String distance,String duration);
}
