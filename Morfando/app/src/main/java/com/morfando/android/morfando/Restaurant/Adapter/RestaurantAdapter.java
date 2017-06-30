package com.morfando.android.morfando.Restaurant.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.morfando.android.morfando.Class.Branch;
import com.morfando.android.morfando.Class.Cuisine;
import com.morfando.android.morfando.Class.Restaurant;
import com.morfando.android.morfando.R;

import java.util.ArrayList;

/**
 * Created by Matias on 6/18/2017.
 */

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.NumberViewHolder> {

    private static final String TAG = RestaurantAdapter.class.getSimpleName();

    private int mNumberItems;

    private ArrayList<Branch> mBranchData;

    final private ListItemClickListener mOnClickListener;

    private static int viewHolderCount;

    public RestaurantAdapter(int numberOfItems, ListItemClickListener listener) {
        mNumberItems = numberOfItems;
        mOnClickListener = listener;
        viewHolderCount = 0;
    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    @Override
    public NumberViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.restaurant_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        NumberViewHolder viewHolder = new NumberViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NumberViewHolder holder, int position) {
        Log.d(TAG, "#" + position);
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mNumberItems;
    }

    public void setmRestaurantData (ArrayList<Branch> data) {
        mBranchData = data;
        notifyDataSetChanged();
    }


    class NumberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Will display the position in the list, ie 0 through getItemCount() - 1
        TextView resName, priceRange, cuisine, distance;
        RatingBar calification;
        public NumberViewHolder(View itemView) {
            super(itemView);

            resName = (TextView) itemView.findViewById(R.id.item_res_name);
            priceRange = (TextView) itemView.findViewById(R.id.item_price_range);
            calification = (RatingBar) itemView.findViewById(R.id.item_res_calification);
            cuisine = (TextView)itemView.findViewById(R.id.item_res_cuisine);
            distance = (TextView)itemView.findViewById(R.id.textview_distance);

        }

        /**
         * A method we wrote for convenience. This method will take an integer as input and
         * use that integer to display the appropriate text within a list item.
         * @param listIndex Position of the item in the list
         */
        void bind(int listIndex) {
            Branch b = mBranchData.get(listIndex);
            this.resName.setText(b.name);
            this.priceRange.setText("$" + b.range.minimum + "- $" + b.range.maximum);
            this.calification.setRating(b.averageCalification);
            String cousines = "";
            for (Cuisine c : b.cuisine) {
                if (cousines.length() != 0){
                    cousines += "|" + c.name;
                }else {
                    cousines = c.name;
                }
            }
            this.cuisine.setText(cousines);
        }

        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }

}
