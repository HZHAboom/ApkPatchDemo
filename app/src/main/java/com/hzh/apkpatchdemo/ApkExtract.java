package com.hzh.apkpatchdemo;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.util.Log;

import java.io.File;

/**
 * Created by Administrator on 2017/11/27 0027.
 */

public class ApkExtract {
    public static String extract(Context context) {
        context = context.getApplicationContext();
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        String apkPath = applicationInfo.sourceDir;
        Log.d("hzh", apkPath);
        return apkPath;
    }
    public static void install(Context context, String apkPath) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        File file = new File(apkPath);
        Uri uri;
        if (Build.VERSION.SDK_INT >= 24){
            i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            uri = FileProvider.getUriForFile(context,"com.hzh.apkpatchdemo.fileprovider",file);
        }else{
            uri = Uri.fromFile(file);
        }
        i.setDataAndType(uri,
                "application/vnd.android.package-archive");
        context.startActivity(i);
    }
}