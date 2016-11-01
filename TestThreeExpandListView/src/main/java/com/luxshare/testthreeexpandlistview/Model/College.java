package com.luxshare.testthreeexpandlistview.Model;

import java.util.List;

/**
 * Created by Administrator on 2016/11/1.
 */
public class College {

    private String name;

    private List<Classes> mClasses;

    public List<Classes> getClasses() {
        return mClasses;
    }

    public void setClasses(List<Classes> classes) {
        mClasses = classes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
