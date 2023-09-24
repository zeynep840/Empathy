package com.fapps.empati;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.onesignal.OneSignal;

public class Main2Activity extends AppCompatActivity {




    private Toolbar actionbar;

    private TabLayout tabsMain;

    private FirebaseAuth auth;
    private FirebaseUser currentUser;
    private ImageButton AddNewButton;
    private RecyclerView recyclerView1;
    private Button child,adult,animal;
    private Button uzman1,uzman;
    private ImageView imageview23;

    private DatabaseReference UsersRef;


    public void init(){


        auth=FirebaseAuth.getInstance();
        UsersRef=FirebaseDatabase.getInstance().getReference().child("Users");

        currentUser=auth.getCurrentUser();


        AddNewButton=(ImageButton) findViewById(R.id.add_new_post_button);





    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        // OneSignal Initialization
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();


        auth=FirebaseAuth.getInstance();
        UsersRef=FirebaseDatabase.getInstance().getReference().child("Users");

        currentUser=auth.getCurrentUser();
        imageview23=(ImageView)findViewById(R.id.imageView23);
        uzman1=(Button)findViewById(R.id.uzman1);

        AddNewButton=(ImageButton) findViewById(R.id.add_new_post_button);

        uzman=(Button)findViewById(R.id.uzman);
        RelativeLayout relativeLayout=findViewById(R.id.layout);
        AnimationDrawable animationDrawable=(AnimationDrawable)relativeLayout.getBackground();
       animationDrawable.setEnterFadeDuration(2000);
       animationDrawable.setExitFadeDuration(3000);
       animationDrawable.start();



        uzman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent9=new Intent(Main2Activity.this,UzmanDanismanimActivity.class);
                startActivity(intent9);

            }
        });
        uzman1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent uzman1=new Intent(Main2Activity.this,SorunlarActivity.class);
                startActivity(uzman1);
            }
        });





    }

    @Override
    //aktif kullanıcı değilse mainactivity e gönderir
    protected void onStart() {
        if(currentUser==null){
            //anasayfadan giriş sayfasına gönderir
            Intent MainIntent=new Intent(Main2Activity.this,MainActivity.class);
            startActivity(MainIntent);
            finish();
        }
        else{
            CheckUserExistence();
        }
        super.onStart();

    }
    //kullanıcı bilgilerini kaydetmek için
    private void CheckUserExistence() {
        final String current_user_id=auth.getCurrentUser().getUid();
        UsersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!dataSnapshot.hasChild(current_user_id)){


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    @Override
    //3 nokta için kullanıldı
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    //Çıkış yapa basıldığında giriş sayfasına yönlendirelecek

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        //neye basıldığına ne yapacağına karar vermek için id le karar veriyoruz
        if(item.getItemId()==R.id.mainLogout){
            auth.signOut();
            Intent loginIntent=new Intent(Main2Activity.this,LoginActivity.class);
            startActivity(loginIntent);
            finish();
        }
        return true;








    }
}
