package com.hj.backwordtool.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

import com.hj.backwordtool.utils.Logger;

/**
 * Created by Administrator on 2014/8/12.
 */
public abstract class BaseActivity extends FragmentActivity {
    protected Logger log = Logger.getLogger();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }
}