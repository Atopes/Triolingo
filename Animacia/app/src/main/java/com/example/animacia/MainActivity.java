package com.example.animacia;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Timer;
import java.util.TimerTask;
public class MainActivity extends AppCompatActivity {
    private boolean stoji, smerDoprava;
    private Timer timer;
    private Handler handler = new Handler();
    private SharedPreferences settings;
    private float autickoX;
    private int rychlost, perioda;
    private float sirka;
    private int kola=0;
    private FrameLayout tatry;
    private ImageView auticko;
    private Button riadenie;
    private Drawable autoDoprava, autoDolava;
    private TextView pocetKol;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tatry = findViewById(R.id.tatry);
        auticko = findViewById(R.id.auticko);
        riadenie = findViewById(R.id.chod);
        pocetKol = findViewById(R.id.kola);
        autoDoprava = getResources().getDrawable(R.drawable.autop);
        autoDolava = getResources().getDrawable(R.drawable.autol);
        tatry.post(new Runnable() {
            public void run() {
                sirka = tatry.getWidth() - auticko.getWidth();
            }
        });
        rychlost=20;
        perioda = 30;
        stoji = true;
        smerDoprava = true;
        settings = getSharedPreferences("ANIMACIA_DATA", Context.MODE_PRIVATE);
        kola = settings.getInt("PREJDENE_KOLA", 0);
        pocetKol.setText("Počet prejdených kôl: "+kola);
        autickoX = settings.getFloat("AUTICKO_X",0);
        auticko.setX(autickoX);
    }
    public void klikloSa(View view) {
        if(!stoji) {
            // zastavime ho
            stoji = true;
            timer.cancel();
            timer = null;
            riadenie.setText("Spusť ma");
        }
        // startujeme auticko
        else {
            stoji = false;
            riadenie.setText("Zastav ma");
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if(!stoji) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                posunSa();
                            }
                        });
                    }
                }
            }, 0, perioda);
        }
    }
    private void posunSa() {
        if(smerDoprava) {
            autickoX += rychlost;
            auticko.setX(autickoX);
            auticko.setImageDrawable(autoDoprava);
            if(autickoX > sirka) {
                autickoX = sirka-auticko.getWidth();
                smerDoprava=false;
                auticko.setX(autickoX);
                auticko.setImageDrawable(autoDolava);
            }
        }
        else {
            autickoX -= rychlost;
            auticko.setX(autickoX);
            auticko.setImageDrawable(autoDolava);
            if (autickoX < 0) {autickoX = 0;
                smerDoprava=true;
                auticko.setX(autickoX);
                auticko.setImageDrawable(autoDoprava);
                kola++;
                pocetKol.setText("Počet prejdených kôl: "+kola);
            }
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("PREJDENE_KOLA", kola);
        editor.putFloat("AUTICKO_X", autickoX);
        editor.apply();
        if (timer != null || stoji == false) {
            timer.cancel();
            timer = null;
            stoji = true;
            riadenie.setText("Spusť ma");
        }
    }
    public void ukonci(View view) {
        kola = 0;
        autickoX = 0;
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("PREJDENE_KOLA", kola);
        editor.putFloat("AUTICKO_X", autickoX);
        editor.apply();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAndRemoveTask();
        } else {
            finish();
        }
    }
}
