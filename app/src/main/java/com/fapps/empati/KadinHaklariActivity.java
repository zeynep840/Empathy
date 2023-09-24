package com.fapps.empati;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class KadinHaklariActivity extends AppCompatActivity {
    private Toolbar mToolbar1;
    private ImageButton camera23;
    private DatabaseReference PostsRef1,SetupRef;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kadin_haklari);
        PostsRef1=FirebaseDatabase.getInstance().getReference().child("KadinHaklari");
        camera23=(ImageButton)findViewById(R.id.camera23);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(linearLayoutManager);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Kadın Hakları");
        camera23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent post2=new Intent(KadinHaklariActivity.this,KadinHaklariPostActivity.class);
                startActivity(post2);
                finish();
            }
        });
        DisplayAllUserPosts();

    }

    private void DisplayAllUserPosts() {
        final FirebaseRecyclerAdapter<KadinHaklari,KadinHaklariViewHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<KadinHaklari,KadinHaklariViewHolder>
                (
                        KadinHaklari.class,
                        R.layout.all_post_layout,
                     KadinHaklariViewHolder.class,
                        PostsRef1
                ) {
            @Override
            protected void populateViewHolder(KadinHaklariViewHolder viewHolder, KadinHaklari model, int position) {
                final String PostKey3 =getRef(position).getKey();

                viewHolder.setDescription2(model.getDescription2());
                viewHolder.setUsername(model.getUsername());

                viewHolder.setDate2(model.getDate2());
                viewHolder.setTime2(model.getTime2());
                viewHolder.CommentPostButton1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent commentPostIntent1=new Intent(KadinHaklariActivity.this,KadinHaklariYorumActivity.class);
                        commentPostIntent1.putExtra("PostKey3",PostKey3);
                        startActivity(commentPostIntent1);


                    }
                });
                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent clickPostIntent=new Intent(KadinHaklariActivity.this,ClickPostKadinHaklariActivity.class);
                        clickPostIntent.putExtra("PostKey3",PostKey3);
                        startActivity(clickPostIntent);

                    }
                });


            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }


    public static class KadinHaklariViewHolder extends RecyclerView.ViewHolder {
        View mView;
        Button CommentPostButton1;


        public KadinHaklariViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            CommentPostButton1=(Button) mView.findViewById(R.id.yorum2);

        }
//buraya setler yazılacak


        public void setDate2(String date2) {
            TextView datem2 = (TextView) mView.findViewById(R.id.date2);
            datem2.setText(" " + date2);

        }

        public void setTime2(String time2) {
            TextView timem2 = (TextView) mView.findViewById(R.id.time2);
            timem2.setText(" " + time2);

        }

        public void setUsername(String username) {
            TextView usernamem2 = (TextView) mView.findViewById(R.id.post_username2);
            usernamem2.setText(" " + username);
        }

        public void setDescription2(String description2) {
            TextView descreption2 = (TextView) mView.findViewById(R.id.descreption2);
            descreption2.setText(" " + description2);
        }




    }
    }
