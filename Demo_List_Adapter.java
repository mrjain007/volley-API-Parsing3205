package com.example.hplaptop.json_example3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
public class gangwal_List_Adapter extends BaseAdapter {
    TextView category_type,product_id,product_name,description,quantity,price;
    ImageView image;
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;

    public gangwal_List_Adapter(Context context,
                                ArrayList<HashMap<String, String>> arraylist) {
        this.context = context;
        data = arraylist;
    }
    @Override
    public int getCount() {
        return data.size() ;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
  

    public View getView(final int position, View convertView, ViewGroup parent) {

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.gangwal_list, parent, false);
        category_type = (TextView) itemView.findViewById(R.id.category_type);
        product_id = (TextView) itemView.findViewById(R.id.product_id);
        product_name = (TextView) itemView.findViewById(R.id.product_name);
        description = (TextView) itemView.findViewById(R.id.description);
        quantity = (TextView) itemView.findViewById(R.id.quantity);
        price = (TextView) itemView.findViewById(R.id.price);
        image = (ImageView) itemView.findViewById(R.id.image);

        category_type.setText(data.get(position).get("category_type"));
        product_id.setText(data.get(position).get("product_id"));
        product_name.setText(data.get(position).get("product_name"));
        description.setText(data.get(position).get("description"));
        quantity.setText(data.get(position).get("quantity"));
        price.setText(data.get(position).get("price"));

        Picasso.with(context).load(data.get(position).get("image")).into(image);


        return itemView;
    }
}
