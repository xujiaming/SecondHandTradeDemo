package com.xjm.works.sht.modules.homePage.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.SearchSuggestionsAdapter;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.wolearn.mvpframelib.base.BaseView;
import com.wolearn.mvpframelib.frame.MvpFragment;
import com.xjm.works.sht.R;
import com.xjm.works.sht.customview.BannnerView;
import com.xjm.works.sht.modules.homePage.contract.HomeContract;
import com.xjm.works.sht.modules.homePage.model.ListViewItemBean;
import com.xjm.works.sht.modules.homePage.presenter.HomePresenter;
import com.xjm.works.sht.myadapter.myListViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends MvpFragment<HomePresenter> implements HomeContract.View{

    public static int currentPages = 1;
    //可以上拉和下拉的ListView
    private PullToRefreshListView homepullrefreshlist;
    private myListViewAdapter listViewAdapter;
    //悬浮式搜索框View
    private FloatingSearchView floatingSearchView;
    //广告条
    private BannnerView bannnerView;
    View view = null;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreateView(inflater,container,savedInstanceState);
        view = inflater.inflate(R.layout.fragment_home,container,false);
        initViews();

        return view;
    }

    //listView滑动监听事件方法
    PullToRefreshBase.OnRefreshListener2 refreshListener = new PullToRefreshBase.OnRefreshListener2() {
        @Override
        public void onPullDownToRefresh(PullToRefreshBase refreshView) {
            mPresenter.refreshFunc(refreshView,1,listViewAdapter,getContext(),currentPages);
        }

        @Override
        public void onPullUpToRefresh(PullToRefreshBase refreshView) {
            mPresenter.refreshFunc(refreshView,2,listViewAdapter,getContext(),currentPages+1);
        }

    };

    //输入框变化的事件监听方法
    FloatingSearchView.OnQueryChangeListener queryChangeListener = new FloatingSearchView.OnQueryChangeListener() {
        @Override
        public void onSearchTextChanged(String oldQuery, String newQuery) {
            floatingSearchView.swapSuggestions(mPresenter.getSuggestion(oldQuery,newQuery));
        }
    };

    //点击箭头查询的监听


    private void initViews(){
        this.homepullrefreshlist = (PullToRefreshListView) view.findViewById(R.id.home_pull_refresh_list);
        this.floatingSearchView = (FloatingSearchView) view.findViewById(R.id.search_mybar);
        this.bannnerView = (BannnerView) view.findViewById(R.id.banner);

        //初始化广告条
        bannnerView.init(getBannerList());
        //以下设置ListView
        this.listViewAdapter = new myListViewAdapter(getContext(),new ArrayList<ListViewItemBean>());
        homepullrefreshlist.setAdapter(listViewAdapter);
        homepullrefreshlist.setOnRefreshListener(refreshListener);
        //初始化首页数据
        mPresenter.refreshFunc(homepullrefreshlist,1,listViewAdapter,getContext(),currentPages);


        //以下设置搜索框
        floatingSearchView.setOnQueryChangeListener(queryChangeListener);
        floatingSearchView.setOnBindSuggestionCallback(new SearchSuggestionsAdapter.OnBindSuggestionCallback() {
            @Override
            public void onBindSuggestion(View suggestionView, ImageView leftIcon, TextView textView, SearchSuggestion item, int itemPosition) {
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(),((TextView)v).getText(),Toast.LENGTH_SHORT).show();
                    }
                });
                leftIcon.setImageResource(R.drawable.little_icon);
            }
        });

    }

    //得到广告banner
    private List<ImageView> getBannerList(){
        // TODO: 2016/11/6 0006
        return mPresenter.getImageList(getContext());
    }

    @Override
    public BaseView getBaseView() {
        return this;
    }


}
