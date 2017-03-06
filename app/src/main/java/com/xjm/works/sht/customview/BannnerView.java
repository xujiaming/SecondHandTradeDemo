package com.xjm.works.sht.customview;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.xjm.works.sht.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/11/3 0003.
 */

public class BannnerView extends FrameLayout {

    //轮播图图片数量
    private int IMAGE_COUNT = 0;
    //自动轮播的时间间隔
    private int TIME_INTERVAL = 5;


    //自动轮播启用开关
    private boolean isAutoPlay = true;


    //放轮播图片的ImageView 的list
    private List<ImageView> imageViewsList;
    //放圆点的View的list
    private List<ImageView> dotViewsList;

    LinearLayout linearLayout;
    private ViewPager vp_banner;
    //当前轮播页
    private int currentItem  = 0;
    //定时任务
    private ScheduledExecutorService scheduledExecutorService;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            vp_banner.setCurrentItem(currentItem);
        }
    };


    public BannnerView(Context context) {
        this(context,null);

    }

    public BannnerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannnerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData(context);
    }


    //初始化组件
    private void initData(Context context){
        LayoutInflater.from(context).inflate(R.layout.banner,this,true);
        vp_banner = (ViewPager) findViewById(R.id.vp_banner);
        linearLayout = (LinearLayout) findViewById(R.id.ll_banner_dot);
        vp_banner.setFocusable(true);

    }

    //初始化数据和事件
    public void init(List<ImageView> imageViewsList) {
        IMAGE_COUNT = imageViewsList.size();

        this.imageViewsList = imageViewsList;

        dotViewsList = new ArrayList<ImageView>();

//        for (int i = 0; i < imageViewsList.size(); i++) {
//            View view = View.inflate(getContext(), R.layout.dot,null);
//            view.setBackground(getResources().getDrawable(R.drawable.dot_normal));
//            dotViewsList.add(view);
//            linearLayout.addView(view);
//        }


        for (int i = 0; i < imageViewsList.size(); i++) {
            ImageView imageView = new ImageView(getContext());
            //掌握**********************
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(10, 10);
            params.setMargins(5, 0, 5, 0);
            imageView.setLayoutParams(params);
            //************************
            imageView.setBackgroundResource(R.drawable.dot_normal);

            dotViewsList.add(imageView);
            //把指示作用的原点图片加入底部的视图中
            linearLayout.addView(dotViewsList.get(i));

        }



        vp_banner.setAdapter(new myAdapter());

        vp_banner.addOnPageChangeListener(new MyPageChangeListener());

        if(isAutoPlay){
            startPlay();
        }
    }


    /**
     * 填充ViewPager的页面适配器
     * @xujiaiming
     */
    private class myAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return imageViewsList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager)container).removeView(imageViewsList.get(position));
//            super.destroyItem(container, position, object);

        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ((ViewPager)container).addView(imageViewsList.get(position));
            return imageViewsList.get(position);
        }
    }

    /**
     * ViewPager的监听器
     * 当ViewPager中页面的状态发生改变时调用
     * @author 许加明
     */
    private class MyPageChangeListener implements ViewPager.OnPageChangeListener {


        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            currentItem = position;
            for(int i=0;i < dotViewsList.size();i++){
                if(i == position){
                    ((View)dotViewsList.get(position)).setBackgroundResource(R.drawable.dot_focused);
                }else {
                    ((View)dotViewsList.get(i)).setBackgroundResource(R.drawable.dot_normal);
                }
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            switch (state) {
                case 1:// 手势滑动，空闲中
                    isAutoPlay = false;
                    break;
                case 2:// 界面切换中
                    isAutoPlay = true;
                    break;
                case 0:// 滑动结束，即切换完毕或者加载完毕
                    // 当前为最后一张，此时从右向左滑，则切换到第一张
                    if (vp_banner.getCurrentItem() == vp_banner.getAdapter().getCount() - 1 && !isAutoPlay) {
                        vp_banner.setCurrentItem(0);
                    }
                    // 当前为第一张，此时从左向右滑，则切换到最后一张
                    else if (vp_banner.getCurrentItem() == 0 && !isAutoPlay) {
                        vp_banner.setCurrentItem(vp_banner.getAdapter().getCount() - 1);
                    }
                    isAutoPlay = true;
                    break;
            }
        }
    }


    /**
     *执行轮播图切换任务
     *@author caizhiming
     */
    private class BannerViewTask implements Runnable{

        @Override
        public void run() {
            synchronized (vp_banner){
                if(isAutoPlay) {
                    currentItem = (currentItem + 1) % imageViewsList.size();
                    handler.obtainMessage().sendToTarget();
                }
            }
        }
    }

    /**
     * 开始轮播图切换
     */
    private void startPlay(){
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new BannerViewTask(),1,4, TimeUnit.SECONDS);
    }

    /**
     * 停止轮播图切换
     */
    private void stopPlay(){
        scheduledExecutorService.shutdown();
    }

    public boolean isAutoPlay() {
        return isAutoPlay;
    }

    public void setAutoPlay(boolean autoPlay) {
        isAutoPlay = autoPlay;
    }
}
