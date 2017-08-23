package cuongnguyen.app.com.day6.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

import cuongnguyen.app.com.day6.R;

/**
 * Created by CuongNguyen on 07/13/17.
 */

public class AdapterImage extends BaseAdapter {
    private Context context;
    private ArrayList<Integer> listImage;

    public AdapterImage(Context context, ArrayList<Integer> listImage) {
        this.context = context;
        this.listImage = listImage;
    }

    @Override
    public int getCount() {
        return listImage.size();
    }

    @Override
    public Object getItem(int position) {
        return listImage.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(R.layout.item_gird_view,parent,false);
        ImageView image= (ImageView) convertView.findViewById(R.id.image);
        image.setImageResource(listImage.get(position));
        return convertView;
    }
}
