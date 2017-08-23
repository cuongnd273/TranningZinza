package cuongnguyen.app.com.day4.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

import cuongnguyen.app.com.day4.R;

/**
 * Created by CuongNguyen on 07/10/17.
 */

public class AdapterGrid extends BaseAdapter {
    private Context context;
    private ArrayList<Integer> list;

    public AdapterGrid(Context context, ArrayList<Integer> list) {
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
        if(convertView==null){
            convertView=inflater.inflate(R.layout.item_gird,parent,false);
            ViewHolder viewHolder=new ViewHolder();
            viewHolder.image= (ImageView) convertView.findViewById(R.id.image);
            convertView.setTag(viewHolder);
        }
        ViewHolder viewHolder= (ViewHolder) convertView.getTag();
        viewHolder.image.setImageResource(list.get(position));
        return convertView;
    }
    class ViewHolder{
        ImageView image;
    }
}
