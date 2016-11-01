package com.luxshare.testthreeexpandlistview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;

import com.luxshare.testthreeexpandlistview.Adapter.SimpleExpandableListViewAdapter;
import com.luxshare.testthreeexpandlistview.Model.Classes;
import com.luxshare.testthreeexpandlistview.Model.College;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ExpandableListView mExpandListView;
    //    private ClassesExpandableListViewAdapter mAdapter;
    private ArrayList<College> mColleges;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initViewById();
        initAdapter();
    }

    private void initAdapter() {
//        mAdapter = new ClassesExpandableListViewAdapter(mList, this);
//        mExpandListView.setAdapter(mAdapter);

        SimpleExpandableListViewAdapter adaper = new SimpleExpandableListViewAdapter(this, mColleges);
        mExpandListView.setAdapter(adaper);
    }

    private void initData() {

        College college = new College();
        college.setName("科技大学");
        List<Classes> classList = new ArrayList<>();

        for (int i = 0; i < 40; i++) {
            Classes cla = new Classes();
            cla.setName("计算机" + i + "班");
            List<String> list = new ArrayList<>();
            for (int j = 0; j < 30; j++) {
                list.add(j + "----");
            }
            cla.setStudents(list);
            classList.add(cla);
        }

        college.setClasses(classList);

        mColleges = new ArrayList<>();
        mColleges.add(college);
    }

    private void initViewById() {
        mExpandListView = ((ExpandableListView) findViewById(R.id.tree_view_simple));
    }


    ExpandableListView.OnChildClickListener listener = new ExpandableListView.OnChildClickListener() {
        @Override
        public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
            return false;
        }
    };
}
