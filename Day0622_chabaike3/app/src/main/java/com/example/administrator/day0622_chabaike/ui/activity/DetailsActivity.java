package com.example.administrator.day0622_chabaike.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.day0622_chabaike.R;
import com.example.administrator.httplib.HttpHelper;
import com.example.administrator.httplib.Request;
import com.example.administrator.httplib.SQLiteDataBaseHelper;
import com.example.administrator.httplib.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;

/**详情页
 * Created by Administrator on 2016/6/22 0022.
 */
public class DetailsActivity extends Activity {
    private TextView tv_title, tv_day,tv_message;
    private ImageView iv_load,iv_collect,iv_share;
    public static JSONObject jsonObject;
    public static long id;
    public static  String strtime;
    public static String title;
    public static String count;
    SQLiteDataBaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent intent = getIntent();
        id = intent.getLongExtra("id", 0);
        Toast.makeText(this, id + "---------", Toast.LENGTH_LONG).show();
        String url = "http://www.tngou.net/api/info/show?id=" + id;
        initView();
        initData(url);
    }

    private void initView() {
        tv_title= (TextView) findViewById(R.id.details_tv_title);
        tv_day= (TextView) findViewById(R.id.details_tv_day);
        tv_message= (TextView) findViewById(R.id.details_tv_message);
        iv_load= (ImageView) findViewById(R.id.details_iv_load);

    }

    private void initData(final String url) {
        db = new SQLiteDataBaseHelper(this, "tea");
        StringRequest req = new StringRequest(url, Request.Method.GET,
                new Request.Callback<String>(){
                    @Override
                    public void onEror(Exception e) {

                    }

                    @Override
                    public void onResonse(String response) {
                        try {
                            jsonObject = new JSONObject(response);
                            title=jsonObject.optString("title");
                            long time =jsonObject.getLong("time");
                            count=jsonObject.getLong("count")+"";
                            strtime = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss").format(time);
                            final String message=jsonObject.optString("message");
                            final String imgPath=jsonObject.optString("img");
                            final String img="http://www.tngou.net/api/info/show?id="+imgPath;
                            Log.i("ssssssssssssssss", img);


                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    tv_title.setText(title);
                                    tv_day.setText(strtime);
                                    tv_message.setText(message);

                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
            HttpHelper.addRequest(req);
        String sql = "INSERT INTO tb_teacontents(_id,title,count,time,type) values (?,?,?,?,?)";
        boolean flag = db.updataData(sql, new String[] {
                "http://www.tngou.net/api/info/show?id="+id, title,
                count, strtime, "1" });
    }

    //点击事件
    public void clickButton(View view) {
        switch (view.getId()) {
            case R.id.details_iv_collect:
			/* 收藏 */
                String sql = "UPDATE tb_teacontents SET type = ? WHERE _id = ?";
                if(jsonObject!=null){
                    String type = "2";//把被浏览过改为被收藏了
                    String id = jsonObject.optString("id")+"";
                    db.updataData(sql, new String[] { type, id });
                    Toast.makeText(DetailsActivity.this, "收藏成功", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.details_iv_share:
			/* 分享 */
                Intent intent = new Intent(DetailsActivity.this,ShareActivity.class);
                startActivity(intent);
                //overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);// 下往上推出效果
                break;

            default:
                break;
        }
    }
}