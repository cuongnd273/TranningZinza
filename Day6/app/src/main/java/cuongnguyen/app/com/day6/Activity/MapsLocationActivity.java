package cuongnguyen.app.com.day6.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import cuongnguyen.app.com.day6.AsyncTask.AsynFindDirection;
import cuongnguyen.app.com.day6.AsyncTask.DownloadFinish;
import cuongnguyen.app.com.day6.R;

import static android.R.attr.key;

public class MapsLocationActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMap;
    int PLACE_AUTOCOMPLETE_REQUEST_CODE_FROM = 1, PLACE_AUTOCOMPLETE_REQUEST_CODE_TO = 2;
    private String TAG = "TrangThai";
    EditText from, to;
    Place placeFrom = null, placeTo = null;
    ImageView find;
    TextView txtDistance,txtDuration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_location);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        getControls();
//        AsynFindDirection asynFindDirection= (AsynFindDirection) new AsynFindDirection(this, new DownloadFinish() {
//            @Override
//            public void finish(PolylineOptions polylineOptions,String distance,String duration) {
//
//            }
//        }).execute("https://maps.googleapis.com/maps/api/directions/json?origin=place_id:ChIJlUG7iC2rNTERdALizHTkBjA&destination=place_id:ChIJF13BXqGrNTERTE3hz8KFDmI&key=AIzaSyCLlXxdRqLK7Qgfr4Ne4MM881t2RTExF6g");
    }

    public void getControls() {
        from = (EditText) findViewById(R.id.from);
        to = (EditText) findViewById(R.id.to);
        find= (ImageView) findViewById(R.id.find);
        txtDistance= (TextView) findViewById(R.id.distance);
        txtDuration= (TextView) findViewById(R.id.duration);
        from.setOnClickListener(this);
        to.setOnClickListener(this);
        find.setOnClickListener(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);
    }
    public void findLocation(int type){
        try {
            Intent intent =
                    new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                            .build(this);
            startActivityForResult(intent, type);
        } catch (GooglePlayServicesRepairableException e) {
            // TODO: Handle the error.
        } catch (GooglePlayServicesNotAvailableException e) {
            // TODO: Handle the error.
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE_FROM) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                placeFrom=place;
                from.setText(place.getName());
                mMap.clear();
                if(placeTo!=null)mMap.addMarker(new MarkerOptions().position(placeTo.getLatLng()).title(placeTo.getName().toString()).icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_home)));
                mMap.addMarker(new MarkerOptions().position(place.getLatLng()).title(place.getName().toString()).icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_home)));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(placeFrom.getLatLng(),14));
                Log.i(TAG, place.getId());
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                Log.i(TAG, status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
            }
        }else if(requestCode==PLACE_AUTOCOMPLETE_REQUEST_CODE_TO){
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                placeTo=place;
                to.setText(place.getName());
                if(placeFrom!=null)mMap.addMarker(new MarkerOptions().position(placeFrom.getLatLng()).title(placeFrom.getName().toString()).icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_home)));
                mMap.addMarker(new MarkerOptions().position(place.getLatLng()).title(place.getName().toString()).icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_home)));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(placeTo.getLatLng(),14));
                Log.i(TAG, place.getId());
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                Log.i(TAG, status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
            }
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.from:
                findLocation(PLACE_AUTOCOMPLETE_REQUEST_CODE_FROM);
                break;
            case R.id.to:
                findLocation(PLACE_AUTOCOMPLETE_REQUEST_CODE_TO);
                break;
            case R.id.find:
                if(placeFrom==null || placeTo==null)
                    Toast.makeText(this,"Hãy chọn điểm đi và điểm đến",Toast.LENGTH_SHORT);
                else
                    findDirection(placeFrom.getId(),placeTo.getId());
                break;
        }
    }
    public void findDirection(String placeIdFrom,String placeIdTo){
        AsynFindDirection findDirection= (AsynFindDirection) new AsynFindDirection(this, new DownloadFinish() {
            @Override
            public void finish(PolylineOptions polylineOptions,String distance,String duration) {
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(placeFrom.getLatLng()).title(String.valueOf(placeFrom.getName())).icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_home)));
                mMap.addMarker(new MarkerOptions().position(placeTo.getLatLng()).title(String.valueOf(placeTo.getName())).icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_home)));
                mMap.addPolyline(polylineOptions);
                txtDistance.setText(distance);
                txtDuration.setText(duration);
            }
        }).execute(getURL(placeIdFrom,placeIdFrom));
    }
    public String getURL(String placeIdFrom,String placeIdTo){
        String url="https://maps.googleapis.com/maps/api/directions/json?origin=place_id:"+placeFrom.getId()+"&destination=place_id:"+placeTo.getId()+"&key=AIzaSyCLlXxdRqLK7Qgfr4Ne4MM881t2RTExF6g";
        Log.i(TAG, "getURL: "+url);
        return url;
    }
}
