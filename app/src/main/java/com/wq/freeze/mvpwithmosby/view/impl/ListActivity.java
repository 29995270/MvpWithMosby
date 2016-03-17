package com.wq.freeze.mvpwithmosby.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.MvpLceViewStateActivity;
import com.wq.freeze.mvpwithmosby.R;
import com.wq.freeze.mvpwithmosby.presenter.ListPresenter;
import com.wq.freeze.mvpwithmosby.view.ListUiView;
import com.wq.freeze.mvpwithmosby.view.SimpleListAdapter;
import com.wq.freeze.mvpwithmosby.view.viewstate.ListUiViewState;

import java.util.List;

/**
 * Created by wangqi on 2016/3/17.
 */
public class ListActivity extends MvpLceViewStateActivity<SwipeRefreshLayout, List<String>, ListUiView, ListPresenter>
        implements ListUiView, SwipeRefreshLayout.OnRefreshListener {

    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_ui);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.contentView);
        SimpleListAdapter simpleListAdapter = new SimpleListAdapter();
        recyclerView.setAdapter(simpleListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        refreshLayout.setOnRefreshListener(this);
    }

    @Override
    public LceViewState<List<String>, ListUiView> createViewState() {
        return new ListUiViewState();
    }

    @Override
    public List<String> getData() {
        return null;
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return pullToRefresh? "net error!!!": "oops, error!! touch me!!";
    }

    @NonNull
    @Override
    public ListPresenter createPresenter() {
        return new ListPresenter();
    }

    @Override
    public void setData(List<String> data) {

    }

    @Override
    public void loadData(boolean pullToRefresh) {

    }

    @Override
    public void showLoadMore() {

    }

    @Override
    public void showLoadMoreError() {

    }

    @Override
    public void loadMoreData() {

    }

    @Override
    public void onRefresh() {

    }
}
