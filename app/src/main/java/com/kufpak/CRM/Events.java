package com.kufpak.CRM;

import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.kufpak.Adpters.Pageradapter;
import com.kufpak.R;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Events extends AppCompatActivity {

    ImageView ivGoBack;
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final Integer[] IMAGES= {R.drawable.p,R.drawable.po,R.drawable.pt,R.drawable.pth,R.drawable.pf,R.drawable.pfi,R.drawable.ps,R.drawable.pse,R.drawable.pe};
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inquery);
        init();
        setListener();
        pager();

    }


    private void setListener() {
        ivGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(com.kufpak.R.anim.in_left, com.kufpak.R.anim.out_right);
            }
        });
    }

        private void init() {

            ivGoBack=findViewById(com.kufpak.R.id.go_back);


        }

    private void pager() {


        for(int i=0;i<IMAGES.length;i++)
            ImagesArray.add(IMAGES[i]);

        mPager = findViewById(R.id.pager);


        mPager.setAdapter(new Pageradapter(Events.this,ImagesArray));

        NUM_PAGES =IMAGES.length;

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

    }

    }

