package com.luxshare.testgreendao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.luxshare.testgreendao.bean.DaoMaster;
import com.luxshare.testgreendao.bean.DaoSession;
import com.luxshare.testgreendao.bean.UpdateBean;
import com.luxshare.testgreendao.bean.UpdateBeanDao;
import com.luxshare.testgreendao.bean.User;
import com.luxshare.testgreendao.bean.UserDao;

public class MainActivity extends AppCompatActivity {

    private UserDao mUserDao;
    private UpdateBeanDao mUpdateBeanDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDB();
    }

    private void initDB() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(getApplicationContext(), "Test.DB", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        DaoSession daoSession = daoMaster.newSession();
        mUserDao = daoSession.getUserDao();
        mUpdateBeanDao = daoSession.getUpdateBeanDao();

    }

    // 添加数据
    public void insertBtnClick(View view) {
        mUserDao.insert(new User(null, "Test---1", 24, "篮球2", "男", "test", "Test1", "test2","Test3"));
    }

    public void insertUpdateClick(View view) {
        mUpdateBeanDao.insert(new UpdateBean(null, "One---1", "测试2", 21, "足球", "Test", "test2","Test3"));
    }
}
