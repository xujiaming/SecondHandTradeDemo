package com.xjm.works.sht.utils;

import android.net.Uri;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/11/1 0001.
 */

public class myHttpUtil {
    private static  OkHttpClient client = null;
    private static Gson gson = new Gson();
//    private static String host = "http://192.168.191.1:8080";
    private static String host = "http://10.0.2.2:8080";



    private myHttpUtil(){}
    public static final OkHttpClient getClient(){
        if(client == null){
            client = new OkHttpClient.Builder().readTimeout(30,TimeUnit.SECONDS).build();
        }
        return client;
    }

    public static final Gson getGson(){
        return gson;
    }
    public static final String synGetJsonStr(String addUrl) throws IOException {
        String result = null;
        Request request = new Request.Builder().url(Uri.encode(host+addUrl)).build();
        Call call = getClient().newCall(request);
        Response response = call.execute();
        if(!response.isSuccessful()) throw new IOException("请求失败"+response);
        result = response.body().toString();
        return result;
    }
    public static final void asynGetJsonStr(String addUrl, Callback callback) throws IOException {
        String result = null;
        Request request = new Request.Builder().url(Uri.encode(host+addUrl)).build();
        Call call = getClient().newCall(request);
        call.enqueue(callback);

    }
    public static final String synPostJsonStr(String addUrl, Map<String,String> map) throws IOException {
        String result = null;
        RequestBody requestBody = null;
        FormBody.Builder builder = new FormBody.Builder();
        for (String key:map.keySet()) {
            builder.add(key,map.get(key));
        }
        requestBody = builder.build();

        Request request = new Request.Builder().url(host+addUrl).post(requestBody).build();

        Call call = getClient().newCall(request);

        Response response = call.execute();

        if(!response.isSuccessful()) throw new IOException("请求失败"+response);
        result = response.body().string();
        return result;
    }
    // TODO: 2016/11/6 0006
//    public static final void asynPostJsonStr(String addUrl, Callback callback) throws IOException {
//        String result = null;
//        Request request = new Request.Builder().url(Uri.encode(host+addUrl)).build();
//        Call call = getClient().newCall(request);
//        call.enqueue(callback);
//
//    }
}
