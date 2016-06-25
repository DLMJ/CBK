package com.example.administrator.httplib;

import java.util.HashMap;

/**请求类
 * Created by Administrator on 2016/6/22 0022.
 */
abstract public class Request<T> {
    private String url;//网址
    private Method method;//请求方法（分为get和post）
    private Callback callback;//结果回调
    public Request(String url, Method method,Callback callback) {
        this.url = url;
        this.method = method;
        this.callback = callback;
    }


    public  enum Method{
        GET,POST
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }



    public Callback getCallback() {
        return callback;
    }



    public void onError(Exception e) {
        callback.onEror(e);
    }

    protected void onResponse(T res){
        callback.onResonse(res);
    }


    public  interface  Callback<T>{

        void onEror(Exception e);
        void onResonse(T response);
    }


    public HashMap<String,String> getPostParams(){
        return null;
    }



    abstract public void dispatchContent(byte[] content);
}
