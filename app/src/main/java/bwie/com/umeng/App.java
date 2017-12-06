package bwie.com.umeng;

import android.app.Application;

import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

/**
 * Created by 不将就 on 2017/12/6.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Config.DEBUG=true;
        UMShareAPI.get(this);

        PlatformConfig.setWeixin("","");
        PlatformConfig.setQQZone("1106391021","sh0sMaki9zivXdJU");
        PlatformConfig.setSinaWeibo("","","");
    }
}
