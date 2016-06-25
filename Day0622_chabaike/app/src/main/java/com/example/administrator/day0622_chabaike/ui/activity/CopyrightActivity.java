package com.example.administrator.day0622_chabaike.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.example.administrator.day0622_chabaike.R;

/**版权信息
 * Created by Administrator on 2016/6/25 0025.
 */
public class CopyrightActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_copyright);
    }
}
