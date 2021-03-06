package com.morfando.android.morfando.Restaurant.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.morfando.android.morfando.Class.Branch;
import com.morfando.android.morfando.Class.Cuisine;
import com.morfando.android.morfando.Class.PhotoBranch;
import com.morfando.android.morfando.R;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Matias on 6/29/2017.
 */

public class lvRestaurantAdapter extends BaseAdapter {

    private ArrayList<Branch> branches;
    private Context myContext;

    public lvRestaurantAdapter (ArrayList<Branch> listBranch, Context usedContext) {
        branches = listBranch;
        myContext = usedContext;
    }

    public void setData(ArrayList<Branch> branches) {
        this.branches=branches;
    }

    public int getCount() {
        if (branches == null){
            return 0;
        }
        return branches.size();
    }

    public Branch getItem(int position) {
        Branch b = branches.get(position);
        return b;
    }

    public long getItemId(int position) {
        Branch b = branches.get(position);
        return b.idBranch;
    }

    public int getId(int position) {
        Branch b = branches.get(position);
        return b.idBranch;
    }

    public View getView(int positionActual, View viewActual, ViewGroup groupActual) {

        View returnView;


        LayoutInflater inflater;
        inflater=(LayoutInflater)myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        returnView = inflater.inflate(R.layout.restaurant_list_item,groupActual,false);

        TextView resName = (TextView) returnView.findViewById(R.id.item_res_name);
        TextView priceRange = (TextView) returnView.findViewById(R.id.item_price_range);
        RatingBar calification = (RatingBar) returnView.findViewById(R.id.item_res_calification);
        TextView cuisine = (TextView)returnView.findViewById(R.id.item_res_cuisine);
        ImageView image = (ImageView)returnView.findViewById(R.id.imgRestaurant);


        Branch b = getItem(positionActual);

        if (b.photo.size() >= 0){
            new DownloadImageTask(image).execute(b.photo.get(0).photo);
        }

        resName.setText(b.restaurant.name + " " + b.name);
        priceRange.setText("$" + String.valueOf(b.range.minimum) + " - $" + String.valueOf(b.range.maximum));

        calification.setRating(Float.parseFloat(b.averageCalification + ""));
        Cuisine c = new Cuisine();
        cuisine.setText(c.cousineList(b.cuisine));

        return returnView;
    }


    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
                return mIcon11;
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                return null;
            }

        }

        protected void onPostExecute(Bitmap result) {
            if (result != null){
                bmImage.setImageBitmap(result);
            }
        }
    }

}
