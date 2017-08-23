package cuongnguyen.app.com.day6.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;

import cuongnguyen.app.com.day6.Adapter.AdapterImage;
import cuongnguyen.app.com.day6.Model.PhotoAddress;
import cuongnguyen.app.com.day6.R;

public class MapsPhotoActivity extends FragmentActivity implements OnMapReadyCallback{

    private GoogleMap mMap;
    ArrayList<PhotoAddress> list;
    HashMap<Marker,PhotoAddress> listLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    public void setData(){
        list=new ArrayList<>();
        list.add(new PhotoAddress(R.drawable.linh,new LatLng(21.043638, 105.779494)));
        list.add(new PhotoAddress(R.drawable.company,new LatLng(21.029646, 105.784923)));
        listLocation=new HashMap<>();
        for(PhotoAddress item : list){
            LatLng latLng=item.getLatLng();
            View view=((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_marker_photo, null);
            ImageView image= (ImageView) view.findViewById(R.id.image);
            TextView count= (TextView) view.findViewById(R.id.count);
            image.setImageResource(item.getImage());
            count.setText(String.valueOf(item.getListImage().size()));
            Marker marker=mMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(this,view))));
            listLocation.put(marker,item);
        }
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        setData();
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(21.043638, 105.779472), 14));
        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                PhotoAddress photoAddress=listLocation.get(marker);
                final AlertDialog.Builder dialog=new AlertDialog.Builder(MapsPhotoActivity.this);
                final View view=getLayoutInflater().inflate(R.layout.custom_dialog_marker,null);
                GridView gridView= (GridView) view.findViewById(R.id.grid_image);
                AdapterImage adapter=new AdapterImage(MapsPhotoActivity.this,photoAddress.getListImage());
                Button btShow= (Button) view.findViewById(R.id.show_all);
                gridView.setAdapter(adapter);
                dialog.setView(view);
                final AlertDialog showImage=dialog.create();
                showImage.show();
                btShow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showImage.dismiss();
                    }
                });
                return false;
            }
        });
    }
    public static Bitmap createDrawableFromView(Context context, View view) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        view.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);

        return bitmap;
    }
}
