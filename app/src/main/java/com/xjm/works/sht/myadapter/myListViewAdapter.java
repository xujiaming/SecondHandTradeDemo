package com.xjm.works.sht.myadapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.loopj.android.image.SmartImage;
import com.loopj.android.image.SmartImageView;
import com.xjm.works.sht.R;
import com.xjm.works.sht.modules.homePage.model.ListViewItemBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/31 0031.
 */

public class myListViewAdapter extends BaseAdapter {
    ArrayList<ListViewItemBean> list = null;
    Context context = null;

    public myListViewAdapter(@NonNull Context context, @NonNull ArrayList<ListViewItemBean> list){
        this.list = list;
        this.context = context;
    }

    public void addFirst(ListViewItemBean bean){
        list.add(0, bean);
    }

    public void addLast(ListViewItemBean bean){
        list.add(bean);
    }

    public void removeAll(){
        list.clear();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        ListViewHoder listViewHoder = null;

        if(convertView != null){
            listViewHoder = (ListViewHoder) convertView.getTag();
        }else {
            listViewHoder = new ListViewHoder();
            view = View.inflate(context, R.layout.home_listview_item,null);
            listViewHoder.smartImageView = (SmartImageView) view.findViewById(R.id.img_item);
            listViewHoder.tv_title = (TextView) view.findViewById(R.id.tv_title);
            listViewHoder.tv_date = (TextView) view.findViewById(R.id.tv_date);
            listViewHoder.tv_description = (TextView) view.findViewById(R.id.tv_description);
            listViewHoder.tv_price = (TextView) view.findViewById(R.id.tv_price);
            convertView = view;
            convertView.setTag(listViewHoder);
        }

        if(list.size() > 0){
            listViewHoder.smartImageView.setImageUrl("http://10.130.24.136:8080"+list.get(position).getPhoto());
            listViewHoder.tv_date.setText(list.get(position).getCreateDate().toString());
            listViewHoder.tv_description.setText(list.get(position).getDescription().toString());
            listViewHoder.tv_price.setText(list.get(position).getNowPrice()+"");
            listViewHoder.tv_title.setText(list.get(position).getName().toString());

        }


        return convertView;
    }

    class ListViewHoder {
        public SmartImageView smartImageView;
        public TextView tv_title;
        public TextView tv_date;
        public TextView tv_description;
        public TextView tv_price;
    }
}
