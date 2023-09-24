package com.fapps.empati;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.tomer.fadingtextview.FadingTextView;

public class UzmaninaDanisActivity extends AppCompatActivity {
    FadingTextView kayanyazilar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uzmanina_danis);
        kayanyazilar=(FadingTextView)findViewById(R.id.fading_text_view);
        RelativeLayout relativeLayout=findViewById(R.id.layout);
        AnimationDrawable animationDrawable=(AnimationDrawable)relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(3000);
        animationDrawable.start();

    }
}
