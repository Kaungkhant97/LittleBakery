package com.kaungkhantthu.xyz.littlebakery.recyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.kaungkhantthu.xyz.littlebakery.OnListFragmentInteractionListener;
import com.kaungkhantthu.xyz.littlebakery.R;
import com.kaungkhantthu.xyz.littlebakery.entity.Cakeitem;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link } and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class CakeItemRecyclerViewAdapter extends RecyclerView.Adapter<CakeItemRecyclerViewAdapter.ViewHolder> {

    private final ArrayList<Cakeitem> cakeitemArrayList;
    private final OnListFragmentInteractionListener mListener;
    private final Context context;


    public CakeItemRecyclerViewAdapter(ArrayList<Cakeitem> cakeItems, OnListFragmentInteractionListener listener,Context c) {
        cakeitemArrayList = cakeItems;
        mListener = listener;
        context = c;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_cakeitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = cakeitemArrayList.get(position);
        Cakeitem item = cakeitemArrayList.get(position);
        holder.mcakepriceView.setText(item.getPrice()+"");
        holder.mCakenameVIew.setText(item.getName()+"");


        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.cookie);
        requestOptions.error(R.drawable.birthday_cake);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);


        Glide.with(context)
                .setDefaultRequestOptions(requestOptions)
                .load(item.getImgurl())
                .into(holder.mCakeImageView);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return cakeitemArrayList.size();
    }

    public void clearCakes() {
        cakeitemArrayList.clear();

    }

    public void addAllCakes(List<Cakeitem> cakeItemList) {
        cakeitemArrayList.addAll(cakeItemList);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mCakeImageView;
        public final TextView mCakenameVIew;
        public final TextView mcakepriceView;

        public Cakeitem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mCakeImageView = (ImageView) view.findViewById(R.id.cake_image);
            mCakenameVIew = (TextView) view.findViewById(R.id.cake_name);
            mcakepriceView = (TextView) view.findViewById(R.id.cake_price);


        }

        @Override
        public String toString() {
            return super.toString() + " '" + mCakenameVIew.getText() + "'";
        }
    }
}
