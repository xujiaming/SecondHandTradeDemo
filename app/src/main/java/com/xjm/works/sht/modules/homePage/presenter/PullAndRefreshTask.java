package com.xjm.works.sht.modules.homePage.presenter;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.xjm.works.sht.modules.homePage.model.ListViewItemBean;
import com.xjm.works.sht.modules.homePage.ui.HomeFragment;
import com.xjm.works.sht.myadapter.myListViewAdapter;
import com.xjm.works.sht.utils.myHttpUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/1 0001.
 */

public class PullAndRefreshTask extends AsyncTask<Void,Void,ArrayList<ListViewItemBean>> {
    int currentPage = 0;
    PullToRefreshListView listView = null;
    int pullState = -1;
    BaseAdapter baseAdapter = null;
    ArrayList<ListViewItemBean> mylist = new ArrayList<>();
    Context context = null;

    public PullAndRefreshTask(PullToRefreshListView listView, int pullState, BaseAdapter baseAdapter, Context context,int current){
        super();
        this.baseAdapter = baseAdapter;
        this.listView = listView;
        this.pullState = pullState;
        this.context = context;
        this.currentPage = current;
    }

    @Override
    protected ArrayList<ListViewItemBean> doInBackground(Void... params) {
        try {
            if(pullState == 1 ){//下拉刷新
                Map<String,String> map =  new HashMap<String,String>(){{put("pages",currentPage+"");}};

                    mylist = myHttpUtil.getGson().fromJson(
                            myHttpUtil.synPostJsonStr("/javamg/commodity/mobileCommodity/list",map),
                            new TypeToken<ArrayList<ListViewItemBean>>(){}.getType());

            }else if (pullState ==2 ){
                Map<String,String> map =  new HashMap<String,String>(){{put("pages",currentPage+1+"");}};
                mylist = myHttpUtil.getGson().fromJson(
                        myHttpUtil.synPostJsonStr("/javamg/commodity/mobileCommodity/list",map),
                        new TypeToken<ArrayList<ListViewItemBean>>(){}.getType());

            }else {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(mylist.size() > 0){
            HomeFragment.currentPages = mylist.get(0).getPages()+1;
        }
        return mylist;
    }

    @Override
    protected void onPostExecute(ArrayList<ListViewItemBean> list) {
        if(pullState == 1){//下拉刷新
            ((myListViewAdapter)baseAdapter).removeAll();
            for (ListViewItemBean bean : list) {
                ((myListViewAdapter)baseAdapter).addFirst(bean);
            }
        }else if (pullState ==2 ){
            for (ListViewItemBean bean : list) {
                ((myListViewAdapter)baseAdapter).addLast(bean);
            }
        }else {

        }
        if(list.size() >0){
            baseAdapter.notifyDataSetChanged();
        }else {
            Toast.makeText(context,"没有数据！",Toast.LENGTH_SHORT).show();
        }

        listView.onRefreshComplete();   //这个必须执行********************
        super.onPostExecute(list);
    }
}
