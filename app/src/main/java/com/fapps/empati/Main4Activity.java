package com.fapps.empati;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Main4Activity extends AppCompatActivity {
    private Button kadinhaklari,suc,gocmen,teknoloji,issizlik,sosyal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        kadinhaklari=(Button)findViewById(R.id.kadınhaklari);
        suc=(Button)findViewById(R.id.suc);
        gocmen=(Button)findViewById(R.id.gocmensorunu);
        teknoloji=(Button)findViewById(R.id.teknoloji);
        issizlik=(Button)findViewById(R.id.issizlik);
        sosyal=(Button)findViewById(R.id.sosyal);
        kadinhaklari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kadinhaklari=new Intent(Main4Activity.this,KadinHaklariActivity.class);
                startActivity(kadinhaklari);
                finish();
            }
        });
        suc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent suc=new Intent(Main4Activity.this,SucActivity.class);
                startActivity(suc);
                finish();
            }
        });
        gocmen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gocmen=new Intent(Main4Activity.this,GocmenActivity.class);
                startActivity(gocmen);
                finish();

            }
        });
        teknoloji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gocmen=new Intent(Main4Activity.this,TeknolojiActivity.class);
                startActivity(gocmen);
                finish();

            }
        });
        issizlik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent issizlik=new Intent(Main4Activity.this,IssizlikActivity.class);
                startActivity(issizlik);
                finish();

            }
        });
        sosyal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sosyal=new Intent(Main4Activity.this,SosyalActivity.class);
                startActivity(sosyal);
                finish();


            }
        });






        RelativeLayout relativeLayout=findViewById(R.id.layout);
        AnimationDrawable animationDrawable=(AnimationDrawable)relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(3000);
        animationDrawable.start();



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("TOPLUMSAL");





    }
}
