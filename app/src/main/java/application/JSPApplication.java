package application;

import android.app.Application;
import android.content.Context;
import android.util.Log;

public class JSPApplication extends Application {

    private static JSPApplication mInstance;
    private String driverLoginName;
    private String driveName;

    public String getDriveName() {
        return driveName;
    }

    public void setDriveName(String driveName) {
        this.driveName = driveName;
    }

    public String getDriverLoginName() {
        return driverLoginName;
    }

    public void setDriverLoginName(String driverLoginName) {
        this.driverLoginName = driverLoginName;
    }

    /**
     * 获取context
     * @return
     */
    public static Context getInstance() {
        if (mInstance == null) {
            mInstance = new JSPApplication();
        }
        return mInstance;
    }
    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        Log.e("application", "onCreate");
        //捕获异常的类
//		CrashHandler.getInstance().init(this);
    }
}
