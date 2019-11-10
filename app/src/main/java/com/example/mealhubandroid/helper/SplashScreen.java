package com.example.mealhubandroid.helper;



import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mealhubandroid.MenuReaderActivity;
import com.example.mealhubandroid.R;

/**
 * Created by Kuncoro on 25/09/2017.
 * www.dedykuncoro.com
 */

public class SplashScreen extends AppCompatActivity {

    PermissionHelper permissionHelper;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        permissionHelper = new PermissionHelper(this);

        checkAndRequestPermissions();
    }

    private boolean checkAndRequestPermissions() {
        permissionHelper.permissionListener(new PermissionHelper.PermissionListener() {
            @Override
            public void onPermissionCheckDone() {
                intent = new Intent(SplashScreen.this, MenuReaderActivity.class);
                startActivity(intent);
                finish();
            }
        });

        permissionHelper.checkAndRequestPermissions();

        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permissionHelper.onRequestCallBack(requestCode, permissions, grantResults);
    }

}