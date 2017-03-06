package com.xjm.works.sht.modules.mian.contract;

import android.support.annotation.NonNull;

import com.wolearn.mvpframelib.base.BasePresenter;
import com.wolearn.mvpframelib.base.BaseView;

/**
 * Created by Administrator on 2016/10/30 0030.
 */

public class MainContract {
    public interface View extends BaseView{
        void btActive(@NonNull int whichButtonId);


    }
    public interface Presenter extends BasePresenter{

    }
}
