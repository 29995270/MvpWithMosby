package com.wq.freeze.mvpwithmosby.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.MvpLceViewStateActivity;
import com.wq.freeze.mvpwithmosby.R;
import com.wq.freeze.mvpwithmosby.presenter.ListPresenter;
import com.wq.freeze.mvpwithmosby.view.ListUiView;
import com.wq.freeze.mvpwithmosby.view.SimpleListAdapter;
import com.wq.freeze.mvpwithmosby.view.viewstate.ListUiViewState;
import com.wq.freeze.mvpwithmosby.view.widget.LoadMoreListener;

import java.util.Collections;
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
    private SimpleListAdapter simpleListAdapter;
    private View loadMoreView;
    private LoadMoreListener loadMoreListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_ui);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.contentView);
        loadMoreView = findViewById(R.id.load_more_view);
        simpleListAdapter = new SimpleListAdapter();
        recyclerView.setAdapter(simpleListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        refreshLayout.setOnRefreshListener(this);

        loadMoreListener = new LoadMoreListener() {

            @Override
            public void onLoad() {
                super.onLoad();
                presenter.loadMore();
            }
        };

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int visibleItemCount = recyclerView.getChildCount();
                int totalItemCount = recyclerView.getLayoutManager().getItemCount();
                int firstVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                if ((totalItemCount - visibleItemCount) <= (firstVisibleItemPosition)) {
                    if (!loadMoreListener.isLoading) {
                        loadMoreListener.onLoad();
                    }
                }
            }
        });
    }

    @Override
    public LceViewState<List<String>, ListUiView> createViewState() {
        return new ListUiViewState();
    }

    @Override
    public void onNewViewStateInstance() {
        super.onNewViewStateInstance();  // super already called loadData(false);
    }

    @Override
    public List<String> getData() {
        return simpleListAdapter.getDataSrc();
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
        simpleListAdapter.setDataSrc(data);
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        if (pullToRefresh) {
            presenter.refresh();
        } else {
            presenter.initScreen();
        }
    }

    @Override
    public void showLoading(boolean pullToRefresh) {
        super.showLoading(pullToRefresh);
        if (pullToRefresh) {
            showContent();
            refreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    refreshLayout.setRefreshing(true);
                    presenter.refresh();
                }
            });
        }
    }

    @Override
    public void showLoadMore() {
        loadMoreView.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissLoadMore() {
        loadMoreView.setVisibility(View.GONE);
    }

    @Override
    public void showLoadMoreError() {
        Toast.makeText(this, "load more error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void moreDataFromRefresh(String extra) {
        simpleListAdapter.appendDataSrc(Collections.singletonList(extra), true);
        viewState.setStateShowContent(simpleListAdapter.getDataSrc());
        refreshLayout.setRefreshing(false);
        recyclerView.smoothScrollToPosition(0);
    }

    @Override
    public void moreDataFromLoadMore(List<String> extra) {
        simpleListAdapter.appendDataSrc(extra, false);
        viewState.setStateShowContent(simpleListAdapter.getDataSrc());
        loadMoreListener.isLoading = false;
        dismissLoadMore();
    }

    @Override
    public void onRefresh() {
        viewState.setStateShowLoading(true);
        loadData(true);
    }
}
