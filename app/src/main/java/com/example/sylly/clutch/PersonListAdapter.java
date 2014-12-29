package com.example.sylly.clutch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sylly.clutch.utils.UICircularImage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Sylly on 2014-12-28.
 */
class PersonListAdapter extends BaseAdapter {

    ViewHolder viewHolder;

    private ArrayList<Person> mItems = new ArrayList<Person>();
    private Context mContext;

    public PersonListAdapter(Context context, ArrayList<Person> list) {
        mContext = context;
        mItems = list;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if(convertView==null){

            // inflate the layout
            LayoutInflater vi = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.fragment_list_item_transition, null);

            // well set up the ViewHolder
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) v.findViewById(R.id.item_title);
            viewHolder.descr = (TextView) v.findViewById(R.id.item_description);
            viewHolder.image = (UICircularImage) v.findViewById(R.id.item_image);

            // store the holder with the view.
            v.setTag(viewHolder);

        }else{
            // just use the viewHolder
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final String item = mItems.get(position).getFirstName();
        final String desc = mItems.get(position).getLastName();
        //final int imageid = mItems.get(position).getImageId();

        //viewHolder.image.setImageResource(imageid);
        viewHolder.title.setText(item);
        viewHolder.descr.setText(desc);
        TextView hiddenView = (TextView) v.findViewById(R.id.hidden_view);
        hiddenView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, item + " hidden view clicked",
                        Toast.LENGTH_SHORT).show();

            }
        });
        viewHolder.image.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, item + " icon clicked",
                        Toast.LENGTH_SHORT).show();

            }
        });

        return v;
    }

    static class ViewHolder {
        TextView title;
        TextView descr;
        UICircularImage image;
        int position;
    }

}