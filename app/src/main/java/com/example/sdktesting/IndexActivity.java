package com.example.sdktesting;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhoufeng
 * @date 2019/7/21
 * @moduleName
 */
public class IndexActivity extends Activity {


//        	private static final String mHomeUrl = "https://sdk.ketianyun.com/showcase/5.html";
//        	private static final String mHomeUrl = "https://sdk.ketianyun.com/sdk/join.html";
//    private String mHomeUrl = "https://sdk.ketianyun.com/sdk/testVideo.html";
//        	private static final String mHomeUrl = "https://sdk.ketianyun.com/sdk/camera.html";
//	private static final String mHomeUrl = "https://webrtc.github.io/samples/src/content/devices/input-output/";
    private static final String mHomeUrl = "https://appr.tc/";
//    private static final String mHomeUrl = "https://appr.tc/r/roomid?vrc=H264&debug=loopback&vsc=H264";
//    private static final String mHomeUrl = "http://10.10.10.239:8000/testCamera.html";
//    private static final String mHomeUrl = "https://catsanddogs.oss-cn-beijing.aliyuncs.com/faceTrance3.html";

    private EditText websiteEdittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index_activity);

        findViewById(R.id.btn1).setOnClickListener(onClickListener);
        findViewById(R.id.btn2).setOnClickListener(onClickListener);
        findViewById(R.id.btn3).setOnClickListener(onClickListener);

        websiteEdittext = this.findViewById(R.id.website);
        websiteEdittext.setText(mHomeUrl);

        requestUserPermission();
    }


    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn1:
                    start(WebViewActivity.class);
                    break;
                case R.id.btn2:
                    start(BrowserActivity.class);
                    break;
                case R.id.btn3:
//                    start(XCrossWalkWebviewActivity.class);
                    break;
                default:
                    break;
            }
        }
    };

    private void start(Class activity) {
        Intent intent = new Intent(this, activity);
        intent.putExtra("url", websiteEdittext.getText().toString().trim());
        startActivity(intent);
    }

    private void requestUserPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            List<String> permissions = new ArrayList<String>();

            int hasCameraPermission = checkSelfPermission(Manifest.permission.CAMERA);
            if (hasCameraPermission != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.CAMERA);
            }

            int hasAudioPermission = checkSelfPermission(Manifest.permission.RECORD_AUDIO);
            if (hasAudioPermission != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.RECORD_AUDIO);
            }
            int hasWritePermission = checkSelfPermission(Manifest.permission.WRITE_SETTINGS);
            if (hasWritePermission != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.WRITE_SETTINGS);
            }

            int hasReadPhonePermission = checkSelfPermission(Manifest.permission.READ_PHONE_STATE);
            if (hasReadPhonePermission != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.READ_PHONE_STATE);
            }
            int hasWriteExteralPermission = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (hasWriteExteralPermission != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }

            int hasreadExteralPermission = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
            if (hasreadExteralPermission != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            }

            if (!permissions.isEmpty()) {
                String[] temps = new String[permissions.size()];
                int i = 0;
                for (String value : permissions) {
                    temps[i] = value;
                    i++;
                }
                requestPermissions(temps, 111);
            }
        }
    }
}
