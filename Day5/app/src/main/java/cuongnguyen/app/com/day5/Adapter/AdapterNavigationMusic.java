package cuongnguyen.app.com.day5.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import cuongnguyen.app.com.day5.Model.MenuDrawer;
import cuongnguyen.app.com.day5.Model.MenuDrawerMusic;
import cuongnguyen.app.com.day5.R;

/**
 * Created by CuongNguyen on 07/12/17.
 */

public class AdapterNavigationMusic extends BaseAdapter {
    private Context context;
    private ArrayList<MenuDrawerMusic> list;

    public AdapterNavigationMusic(Context context, ArrayList<MenuDrawerMusic> list) {
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
            convertView=inflater.inflate(R.layout.item_group_navigation_music,parent,false);
            TextView name= (TextView) convertView.findViewById(R.id.name);
            name.setText(list.get(position).getName());
        }else{
            convertView=inflater.inflate(R.layout.item_naviagtion_music,parent,false);
            TextView name= (TextView) convertView.findViewById(R.id.name);
            ImageView image= (ImageView) convertView.findViewById(R.id.image);
            TextView count= (TextView) convertView.findViewById(R.id.count);
            name.setText(list.get(position).getName());
            image.setImageResource(list.get(position).getImage());
            if(list.get(position).getCount()!=0){
                count.setText(String.valueOf(list.get(position).getCount()));
            }else{
                count.setVisibility(View.INVISIBLE);
            }
        }
        return convertView;
    }
}
