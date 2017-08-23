package cuongnguyen.app.com.day5.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import cuongnguyen.app.com.day5.Model.MenuDrawer;
import cuongnguyen.app.com.day5.R;

/**
 * Created by CuongNguyen on 07/11/17.
 */

public class AdapterNavigationGroup extends BaseAdapter{
    private Context context;
    private ArrayList<MenuDrawer> list;

    public AdapterNavigationGroup(Context context, ArrayList<MenuDrawer> list) {
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

        if(list.get(position).isGroup()){
            convertView=inflater.inflate(R.layout.item_group_navigation,parent,false);
            TextView name= (TextView) convertView.findViewById(R.id.name);
            name.setText(list.get(position).getName());
        }else{
            convertView=inflater.inflate(R.layout.item_navigation,parent,false);
            ImageView image= (ImageView) convertView.findViewById(R.id.image);
            TextView name= (TextView) convertView.findViewById(R.id.name);
            image.setImageResource(list.get(position).getImage());
            image.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
            name.setText(list.get(position).getName());
        }
        return convertView;
    }
}
