package com.example.administrator.httplib;

/**
 * 网络请求助手
 * Created by Administrator on 2016/6/22 0022.
 */
public class HttpHelper {
    private static HttpHelper Instance;
    private RequestQueue mQueue;



    //单例模式，保证网络请求只new一次
    private static HttpHelper getInstance(){
        if(Instance==null){
            synchronized (HttpHelper.class){
                if(Instance==null){
                    Instance=new HttpHelper();
                }
            }
        }
        return Instance;
    }
    private HttpHelper(){
        mQueue=new RequestQueue();
    }
    public static void addRequest(Request request){
        getInstance().mQueue.addRequest(request);
    }
}
