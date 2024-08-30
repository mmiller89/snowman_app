package com.reinarc.snowmangame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    String userName = "";
    EditText nameInput;
    Switch hardToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

    }

    public void startGame(View view){
        nameInput = findViewById(R.id.textInputEditText);
        hardToggle = findViewById(R.id.difficulty);
        boolean hard = hardToggle.isChecked();
        userName = String.valueOf(nameInput.getText());

        Intent i = new Intent(MainActivity.this, MainGame.class);
        i.putExtra("name", userName);

        if (hard){
            i.putExtra("difficulty", "Hard");
        } else {
            i.putExtra("difficulty", "Normal");
        }

        startActivity(i);
    }


}