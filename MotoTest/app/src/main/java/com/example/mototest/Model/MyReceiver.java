package com.example.mototest.Model;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mototest.R;
import com.example.mototest.View.Login;
import com.example.mototest.View.Register;

public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "MyBroadcastReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        boolean isConnected = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);
        if(isConnected){
            Toast.makeText(context,"YÊU CẦU BẬT WIFI TRƯỚC KHI SỬ DỤNG", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(context,"KẾT NỐI THÀNH CÔNG", Toast.LENGTH_SHORT).show();
        }
    }

}
