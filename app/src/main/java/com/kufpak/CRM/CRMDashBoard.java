package com.kufpak.CRM;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.kufpak.Adpters.Pageradapter;
import com.kufpak.CRM.Fragments.PagerAdapterooo;
import com.kufpak.Others.ModelClassOfTab;
import com.kufpak.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class CRMDashBoard extends AppCompatActivity implements View.OnClickListener {
    Context context;
    LinearLayout linFoundation,linContactUs,linVisitUs,linRateApp,linShare,linHome;
    TabLayout tabLayout;
    ImageView ivDrawerMenu,ivLive, ivFb,ivYoutube,ivInstagarm,ivTwitter;
    DrawerLayout mDrawerLayout;
    protected FrameLayout frameLayout;
    protected LinearLayout linearLayoutDrawer;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    /////Menue expandable
    ViewPager viewPager;
    public static boolean countfragment;
    ModelClassOfTab modelClassOfTab;
    List<ModelClassOfTab> list;

    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final Integer[] IMAGES= {R.drawable.one,R.drawable.two,R.drawable.three,R.drawable.foure,R.drawable.five,R.drawable.sixe,R.drawable.seven,R.drawable.eight,R.drawable.nine,R.drawable.ten,R.drawable.eleven,R.drawable.twelve,R.drawable.thirteen,R.drawable.fouteen,R.drawable.fifty,R.drawable.seventeen,R.drawable.eighteen,R.drawable.ninteen};
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

                case R.id.bHome:
                    /////do code here
                    return true;
                case R.id.bConfrences:
                    Toast.makeText(CRMDashBoard.this,"Under Development",Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(CRMDashBoard.this, ContactUs.class));
//                    overridePendingTransition(com.ezcorporate.R.anim.in_right, com.ezcorporate.R.anim.out_left);
                    return true;
                case R.id.bevents:
                    startActivity(new Intent(CRMDashBoard.this, Events.class));
                    overridePendingTransition(com.kufpak.R.anim.in_right, com.kufpak.R.anim.out_left);
                    return true;
            }
            return false;
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.kufpak.R.layout.activity_crmdash_board);

        this.context=CRMDashBoard.this;

        init();
        setListener();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(com.kufpak.R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        drawerSetting();
        try {
            TabViewPager();
        }catch (Exception e){
            Log.i("Exception",""+e);
        }
    }

    private void setListener() {
        ivDrawerMenu.setOnClickListener((CRMDashBoard.this));


        ivLive.setOnClickListener(new View.OnClickListener() {
            @androidx.annotation.RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                Toast.makeText(CRMDashBoard.this,"Under Development",Toast.LENGTH_SHORT).show();

//                startActivity(new Intent(CRMDashBoard.this, HoldsList.class));
//                CRMDashBoard.this.finish();
            }
        });

        /////////////////////////////////////////////////////////////////////////
        //////////////////  drawer menue click listener  ///////////////////

        linHome.setOnClickListener(new View.OnClickListener() {
            @androidx.annotation.RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        linFoundation.setOnClickListener(new View.OnClickListener() {
            @androidx.annotation.RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://kuf.org.pk/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                overridePendingTransition(com.kufpak.R.anim.in_right, com.kufpak.R.anim.out_left);

            }
        });

        linContactUs.setOnClickListener(new View.OnClickListener() {
            @androidx.annotation.RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CRMDashBoard.this, ContactUs.class));
                overridePendingTransition(com.kufpak.R.anim.in_right, com.kufpak.R.anim.out_left);
            }
        });

        linRateApp.setOnClickListener(new View.OnClickListener() {
            @androidx.annotation.RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        linShare.setOnClickListener(new View.OnClickListener() {
            @androidx.annotation.RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {

                shareApp(context);
            }
        });

        ivInstagarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://instagram.com/_u/syedkaramatalihussain/?hl=en");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                likeIng.setPackage("com.instagram.android");

                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://instagram.com/syedkaramatalihussain/?hl=en")));
                }
                overridePendingTransition(com.kufpak.R.anim.in_left, com.kufpak.R.anim.out_right);
            }
        });

        ivTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                PackageInfo info = null;
                try {
                    info = getPackageManager().getPackageInfo("com.twitter.android", 0);
                    if (info.applicationInfo.enabled) {
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?user_id=839109607633088512"));
                        startActivity(intent);
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/@PeerSyedKaramat"));
                    startActivity(intent);
                }
                overridePendingTransition(com.kufpak.R.anim.in_left, com.kufpak.R.anim.out_right);
            }

        });

        ivYoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    //here we try to open the link in app
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube.com/channel/UCxLhnkoHfOuLy9v3JAMumRg")));
                }catch (Exception e) {
                    //the app isn't available: we open in browser`
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UCxLhnkoHfOuLy9v3JAMumRg")));
                }
                overridePendingTransition(com.kufpak.R.anim.in_left, com.kufpak.R.anim.out_right);
            }
        });

        ivFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/280827222639495")));
                } catch (Exception e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/280827222639495")));
                }
                overridePendingTransition(com.kufpak.R.anim.in_left, com.kufpak.R.anim.out_right);
            }
        });

        linVisitUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("https://goo.gl/maps/ZTpQQb2cauGMiWXs8"));
                startActivity(intent);

            }
        });
    }

    public void init() {

        ivFb=findViewById(R.id.ivFb);
        ivYoutube=findViewById(R.id.ivYoutube);
        ivTwitter=findViewById(R.id.ivTwitter);
        ivInstagarm=findViewById(R.id.ivInstagram);
        ivDrawerMenu = findViewById(R.id.ivDrawerIcon);
        ivLive = findViewById(R.id.ivLive);


        linHome=findViewById(R.id.linHome);
        linFoundation=findViewById(R.id.linFoundation);
        linContactUs=findViewById(R.id.linContactUs);
        linVisitUs=findViewById(R.id.linVisitUs);
        linRateApp=findViewById(R.id.linRateApp);
        linShare=findViewById(R.id.linShare);
        frameLayout = (FrameLayout) findViewById(R.id.content_frame);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        linearLayoutDrawer = findViewById(R.id.linearLayoutDrawer);

        countfragment=true;
        list =new ArrayList<>();

        pager();
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivDrawerIcon:
                if (!mDrawerLayout.isDrawerOpen(linearLayoutDrawer)) {
                    mDrawerLayout.openDrawer(linearLayoutDrawer);
                }
                break;
        }
    }

    private void drawerSetting() {
        actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,      /* host Activity */
                mDrawerLayout,     /* DrawerLayout object */
                R.mipmap.ic_launcher,     /* nav drawer image to replace 'Up' caret */
                R.string.open_drawer,       /* "open drawer" description for accessibility */
                R.string.close_drawer)      /* "close drawer" description for accessibility */ {
            @Override
            public void onDrawerClosed(View drawerView) {
//                getSupportActionBar().setTitle(listArray[position]);
                //Toast.makeText(MainActivity.this,"Closed",Toast.LENGTH_SHORT).show();
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                //  Toast.makeText(MainActivity.this,"Open",Toast.LENGTH_SHORT).show();
//                getSupportActionBar().setTitle(getString(R.string.app_name));
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                //Toast.makeText(MainActivity.this,"slide",Toast.LENGTH_SHORT).show();
                super.onDrawerSlide(drawerView, slideOffset);
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                //   Toast.makeText(MainActivity.this,"changed",Toast.LENGTH_SHORT).show();
                super.onDrawerStateChanged(newState);
            }
        };
        mDrawerLayout.setDrawerListener(actionBarDrawerToggle);
    }

    public void TabViewPager(){
            modelClassOfTab = new ModelClassOfTab();
            modelClassOfTab.setName("Latest");
            modelClassOfTab.setIcons(R.drawable.latest);
            list.add(modelClassOfTab);

        modelClassOfTab = new ModelClassOfTab();
        modelClassOfTab.setName("Dars-e-Quran");
        modelClassOfTab.setIcons(R.drawable.quran);
        list.add(modelClassOfTab);

            tabLayout = (TabLayout)findViewById(com.kufpak.R.id.sliding_tabs);
            tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
            viewPager = (ViewPager)findViewById(com.kufpak.R.id.viewpager);
            final PagerAdapterooo adapter = new PagerAdapterooo(context,getSupportFragmentManager(),list.size(),list);

            viewPager.setAdapter(adapter);
            tabLayout.setupWithViewPager(viewPager);
        for (int i=0;i<list.size();i++){
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(adapter.getTabView(i));
            Log.i("taskname",""+list.get(i).getName());
        }
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {


                for (int i=0;i<list.size();i++){
                    TabLayout.Tab tab = tabLayout.getTabAt(i);
                    tab.setCustomView(adapter.getTabView(i));
                    Log.i("taskname",""+list.get(i).getName());
                }
                countfragment=true;
                viewPager.getAdapter().notifyDataSetChanged();

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        }


    public static void shareApp(Context context)
    {
        final String appPackageName = context.getPackageName();
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        //sendIntent.putExtra(Intent.EXTRA_TEXT, "Check out the App at: https://play.google.com/store/apps/details?id=" + appPackageName);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Check out the App at: https://play.google.com/store/apps" + appPackageName);
        sendIntent.setType("text/plain");
        context.startActivity(sendIntent);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(linearLayoutDrawer)) {
            mDrawerLayout.closeDrawer(linearLayoutDrawer);
        } else {
//            mDrawerLayout.openDrawer(linearLayoutDrawer);
            super.onBackPressed();
            finish();
            overridePendingTransition(R.anim.in_left,R.anim.out_right);
        }
    }

    private void pager() {


        for(int i=0;i<IMAGES.length;i++)
            ImagesArray.add(IMAGES[i]);

        mPager = (ViewPager) findViewById(R.id.pager);


        mPager.setAdapter(new Pageradapter(CRMDashBoard.this,ImagesArray));


//        CirclePageIndicator indicator = (CirclePageIndicator)
//                findViewById(R.id.indicator);
//
//        indicator.setViewPager(mPager);
//
//        final float density = getResources().getDisplayMetrics().density;
//
//        indicator.setRadius(5 * density);



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

        // Pager listener over indicator
//        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//
//            @Override
//            public void onPageSelected(int position) {
//                currentPage = position;
//
//            }
//
//            @Override
//            public void onPageScrolled(int pos, float arg1, int arg2) {
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int pos) {
//
//            }
//        });

    }


}