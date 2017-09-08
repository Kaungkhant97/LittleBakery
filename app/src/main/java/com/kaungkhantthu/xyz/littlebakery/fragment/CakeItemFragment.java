package com.kaungkhantthu.xyz.littlebakery.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kaungkhantthu.xyz.littlebakery.util.Constants;
import com.kaungkhantthu.xyz.littlebakery.activity.DetailActivity;
import com.kaungkhantthu.xyz.littlebakery.util.OnListFragmentInteractionListener;
import com.kaungkhantthu.xyz.littlebakery.R;
import com.kaungkhantthu.xyz.littlebakery.entity.Cakeitem;
import com.kaungkhantthu.xyz.littlebakery.mvp.presenter.CakeitemPresenter;
import com.kaungkhantthu.xyz.littlebakery.mvp.view.CakeItemView;
import com.kaungkhantthu.xyz.littlebakery.recyclerView.CakeItemRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class CakeItemFragment extends Fragment implements CakeItemView,OnListFragmentInteractionListener {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 2;
    private OnListFragmentInteractionListener mListener;
    private RecyclerView recyler_cake;
    private FrameLayout errorlayout;
    private TextView errotext;
    private Button errorbtn;
    private CakeItemRecyclerViewAdapter adapter;
    private CakeitemPresenter cakePresenter;
    private DividerItemDecoration mDividerItemDecoration;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CakeItemFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static CakeItemFragment newInstance() {
        CakeItemFragment fragment = new CakeItemFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentlist, container, false);

        // Set the adapter
        recyler_cake = (RecyclerView) view.findViewById(R.id.recycler_item);
        errorlayout = (FrameLayout) view.findViewById(R.id.errorLayout);

        errotext = (TextView) view.findViewById(R.id.errorText);
        errorbtn = (Button) view.findViewById(R.id.btn_error);

        initRecycler();
        init();

        return view;
    }

    private void init() {
        cakePresenter = new CakeitemPresenter(this);
        cakePresenter.init();
    }

    private void initRecycler() {
        recyler_cake.setLayoutManager(new GridLayoutManager(getContext(),1));
        mDividerItemDecoration = new DividerItemDecoration(recyler_cake.getContext(),
                DividerItemDecoration.VERTICAL);
        recyler_cake.addItemDecoration(mDividerItemDecoration);
        adapter = new CakeItemRecyclerViewAdapter(new ArrayList<Cakeitem>(),this,getContext());
        recyler_cake.setAdapter(adapter);


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void showCakelist(List<Cakeitem> cakeItemList) {
        errorlayout.setVisibility(View.GONE);
        recyler_cake.setVisibility(View.VISIBLE);
        recyler_cake.bringToFront();
        adapter.clearCakes();
        adapter.notifyDataSetChanged();
        adapter.addAllCakes(cakeItemList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorView() {
        Log.e("showErrorView: ", "error in event");
        errorlayout.setVisibility(View.VISIBLE);
        recyler_cake.setVisibility(View.GONE);
        errorlayout.bringToFront();
        errorbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("onClick: ", "click" + "");
                cakePresenter.requestCakes();
            }
        });
    }

    @Override
    public void showErrorView(String errorText) {

    }





    @Override
    public void onListItemClick(Object item, View v) {
        Cakeitem c = (Cakeitem)item;

        Intent i = new Intent(getActivity(),DetailActivity.class);
        i.putExtra(Constants.CAKEID,c.getId());

        startActivity(i);
    }



}
