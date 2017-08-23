package com.kaungkhantthu.xyz.littlebakery.recyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kaungkhantthu.xyz.littlebakery.R;
import com.kaungkhantthu.xyz.littlebakery.entity.Orderitem;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by kaungkhantthu on 8/22/17.
 */

public class OrderRecyclerAdapter extends RecyclerView.Adapter<OrderRecyclerAdapter.Viewholder>{

    private final RealmResults<Orderitem> orders;
    private final Context context;

    public OrderRecyclerAdapter(RealmResults<Orderitem> orderitems,Context c) {
        this.context = c;
        this.orders = orderitems;
    }

    @Override
    public OrderRecyclerAdapter.Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_orderitem, parent, false);

        return new OrderRecyclerAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(OrderRecyclerAdapter.Viewholder holder, final int position) {
        final Orderitem order = orders.get(position);
        holder.ordernameview.setText(order.getCakeName());
        holder.orderpriceview.setText("Price: "+ order.getPrice()+"");
        holder.orderamountview.setText(order.getQuantity()+" items");


        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.mipmap.ic_launcher);
        requestOptions.error(R.drawable.cookie);

        Glide.with(context)
                .setDefaultRequestOptions(requestOptions)
                .load(order.getCakeImgurl())
                .into(holder.orderImgview);

        holder.addOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity = order.getQuantity();

                ++quantity;

                Realm.getDefaultInstance().beginTransaction();
                order.setQuantity(quantity);
                notifyDataSetChanged();
                Realm.getDefaultInstance().commitTransaction();

            }
        });

        holder.minusOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity = order.getQuantity();

                --quantity;

                Realm.getDefaultInstance().beginTransaction();
                order.setQuantity(quantity);
                notifyDataSetChanged();
                Realm.getDefaultInstance().commitTransaction();

            }
        });
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }



    public class Viewholder extends RecyclerView.ViewHolder {

         TextView orderamountview;
        ImageView orderImgview;
        TextView orderpriceview;
        TextView ordernameview;
        ImageView addOrder;
        ImageView minusOrder;



        public Viewholder(View view) {

            super(view);
            orderImgview = (ImageView)view.findViewById(R.id.order_image);
            ordernameview = (TextView)view.findViewById(R.id.order_name);
            orderpriceview = (TextView)view.findViewById(R.id.order_price);
            orderamountview = (TextView)view.findViewById(R.id.order_amount);
            addOrder = (ImageView)view.findViewById(R.id.imgbtn_plus);
            minusOrder = (ImageView)view.findViewById(R.id.imgbtn_minus);


        }
    }


}

