package com.morfando.android.morfando.Restaurant.Single.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.morfando.android.morfando.Class.SocialNetwork;
import com.morfando.android.morfando.R;

import java.util.ArrayList;

/**
 * Created by Matias on 8/4/2017.
 */

public class socialNetworkAdapter extends BaseAdapter {

    private Context mContext;

    ArrayList<SocialNetwork> mySocialNetworks;

    public socialNetworkAdapter(Context c, ArrayList<SocialNetwork> sn) {
        mContext = c;
        mySocialNetworks = sn;
    }

    public int getCount() {
        return mySocialNetworks.size();
    }

    public SocialNetwork getItem(int position) {
        return mySocialNetworks.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    public String getValue(int positon){
        return mySocialNetworks.get(positon).value;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        //ImageView a retornar
        ImageView imageView;

        imageView = new ImageView(mContext);
        imageView.setLayoutParams(new GridView.LayoutParams(45,45));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        switch (getItem(position).name){
            case "Facebook":
                imageView.setImageResource(R.drawable.amex);
                break;
            case "Twitter":
                imageView.setImageResource(R.drawable.amex);
                break;
            case "Website":
                imageView.setImageResource(R.drawable.amex);
                break;
            case "Instagram":
                imageView.setImageResource(R.drawable.amex);
                break;
        }

        return imageView;
    }

}
