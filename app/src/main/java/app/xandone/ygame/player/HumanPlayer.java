package app.xandone.ygame.player;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;

import app.xandone.ygame.base.Sprite;
import app.xandone.ygame.conifg.Config;

/**
 * author: xandone
 * created on: 2018/12/10 21:54
 */
public class HumanPlayer extends Sprite {

    public HumanPlayer(Bitmap bitmap, int w, int h) {
        super(bitmap, w, h);
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
        myPoint.add(point);
        int point_x = point.x * Config.GRID_WIDTH - Config.GRID_WIDTH / 2;
        int point_y = point.y * Config.GRID_WIDTH - Config.GRID_WIDTH / 2;
        Point chess = new Point(point_x, point_y);
        myChess.add(chess);
    }
}
