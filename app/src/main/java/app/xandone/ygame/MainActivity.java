package app.xandone.ygame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import app.xandone.ygame.gobang.GameView;


public class MainActivity extends AppCompatActivity {
    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gameView = (GameView) findViewById(R.id.gameView);
        findViewById(R.id.resetBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameView.reset();
            }
        });
    }
}
