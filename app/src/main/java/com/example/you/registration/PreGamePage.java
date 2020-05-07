package com.example.you.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.you.R;
import com.example.you.test.GamePage1;


public class PreGamePage extends AppCompatActivity {

    Button startGame;

    String gameStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_game_page);

        startGame = (Button) findViewById(R.id.buttonPlay);

        gameStatus = "";

        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameStatus = "YES";
                Intent intent = new Intent(PreGamePage.this, GamePage1.class);
                intent.putExtra("GameStatus", gameStatus);
                startActivity(intent);
            }
        });

    }
}