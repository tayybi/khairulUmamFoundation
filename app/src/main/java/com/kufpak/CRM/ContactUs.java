package com.kufpak.CRM;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.youtube.player.internal.e;
import com.kufpak.R;

public class ContactUs extends AppCompatActivity {

    ImageView ivFb,ivYoutube,ivInstagarm,ivTwitter,ivGoBack;
    LinearLayout lnCall,lnMap,lnMail,lnWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.kufpak.R.layout.activity_contact_us);

        init();
        setListener();
    }

    private void setListener() {
        ivGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(com.kufpak.R.anim.in_left, com.kufpak.R.anim.out_right);
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
//
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
        lnWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://kuf.org.pk/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                overridePendingTransition(com.kufpak.R.anim.in_left, com.kufpak.R.anim.out_right);
            }
        });
        lnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("https://goo.gl/maps/ZTpQQb2cauGMiWXs8"));
                startActivity(intent);

            }
        });
        lnMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mailIntent = new Intent(Intent.ACTION_VIEW);
                Uri data = Uri.parse("mailto:?subject=" + "Mail From App"+ "&body=" + "Dear Sir," + "&to=" + "info@kufpak.com");
                mailIntent.setData(data);
                startActivity(Intent.createChooser(mailIntent, "Send mail..."));
            }
        });
        lnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "03001234567", null)));
            }
        });
    }


    private void init() {

        ivGoBack = findViewById(com.kufpak.R.id.go_back);
        ivFb=findViewById(R.id.ivFb);
        ivYoutube=findViewById(R.id.ivYoutube);
        ivTwitter=findViewById(R.id.ivTwitter);
        ivInstagarm=findViewById(R.id.ivInstagram);
        lnCall=findViewById(R.id.lnCall);
        lnMail=findViewById(R.id.lnMail);
        lnMap=findViewById(R.id.lnMap);
        lnWeb=findViewById(R.id.lnWeb);
    }

}
