package com.fapps.empati;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends AppCompatActivity {

    private Button ResetPasswordSendEmailButton;
    private EditText ResetEmailInput;
    private Toolbar mToolbar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth=FirebaseAuth.getInstance();
        setContentView(R.layout.activity_reset_password);
        ResetPasswordSendEmailButton=(Button)findViewById(R.id.reset_password_email_button);
        ResetEmailInput=(EditText)findViewById(R.id.reset_password_EMAIL);

        ResetPasswordSendEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail=ResetEmailInput.getText().toString();
                if(TextUtils.isEmpty(userEmail)){
                    Toast.makeText(ResetPasswordActivity.this,"Lütfen geçerli bir email adresi giriniz...",Toast.LENGTH_SHORT).show();
                }
                else{
                    mAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(ResetPasswordActivity.this,"Şifrenizi sıfırlamak istiyorsanız lütfen e-posta hesabını kontrol ediniz...",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ResetPasswordActivity.this,LoginActivity.class));


                            }
                            else{
                                String message=task.getException().getMessage();
                                Toast.makeText(ResetPasswordActivity.this,"Hata..."+message,Toast.LENGTH_SHORT).show();

                            }

                        }
                    });
                }
            }
        });
    }
}
