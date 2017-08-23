package cuongnguyen.app.com.day4.Adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import cuongnguyen.app.com.day4.Model.Product;
import cuongnguyen.app.com.day4.R;

/**
 * Created by CuongNguyen on 07/10/17.
 */

public class AdapterProduct extends BaseAdapter {
    private Context context;
    private ArrayList<Product> list;

    public AdapterProduct(Context context, ArrayList<Product> list) {
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
            convertView=inflater.inflate(R.layout.item_product,parent,false);
            ViewHolder viewHolder=new ViewHolder();
            viewHolder.image= (ImageView) convertView.findViewById(R.id.image_product);
            viewHolder.name= (TextView) convertView.findViewById(R.id.name_product);
            viewHolder.price_new= (TextView) convertView.findViewById(R.id.price_new);
            viewHolder.price_old= (TextView) convertView.findViewById(R.id.price_old);
            viewHolder.sale= (TextView) convertView.findViewById(R.id.sale);
            convertView.setTag(viewHolder);
        }
        ViewHolder viewHolder= (ViewHolder) convertView.getTag();
        viewHolder.image.setImageResource(list.get(position).getImage());
        viewHolder.name.setText(list.get(position).getName());
        viewHolder.price_new.setText("Rs: "+String.valueOf(list.get(position).getPrice()-list.get(position).getPrice()*list.get(position).getSale()/100));
        viewHolder.price_old.setText("Rs: "+String.valueOf(list.get(position).getPrice()));
        viewHolder.price_old.setPaintFlags(viewHolder.price_old.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
        viewHolder.sale.setText(String.valueOf(list.get(position).getSale()+"%"));
        return convertView;
    }
    class ViewHolder{
        ImageView image;
        TextView name,price_old,price_new,sale;
    }
}
