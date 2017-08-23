package cuongnguyen.app.com.day6.Activity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

import cuongnguyen.app.com.day6.AsyncTask.AsynGetRestaurant;
import cuongnguyen.app.com.day6.AsyncTask.GetRestaurantFinish;
import cuongnguyen.app.com.day6.Model.GPSTracker;
import cuongnguyen.app.com.day6.Model.Restaurant;
import cuongnguyen.app.com.day6.R;

public class MapsRestaurantActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    double latitude,longitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_restaurant);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        GPSTracker gps = new GPSTracker(this);
        latitude = gps.getLatitude();
        longitude = gps.getLongitude();
        AsynGetRestaurant asynGetRestaurant= (AsynGetRestaurant) new AsynGetRestaurant(new GetRestaurantFinish() {
            @Override
            public void finish(ArrayList<Restaurant> list) {
                for(Restaurant item : list){
                    mMap.addMarker(new MarkerOptions().position(item.getLatLng()).title(item.getName()).icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_restaurant)));
                }
            }
        }).execute("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+latitude+","+longitude+"&radius=5000&type=restaurant&key=AIzaSyCLlXxdRqLK7Qgfr4Ne4MM881t2RTExF6g");
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng you = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(you).title("You are here"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(you,15));
    }
}
