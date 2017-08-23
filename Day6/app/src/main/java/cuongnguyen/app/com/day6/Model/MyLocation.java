package cuongnguyen.app.com.day6.Model;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by CuongNguyen on 07/12/17.
 */

public class MyLocation {
    private String name;
    private LatLng latLng;
    private String info;
    private int image;

    public MyLocation(String name, LatLng latLng, String info, int image) {
        this.name = name;
        this.latLng = latLng;
        this.info = info;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
