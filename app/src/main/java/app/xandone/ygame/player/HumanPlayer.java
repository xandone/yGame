package app.xandone.ygame.player;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import app.xandone.ygame.base.Sprite;

/**
 * author: xandone
 * created on: 2018/12/10 21:54
 */
public class HumanPlayer extends Sprite {

    public HumanPlayer(Bitmap bitmap) {
        this.mBitmap = bitmap;
    }

    @Override
    protected void beforeDraw(Canvas canvas, Paint paint) {

    }

    @Override
    protected void onDraw(Canvas canvas, Paint paint) {
        for (Point point : myChess) {
            canvas.drawBitmap(mBitmap, point.x, point.y, paint);
        }
    }

    @Override
    protected void afterDraw(Canvas canvas, Paint paint) {

    }

    @Override
    public void play(Point point) {
        myChess.add(point);
    }
}
