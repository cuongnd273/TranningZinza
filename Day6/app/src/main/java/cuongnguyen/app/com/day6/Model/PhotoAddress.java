package cuongnguyen.app.com.day6.Model;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import cuongnguyen.app.com.day6.R;

/**
 * Created by CuongNguyen on 07/12/17.
 */

public class PhotoAddress {
    private int image;
    private LatLng latLng;
    private ArrayList<Integer> listImage;

    public PhotoAddress(int image, LatLng latLng) {
        this.image = image;
        this.latLng = latLng;
        this.listImage = new ArrayList<>();
        listImage.add(R.drawable.miss1);
        listImage.add(R.drawable.miss2);
        listImage.add(R.drawable.miss3);
        listImage.add(R.drawable.miss4);
        listImage.add(R.drawable.miss5);
        listImage.add(R.drawable.miss6);
        listImage.add(R.drawable.miss7);
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public ArrayList<Integer> getListImage() {
        return listImage;
    }

    public void setListImage(ArrayList<Integer> listImage) {
        this.listImage = listImage;
    }
}
