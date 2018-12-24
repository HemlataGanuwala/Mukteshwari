package com.example.dhanashri.mukteshwarigurupeethwardh;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final GlobalClass globalClass=(GlobalClass)getApplicationContext();
        globalClass.setconstr("http://gurupith.skyvisioncables.com/api/");
//        globalClass.setconstr("http://192.168.0.106:8019/api/");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);

    }
}
