package cuongnguyen.app.com.day8.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cuongnguyen.app.com.day8.Model.Song;
import cuongnguyen.app.com.day8.R;

/**
 * Created by CuongNguyen on 07/14/17.
 */

public class AdapterSong extends BaseAdapter {
    private Context context;
    private ArrayList<Song> list;
    private ClickFavorite clickFavorite;

    public AdapterSong(Context context, ArrayList<Song> list) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView==null){
            convertView=inflater.inflate(R.layout.item_music,parent,false);
            ViewHolder viewHolder=new ViewHolder();
            viewHolder.name= (TextView) convertView.findViewById(R.id.name);
            viewHolder.favorite= (ImageView) convertView.findViewById(R.id.favorite);
            convertView.setTag(viewHolder);
        }
        final ViewHolder viewHolder= (ViewHolder) convertView.getTag();
        viewHolder.name.setText(list.get(position).getName());
        if(list.get(position).isFavorite())
            viewHolder.favorite.setImageResource(R.drawable.favorite_select);
        else
            viewHolder.favorite.setImageResource(R.drawable.favorite_unselect);

        viewHolder.favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, list.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }
    public class ViewHolder{
        public TextView name;
        public ImageView favorite;
    }
    public interface ClickFavorite{
        public void click(ViewHolder view,int pos);
    }
}
