package com.luxshare.testthreeexpandlistview.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.luxshare.testthreeexpandlistview.Model.College;
import com.luxshare.testthreeexpandlistview.R;
import com.luxshare.testthreeexpandlistview.View.CustomExpandableListView;

import java.util.List;

/**
 * Created by Administrator on 2016/11/1.
 */
public class SimpleExpandableListViewAdapter extends BaseExpandableListAdapter {

    private List<College> mList;

    private Context mContext;

    public SimpleExpandableListViewAdapter(Context context, List<College> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public int getGroupCount() {
        return mList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        // 一定要返回1
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mList.get(groupPosition).getClasses().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
//        return getGenericView(mList.get(groupPosition).getName());
        GroupViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_head_layout, null);
            holder = new GroupViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (GroupViewHolder) convertView.getTag();
        }
        holder.mTitle.setText(mList.get(groupPosition).getName());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
//        return getGenericExpandListView(mList.get(groupPosition));
        ChildViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_child_layout, null);
            holder = new ChildViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ChildViewHolder) convertView.getTag();
        }
        ClassesExpandableListViewAdapter adapter = new ClassesExpandableListViewAdapter(mList.get(groupPosition).getClasses(), mContext);
        holder.mExpandListView.setAdapter(adapter);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private View getGenericView(String name) {
        AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView tv = new TextView(mContext);
        tv.setLayoutParams(layoutParams);
        tv.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
        tv.setPadding(100, 20, 0, 20);
        tv.setText(name);
        tv.setTextColor(Color.BLACK);
        return tv;
    }

    private View getGenericExpandListView(College college) {
        AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        CustomExpandableListView view = new CustomExpandableListView(mContext);
        ClassesExpandableListViewAdapter adapter = new ClassesExpandableListViewAdapter(college.getClasses(), mContext);
        view.setAdapter(adapter);
        view.setPadding(20, 0, 0, 0);
        return view;
    }

    private static class GroupViewHolder {
        private final TextView mTitle;

        public GroupViewHolder(View view) {
            mTitle = ((TextView) view.findViewById(R.id.item_head_tv));
        }
    }

    private static class ChildViewHolder {

        private final CustomExpandableListView mExpandListView;

        public ChildViewHolder(View view) {
            mExpandListView = ((CustomExpandableListView) view.findViewById(R.id.childexpandlistview));
        }
    }
}
