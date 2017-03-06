package com.xjm.works.sht.modules.mian.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.wolearn.mvpframelib.base.BaseView;
import com.wolearn.mvpframelib.frame.MvpActivity;
import com.xjm.works.sht.R;
import com.xjm.works.sht.customview.MyViewPager;
import com.xjm.works.sht.modules.homePage.ui.HomeFragment;
import com.xjm.works.sht.modules.joinPage.ui.JoinFragment;
import com.xjm.works.sht.modules.mePage.ui.MeFragment;
import com.xjm.works.sht.modules.mian.contract.MainContract;
import com.xjm.works.sht.modules.mian.presenter.MainPresenter;
import com.xjm.works.sht.myadapter.myViewPagerAdapter;

import java.util.ArrayList;

public class MainActivity extends MvpActivity<MainPresenter> implements MainContract.View {

    private RadioButton rbt_home;
    private RadioButton rbt_list;
    private RadioButton rbt_me;
    private RadioGroup rg_button_meun;
    private MyViewPager vp_main;

    //页面当前位置
    private int position = 0;
    //底部点击时active图片列表
    private int[] btActiveList = new int[]{R.drawable.bag_active,R.drawable.list_active,R.drawable.avatar_active};
    //底部未点击时的图片列表
    private int[] btNoActiveList = new int[]{R.drawable.bag,R.drawable.list,R.drawable.avatar};
    //定义三个fragment
    Fragment fragmentHome = null;
    Fragment fragmentJoin = null;
    Fragment fragmentMe = null;
    //定义fragment列表
    ArrayList<Fragment> fragmentList = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        initLisenter();
    }

    @Override
    public BaseView getBaseView() {
        return this;
    }

    //初始化控件
     private void initView(){
         //变量初始化
         this.vp_main = (MyViewPager) findViewById(R.id.vp_main);
         this.rg_button_meun = (RadioGroup) findViewById(R.id.rg_buttonmeun);
         this.rbt_me = (RadioButton) findViewById(R.id.rbt_me);
         this.rbt_list = (RadioButton) findViewById(R.id.rbt_list);
         this.rbt_home = (RadioButton) findViewById(R.id.rbt_home);
         this.fragmentHome = new HomeFragment();
         this.fragmentJoin = new JoinFragment();
         this.fragmentMe = new MeFragment();
         fragmentList = new ArrayList<Fragment>(){{add(fragmentHome);add(fragmentJoin);add(fragmentMe);}};

         //初始化vp_main
         vp_main.setAdapter(new myViewPagerAdapter(getSupportFragmentManager(),fragmentList));
         vp_main.setScoll(false);//禁止滑动
         //设置开始界面
         btActive(0);

    }
    private void initLisenter(){
        rg_button_meun.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
                if(!radioButton.isChecked()){
                    return;
                }
                switch (checkedId){
                    case R.id.rbt_home :
                        position = 0;
                        break;
                    case R.id.rbt_list :
                        position = 1;
                        break;
                    case R.id.rbt_me :
                        position = 2;
                        break;
                }
                btActive(position);
            }
        });
    }


    @Override
    public void btActive(@NonNull int whichButtonId) {
        RadioButton childAt = (RadioButton) rg_button_meun.getChildAt(whichButtonId);
        vp_main.setCurrentItem(whichButtonId,true);

        position = whichButtonId;
        childAt.setChecked(true);
    }
}
