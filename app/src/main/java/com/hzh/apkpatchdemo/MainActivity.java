package com.hzh.apkpatchdemo;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void doBspatch() {
        final File destApk = new File(Environment.getExternalStorageDirectory(), "new.apk");
        final File patch = new File(Environment.getExternalStorageDirectory(), "PATCH.patch");

        //一定要检查文件都存在
        BsPatchUtil.patch(ApkExtract.extract(MainActivity.this),
                destApk.getAbsolutePath(),
                patch.getAbsolutePath());

        if (destApk.exists())
            ApkExtract.install(MainActivity.this, destApk.getAbsolutePath());
    }

    public void onClick(View view) {
        if (ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
        } else {
            doBspatch();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 2) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                doBspatch();
            }
        }
    }
}
