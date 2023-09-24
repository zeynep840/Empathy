package com.fapps.empati;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class TurizmYorumActivity extends AppCompatActivity {
    private EditText edt_yorum_ekle;
    private ImageView profil_resmi, gonder;
    private String Post_Key, saveCurrentDate, saveCurrentTime, postRandom,current_user_id;
    private DatabaseReference postsRef,UsersReference;
    private RecyclerView CommentsList;
    private FirebaseAuth mAuth;

    private String uid, postid,current_user_id1;
    private FirebaseUser firebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turizm_yorum);
        Post_Key = getIntent().getExtras().get("PostKey2").toString();
        postsRef = FirebaseDatabase.getInstance().getReference().child("Turizm").child(Post_Key).child("Comments");
        mAuth=FirebaseAuth.getInstance();
        current_user_id1=mAuth.getCurrentUser().getUid();
        UsersReference=FirebaseDatabase.getInstance().getReference().child("Users3");



        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        edt_yorum_ekle = (EditText) findViewById(R.id.edt_yorumEkle_yorumlarActivity);

        gonder = (ImageView) findViewById(R.id.post_comment_btn);
        Intent intent = getIntent();
        postid = intent.getStringExtra(postid);
        uid = intent.getStringExtra(uid);
        CommentsList=(RecyclerView)findViewById(R.id.recycler_view_yorumlarActivity);
        CommentsList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        CommentsList.setLayoutManager(linearLayoutManager);

        gonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //usersreference komutları eklenecek
                UsersReference.child(current_user_id1).addValueEventListener(new ValueEventListener() {
                    @Override

                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            String comments = edt_yorum_ekle.getText().toString();
                            if (TextUtils.isEmpty(comments)) {
                                Toast.makeText(TurizmYorumActivity.this, "Boş Yorum Gönderemezsin...", Toast.LENGTH_SHORT).show();
                            } else {
                                String userFullname = dataSnapshot.child("username").getValue().toString();
                                String postid = postsRef.push().getKey();
                                Calendar calFordDate = Calendar.getInstance();
                                SimpleDateFormat currentDate = new SimpleDateFormat("dd-MMM-yyyy");
                                saveCurrentDate = currentDate.format(calFordDate.getTime());
                                Calendar calFordTime=Calendar.getInstance();
                                SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss");

                                saveCurrentTime = currentTime.format(calFordTime.getTime());
                                postRandom = saveCurrentDate + saveCurrentTime;

                                DatabaseReference yorumlarYolu = FirebaseDatabase.getInstance().getReference("Yorumlar").child(postid);
                                HashMap<String, Object> commentsMap = new HashMap<>();
                                commentsMap .put("yorum", comments);
                                commentsMap .put("date", saveCurrentDate);
                                commentsMap .put("time", saveCurrentTime);
                                commentsMap.put("username", userFullname);
                                //boş yollamasını sağlıyor
                                edt_yorum_ekle.setText("");


                                postsRef.child(postRandom).updateChildren(commentsMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(TurizmYorumActivity.this, "Başarılı bir şekilde yüklendi", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(TurizmYorumActivity.this, "Eror", Toast.LENGTH_SHORT).show();

                                        }


                                    }
                                });
                            }

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });





            }
        });



    }




    @Override
    protected void onStart() {

        super.onStart();
        FirebaseRecyclerAdapter<TurizmYorum,TurizmYorumViewHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<TurizmYorum, TurizmYorumViewHolder>
                (TurizmYorum.class,
                        R.layout.all_comments_layout,
                        TurizmYorumViewHolder.class,
                        postsRef

                )
        {
            @Override
            protected void populateViewHolder(TurizmYorumViewHolder viewHolder, TurizmYorum model, int position) {
                viewHolder.setUsername(model.getUsername());
                viewHolder.setDate(model.getDate());
                viewHolder.setTime(model.getTime());
                viewHolder.setYorum(model.getYorum());


            }

        };
        CommentsList.setAdapter(firebaseRecyclerAdapter);
    }

    public static class TurizmYorumViewHolder extends RecyclerView.ViewHolder{
        View mView;

        public TurizmYorumViewHolder(@NonNull View itemView) {
            super(itemView);
            mView=itemView;
        }
        //setler yazılacak
        public void setUsername(String username) {
            TextView myUsername=(TextView)mView.findViewById(R.id.comment_username);
            myUsername.setText("@ "+username);

        }
        public void setTime(String time) {
            TextView myTime=(TextView)mView.findViewById(R.id.comment_time);
            myTime.setText(" Saat:"+time);

        }
        public void setDate(String date) {
            TextView myDate=(TextView)mView.findViewById(R.id.comment_date);
            myDate.setText(" Tarih:"+date);

        }
        public void setYorum(String yorum) {
            TextView myComment=(TextView)mView.findViewById(R.id.comment_text);
            myComment.setText(yorum);

        }



    }
    }
