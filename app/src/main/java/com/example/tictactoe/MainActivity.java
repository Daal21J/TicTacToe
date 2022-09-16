package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;


public class MainActivity extends AppCompatActivity {
    public static final String info="INFO";
    public void quit(View view){ //if user clicks in quit, he will be taken to previous activity

        finish();
    }
    String player_1;
    String player_2;
    Intent inIntent;
    int counter=0; //when this counter reaches 9 and winning condition is not verified, then the game is set to draw
    int[] gameState={0,0,0,0,0,0,0,0,0}; //initial state of the game, all positions are empty
    int [][] winPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};//if these positions are tapped by the same player, then he wins
    int activePlayer=1; // player 1 starts the game
    boolean active=true; //the game is set to active

    public void play(View view){
        ImageView sign=(ImageView) view;
        int tapped=Integer.parseInt(sign.getTag().toString()); // we get the tapped position

      if(gameState[tapped]==0 && active) { //if the position is still empty, and the game is active
          gameState[tapped]=activePlayer; //then we set the value of gamestate in tapped position (index) to the player who tapped it
          if (activePlayer == 1) { //in case it was player 1
              sign.setImageResource(R.drawable.cross);
              counter++; //one move was played, counter is increased
              activePlayer = 2; //now player 2 should play

          } else {
              sign.setImageResource(R.drawable.circle); //if it was player 2
              activePlayer = 1; //set player to player 1
              counter++; //increase counter
          }
          for (int[] wP : winPositions) {
              if (gameState[wP[0]] == gameState[wP[1]] && gameState[wP[1]] == gameState[wP[2]] && gameState[wP[0]] != 0) { //if the player tapped the winning positions
                  active=false; //the game stops
                  String winner = "";
                  //we set the winning player
                  if (activePlayer == 2) {
                      winner = player_1;
                  }
                  if(activePlayer == 1){
                      winner = player_2;
                  }
                  Button pAgainButton;
                  Button quitButton;
                  LottieAnimationView animationView=(LottieAnimationView)findViewById(R.id.animationView);
                  GridLayout gridLayout=(GridLayout)findViewById(R.id.gridLayout);
                  TextView winText;
                  pAgainButton=(Button)findViewById(R.id.pAgainButton);
                  quitButton=(Button)findViewById(R.id.quitButton);
                  winText=(TextView)findViewById(R.id.winText);
                  winText.setText(winner+" has won!"); //we display the winning player
                  winText.setVisibility(View.VISIBLE);
                  gridLayout.setVisibility(view.INVISIBLE);
                  animationView.setVisibility(view.VISIBLE);
                  pAgainButton.setVisibility(View.VISIBLE);//the play again button is now appearing
                  quitButton.setVisibility(View.VISIBLE);
                  counter=0;

              }
              else if(counter==9){ //if the counter reached 9, and no player won, in this case:
                  active=false; //the game stops
                  Button pAgainButton;
                  Button quitButton;
                  TextView winText;
                  pAgainButton=(Button)findViewById(R.id.pAgainButton);
                  quitButton=(Button)findViewById(R.id.quitButton);
                  winText=(TextView)findViewById(R.id.winText);
                  winText.setText("noone won"); //we display that noone won
                  winText.setVisibility(View.VISIBLE);
                  quitButton.setVisibility(View.VISIBLE);
                  pAgainButton.setVisibility(View.VISIBLE); //button play again appears

              }
          }
      }
    }


    public void playAgain(View view){
        Button pAgainButton;
        TextView winText;
        LottieAnimationView animationView=(LottieAnimationView)findViewById(R.id.animationView);
        GridLayout gridLayout=(GridLayout)findViewById(R.id.gridLayout);
        pAgainButton=(Button)findViewById(R.id.pAgainButton);
        winText=(TextView)findViewById(R.id.winText);
        winText.setVisibility(View.INVISIBLE);
        pAgainButton.setVisibility(View.INVISIBLE);
        animationView.setVisibility(View.INVISIBLE);
        gridLayout.setVisibility(View.VISIBLE);
        for(int i = 0; i < gridLayout.getChildCount(); i++) {
            ImageView sign = (ImageView) gridLayout.getChildAt(i);
            sign.setImageDrawable(null);

        }
        for (int i=0;i<gameState.length;i++){
            gameState[i]=0;
        }
         counter=0;
         activePlayer=1;
         active=true;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inIntent=getIntent();
        player_1=inIntent.getStringExtra(home.PlayerOne);
        player_2=inIntent.getStringExtra(home.PlayerTwo);
    }
}