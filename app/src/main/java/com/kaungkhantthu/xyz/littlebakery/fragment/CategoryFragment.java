package com.kaungkhantthu.xyz.littlebakery.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

import com.kaungkhantthu.xyz.littlebakery.activity.CakebyCategoryActivity;
import com.kaungkhantthu.xyz.littlebakery.activity.DetailActivity;
import com.kaungkhantthu.xyz.littlebakery.entity.Cakeitem;
import com.kaungkhantthu.xyz.littlebakery.util.Constants;
import com.kaungkhantthu.xyz.littlebakery.util.OnListFragmentInteractionListener;
import com.kaungkhantthu.xyz.littlebakery.R;
import com.kaungkhantthu.xyz.littlebakery.entity.Category;
import com.kaungkhantthu.xyz.littlebakery.mvp.presenter.CategoryPresenter;
import com.kaungkhantthu.xyz.littlebakery.mvp.view.CategoryItemView;
import com.kaungkhantthu.xyz.littlebakery.recyclerView.CategoryItemRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kaungkhantthu on 8/21/17.
 */

public class CategoryFragment extends Fragment implements CategoryItemView,OnListFragmentInteractionListener {
    private  static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 2;
    private OnListFragmentInteractionListener mListener;
    private RecyclerView recyler_category;
    private FrameLayout errorlayout;
    private TextView errotext;
    private Button errorbtn;
    private CategoryItemRecyclerAdapter adapter;
    private CategoryPresenter categoryPresenter;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CategoryFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static CategoryFragment newInstance() {
        CategoryFragment fragment = new CategoryFragment();

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
        recyler_category = (RecyclerView) view.findViewById(R.id.recycler_item);
        errorlayout = (FrameLayout) view.findViewById(R.id.errorLayout);

        errotext = (TextView) view.findViewById(R.id.errorText);
        errorbtn = (Button) view.findViewById(R.id.btn_error);

        initRecycler();
        init();

        return view;
    }

    private void init() {
        categoryPresenter = new CategoryPresenter(this);
        categoryPresenter.init();
    }

    private void initRecycler() {
        recyler_category.setLayoutManager(new GridLayoutManager(getContext(),2));
        adapter = new CategoryItemRecyclerAdapter(new ArrayList<Category>(),this,getContext());
        recyler_category.setAdapter(adapter);

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
    public void showCategorylist(List<Category> categories) {
        errorlayout.setVisibility(View.GONE);
        recyler_category.setVisibility(View.VISIBLE);
        recyler_category.bringToFront();
        adapter.clearCategory();
        adapter.notifyDataSetChanged();
        adapter.addALlcategory(categories);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorView() {
        Log.e("showErrorView: ", "error in event");
        errorlayout.setVisibility(View.VISIBLE);
        recyler_category.setVisibility(View.GONE);
        errorlayout.bringToFront();
        errorbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("onClick: ", "click" + "");
                categoryPresenter.requestCategory();
            }
        });
    }

    @Override
    public void showErrorView(String errorText) {

    }



    @Override
    public void onListItemClick(Object item, View v) {
        Category c =(Category)item;


        Intent i = new Intent(getContext(),CakebyCategoryActivity.class);
        i.putExtra(Constants.CATID,c.getId());
        startActivity(i);
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

}

