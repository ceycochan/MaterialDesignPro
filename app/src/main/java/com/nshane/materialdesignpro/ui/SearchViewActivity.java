package com.nshane.materialdesignpro.ui;

/**
 * Created by da on 2017-12-6.
 */

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.nshane.materialdesignpro.R;
import com.nshane.materialdesignpro.utils.FileUtils;
import com.nshane.materialdesignpro.utils.LogUtil;

import java.lang.reflect.Method;

/**
 * @description Toolbar下SearchView的使用
 */


public class SearchViewActivity extends AppCompatActivity {
    private static final String TAG = SearchViewActivity.class.getSimpleName();
    private ListView mLvMusic;
    private SearchView mSearchView;
    private SearchView.SearchAutoComplete mSearchAutoComplete;
    private Toolbar mToolbar;
    private static final int REQ_PERMISSION = 100;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_search_view);


        // onCreate integrated


        initView();
        initToolbar();
        requestPermission();


    }


    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mLvMusic = (ListView) findViewById(R.id.lv);
    }


    private void initToolbar() {
        setSupportActionBar(mToolbar);
        //toolbar的返回
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mSearchAutoComplete.isShown()) {
                    try {
                        mSearchAutoComplete.setText("");
                        Method method = mSearchView.getClass().getDeclaredMethod("onCloseClicked");
                        method.setAccessible(true);
                        method.invoke(mSearchView);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    finish();
                }
            }
        });
    }


    // invoked when menu is initialized by 1st time
    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.menu_search_view, menu);


        MenuItem searchItem = menu.findItem(R.id.menu_search);

//        searchItem.collapseActionView();
//        searchItem.expandActionView();


        //通过MenuItem得到SearchView
        mSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        mSearchView.setQueryHint("输入名字查找");


        //设置搜索框有字时显示清空按钮,无字时隐藏
        mSearchView.onActionViewExpanded();// 当展开无输入内容的时候，没有关闭的图标
        mSearchView.setIconified(true);//设置searchView处于展开状态


        // 监听SearchView 的内容
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //提交按钮的点击事件
                Toast.makeText(SearchViewActivity.this, query, Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //当输入框内容改变的时候回调
                LogUtil.d(TAG, "内容: " + newText);
//                Cursor cursor;
//                if (TextUtils.isEmpty(newText)) {
//                    cursor = null;
//                } else {
//                    cursor = queryData(newText);
//                }
//                setAdapter(cursor);
                queryMusic(newText);
                return true;
            }
        });


        mSearchAutoComplete = (SearchView.SearchAutoComplete) mSearchView.findViewById(R.id.search_src_text);


        //设置触发查询的最少字符数（默认1个字符才会触发查询,缺省为从第一个字符开始查询）
        mSearchAutoComplete.setThreshold(0);


        //设置SearchView输入框文字样式&大小
        mSearchAutoComplete.setHintTextColor(getResources().getColor(android.R.color.white));
        mSearchAutoComplete.setTextColor(getResources().getColor(android.R.color.white));
//        mSearchAutoComplete.setTextSize(14);

        return super.onCreateOptionsMenu(menu);

    }

    /**
     * 模糊查询
     *
     * @param key
     */

    private void queryMusic(String key) {
        String[] musics = new String[]{};
        if (!TextUtils.isEmpty(key)) {
            musics = FileUtils.queryMusic(this, key);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, musics);
        mLvMusic.setAdapter(adapter);

    }


//    private Cursor queryData(String key) {
//        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(getFilesDir() + "music.db", null);
//        Cursor cursor = null;
//        try {
//            String querySql = "select * from tb_music where name like '%" + key + "%'";
//            cursor = db.rawQuery(querySql, null);
//            LogUtil.d("cg", "querySql = " + querySql);
//        } catch (Exception e) {
//            e.printStackTrace();
//            String createSql = "create table tb_music (_id integer primary key autoincrement,name varchar(100))";
//            db.execSQL(createSql);
//
//            String insertSql = "insert into tb_music values (null,?)";
//            for (int i = 0; i < Cheeses.sCheeseStrings.length; i++) {
//                db.execSQL(insertSql, new String[]{Cheeses.sCheeseStrings[i]});
//            }
//
//            String querySql = "select * from tb_music where name like '%" + key + "%'";
//            cursor = db.rawQuery(querySql, null);
//
//            LogUtil.d("cg", "createSql = " + createSql);
//            LogUtil.d("cg", "querySql = " + querySql);
//        }
//        return cursor;
//    }


    private void requestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //如果还没有读取SD卡的权限,申请
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQ_PERMISSION);
        }
    }


    // 当前activity下是否获取到读取内存卡权限
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQ_PERMISSION) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                LogUtil.d("cg", "获取到的权限代码:" + grantResults[0]);
                Toast.makeText(this, "读取SD卡权限被拒绝了", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
