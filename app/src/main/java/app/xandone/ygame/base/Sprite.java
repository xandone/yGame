package app.xandone.ygame.base;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;

import app.xandone.ygame.play.PlayImpl;

/**
 * author: Admin
 * created on: 2018/12/10 15:20
 * description:
 */
public abstract class Sprite implements PlayImpl {
    protected List<Point> myChess = new ArrayList<>();//保存绘制的点(实际像素)
    protected List<Point> myPoint = new ArrayList<>();//保存点(格子)
    protected Point lastPoint = new Point();
    protected int maxW, maxH;
    protected int current_repent = MAX_REPENT_COUNT;

    protected Bitmap mBitmap;
    protected List<Point> freePoints;

    public static final int MAX_REPENT_COUNT = 3;

    public Sprite(Bitmap bitmap, int w, int h, List<Point> freePoints) {
        this.mBitmap = bitmap;
        this.maxW = w;
        this.maxH = h;
        this.freePoints = freePoints;
    }

    @Override
    public List<Point> getMyPoint() {
        return myPoint;
    }

    @Override
    public List<Point> getMyChess() {
        return myChess;
    }

    @Override
    public void repent() {
        current_repent--;
        if (current_repent <= 0) {
            current_repent = 0;
        }
    }

    @Override
    public boolean isWin() {
        int size = myPoint.size();
        int count = 0;
        if (size < 5) {
            return false;
        }
        Point point = myPoint.get(size - 1);
        lastPoint.set(point.x, point.y);
        while (myPoint.contains(lastPoint) && lastPoint.x > 0 && count < 5) {
            count++;
            lastPoint.set(lastPoint.x - 1, lastPoint.y);
        }
        if (count >= 5) {
            return true;
        }
        count = 0;
        lastPoint.set(point.x, point.y);
        while (myPoint.contains(lastPoint) && lastPoint.x < maxW && count < 5) {
            count++;
            lastPoint.set(lastPoint.x + 1, lastPoint.y);
        }
        if (count >= 5) {
            return true;
        }
        count = 0;
        lastPoint.set(point.x, point.y);
        while (myPoint.contains(lastPoint) && lastPoint.y > 0 && count < 5) {
            count++;
            lastPoint.set(lastPoint.x, lastPoint.y - 1);
        }
        if (count >= 5) {
            return true;
        }
        count = 0;
        lastPoint.set(point.x, point.y);
        while (myPoint.contains(lastPoint) && lastPoint.y < maxH && count < 5) {
            count++;
            lastPoint.set(lastPoint.x, lastPoint.y + 1);
        }
        if (count >= 5) {
            return true;
        }

        count = 0;
        lastPoint.set(point.x, point.y);
        while (myPoint.contains(lastPoint) && lastPoint.x > 0 && lastPoint.y > 0 && count < 5) {
            count++;
            lastPoint.set(lastPoint.x - 1, lastPoint.y - 1);
        }
        if (count >= 5) {
            return true;
        }

        count = 0;
        lastPoint.set(point.x, point.y);
        while (myPoint.contains(lastPoint) && lastPoint.x < maxW && lastPoint.y < maxH && count < 5) {
            count++;
            lastPoint.set(lastPoint.x + 1, lastPoint.y + 1);
        }
        if (count >= 5) {
            return true;
        }
        
        count = 0;
        lastPoint.set(point.x, point.y);
        while (myPoint.contains(lastPoint) && lastPoint.x > 0 && lastPoint.y > 0 && count < 5) {
            count++;
            lastPoint.set(lastPoint.x + 1, lastPoint.y - 1);
        }
        if (count >= 5) {
            return true;
        }

        count = 0;
        lastPoint.set(point.x, point.y);
        while (myPoint.contains(lastPoint) && lastPoint.x < maxW && lastPoint.y < maxH && count < 5) {
            count++;
            lastPoint.set(lastPoint.x - 1, lastPoint.y + 1);
        }
        if (count >= 5) {
            return true;
        }

        return false;
    }

    public void draw(Canvas canvas, Paint paint) {
        beforeDraw(canvas, paint);
        onDraw(canvas, paint);
        afterDraw(canvas, paint);
    }

    protected abstract void beforeDraw(Canvas canvas, Paint paint);

    protected abstract void onDraw(Canvas canvas, Paint paint);

    protected abstract void afterDraw(Canvas canvas, Paint paint);

}
