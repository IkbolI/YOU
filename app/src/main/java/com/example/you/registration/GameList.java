package com.example.you.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.you.R;

public class GameList extends AppCompatActivity {

    Button btnGame9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);

        btnGame9 = (Button) findViewById(R.id.game9);
        btnGame9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GameList.this, PreGamePage.class));
            }
        });
    }
}
