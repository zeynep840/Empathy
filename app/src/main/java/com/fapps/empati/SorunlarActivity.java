package com.fapps.empati;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class SorunlarActivity extends AppCompatActivity {
    private Button button23,toplumsal,button34,imageView56,imageView57,imageView58;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorunlar);
        toplumsal=(Button) findViewById(R.id.toplumsal);
        imageView56=(Button)findViewById(R.id.imageView56) ;
        imageView57=(Button)findViewById(R.id.imageView57);
        imageView58=(Button)findViewById(R.id.imageView58);

        button23=(Button)findViewById(R.id.button23);
        button34=(Button)findViewById(R.id.button34);
        RelativeLayout relativeLayout=findViewById(R.id.layout);
        AnimationDrawable animationDrawable=(AnimationDrawable)relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(3000);
        animationDrawable.start();
        button23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SorunlarActivity.this,Main3Activity.class);
                startActivity(intent);

            }
        });
        toplumsal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(SorunlarActivity.this,Main4Activity.class);
                startActivity(intent1);


            }
        });
        button34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5=new Intent(SorunlarActivity.this,Main5Activity.class);
                startActivity(intent5);


            }
        });
        imageView56.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent6=new Intent(SorunlarActivity.this,OzelSorunlarActivity.class);
                startActivity(intent6);


            }
        });
        imageView57.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent7=new Intent(SorunlarActivity.this,HayvanActivity.class);
                startActivity(intent7);


            }
        });
        imageView58.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent8=new Intent(SorunlarActivity.this,TurizmActivity.class);
                startActivity(intent8);


            }
        });
    }
}
