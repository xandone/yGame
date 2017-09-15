package app.xandone.ygame.gobang;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * author: xandone
 * created on: 2017/9/15 22:07
 */

public class Piece {
    private Paint mPaint;
    private Canvas mCanvas;
    private Bitmap mBitmap;
    private List<Point> mList;

    public Piece(Bitmap bitmap) {
        this.mBitmap = bitmap;
        mList = new ArrayList<>();
    }

    public void drawSelf(Paint paint, Canvas canvas, Point point) {
        if (mBitmap != null) {
            canvas.drawBitmap(mBitmap, point.x, point.y, paint);
        }
    }

    public void drawAll(Paint paint, Canvas canvas) {
        for (Point point : mList) {
            drawSelf(paint, canvas, point);
        }

    }

    public void addPonit(Point ponit) {
        mList.add(ponit);
    }

    public void removePoint(Point point) {
        mList.remove(point);
    }

    public void clearPoint() {
        mList.clear();
    }

    public boolean contains(Point point) {
        if (mList.contains(point)) {
            return true;
        }
        return false;
    }

}
