package com.example.myapplication;


import android.annotation.TargetApi;

import android.app.Activity;

import android.content.Context;

import android.os.Build;

import android.support.v4.content.ContextCompat;

import android.view.View;

import android.view.ViewGroup;







public class StatusBarCompat {

    private static final int INVALID_VAL = -1;



    //判断版本

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)

    public static View compat(Activity activity, int statusColor) {

        //默认颜色

        int color = ContextCompat.getColor(activity, R.color.colorPrimaryDark);

        //若果版本大于等于21 给状态栏设置颜色

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            if (statusColor != INVALID_VAL) {

                color = statusColor;

            }

            activity.getWindow().setStatusBarColor(color);

            return null;

        }

        //如果版本在大于等于19小于21

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT

                && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {

            //获取contentView

            ViewGroup contentView = (ViewGroup) activity.findViewById(android.R.id.content);

            if (statusColor != INVALID_VAL) {

                color = statusColor;

            }

            //获取contentView里的第一个view 也就是状态栏

            View statusBarView = contentView.getChildAt(0);

            if (statusBarView != null && statusBarView.getMeasuredHeight() == getStatusBarHeight(activity)) {

                statusBarView.setBackgroundColor(color);

                return statusBarView;

            }





            statusBarView = new View(activity);

            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,

                    getStatusBarHeight(activity));

            statusBarView.setBackgroundColor(color);

            contentView.addView(statusBarView, lp);

            return statusBarView;

        }

        return null;



    }



    public static void compat(Activity activity) {

        compat(activity, INVALID_VAL);

    }

    public static int getStatusBarHeight(Context context) {

        int result = 0;

        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");

        if (resourceId > 0) {

            result = context.getResources().getDimensionPixelSize(resourceId);

        }

        return result;

    }

}
