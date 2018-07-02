package com.example.admin.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //0 = O and 1 = X
    int activePlayer = 0;

    boolean gameIsActive = true;

    int[] gameState = {2,2,2,2,2,2,2,2,2};

    int [][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8} , {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    public void dropIn(View view){
        ImageView counter = (ImageView) view;
        counter.getTag().toString();

        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter] == 2 && gameIsActive) {

            gameState[tappedCounter] = activePlayer;


            counter.setTranslationY(-1000f);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.o);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.xx);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);

            for(int[] winningPosition : winningPositions) {
                if(gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {

                    String winner = "X";
                    if(gameState[winningPosition[0]] == 0) {
                        winner = "O";
                    }
                    gameIsActive = false;

                    TextView playAgainText = (TextView)findViewById(R.id.playAgainText);
                    playAgainText.setText(winner + " has WON");

                    LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);
                }
                else {
                    boolean gameIsOver = true;
                    for(int counterState :  gameState) {
                        if(counterState == 2) {
                            gameIsOver = false;
                        }
                    }
                    if(gameIsOver) {
                        TextView playAgainText = (TextView)findViewById(R.id.playAgainText);
                        playAgainText.setText( "Its a DRAW");

                        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
                        layout.setVisibility(View.VISIBLE);

                    }
                }
            }
        }
    }

    public void playAgain(View view){
        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);

        activePlayer = 0;
        gameIsActive = true;
        for(int i = 0; i < 9; i++) {
           gameState[i] = 2;
        }

        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);
        for(int i = 0; i < gridLayout.getChildCount(); i++) {
            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
