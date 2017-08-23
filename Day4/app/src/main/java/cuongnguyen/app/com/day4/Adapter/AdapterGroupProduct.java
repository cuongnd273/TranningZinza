package cuongnguyen.app.com.day4.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import cuongnguyen.app.com.day4.R;

/**
 * Created by CuongNguyen on 07/10/17.
 */

public class AdapterGroupProduct extends BaseAdapter {
    private Context context;
    private ArrayList<String> list;

    public AdapterGroupProduct(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(R.layout.item_group_product,parent,false);
        TextView name= (TextView) convertView.findViewById(R.id.name);
        name.setText(list.get(position));
        return convertView;
    }
}
