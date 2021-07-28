package com.example.mototest.View.Test;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mototest.Api.ApiService;
import com.example.mototest.MainActivity;
import com.example.mototest.Model.DBHandler;
import com.example.mototest.Model.Question;
import com.example.mototest.Model.Test;
import com.example.mototest.R;
import com.example.mototest.View.TestRanDom.TestRanDomFragment;

import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LayoutTest extends AppCompatActivity{
    private TextView tvbackquestion,tvnextquestion,tvcurrentquestion,tvmaxquestion,toolbar_time,tv_toolbar_check;
    private ImageView toolbar_back;
    private ViewPager viewPager;
//    private List<Question> questionList;
    private ActionBar actionBar;
    private  Question question;
    private ArrayList<String> listans=new ArrayList<String>();
    ArrayList<Question> questionArrayList=new ArrayList<>();
    private int point=0;
    private int ttTime=1;
    private String action="getTest";
    private boolean isSubmit=false;
    private String idTest;
    private DBHandler dbHandler ;
    Activity activity=this;
    ArrayList<String> listdadung=new ArrayList<>();
    Dialog dialog2;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_test);
        actionBar= getSupportActionBar();
        dbHandler = new DBHandler(this);
        dialog2=new Dialog(activity);
        dialog2.setContentView(R.layout.loading);
        dialog2.show();

        initUi();
//        questionArrayList = getQuestionList();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(true){
            dialog2.dismiss();
            questionArrayList=dbHandler.getQSinTest(Integer.parseInt(bundle.getString("Idtest")));

            idTest = bundle.getString("Idtest");
            while (listans.size() < questionArrayList.size()) {
                listans.add("");
            }
            ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),
                    FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, questionArrayList);
            viewPager.setAdapter(viewPagerAdapter);

            tv_toolbar_check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkanser(false);
                }
            });

            tvcurrentquestion.setText("1");
            tvmaxquestion.setText(String.valueOf(questionArrayList.size()));
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    tvcurrentquestion.setText(String.valueOf(position + 1));
                    if (position == 0) {
                        tvbackquestion.setVisibility(View.GONE);
                        tvnextquestion.setVisibility(View.VISIBLE);
                    } else if (position == questionArrayList.size() - 1) {
                        tvbackquestion.setVisibility(View.VISIBLE);
                        tvnextquestion.setVisibility(View.GONE);
                    } else {
                        tvbackquestion.setVisibility(View.VISIBLE);
                        tvnextquestion.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

            toolbar_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    confirmBack();
                }

            });

            tvbackquestion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                }
            });

            tvnextquestion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                }
            });
            countdowntime();
        }
        else
        if(bundle!=null) {
            if (bundle.getString("Idtest").equals("random")) {
                action = "getTestRand";
            }
            ApiService.apiservice.getTest(action, bundle.getString("Idtest")).enqueue(new Callback<Test>() {
                @Override
                public void onResponse(Call<Test> call, Response<Test> response) {
                    dialog2.dismiss();
//                Toast.makeText(LayoutTest.this,"Call API SUCCESS",Toast.LENGTH_SHORT).show();
                    idTest = bundle.getString("Idtest");
                    Test test = response.body();
                    if (test != null) {
                        Log.e("test:", Integer.toString(test.getIdtest()));
                        questionArrayList = test.getListquestion();
                        while (listans.size() < questionArrayList.size()) {
                            listans.add("");
                        }
                        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),
                                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, questionArrayList);
                        viewPager.setAdapter(viewPagerAdapter);

                        tv_toolbar_check.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                checkanser(false);
                            }
                        });

                        tvcurrentquestion.setText("1");
                        tvmaxquestion.setText(String.valueOf(questionArrayList.size()));
                        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                            @Override
                            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                            }

                            @Override
                            public void onPageSelected(int position) {
                                tvcurrentquestion.setText(String.valueOf(position + 1));
                                if (position == 0) {
                                    tvbackquestion.setVisibility(View.GONE);
                                    tvnextquestion.setVisibility(View.VISIBLE);
                                } else if (position == questionArrayList.size() - 1) {
                                    tvbackquestion.setVisibility(View.VISIBLE);
                                    tvnextquestion.setVisibility(View.GONE);
                                } else {
                                    tvbackquestion.setVisibility(View.VISIBLE);
                                    tvnextquestion.setVisibility(View.VISIBLE);
                                }
                            }

                            @Override
                            public void onPageScrollStateChanged(int state) {

                            }
                        });

                        toolbar_back.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                confirmBack();
                            }

                        });

                        tvbackquestion.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                            }
                        });

                        tvnextquestion.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                            }
                        });
                    }
                    countdowntime();
                }

                @Override
                public void onFailure(Call<Test> call, Throwable t) {
                    dialog2.dismiss();
                    Toast.makeText(LayoutTest.this, "Lấy dữ liệu bài thi thất bại", Toast.LENGTH_SHORT).show();
                }
            });
        }


    }
    private void countdowntime() {
        long duration = TimeUnit.MINUTES.toMillis(ttTime);

        CountDownTimer countDownTimer=new CountDownTimer(duration,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                //chuyển đổi mili giây sang phút và giây khi tick
                 String sduation = String.format(Locale.ENGLISH, "%02d : %02d"
                        , TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))

                );
                toolbar_time.setText(sduation);
                if(isSubmit==true)
                    this.cancel();
            }
            @Override
            public void onFinish() {
                Toast.makeText(getApplicationContext(),"Hết thời gian thi",Toast.LENGTH_SHORT).show();
                if(isSubmit==false)
                {
                    checkanser(true);
                }


            }
        }.start();
    }




    private void initUi() {
        tvbackquestion=(TextView)findViewById(R.id.tv_back_quesion);
        tvnextquestion=(TextView)findViewById(R.id.tv_next_quesion);
        tvcurrentquestion=(TextView)findViewById(R.id.tv_current_question);
        tvmaxquestion=(TextView)findViewById(R.id.tv_max_quesion);
        viewPager=(ViewPager)findViewById(R.id.viewpager);
        toolbar_time=(TextView)findViewById(R.id.tv_toolbar_time);
        toolbar_back=(ImageView) findViewById(R.id.iv_toolbar_back);
        tv_toolbar_check=(TextView) findViewById(R.id.tv_toobar_check);




    }

    protected boolean getisSubmit(){
        return isSubmit;
    }

    protected void setAns(Integer stt,String ctl){
        if(stt-1<listans.size())
            listans.set(stt-1,ctl);
        else {
            while ((stt - 1) > listans.size()) {
                listans.add("");
            }
            listans.add(ctl);
        }
    }

    private void checkanser(boolean timeout) {


        Button btn_exit,btn_finish;


        Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.check_question);
        dialog.setTitle("Danh sach câu trả lời");
        btn_exit=(Button)dialog.findViewById(R.id.btn_exit);
        btn_finish=(Button)dialog.findViewById(R.id.btn_finish);
