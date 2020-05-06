package com.example.you.gaming;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.you.R;
import com.example.you.registration.PreGamePage;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GamePage12 extends AppCompatActivity {

    String gameStatus;

    ArrayList<String> usersNameYes, usersNameMaybe, usersNameNo;

    Button btn_Yes, btn_Maybe, btn_No, btn_Cont;

    TextView textView, textView2, textView_YES, textView_MAYBE, textView_NO;

    ConstraintLayout layoutPLay, layoutResult;

    FirebaseAuth mAuth;
    FirebaseDatabase db;
    DatabaseReference game, users;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page1);

        gameStatus = getIntent().getStringExtra("GameStatus");

        layoutPLay = (ConstraintLayout) findViewById(R.id.constraint_layout_GP1_play);
        layoutResult = (ConstraintLayout) findViewById(R.id.constraint_layout_GP1_result);

        btn_Yes = (Button) findViewById(R.id.button_YES);
        btn_Maybe = (Button) findViewById(R.id.button_MAYBE);
        btn_No = (Button) findViewById(R.id.button_NO);
        btn_Cont = (Button) findViewById(R.id.buttonContinue);

        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView_YES = (TextView) findViewById(R.id.textView_YES);
        textView_MAYBE = (TextView) findViewById(R.id.textView_MAYBE);
        textView_NO = (TextView) findViewById(R.id.textView_NO);

        textView.setText("Молодёжь способна принимать собственные решения.\n\n Young people are able to make their own decisions.");
        textView.setTextSize(20.0f);
        textView2.setText("Молодёжь способна принимать собственные решения.\n\n Young people are able to make their own decisions.");
        textView2.setTextSize(20.0f);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        user = mAuth.getCurrentUser();
        game = db.getReference("GAME").child("GP12");
        users = db.getReference("Users").child(user.getUid()).child("name");
        usersNameYes = new ArrayList<>();
        usersNameMaybe = new ArrayList<>();
        usersNameNo = new ArrayList<>();

        if (gameStatus.equals("YES")) {

            layoutResult.setVisibility(View.INVISIBLE);

            btn_Yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    users.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String userName = dataSnapshot.getValue(String.class);
                            game.child("Yes").child(user.getUid()).child("name").setValue(userName);
                            Intent intent = new Intent(GamePage12.this, PreGamePage.class);
                            intent.putExtra("GameStatus", gameStatus);
                            startActivity(intent);
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });
                }
            });

            btn_Maybe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    users.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String userName = dataSnapshot.getValue(String.class);
                            game.child("Maybe").child(user.getUid()).child("name").setValue(userName);
                            Intent intent = new Intent(GamePage12.this, PreGamePage.class);
                            intent.putExtra("GameStatus", gameStatus);
                            startActivity(intent);
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });
                }
            });

            btn_No.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    users.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String userName = dataSnapshot.getValue(String.class);
                            game.child("No").child(user.getUid()).child("name").setValue(userName);
                            Intent intent = new Intent(GamePage12.this, PreGamePage.class);
                            intent.putExtra("GameStatus", gameStatus);
                            startActivity(intent);
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });
                }
            });
        }

        if (gameStatus.equals("NO")) {

            layoutPLay.setVisibility(View.INVISIBLE);

            readDataYes(new FirebaseCallback() {
                @Override
                public void onCallback(List<String> list) {
                    String listData;
                    if (list.isEmpty()) {
                        listData = "";
                    } else {
                        listData = list.get(0);
                        for (int i=1; i<list.size(); i++) {
                            listData = listData + "\n" + list.get(i);
                        }
                    }
                    textView_YES.setText(listData);
                }
            });

            readDataMaybe(new FirebaseCallback() {
                @Override
                public void onCallback(List<String> list) {
                    String listData;
                    if (list.isEmpty()) {
                        listData = "";
                    } else {
                        listData = list.get(0);
                        for (int i=1; i<list.size(); i++) {
                            listData = listData + "\n" + list.get(i);
                        }
                    }
                    textView_MAYBE.setText(listData);
                }
            });

            readDataNo(new FirebaseCallback() {
                @Override
                public void onCallback(List<String> list) {
                    String listData;
                    if (list.isEmpty()) {
                        listData = "";
                    } else {
                        listData = list.get(0);
                        for (int i=1; i<list.size(); i++) {
                            listData = listData + "\n" + list.get(i);
                        }
                    }
                    textView_NO.setText(listData);
                }
            });

            btn_Cont.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(GamePage12.this, PreGamePage.class);
                    intent.putExtra("GameStatus", gameStatus);
                    startActivity(intent);
                }
            });
        }
    }

    private void readDataYes (final FirebaseCallback firebaseCallback) {
        game.child("Yes").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()) {
                    String retirevedUsersName = ds.child("name").getValue(String.class);
                    usersNameYes.add(retirevedUsersName);
                }
                firebaseCallback.onCallback(usersNameYes);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private void readDataMaybe (final FirebaseCallback firebaseCallback) {
        game.child("Maybe").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()) {
                    String retirevedUsersName = ds.child("name").getValue(String.class);
                    usersNameMaybe.add(retirevedUsersName);
                }
                firebaseCallback.onCallback(usersNameMaybe);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private void readDataNo (final FirebaseCallback firebaseCallback) {
        game.child("No").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()) {
                    String retirevedUsersName = ds.child("name").getValue(String.class);
                    usersNameNo.add(retirevedUsersName);
                }
                firebaseCallback.onCallback(usersNameNo);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private interface FirebaseCallback {
        void onCallback (List<String> list);
    }
}