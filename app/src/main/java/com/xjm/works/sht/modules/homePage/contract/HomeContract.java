package com.xjm.works.sht.modules.homePage.contract;

import android.content.Context;
import android.widget.ImageView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.wolearn.mvpframelib.base.BasePresenter;
import com.wolearn.mvpframelib.base.BaseView;
import com.xjm.works.sht.modules.homePage.model.MySearchSuggestion;
import com.xjm.works.sht.myadapter.myListViewAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/10/30 0030.
 */

public class HomeContract {
    public interface View extends BaseView{

    }
    public interface Presenter extends BasePresenter{
        void refreshFunc(PullToRefreshBase refreshView, int mode, myListViewAdapter listViewAdapter, Context context,int currentPage);//刷新事件方法
        List<MySearchSuggestion> getSuggestion(String oldQuery,String newQuery);
        List<ImageView> getImageList(Context context);
    }
}
