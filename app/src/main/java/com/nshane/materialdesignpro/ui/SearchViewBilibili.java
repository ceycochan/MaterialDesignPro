package com.nshane.materialdesignpro.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.nshane.materialdesignpro.R;
import com.wyt.searchbox.SearchFragment;
import com.wyt.searchbox.custom.IOnSearchClickListener;

import butterknife.ButterKnife;

/**
 * Created by da on 2017-12-13.
 */

public class SearchViewBilibili extends AppCompatActivity implements Toolbar.OnMenuItemClickListener, IOnSearchClickListener {



    private SearchFragment searchFragment;
    private TextView mSearchTarget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setContentView(R.layout.activity_search_bilibili);

        Toolbar mTb = (Toolbar) findViewById(R.id.tb_bili);
        mTb.setTitle("Bilibili Search");
        setSupportActionBar(mTb);

        mSearchTarget = (TextView) findViewById(R.id.tv_search_target);


        searchFragment = SearchFragment.newInstance();

        mTb.setOnMenuItemClickListener(this);

        searchFragment.setOnSearchClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_bilibili, menu);
        return true;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search_bilibili:
                searchFragment.show(getSupportFragmentManager(), SearchFragment.TAG);
                break;
        }
        return true;
    }

    @Override
    public void OnSearchClick(String keyword) {
        mSearchTarget.setText(keyword);
    }
}
