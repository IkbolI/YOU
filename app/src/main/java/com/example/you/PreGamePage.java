package com.example.you;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PreGamePage extends AppCompatActivity {

    Button startGame, seeResults;

    String gameStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_game_page);

        startGame = (Button) findViewById(R.id.buttonPlay);
        seeResults = (Button) findViewById(R.id.buttonResult);

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

        seeResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameStatus = "NO";
                Intent intent = new Intent(PreGamePage.this, GamePage1.class);
                intent.putExtra("GameStatus", gameStatus);
                startActivity(intent);
            }
        });
    }
}
