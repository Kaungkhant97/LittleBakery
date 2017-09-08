package com.kaungkhantthu.xyz.littlebakery.activity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kaungkhantthu.xyz.littlebakery.R;
import com.kaungkhantthu.xyz.littlebakery.entity.OrderDetail;

import java.util.Collections;
import java.util.Date;

import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by kaungkhantthu on 8/29/17.
 */

class OrderHistoryRecyclerAdapter extends RecyclerView.Adapter<OrderHistoryRecyclerAdapter.Viewholder> {
    private final RealmResults<OrderDetail> ordershistories;

    public OrderHistoryRecyclerAdapter(RealmResults<OrderDetail> orderHistories) {
        this.ordershistories = orderHistories;
        this.ordershistories.sort("deliveryDate",Sort.ASCENDING);
    }

    @Override
    public OrderHistoryRecyclerAdapter.Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_orderhistories, parent, false);

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(OrderHistoryRecyclerAdapter.Viewholder holder, int position) {
        holder.txt_orderitem.setText(ordershistories.get(position).toString());
        holder.txt_orderQuantity.setText("Total Quantity: "+ordershistories.get(position).getTotalqautity()+"");
        holder.txt_orderTotalPrice.setText("Total Price: "+ordershistories.get(position).getTotalprice()+"");
        holder.txt_orderDate.setText("Delivery Date:"+new Date(ordershistories.get(position).getDeliveryDate()).toLocaleString()+"");
    }

    @Override
    public int getItemCount() {
        return ordershistories.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

         TextView txt_orderTotalPrice;
        TextView txt_orderQuantity;
        TextView txt_orderitem;
        TextView txt_orderDate;

        public Viewholder(View itemView) {
            super(itemView);
            txt_orderitem = (TextView) itemView.findViewById(R.id.txt_orderitems);
            txt_orderQuantity = (TextView) itemView.findViewById(R.id.txt_orderQuantity);
            txt_orderDate = (TextView) itemView.findViewById(R.id.txt_orderdate);
            txt_orderTotalPrice = (TextView) itemView.findViewById(R.id.txt_orderTotalPrice);

        }



    }
}
