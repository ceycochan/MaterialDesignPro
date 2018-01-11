package com.nshane.materialdesignpro.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nshane.materialdesignpro.R;
import com.wyt.searchbox.utils.KeyBoardUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bryan on 2018-1-9.
 */

public class SearchCustomActivity extends AppCompatActivity {


    private static final String TAG = SearchCustomActivity.class.getSimpleName();
    @BindView(R.id.iv_search_back)
    ImageView ivSearchBack;
    @BindView(R.id.et_search_edit)
    EditText etSearchEdit;
    @BindView(R.id.iv_search_erase)
    ImageView ivSearchErase;
    @BindView(R.id.iv_search_entry)
    ImageView ivSearchEntry;
    @BindView(R.id.iv_keyboard)
    ImageView ivKeyboard;

    /**
     * 仅用于测试EditText的吊起&隐藏
     *
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_search_custom);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        etSearchEdit.setHint("Edit & Search");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                manager.showSoftInput(etSearchEdit, 0);
            }
        }, 200);


        etSearchEdit.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        etSearchEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            //actionId对应XML中的imeOptions
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    Toast.makeText(SearchCustomActivity.this, "Commence Searching", Toast.LENGTH_SHORT).show();
                    KeyBoardUtils.closeKeyboard(SearchCustomActivity.this,etSearchEdit);

                    return true;

                }
                return false;
            }


        });


    }


    private boolean isKeyBoard = true;

    @OnClick({R.id.iv_search_back, R.id.et_search_edit, R.id.iv_search_erase, R.id.iv_search_entry, R.id.iv_keyboard})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_search_back:
                finish();
                break;
            case R.id.et_search_edit:
                break;
            case R.id.iv_search_erase:
                etSearchEdit.setText("");
                break;
            case R.id.iv_search_entry:
                Toast.makeText(SearchCustomActivity.this, "commence searching", Toast.LENGTH_SHORT).show();
                break;

            case R.id.iv_keyboard:
                // 控制开关键盘
                if (isKeyBoard) {
                    KeyBoardUtils.closeKeyboard(this, etSearchEdit);
                    isKeyBoard = false;
                } else {
                    KeyBoardUtils.openKeyboard(this, etSearchEdit);
                    isKeyBoard = true;
                }
                break;
        }
    }


    /**
     * hide keyboard once other view area was clicked
     *
     * @param event
     * @return
     */
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(getWindow().getDecorView()
                .getWindowToken(), 0);

        return super.onTouchEvent(event);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d("cg", "系统返回退出界面");
        this.finish();
    }


}
