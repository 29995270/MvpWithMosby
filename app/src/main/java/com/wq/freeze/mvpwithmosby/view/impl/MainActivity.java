package com.wq.freeze.mvpwithmosby.view.impl;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

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
    private AlertDialog loginDialog;

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
        viewState.setColorState(color);
        rootContent.setBackgroundColor(Color.parseColor(color));
    }

    @Override
    public void showLoginDialog() {
        viewState.setIsLoginShow(true);
        loginDialog = new AlertDialog.Builder(this)
                .setTitle("Login")
                .setMessage("do you want login?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        viewState.setIsLoginShow(false);
//                        presenter.checkUserState(System.currentTimeMillis()%2 == 0);
                        presenter.checkUserState(true);
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        viewState.setIsLoginShow(false);
                    }
                })
                .setCancelable(false)
                .show();
    }

    @Override
    public void showUserInvalid() {
        Toast.makeText(this, "sorry failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void gotoListUi() {
        startActivity(new Intent(this, ListActivity.class));
    }

    @Override
    protected void onDestroy() {
        if (loginDialog != null && loginDialog.isShowing()) {
            loginDialog.dismiss();
        }
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        presenter.showMessage();
        presenter.changeColor();
        presenter.showDialog();
    }
}
