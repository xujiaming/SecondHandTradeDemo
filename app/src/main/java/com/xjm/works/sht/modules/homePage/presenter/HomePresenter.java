package com.xjm.works.sht.modules.homePage.presenter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.wolearn.mvpframelib.frame.MvpPresenter;
import com.xjm.works.sht.R;
import com.xjm.works.sht.modules.homePage.contract.HomeContract;
import com.xjm.works.sht.modules.homePage.model.HomeManager;
import com.xjm.works.sht.modules.homePage.model.MySearchSuggestion;
import com.xjm.works.sht.myadapter.myListViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/30 0030.
 */

public class HomePresenter extends MvpPresenter<HomeManager,HomeContract.View> implements HomeContract.Presenter {

    //mode=1下拉刷新
    @Override
    public void refreshFunc(PullToRefreshBase refreshView, int mode, myListViewAdapter listViewAdapter, Context context,int currentPage) {
        PullToRefreshListView refreshListView = (PullToRefreshListView)refreshView;
        PullAndRefreshTask task = new PullAndRefreshTask(refreshListView,mode,listViewAdapter,context,currentPage);
        task.execute();
    }

    @Override
    public List<MySearchSuggestion> getSuggestion(String oldQuery,String newQuery) {
        ArrayList<MySearchSuggestion> list = new ArrayList<>();
        list.add(new MySearchSuggestion(newQuery));
        list.add(new MySearchSuggestion(oldQuery));
        return list;
    }

    @Override
    public List<ImageView> getImageList(Context context) {

        List<ImageView> list = null;
        //此处应该从网络获得这里是测试数据
        list = new ArrayList<ImageView>();
        for (int i = 0; i < 3; i++) {
            ImageView imageView = new ImageView(context,null);
            imageView.setImageResource(R.drawable.ic_launcher_web);
            list.add(imageView);
        }
        return list;
    }
}
