package com.hzh.apkpatchdemo;

/**
 * Created by Administrator on 2017/11/27 0027.
 */

public class BsPatchUtil {
    static {
        System.loadLibrary("bspatch");
    }

    public static native int patch(String oldApkPath, String newApkPath, String patchPath);
}
