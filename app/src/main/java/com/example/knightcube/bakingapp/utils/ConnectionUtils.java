package com.example.knightcube.bakingapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;

import java.net.InetAddress;

/**
 * Created by Rajat Kumar Gupta on 03/07/2018.
 */
public class ConnectionUtils {
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

}
