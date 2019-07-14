package com.zym.androidtagcloud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private TagCloudView mTagCloudView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTagCloudView = findViewById(R.id.tag_cloud_view);
        initData();
    }

    private void initData() {
        mTagCloudView.addTag("JAVA");
        mTagCloudView.addTag("Object C");
        mTagCloudView.addTag("Python");
        mTagCloudView.addTag("HTML");
        mTagCloudView.addTag("C");
        mTagCloudView.addTag("C++");
        mTagCloudView.addTag("C#");
        mTagCloudView.addTag("Kotlin");
        mTagCloudView.addTag("Swift");
        mTagCloudView.addTag("Ruby");
        mTagCloudView.addTag("Lisp");
        mTagCloudView.addTag("Lisp");
        mTagCloudView.addTag("Lisp");
        mTagCloudView.addTag("Lisp");
        mTagCloudView.addTag("Lisp");
        mTagCloudView.addTag("Lisp");
        mTagCloudView.addTag("Lisp");

        mTagCloudView.post(new Runnable() {
            @Override
            public void run() {
                int width = mTagCloudView.getWidth();
                int height = mTagCloudView.getHeight();
                Log.i(TAG, "width = " + width + ", height = " + height);
                mTagCloudView.setShowRect(0, 0, width, height);
                mTagCloudView.start();
            }
        });

    }
}
