package com.example.you.registration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.you.R;
import com.example.you.models.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AuthActivity extends AppCompatActivity {

    EditText username, email, password;
    Button registration;

    ConstraintLayout parentLayout;

    FirebaseAuth mAuth;
    FirebaseDatabase db;
    DatabaseReference users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        username = (EditText) findViewById(R.id.editText1);
        email = (EditText) findViewById(R.id.editText2);
        password = (EditText) findViewById(R.id.editText3);
        registration = (Button) findViewById(R.id.button_regist);
        parentLayout = (ConstraintLayout) findViewById(R.id.constraint_layout_auth);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("Users");

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            Intent intent = new Intent(AuthActivity.this, PreGamePage.class);
            startActivity(intent);
        } else {

            registration.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (TextUtils.isEmpty(username.getText().toString())) {
                        Snackbar.make(parentLayout, "Пожалуйста, введите имя пользователя", Snackbar.LENGTH_SHORT).show();
                        return;
                    }

                    if (TextUtils.isEmpty(email.getText().toString())) {
                        Snackbar.make(parentLayout, "Пожалуйста, введите э-почту", Snackbar.LENGTH_SHORT).show();
                        return;
                    }

                    if (TextUtils.isEmpty(password.getText().toString())) {
                        Snackbar.make(parentLayout, "Пожалуйста, введите пароль", Snackbar.LENGTH_SHORT).show();
                        return;
                    }

                    mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {

                                    FirebaseUser user2 = mAuth.getCurrentUser();

                                    User user = new User(
                                            email.getText().toString(),
                                            password.getText().toString(),
                                            username.getText().toString()
                                    );

                                    users.child(user2.getUid())
                                            .setValue(user)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Snackbar.make(parentLayout, "Регистрация прошла успешно!", Snackbar.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(AuthActivity.this, PreGamePage.class);
                                                    startActivity(intent);
                                                }
                                            });
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Snackbar.make(parentLayout, "Регистрация не удалась: " + e.getMessage(), Snackbar.LENGTH_LONG).show();
                                }
                            });
                }
            });
        }
    }
}

