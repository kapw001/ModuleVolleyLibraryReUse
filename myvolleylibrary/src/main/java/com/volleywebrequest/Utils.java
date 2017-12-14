package com.volleywebrequest;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.ContextThemeWrapper;

/**
 * Created by yasar on 5/10/17.
 */

public class Utils {

    private static final String TAG = "Volley Library Utils";
    private static ProgressDialog progressDialog;

    public static void showProgress(Context context, String msg) {
//        if (!((Activity) context).isFinishing()) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage(msg);
        progressDialog.show();
//        } else {
//            Log.e(TAG, "showProgress: Context or Activity not running  ");
//        }
    }

    public static void hideProgress() {
        if (progressDialog != null) {
            progressDialog.cancel();
        }
    }
}
