package com.xjm.works.sht.modules.joinPage.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wolearn.mvpframelib.base.BaseView;
import com.wolearn.mvpframelib.frame.MvpFragment;
import com.xjm.works.sht.R;
import com.xjm.works.sht.modules.joinPage.contract.JoinContract;
import com.xjm.works.sht.modules.joinPage.presenter.JoinPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class JoinFragment extends MvpFragment<JoinPresenter> implements JoinContract.View {


    public JoinFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_join,container,false);
        return view;
    }

    @Override
    public BaseView getBaseView() {
        return this;
    }

}
