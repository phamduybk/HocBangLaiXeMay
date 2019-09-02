package vn.com.lvvu.hocbanglaixemay;

import android.app.Application;
import android.content.Context;

/**
 * Created by levan on 7/17/2019.
 */

public class MyApplication extends Application {

    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();

        /* If you has other classes that need context object to initialize when application is created,
         you can use the appContext here to process. */
    }

    public static Context getAppContext() {
        return appContext;
    }

}
