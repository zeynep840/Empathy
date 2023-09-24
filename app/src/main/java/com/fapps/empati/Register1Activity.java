package com.fapps.empati;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;

public class Register1Activity extends AppCompatActivity {

    private Uri ImageUri;
    private EditText userEmailEt;
    private EditText userPasswordEt;
    private EditText userConfirmPasswordEt;
    private Button registerBtn;
    private FirebaseAuth mAuth;

    private EditText kullanıcıadi,adsoyad,ulke1;
    ProgressDialog pd;

    private Button buttonset;

    private String downloadUrl,myUrl;
    private static final int Gallery_Pick=1;



    private DatabaseReference UsersRef,postref;
    String CurrentUserID;
    private DatabaseReference reference2;
    private StorageReference UserProfileImageRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register1);

        userEmailEt = (EditText) findViewById(R.id.txtusername23);
        userPasswordEt = (EditText) findViewById(R.id.txtusername24);
        userConfirmPasswordEt = (EditText) findViewById(R.id.parolaonay23);
        registerBtn = (Button) findViewById(R.id.btnRegister23);
        kullanıcıadi = (EditText) findViewById(R.id.kullaniciadi232);
        mAuth = FirebaseAuth.getInstance();


        RelativeLayout relativeLayout=findViewById(R.id.layout);
        AnimationDrawable animationDrawable=(AnimationDrawable)relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(3000);
        animationDrawable.start();


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd = new ProgressDialog(Register1Activity.this);
                pd.setMessage("Lütfen bekleyiniz...");
                pd.show();
                String username = kullanıcıadi.getText().toString();
                String userEmail = userEmailEt.getText().toString();
                String userPassword = userPasswordEt.getText().toString();

                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(userEmail) || TextUtils.isEmpty(userPassword) ) {
                    Toast.makeText(Register1Activity.this, "Lütfen tüm alanları doldurunuz", Toast.LENGTH_SHORT).show();

                } else if (userPassword.length() < 6) {
                    Toast.makeText(Register1Activity.this, "Şifreniz en az 6 karakter olmalı", Toast.LENGTH_SHORT).show();
                } else {
                    register(username,userEmail,userPassword);


                }

            }
        });



    }

    private String getFileExtension(Uri uri){
        ContentResolver contentResolver=getContentResolver();
        MimeTypeMap mime=MimeTypeMap.getSingleton();
        return  mime.getExtensionFromMimeType(contentResolver.getType(uri));
    }



    private void register( final String kullaniciadi, String userEmailEt, String userPasswordEt){
        mAuth.createUserWithEmailAndPassword(userEmailEt,userPasswordEt).addOnCompleteListener(Register1Activity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    FirebaseUser firebaseUser2=mAuth.getCurrentUser();
                    String userid2=firebaseUser2.getUid();
                    reference2=FirebaseDatabase.getInstance().getReference().child("Users3").child(userid2);
                    HashMap<String,Object> hashMap=new HashMap<>();
                    hashMap.put("id2",userid2);
                    hashMap.put("username",kullaniciadi.toLowerCase());


                    reference2.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                pd.dismiss();
                                Intent loginIntent2 = new Intent(Register1Activity.this,LoginActivity.class);
                                loginIntent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(loginIntent2);

                            }

                        }
                    });


                }
                else{
                    Toast.makeText(Register1Activity.this,"HATA",Toast.LENGTH_SHORT).show();
                }


            }


        });


    }

}

