package com.kaungkhantthu.xyz.littlebakery.recyclerView;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kaungkhantthu.xyz.littlebakery.util.OnListFragmentInteractionListener;
import com.kaungkhantthu.xyz.littlebakery.R;
import com.kaungkhantthu.xyz.littlebakery.entity.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kaungkhantthu on 8/21/17.
 */

public class CategoryItemRecyclerAdapter extends RecyclerView.Adapter<CategoryItemRecyclerAdapter.ViewHolder> {

    private final ArrayList<Category> categoryitemArrayList;
    private final OnListFragmentInteractionListener mListener;
    private final Context context;


    public CategoryItemRecyclerAdapter(ArrayList<Category> categories, OnListFragmentInteractionListener listener,Context c) {
        categoryitemArrayList = categories;
        mListener = listener;
         context = c;
    }

    @Override
    public CategoryItemRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_categoryitem, parent, false);
        return new CategoryItemRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CategoryItemRecyclerAdapter.ViewHolder holder, int position) {
        holder.category = categoryitemArrayList.get(position);
        Category category = categoryitemArrayList.get(position);

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(new ColorDrawable(Color.parseColor("#EFEFEF")));
        requestOptions.error(new ColorDrawable(Color.parseColor("#000000")));

        Glide.with(context)
                .setDefaultRequestOptions(requestOptions)
                .load(getDrawable(category.getId()))
                .into(holder.mcategoryIcon);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListItemClick(holder.category,v);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryitemArrayList.size();
    }

    public void clearCategory() {
        categoryitemArrayList.clear();

    }

    public void addALlcategory(List<Category> categories) {
        categoryitemArrayList.addAll(categories);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mcategoryIcon;
        public Category category;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mcategoryIcon = (ImageView) view.findViewById(R.id.category_icon);
        }

    }

    @DrawableRes
    private  int getDrawable(int catid){


        switch (catid){


            case 1:return R.drawable.cake;
            case 2:return R.drawable.donut;
            case 3:return R.drawable.cupcake;
            case 4:return R.drawable.bread;
            case 5:return R.drawable.cookie;
            case 6: return R.drawable.birthday_cake;
            case 7:return R.drawable.croissant;
            default:return R.drawable.cake;

        }


    }
}
