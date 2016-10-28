package com.luxshare.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ViewSwitcher;

import com.luxshare.test.toolbar.ToolBarActivity;
import com.squareup.picasso.Picasso;

public class MainActivity extends ToolBarActivity implements ViewSwitcher.ViewFactory, View.OnTouchListener {

    private ImageSwitcher mIs;
    private float startX, endX;
    private int index;
    private String[] imageUrl = {"http://img4.imgtn.bdimg.com/it/u=3123432318,2547934550&fm=21&gp=0.jpg"
                                ,"http://img4.imgtn.bdimg.com/it/u=3217962789,3430649993&fm=21&gp=0.jpg"
                                ,"http://img1.imgtn.bdimg.com/it/u=4002728021,3052237326&fm=21&gp=0.jpg"
                                ,"http://img2.imgtn.bdimg.com/it/u=2692675169,1336708413&fm=21&gp=0.jpg"
                                ,"http://img5.imgtn.bdimg.com/it/u=78774946,2151458707&fm=21&gp=0.jpg"
                                ,"http://img1.imgtn.bdimg.com/it/u=3242708408,177558263&fm=21&gp=0.jpg"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setToolbarTitle("测试");

        mIs = ((ImageSwitcher) findViewById(R.id.imgswitcher));
        mIs.setFactory(this);
        mIs.setOnTouchListener(this);

    }

    @Override
    public View makeView() {
        System.out.println("-----1-----");
        ImageView i = new ImageView(this);
        i.setLayoutParams(new ImageSwitcher.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
        Picasso.with(this).load(imageUrl[index]).into(i);
        return i;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            startX = event.getX();
            return true;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            endX = event.getX();
            if (endX - startX > 20) {
                index = index == 0 ? imageUrl.length - 1 : index - 1;
                // 设置动画的时候不可见的那一个是第一个slide_in_left，可见的是第二个slide_out_right
                mIs.setInAnimation(AnimationUtils.loadAnimation(this,R.anim.slide_in_left));
                mIs.setOutAnimation(AnimationUtils.loadAnimation(this,R.anim.slide_out_right));
            } else if (startX - endX > 20) {
                index = index == imageUrl.length - 1 ? 0 : index + 1;
                mIs.setInAnimation(AnimationUtils.loadAnimation(this,R.anim.slide_in_right));
                mIs.setOutAnimation(AnimationUtils.loadAnimation(this,R.anim.slide_out_left));
            }


            return true;
        }
        return false;
    }
}
