package com.xjm.works.sht.modules.mePage.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wolearn.mvpframelib.base.BaseView;
import com.wolearn.mvpframelib.frame.MvpFragment;
import com.xjm.works.sht.R;
import com.xjm.works.sht.modules.mePage.contract.MeContract;
import com.xjm.works.sht.modules.mePage.presenter.MePresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends MvpFragment<MePresenter> implements MeContract.View {


    public MeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreateView(inflater,container,savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_me,container,false);
        return view;
    }

    @Override
    public BaseView getBaseView() {
        return this;
    }

}
