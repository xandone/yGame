package app.xandone.ygame;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import app.xandone.ygame.gobang.GameView;

/**
 * author: Admin
 * created on: 2018/12/11 16:11
 * description:
 */
public class GameActivity extends AppCompatActivity {
    private GameView gameView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_game_layout);

        gameView = (GameView) findViewById(R.id.gameView);
    }

    public void reset(View view) {
        gameView.reset();
    }
}
