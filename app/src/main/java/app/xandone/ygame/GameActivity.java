package app.xandone.ygame;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import app.xandone.ygame.conifg.Config;
import app.xandone.ygame.gobang.GameView;

/**
 * author: Admin
 * created on: 2018/12/11 16:11
 * description:
 */
public class GameActivity extends AppCompatActivity {
    private GameView gameView;
    private ImageView chess_iv;
    private TextView chess_turn_tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_game_layout);

        gameView = (GameView) findViewById(R.id.gameView);
        chess_iv = (ImageView) findViewById(R.id.chess_iv);
        chess_turn_tv = (TextView) findViewById(R.id.chess_turn_tv);

        gameView.setChessTurnCallback(new GameView.ChessTurnHelper() {
            @Override
            public void callback(int turn) {
                if (turn == Config.CHESS_HUMAN_TURN) {
                    chess_iv.setImageResource(R.drawable.p2);
                    chess_turn_tv.setText("人类");
                } else {
                    chess_iv.setImageResource(R.drawable.p1);
                    chess_turn_tv.setText("AI");
                }
            }
        });
    }

    public void reset(View view) {
        gameView.reset();
    }
}