//        TINH DIEM

//        tv_result.setText(Integer.toString(point));
            CheckAnserAdapter checkAnserAdapter = new CheckAnserAdapter(listans, listdadung, this);
            GridView gridView = (GridView) dialog.findViewById(R.id.grv_listquestion);
            gridView.setAdapter(checkAnserAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                viewPager.setCurrentItem(position);
                dialog.dismiss();
            }
        });
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        if(isSubmit) btn_finish.setVisibility(View.GONE);
        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Submit();
                dialog.dismiss();
            }
        });

        if(timeout){
            Submit();
            dialog.dismiss();
        }
        dialog.show();
    }

    protected String getResult(int i){
        return questionArrayList.get(i).getDadung();
    }

    private void Submit(){
        for(int i=0;i<listans.size();i++){
            if (listans.get(i).equals(questionArrayList.get(i).getDadung()))
            {
                point+=1;
            }
            listdadung.add(questionArrayList.get(i).getDadung());
        }
        String[] DoTime=((String) toolbar_time.getText()).split(":");
        int minutes=Integer.parseInt(DoTime[0].trim());
        int seconds=Integer.parseInt(DoTime[1].trim());
        int timelambai=ttTime*60-minutes*60-seconds;
        String Dominutes=Integer.toString(timelambai/60);
        String Doseconds=Integer.toString(timelambai%60);
        Bundle bundle=new Bundle();
        bundle.putInt("point", point);
        bundle.putString("minutes",Dominutes);
        bundle.putString("seconds",Doseconds);
        bundle.putString("IdTest",idTest);
        isSubmit=true;
        Intent intent=new Intent(LayoutTest.this,Result.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    private void confirmBack(){
        if(!isSubmit){
            Dialog dialog=new Dialog(activity);
            dialog.setContentView(R.layout.dialog_custom);
//                customDialog.showdialog(activity,"Đăng Nhập","Bạn có đăng nhập không hahahahahahakkkkkkkkkkkkkk ?");
//                dialog.setTitle("Bạn có đăng nhập không hahahahahahakkkkkkkkkkkkkk ?");
            Button btn_yes=(Button)dialog.findViewById(R.id.btn_yes);
            Button btn_no=(Button)dialog.findViewById(R.id.btn_no);
            TextView tv_dialog_title= dialog.findViewById(R.id.tv_dialog_title);
            TextView tv_dialog_content=dialog.findViewById(R.id.tv_dialog_content);
            tv_dialog_title.setText("Xác nhận thoát");
            tv_dialog_content.setText("Bạn chưa làm xong bài bạn chắc chắn muốn thoát chứ");
            btn_yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    isSubmit=true;
                    finish();
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
        else finish();
    }
    @Override
    protected void onStop() {
        isSubmit=true;
        super.onStop();
    }



    @Override
    public void onBackPressed() {
//        super.onBackPressed();

        confirmBack();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        boolean static check=true;
    }
}
