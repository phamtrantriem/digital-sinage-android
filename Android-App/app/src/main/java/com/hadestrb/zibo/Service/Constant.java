package com.hadestrb.zibo.Service;

import android.annotation.SuppressLint;
import android.content.Context;
import android.provider.Settings;

public interface Constant {
    String CLIENT_VERSION = "1.0";
    String CLIENT_TYPE = "mobile";
    String OPERATING_SYSTEM = "android";
    String MAC_ADDRESS = "02:00:00:00:00";

    @SuppressLint("HardwareIds")
    static String getHardwareCode(Context context) {
        return Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }
}
