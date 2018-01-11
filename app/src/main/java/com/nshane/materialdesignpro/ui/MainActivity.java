package com.nshane.materialdesignpro.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.nshane.materialdesignpro.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getLayoutInflater();
    }

    private void jumpToActivity(Class activityClass) {
        startActivity(new Intent(this, activityClass));
    }


    public void searchView2(View view) {
        jumpToActivity(SearchViewActivity.class);
    }

    public void searchView1(View view) {
        jumpToActivity(SearchViewBilibili.class);
    }

    public void customSearch(View view) {
        jumpToActivity(SearchCustomActivity.class);
    }


    //toolbar navigation 设置

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId() == android.R.id.home) {
//            onBackPressed();
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }


//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        Log.d("cg", "back button");
//        this.finish();
//    }


}
