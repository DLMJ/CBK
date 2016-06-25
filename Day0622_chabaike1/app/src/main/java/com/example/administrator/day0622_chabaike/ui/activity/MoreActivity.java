package com.example.administrator.day0622_chabaike.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.administrator.day0622_chabaike.R;

/**更多页面
 * Created by Administrator on 2016/6/23 0023.
 */
public class MoreActivity extends Activity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        findViewById(R.id.more_tv_collect).setOnClickListener(this);//页面收藏
        findViewById(R.id.more_iv_history).setOnClickListener(this);//浏览历史
        findViewById(R.id.more_tv_copyright).setOnClickListener(this);//关于版本信息

    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        switch (v.getId()){
            case R.id.more_tv_collect:
                intent.setClass(MoreActivity.this,CollectActivity.class);
                break;
            case R.id.more_iv_history:
                intent.setClass(MoreActivity.this,HistoryActivity.class);
                break;
            case R.id.more_tv_copyright:
                intent.setClass(MoreActivity.this,CopyrightActivity.class);
                break;
        }
        startActivity(intent);

    }
}
