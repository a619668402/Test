package com.luxshare.testtreeview.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.luxshare.testtreeview.R;
import com.unnamed.b.atv.model.TreeNode;

/**
 * Created by Administrator on 2016/10/28.
 */
public class OneTreeHolder extends TreeNode.BaseNodeViewHolder<OneTreeHolder.IconTreeItem> {

    private TextView mtv;
    private Button mBtn;

    public OneTreeHolder(Context context) {
        super(context);
    }

    private onClickListener mListener;

    public onClickListener getListener() {
        return mListener;
    }

    public void setListener(onClickListener listener) {
        mListener = listener;
    }

    @Override
    public View createNodeView(final TreeNode node, final IconTreeItem value) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.item_one, null, false);

        mtv = ((TextView) view.findViewById(R.id.node_value));
        mtv.setText(value.text);

        mBtn = ((Button) view.findViewById(R.id.btn));
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onClick(value, node, OneTreeHolder.this);
                }
            }
        });
        return view;
    }

    @Override
    public void toggle(boolean active) {
    }

    @Override
    public int getContainerStyle() {
        return R.style.TreeNodeStyleCustom;
    }

    public static class IconTreeItem {
        public int icon;
        public String text;

        public IconTreeItem(int icon, String text) {
            this.icon = icon;
            this.text = text;
        }
    }

    public interface onClickListener {
        void onClick(IconTreeItem item, TreeNode node, OneTreeHolder holder);
    }
}
