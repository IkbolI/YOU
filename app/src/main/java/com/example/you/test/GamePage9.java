package com.example.you.test;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.you.R;
import com.example.you.adapter.CommentAdapter;
import com.example.you.adapter.UserAdapter;
import com.example.you.models.AnswerObject;
import com.example.you.models.Comment;
import com.example.you.models.UserModel;
import com.example.you.registration.GameList;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.List;

public class GamePage9 extends AppCompatActivity implements View.OnClickListener {
    TextView mainQuestion;
    LinearLayout blockOne;
    Button btn_yes, btn_maybe, btn_no;
    MaterialEditText comment;
    Button btn_send;
    LinearLayout blockTwo;
    RecyclerView recycler_yes, recycler_maybe, recycler_no;
    RecyclerView recycler_comments;
    Button btn_next;

    FirebaseAuth mAuth;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    final DatabaseReference game = db.getReference("GameMain").child("GP9");
    final DatabaseReference users = db.getReference("Users").child(user.getUid());


    List<UserModel> userListYes;
    List<UserModel> userListMaybe;
    List<UserModel> userListNo;
    List<Comment> commentList;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_main);

        mainQuestion = (TextView) findViewById(R.id.mainQuestion);
        blockOne = (LinearLayout) findViewById(R.id.blockOne);
        btn_yes = (Button) findViewById(R.id.btn_yes);
        btn_maybe = (Button) findViewById(R.id.btn_maybe);
        btn_no = (Button) findViewById(R.id.btn_no);
        comment = (MaterialEditText) findViewById(R.id.comment);
        btn_send = (Button) findViewById(R.id.btn_send);
        blockTwo = (LinearLayout) findViewById(R.id.blockTwo);
        recycler_yes = (RecyclerView) findViewById(R.id.recycler_yes);
        recycler_maybe = (RecyclerView) findViewById(R.id.recycler_maybe);
        recycler_no = (RecyclerView) findViewById(R.id.recycler_no);
        recycler_comments = (RecyclerView) findViewById(R.id.recycler_comments);
        btn_next = (Button) findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnNextClick();
            }
        });

        mainQuestion.setText(" Взрослые слишком много критикуют молодёжь.");
        mAuth = FirebaseAuth.getInstance();

        userListYes = new ArrayList<>();
        userListMaybe = new ArrayList<>();
        userListNo = new ArrayList<>();
        commentList = new ArrayList<>();

        btn_yes.setOnClickListener(this);
        btn_maybe.setOnClickListener(this);
        btn_no.setOnClickListener(this);
        btn_send.setOnClickListener(this);

    }

    @Override
    public void onClick (View v){
        switch (v.getId()) {
            case R.id.btn_yes:
                btnYesClick();
                break;
            case R.id.btn_maybe:
                btnMaybeClick();
                break;
            case R.id.btn_no:
                btnNoClick();
                break;
            case R.id.btn_send:
                btnSendClick();
                break;
//            case R.id.btn_next:
//                btnNextClick();
//                break;
        }
    }

    public void btnNextClick () {
        startActivity(new Intent(GamePage9.this, GamePage10.class));

    }

    public void btnYesClick () {
        btn_maybe.setEnabled(false);
        btn_no.setEnabled(false);
        btn_maybe.setBackground(getResources().getDrawable(R.drawable.btn_disabled));
        btn_no.setBackground(getResources().getDrawable(R.drawable.btn_disabled));
        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String userName = dataSnapshot.child("name").getValue(String.class);
                String imageUrl = dataSnapshot.child("imageUrl").getValue(String.class);
                AnswerObject answerObject = new AnswerObject(userName, imageUrl);
                game.child("Yes").child(user.getUid()).setValue(answerObject);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, GameList.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
    public void btnMaybeClick () {
        btn_yes.setEnabled(false);
        btn_no.setEnabled(false);
        btn_yes.setBackground(getResources().getDrawable(R.drawable.btn_disabled));
        btn_no.setBackground(getResources().getDrawable(R.drawable.btn_disabled));
        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String userName = dataSnapshot.child("name").getValue(String.class);
                String imageUrl = dataSnapshot.child("imageUrl").getValue(String.class);
                AnswerObject answerObject = new AnswerObject(userName, imageUrl);
                game.child("Maybe").child(user.getUid()).setValue(answerObject);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void btnNoClick () {
        btn_maybe.setEnabled(false);
        btn_yes.setEnabled(false);
        btn_maybe.setBackground(getResources().getDrawable(R.drawable.btn_disabled));
        btn_yes.setBackground(getResources().getDrawable(R.drawable.btn_disabled));
        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String userName = dataSnapshot.child("name").getValue(String.class);
                String imageUrl = dataSnapshot.child("imageUrl").getValue(String.class);
                AnswerObject answerObject = new AnswerObject(userName, imageUrl);
                game.child("No").child(user.getUid()).setValue(answerObject);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void btnSendClick () {
        users.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String userName = (String) dataSnapshot.child("name").getValue();
                String imageUrl = (String) dataSnapshot.child("imageUrl").getValue();
                if (comment.getText().toString().trim().length() > 0) {
                    String commentText = comment.getText().toString();
                    Comment comment = new Comment(userName, imageUrl, commentText);
                    game.child("Comment").child(user.getUid()).setValue(comment);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        blockOne.setVisibility(View.GONE);
        blockTwo.setVisibility(View.VISIBLE);

        /**
         * Loading data from Firebase
         * YES, MAYBE, NO
         * Comments
         */
        loadData();
    }

    public void loadData () {
        readYesData();
        readMaybeData();
        readNoData();
        readComments();

    }

    public void readYesData () {
        game.child("Yes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        String retrievedUsersName = ds.child("name").getValue(String.class);
                        String imageUrl = ds.child("imageUrl").getValue(String.class);
                        UserModel userModel = new UserModel(retrievedUsersName, imageUrl);
                        userListYes.add(userModel);
                    }

                    UserAdapter yesAdapter = new UserAdapter(GamePage9.this, userListYes);
                    recycler_yes.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recycler_yes.setAdapter(yesAdapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }


    public void readMaybeData () {
        game.child("Maybe").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        String retrievedUsersName = ds.child("name").getValue(String.class);
                        String imageUrl = ds.child("imageUrl").getValue(String.class);
                        UserModel userModel = new UserModel(retrievedUsersName, imageUrl);
                        userListMaybe.add(userModel);
                    }

                    UserAdapter maybeAdapter = new UserAdapter(GamePage9.this, userListMaybe);
                    recycler_maybe.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recycler_maybe.setAdapter(maybeAdapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void readNoData () {
        game.child("No").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        String retrievedUsersName = ds.child("name").getValue(String.class);
                        String imageUrl = ds.child("imageUrl").getValue(String.class);
                        UserModel userModel = new UserModel(retrievedUsersName, imageUrl);
                        userListNo.add(userModel);
                    }

                    UserAdapter noAdapter = new UserAdapter(GamePage9.this, userListNo);
                    recycler_no.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recycler_no.setAdapter(noAdapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void readComments () {
        game.child("Comment").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        String retrievedUsersName = ds.child("name").getValue(String.class);
                        String comment = ds.child("comment").getValue(String.class);
                        String imageUrl = ds.child("imageUrl").getValue(String.class);
                        Comment commentObject = new Comment(retrievedUsersName, imageUrl, comment);
                        commentList.add(commentObject);
                    }

                    CommentAdapter adapter = new CommentAdapter(GamePage9.this, commentList);
                    recycler_comments.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recycler_comments.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

}
