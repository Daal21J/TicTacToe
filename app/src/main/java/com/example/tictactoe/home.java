package com.example.tictactoe;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class home extends AppCompatActivity {
    EditText playerOneName;
    EditText playerTwoName;
    String tmpText="";
    Intent outIntent;
    Button letsPlayButton;
    public static final String PlayerOne="PLAYER_ONE";
    public static final String PlayerTwo="PLAYER_TWO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        playerOneName=(EditText)findViewById(R.id.playerOneName);
        playerTwoName=(EditText)findViewById(R.id.playerTwoName);


        letsPlayButton=(Button)findViewById(R.id.letsPlayButton);

        letsPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                outIntent = new Intent(home.this,MainActivity.class);
                tmpText=playerOneName.getText().toString();
                outIntent.putExtra(PlayerOne,tmpText);
                tmpText=playerTwoName.getText().toString();
                outIntent.putExtra(PlayerTwo,tmpText);
                startActivity(outIntent);

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        playerOneName.setText("");
        playerTwoName.setText("");
    }
}