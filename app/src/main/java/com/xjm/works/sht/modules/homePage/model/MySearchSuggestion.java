package com.xjm.works.sht.modules.homePage.model;

import android.annotation.SuppressLint;
import android.os.Parcel;

import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.wolearn.mvpframelib.frame.MvpModel;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/2 0002.
 */
@SuppressLint("ParcelCreator")
public class MySearchSuggestion  extends MvpModel implements SearchSuggestion{
    String str = null;
    public MySearchSuggestion( String str){
        this.str = str;
    }
    @Override
    public String getBody() {
        return str;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(str);
    }
}
