package com.example.memorygame;


import android.app.ProgressDialog;
import android.content.Context;


public class Util {

    public static ProgressDialog sProgressDialog;


    public static void showProgressDialog(Context context) {
        dismissProgressDialog();
        sProgressDialog = new ProgressDialog(context, R.style.MyTheme);
        sProgressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        sProgressDialog.setCancelable(false);
        try {
            sProgressDialog.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void dismissProgressDialog() {
        if (sProgressDialog != null && sProgressDialog.isShowing()) {
            sProgressDialog.dismiss();
        }
    }


}
