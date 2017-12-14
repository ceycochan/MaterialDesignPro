package com.nshane.materialdesignpro.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by da on 2017-12-11.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


//    public void setToolbar(@Nullable View view, @Nullable String title, @Nullable Drawable icon) {
//        Toolbar mTb = (Toolbar) view.findViewById(R.id.tb_toolbar);
//        mTb.setTitle(title);
//        mTb.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));
//        mTb.setNavigationIcon(icon);
//        setSupportActionBar(mTb);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//    }


}
