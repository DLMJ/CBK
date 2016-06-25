package com.example.administrator.httplib;

/**字符串请求
 * Created by Administrator on 2016/6/22 0022.
 */
public class StringRequest extends Request<String>{
    public StringRequest(String url, Method method, Callback callback) {
        super(url, method, callback);
    }



    @Override
    public void dispatchContent(byte[] content) {
        onResponse(new String(content));
    }

}
