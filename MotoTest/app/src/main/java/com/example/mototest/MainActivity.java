package com.example.mototest;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mototest.Api.InfoAcc;
import com.example.mototest.Model.MyReceiver;
import com.example.mototest.Model.Question;
import com.example.mototest.View.Login;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.mototest.databinding.ActivityMainBinding;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private TextView tvbackq,tvnextq,tvcurrentq,tvmaxq,tv_name_main,tv_username_main;
    private ViewPager viewPager;
    private View view;
    protected Question question;
    public int check=0;
    private Activity activity = this;
    private MyReceiver mNetworkReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        mNetworkReceiver = new MyReceiver();
        this.registerReceiver(mNetworkReceiver, filter);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_test, R.id.nav_test_random, R.id.nav_review, R.id.nav_tip,R.id.nav_change_pass,R.id.nav_logout,R.id.nav_dashboard)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        view=navigationView.getHeaderView(0);
        tv_name_main=view.findViewById(R.id.tv_name_main);
        tv_username_main=view.findViewById(R.id.tv_usn_main);
//        Intent intent = getIntent();
//        Bundle bundle = intent.getExtras();
//        if(bundle!=null) {
            tv_name_main.setText(((InfoAcc) getApplication()).getName());
            tv_username_main.setText(((InfoAcc) getApplication()).getUsername());
//        }
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
//                int id = item.getItemId();
//                if (id == R.id.nav_logout) {
//                    finish();
//                    return true;
//                }
//                return true;
//            }
//        });

        MenuItem dashboard = navigationView.getMenu().findItem(R.id.nav_dashboard);
        if(!((InfoAcc) getApplication()).getPermission().equals("admin")) dashboard.setVisible(false);
        MenuItem logoutItem = navigationView.getMenu().findItem(R.id.nav_logout);
        logoutItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
//                Toast.makeText(getBaseContext(),"OK",Toast.LENGTH_SHORT).show();
                confirmLogout();
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void confirmLogout(){
            Dialog dialog=new Dialog(this);
//        View view  = getActivity().getLayoutInflater().inflate(R.layout.dialog_custom, null);
            dialog.setContentView(R.layout.dialog_custom);

            Button btn_yes=(Button)dialog.findViewById(R.id.btn_yes);
            Button btn_no=(Button)dialog.findViewById(R.id.btn_no);
            TextView tv_dialog_title= dialog.findViewById(R.id.tv_dialog_title);
            TextView tv_dialog_content=dialog.findViewById(R.id.tv_dialog_content);
            tv_dialog_title.setText("Xác nhận đăng xuất");
            tv_dialog_content.setText("Bạn có chắc muốn đăng xuất chứ");
            btn_yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    finish();
                    Intent intent=new Intent(MainActivity.this, Login.class);
                    startActivity(intent);
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

//    private final MyReceiver mNetworkReceiver2 = new MyReceiver(){
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//            if (cm.getActiveNetworkInfo() == null) {
//                Dialog dialog=new Dialog(activity);
////        View view  = getActivity().getLayoutInflater().inflate(R.layout.dialog_custom, null);
//                dialog.setContentView(R.layout.dialog_custom);
//
//                Button btn_yes=(Button)dialog.findViewById(R.id.btn_yes);
//                Button btn_no=(Button)dialog.findViewById(R.id.btn_no);
//                TextView tv_dialog_title= dialog.findViewById(R.id.tv_dialog_title);
//                TextView tv_dialog_content=dialog.findViewById(R.id.tv_dialog_content);
//                tv_dialog_title.setText("Xác nhận chuyển hướng");
//                tv_dialog_content.setText("Bạn đã mất kết nối mạng, chuyển qua chế độ offline?");
//                btn_yes.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
////                        dialog.dismiss();
////                        finish();
////                        Intent intent=new Intent(MainActivity.this, Login.class);
////                        startActivity(intent);
//                        Toast.makeText(context, "Internet Connected 2", Toast.LENGTH_LONG).show();
//                    }
//                });
//                btn_no.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dialog.dismiss();
//                    }
//                });
//                dialog.show();
//
//            } else if (cm.getActiveNetworkInfo() != null) {
//                Toast.makeText(context, "Internet Connected 2", Toast.LENGTH_LONG).show();
//            }
//        }
//    };

}