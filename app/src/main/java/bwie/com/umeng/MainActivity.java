package bwie.com.umeng;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 分享
     */
    private Button mFx;
    /**
     * 获取
     */
    private Button mHq;
    /**
     * 权限
     */
    private Button mQx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void ShareWeb(int thumb_img) {
        UMImage thumb = new UMImage(this, thumb_img);
        UMWeb web = new UMWeb("http://www.baidu.com");
        web.setThumb(thumb);
        web.setDescription("测试程序");
        web.setTitle("1510d");
        new ShareAction(this).withMedia(web).setPlatform(SHARE_MEDIA.QQ).setCallback(shareListener).share();
    }

    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
            Toast.makeText(MainActivity.this, "开始了", Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(MainActivity.this, "成功了", Toast.LENGTH_LONG).show();

        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {

            Toast.makeText(MainActivity.this, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {

            Toast.makeText(MainActivity.this, "取消了", Toast.LENGTH_LONG).show();

        }
    };


    //
    UMAuthListener authListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            String temp = "";
            for (String key : data.keySet()) {
                temp = temp + key + " : " + data.get(key) + "\n";
            }
            Toast.makeText(MainActivity.this, temp, Toast.LENGTH_LONG).show();

        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {

            Toast.makeText(MainActivity.this, "错误", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(MainActivity.this, "取消", Toast.LENGTH_LONG).show();
        }
    };

    private void initView() {
        mFx = (Button) findViewById(R.id.fx);
        mFx.setOnClickListener(this);
        mHq = (Button) findViewById(R.id.hq);
        mHq.setOnClickListener(this);
        mQx = (Button) findViewById(R.id.qx);
        mQx.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fx:
                ShareWeb(R.mipmap.ic_launcher);
                break;
            case R.id.hq:
                UMShareAPI.get(MainActivity.this).getPlatformInfo(MainActivity.this, SHARE_MEDIA.QQ, authListener);
                break;
            case R.id.qx:
                    UMShareAPI.get(MainActivity.this).getPlatformInfo(MainActivity.this, SHARE_MEDIA.QQ, authListener);
                break;
        }
    }
}
