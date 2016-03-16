package com.wq.freeze.mvpwithmosby.view.impl;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.hannesdorfmann.mosby.mvp.viewstate.ViewState;
import com.wq.freeze.mvpwithmosby.R;
import com.wq.freeze.mvpwithmosby.presenter.MainPresenter;
import com.wq.freeze.mvpwithmosby.view.MainView;
import com.wq.freeze.mvpwithmosby.view.MvpViewStateActivity;
import com.wq.freeze.mvpwithmosby.view.viewstate.MainViewState;

public class MainActivity extends MvpViewStateActivity<MainView, MainPresenter, MainViewState> implements MainView, View.OnClickListener {

    private FloatingActionButton fab;
    private CoordinatorLayout rootContent;
    private TextInputEditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        rootContent = (CoordinatorLayout) findViewById(R.id.root_content);
        editText = (TextInputEditText) findViewById(R.id.edit_text);
        assert fab != null;
        fab.setOnClickListener(this);
    }

    @NonNull
    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @NonNull
    @Override
    public ViewState<MainView> createViewState() {
        return new MainViewState();
    }

    // Will be called when no view state exists yet,
    // which is the case the first time MainActivity starts
    @Override
    public void onNewViewStateInstance() {
        Snackbar.make(rootContent, "hello nice to see you", Snackbar.LENGTH_SHORT)
                .show();
    }

    @Override
    public void showSnackBar(String text) {
        Snackbar.make(rootContent, text, Snackbar.LENGTH_SHORT)
                .show();
    }

    @Override
    public void changeBgColor(String color) {
        viewState.setState(color);
        rootContent.setBackgroundColor(Color.parseColor(color));
    }

    @Override
    public void onClick(View v) {
        presenter.showMessage();
        presenter.changeColor();
    }
}
