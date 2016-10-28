package com.luxshare.test.toolbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.luxshare.test.R;


/**
 * Created by Administrator on 2016/10/7.
 */
public class ToolBarHelper {

    /* 上下文对象 创建View时用到*/
    private Context mContext;

    /* base View */
    private FrameLayout mContentView;

    /* 用户自定义View */
    private View mUserView;

    /* toolbar */
    private Toolbar mToolbar;

    /* toolbar上的自定义布局 */
    private View mNavigationView;

    /* 视图构造器 */
    private LayoutInflater mInflater;

    /**
     * 两个属性
     * 1.ToolBar是否悬浮在窗口智商
     * 2.ToolBar的高度获取
     */
    private static int[] ATTRS = {R.attr.windowActionBarOverlay, R.attr.actionBarSize};


    /* toolbar标题 */
    private TextView mTitle;


    /* toolbar 返回按钮 */
    private Button mBack;


    public ToolBarHelper(Context context, int layoutId) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        // 初始化整个内容
        initContentView();
        // 初始化用户定义的内容
        initUserView(layoutId);
        // 初始化toolbar
        initToolBar();
    }


    private void initContentView() {
        // 创建一个桢布局作为视图容器的父容器
        mContentView = new FrameLayout(mContext);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mContentView.setLayoutParams(params);
    }

    private void initToolBar() {
        // 通过inflater获取toolbar的布局文件
        View toolbar = mInflater.inflate(R.layout.toolbar, mContentView);
        mToolbar = (Toolbar) toolbar.findViewById(R.id.id_toolbar);
        initNavigationView(mToolbar);
    }

    private void initNavigationView(Toolbar toolbar) {
        mNavigationView = mInflater.inflate(R.layout.navigation_bar, null);
        toolbar.addView(mNavigationView,new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        initNavigationViewById(mNavigationView);
    }

    private void initNavigationViewById(View navigationView) {
        mTitle = ((TextView) navigationView.findViewById(R.id.navigation_title));
        mBack = ((Button) navigationView.findViewById(R.id.navigation_back));
    }


    private void initUserView(int id) {
        mUserView = mInflater.inflate(id, null);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        TypedArray typedArray = mContext.getTheme().obtainStyledAttributes(ATTRS);
        // 获取主题中定义的悬浮标志
        boolean overly = typedArray.getBoolean(0, false);
        // 获取主题中定义的toolbar的高度
        int toolBarSize = (int) typedArray.getDimension(Integer.parseInt("1"), (int) mContext.getResources().getDimension(R.dimen.abc_action_bar_default_height_material));
        typedArray.recycle();

        // 如果是悬浮状态，则不需要设置间距
        params.topMargin = overly ? 0 : toolBarSize;
        mContentView.addView(mUserView, params);
    }


    public FrameLayout getContentView() {
        return mContentView;
    }


    public Toolbar getToolbar() {
        return mToolbar;
    }


    public void setToolBarTitle(String title) {
        mTitle.setVisibility(View.VISIBLE);
        mTitle.setText(title);
    }

    public void setBackBtn(View.OnClickListener listener) {
        mBack.setVisibility(View.VISIBLE);
        mBack.setOnClickListener(listener);
    }

}
