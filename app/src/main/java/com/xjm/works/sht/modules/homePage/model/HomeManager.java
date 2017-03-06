package com.xjm.works.sht.modules.homePage.model;

import android.widget.ImageView;

import com.wolearn.mvpframelib.frame.MvpModel;
import com.xjm.works.sht.utils.myHttpUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/10/30 0030.
 */

public class HomeManager extends MvpModel {


    public List<ImageView> getViewListFromInt(){
        List<ImageView> list = null;
        try {
            String listStr = myHttpUtil.synPostJsonStr("/javamg/commodity/mobileCommodity/getPhoto", new HashMap<String, String>());

        } catch (IOException e) {
            e.printStackTrace();
        }
        // TODO: 2016/11/6 0006  
        return list;
    }
}
