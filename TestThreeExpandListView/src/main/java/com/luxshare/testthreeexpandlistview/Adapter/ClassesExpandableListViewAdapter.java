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

import com.luxshare.testthreeexpandlistview.Model.Classes;
import com.luxshare.testthreeexpandlistview.R;

import java.util.List;

/**
 * Created by Administrator on 2016/11/1.
 */
public class ClassesExpandableListViewAdapter extends BaseExpandableListAdapter {

    private List<Classes> mClasses;

    private Context mContext;

    public ClassesExpandableListViewAdapter(List<Classes> list, Context context) {
        this.mClasses = list;
        this.mContext = context;
    }

    @Override
    public int getGroupCount() {
        return mClasses.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mClasses.get(groupPosition).getStudents().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mClasses.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mClasses.get(groupPosition).getStudents().get(childPosition);
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
//        return getGenericView(mClasses.get(groupPosition).getName());
        GroupViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_head_layout, null);
            holder = new GroupViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (GroupViewHolder) convertView.getTag();
        }
        holder.mTitle.setText(mClasses.get(groupPosition).getName());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        GroupViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_child_child_layout, null);
            holder = new GroupViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (GroupViewHolder) convertView.getTag();
        }
//        holder.mTitle.setText(mClasses.get(groupPosition).getStudents().get(childPosition));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    private static class GroupViewHolder {
        private final TextView mTitle;

        public GroupViewHolder(View view) {
            mTitle = ((TextView) view.findViewById(R.id.item_head_tv));
        }
    }


    /**
     * 根据字符串生成布局，
     *
     * @param name
     * @return
     */
    private View getGenericView(String name) {
        AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView tv = new TextView(mContext);
        tv.setLayoutParams(layoutParams);


        tv.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);

        tv.setPadding(80, 20, 0, 20);
        tv.setTextSize(18);
        tv.setText(name);
        tv.setTextColor(Color.RED);
        return tv;
    }
}
