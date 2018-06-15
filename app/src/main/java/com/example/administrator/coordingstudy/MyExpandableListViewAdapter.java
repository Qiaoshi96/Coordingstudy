package com.example.administrator.coordingstudy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/6/15 0015.
 */

public class MyExpandableListViewAdapter extends BaseExpandableListAdapter {
    //创建构造方法传入集合
    private Map<String, List<String>> dataset;
    private Context context;
    private String[] parentList;

    public MyExpandableListViewAdapter(Map<String, List<String>> dataset, Context context, String[] parentList) {
        this.dataset = dataset;
        this.context = context;
        this.parentList = parentList;
    }

    //  获得父项的数量
    @Override
    public int getGroupCount() {
        return dataset.size();
    }

    //  获得某个父项的子项数目
    @Override
    public int getChildrenCount(int i) {
        return dataset.get(parentList[i]).size();
    }

    //  获得某个父项
    @Override
    public Object getGroup(int i) {
        return dataset.get(parentList[i]);
    }

    //  获得某个父项的某个子项
    @Override
    public Object getChild(int i, int i1) {
        return dataset.get(parentList[i]).get(i1);
    }

    //  获得某个父项的id
    @Override
    public long getGroupId(int i) {
        return i;
    }

    //  获得某个父项的某个子项的id
    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    //  按函数的名字来理解应该是是否具有稳定的id，这个方法目前一直都是返回false，没有去改动过
    @Override
    public boolean hasStableIds() {
        return false;
    }

    //  获得父项显示的view
    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.parent_item, null);
        }
        view.setTag(R.layout.parent_item, i);
        view.setTag(R.layout.child_item, -1);
        TextView text = (TextView) view.findViewById(R.id.parent_title);
        text.setText(parentList[i]);

        return view;
    }

    //  获得子项显示的view
    @Override
    public View getChildView(int i, final int i1, boolean b, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.child_item, null);
        }
        view.setTag(R.layout.parent_item, i);
        view.setTag(R.layout.child_item, i1);
        final TextView text = (TextView) view.findViewById(R.id.child_title);
        final CheckBox box=view.findViewById(R.id.checkbox);
        box.setEnabled(false);
        text.setText(dataset.get(parentList[i]).get(i1));

        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "点到了内置的textview"+i1, Toast.LENGTH_SHORT).show();

            }
        });

        return view;
    }

    //  子项是否可选中，如果需要设置子项的点击事件，需要返回true
    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
