package com.luxshare.test.toolbar;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by Administrator on 2016/10/7.
 */
public class ToolBarActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    private ToolBarHelper mToolBarHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {

        mToolBarHelper = new ToolBarHelper(this,layoutResID);
        mToolbar = mToolBarHelper.getToolbar();
        setContentView(mToolBarHelper.getContentView());
        // 把 toolbar 设置到 activity 中
        setSupportActionBar(mToolbar);
        onCreateCustomToolBar(mToolbar);
    }

    public void onCreateCustomToolBar(Toolbar toolbar) {
        toolbar.setContentInsetsAbsolute(0,0);
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setToolbarTitle(String title) {
        mToolBarHelper.setToolBarTitle(title);
    }


    public void setNavigationBack() {
        mToolBarHelper.setBackBtn(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
