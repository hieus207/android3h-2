package com.example.mototest.View;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.mototest.R;

public class CustomDialog {
    Button btn_yes, btn_no;
    TextView tv_dialogtitle,tv_dialogcontent;

    public void showdialog(Activity activity, String title,String content) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //full màn
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.custom_dialog);
        btn_yes = (Button) dialog.findViewById(R.id.btn_yes);
        btn_no = (Button) dialog.findViewById(R.id.btn_no);
        tv_dialogtitle = (TextView) dialog.findViewById(R.id.tv_dialog_title);
        tv_dialogcontent = (TextView) dialog.findViewById(R.id.tv_dialog_content);
        tv_dialogtitle.setText(title);
        tv_dialogcontent.setText(content);
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}