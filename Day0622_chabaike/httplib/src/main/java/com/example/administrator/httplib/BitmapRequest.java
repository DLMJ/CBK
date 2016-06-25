package com.example.administrator.httplib;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**图片请求
 * Created by Administrator on 2016/6/22 0022.
 */
public class BitmapRequest extends Request<Bitmap>{
    public BitmapRequest(String url, Method method, Callback callback) {
        super(url, method, callback);
    }

    @Override
    public void dispatchContent(byte[] content) {
        Bitmap bm = BitmapFactory.decodeByteArray(content, 0, content.length);
        onResponse(bm);
    }
}
