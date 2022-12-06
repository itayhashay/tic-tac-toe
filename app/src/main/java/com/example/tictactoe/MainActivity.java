package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton[][] buttons = new ImageButton[3][3];
    private int clickedButtons;
    private ImageView turn_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        turn_image = findViewById(R.id.main_turn_image);
        clickedButtons = 0;
        buttons[0][0] = findViewById(R.id.main_btn_00);
        buttons[0][0].setTag(R.drawable.empty);
        buttons[0][1] = findViewById(R.id.main_btn_01);
        buttons[0][1].setTag(R.drawable.empty);
        buttons[0][2] = findViewById(R.id.main_btn_02);
        buttons[0][2].setTag(R.drawable.empty);
        buttons[1][0] = findViewById(R.id.main_btn_10);
        buttons[1][0].setTag(R.drawable.empty);
        buttons[1][1] = findViewById(R.id.main_btn_11);
        buttons[1][1].setTag(R.drawable.empty);
        buttons[1][2] = findViewById(R.id.main_btn_12);
        buttons[1][2].setTag(R.drawable.empty);
        buttons[2][0] = findViewById(R.id.main_btn_20);
        buttons[2][0].setTag(R.drawable.empty);
        buttons[2][1] = findViewById(R.id.main_btn_21);
        buttons[2][1].setTag(R.drawable.empty);
        buttons[2][2] = findViewById(R.id.main_btn_22);
        buttons[2][2].setTag(R.drawable.empty);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setOnClickListener(this);
            }
        }

        Button newGameButton = findViewById(R.id.main_new_game_btn);
        newGameButton.setOnClickListener(view -> newGame());
    }

    @Override
    public void onClick(View v) {
        Integer resource = (Integer)((ImageButton) v).getTag();
        if (!resource.toString().equals("" + R.drawable.empty)) {
            return;
        }
        if (clickedButtons % 2 == 0) {
            ((ImageButton) v).setImageResource(R.drawable.x);
            ((ImageButton) v).setTag(R.drawable.x);
            turn_image.setImageResource(R.drawable.oplay);
        } else {
            ((ImageButton) v).setImageResource(R.drawable.o);
            ((ImageButton) v).setTag(R.drawable.o);
            turn_image.setImageResource(R.drawable.xplay);
        }
        ((ImageButton) v).setClickable(false);
        clickedButtons++;
        if (didGameEnd()) {
            if (clickedButtons % 2 != 0) {
                gameEnd(1);
            } else {
                gameEnd(2);
            }
        } else if (clickedButtons == 9) {
            gameEnd(0);
        }
    }

    private boolean didGameEnd() {
        Integer[][] board = new Integer[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = (Integer)buttons[i][j].getTag();
            }
        }
        for (int i = 0; i < 3; i++) {
            if ((board[i][0].toString().equals(board[i][1].toString()) && board[i][0].toString().equals(board[i][2].toString()) && !board[i][0].toString().equals("" + R.drawable.empty + ""))
                    || (board[0][i].toString().equals(board[1][i].toString()) && board[0][i].toString().equals(board[2][i].toString()) && !board[0][i].toString().equals("" + R.drawable.empty + ""))) {
                return true;
            }
        }

        if ((board[0][0].toString().equals(board[1][1].toString()) && board[0][0].toString().equals(board[2][2].toString()) && !board[0][0].toString().equals("" + R.drawable.empty))
                || (board[0][2].toString().equals(board[1][1].toString()) && board[0][2].toString().equals(board[2][0].toString()) && !board[1][1].toString().equals("" + R.drawable.empty))) {
            return true;
        }
        return false;
    }

    private void gameEnd(int status) {
        ImageView win_image = findViewById(R.id.main_win_image);
        Button newGameBtn = findViewById(R.id.main_new_game_btn);
        if(status == 1) {
            win_image.setImageResource(R.drawable.xwin);
        } else if(status == 2) {
            win_image.setImageResource(R.drawable.owin);
        } else {
            win_image.setImageResource(R.drawable.nowin);
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setClickable(false);
            }
        }
        turn_image.setVisibility(View.INVISIBLE);
        win_image.setVisibility(View.VISIBLE);
        newGameBtn.setVisibility(View.VISIBLE);
    }

    private void newGame() {
        ImageView win_image = findViewById(R.id.main_win_image);
        Button newGameBtn = findViewById(R.id.main_new_game_btn);
        turn_image.setVisibility(View.VISIBLE);
        win_image.setVisibility(View.INVISIBLE);
        newGameBtn.setVisibility(View.INVISIBLE);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setImageResource(R.drawable.empty);
                buttons[i][j].setTag(R.drawable.empty);
                buttons[i][j].setClickable(true);
            }
        }
        turn_image.setImageResource(R.drawable.xplay);
        clickedButtons = 0;
    }
}