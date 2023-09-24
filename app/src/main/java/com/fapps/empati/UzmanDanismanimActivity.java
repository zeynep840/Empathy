package com.fapps.empati;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class UzmanDanismanimActivity extends AppCompatActivity {
    private Button yorum3,yorum4,yorum5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uzman_danismanim);
        RelativeLayout relativeLayout=findViewById(R.id.layout);
        AnimationDrawable animationDrawable=(AnimationDrawable)relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(3000);
        animationDrawable.start();
        yorum3=(Button)findViewById(R.id.yorum3);
        yorum4=(Button)findViewById(R.id.yorum4);
        yorum5=(Button)findViewById(R.id.yorum5);
        yorum3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent doktor=new Intent(UzmanDanismanimActivity.this,UzmaninaDanisActivity.class);
                startActivity(doktor);
            }
        });
        yorum4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sosyolog=new Intent(UzmanDanismanimActivity.this,UzmaninaDanisActivity.class);
                startActivity(sosyolog);
            }
        });
        yorum5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent veteriner=new Intent(UzmanDanismanimActivity.this,UzmaninaDanisActivity.class);
                startActivity(veteriner);
            }
        });
    }
}
