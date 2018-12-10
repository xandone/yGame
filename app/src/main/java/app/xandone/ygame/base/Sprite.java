package app.xandone.ygame.base;

import android.graphics.Canvas;
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
    protected List<Point> myChess = new ArrayList<>();
    protected Point lastPoint = new Point();
    protected int maxW, maxH;
    protected int current_repent = MAX_REPENT_COUNT;

    public static final int MAX_REPENT_COUNT = 3;

    @Override
    public List<Point> getMyPoint() {
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
        int size = myChess.size();
        int count = 1;
        if (size < 5) {
            return false;
        }
        Point point = myChess.get(size - 1);
        lastPoint.set(point.x, point.y);
        while (myChess.contains(lastPoint) && lastPoint.x > 0 && count < 5) {
            count++;
            lastPoint.set(lastPoint.x - 1, lastPoint.y);
        }
        if (count >= 5) {
            return true;
        }
        count = 1;
        while (myChess.contains(lastPoint) && lastPoint.x < maxW && count < 5) {
            count++;
            lastPoint.set(lastPoint.x + 1, lastPoint.y);
        }
        if (count >= 5) {
            return true;
        }
        count = 1;
        while (myChess.contains(lastPoint) && lastPoint.y > 0 && count < 5) {
            count++;
            lastPoint.set(lastPoint.x, lastPoint.y - 1);
        }
        if (count >= 5) {
            return true;
        }
        count = 1;
        while (myChess.contains(lastPoint) && lastPoint.y < maxH && count < 5) {
            count++;
            lastPoint.set(lastPoint.x, lastPoint.y + 1);
        }
        if (count >= 5) {
            return true;
        }

        count = 1;
        while (myChess.contains(lastPoint) && lastPoint.x > 0 && lastPoint.y > 0 && count < 5) {
            count++;
            lastPoint.set(lastPoint.x - 1, lastPoint.y - 1);
        }
        if (count >= 5) {
            return true;
        }

        count = 1;
        while (myChess.contains(lastPoint) && lastPoint.x < maxW && lastPoint.y < maxH && count < 5) {
            count++;
            lastPoint.set(lastPoint.x + 1, lastPoint.y + 1);
        }
        if (count >= 5) {
            return true;
        }

        return false;
    }

    protected abstract void beforeDraw(Canvas canvas);

    protected abstract void onDraw(Canvas canvas);

    protected abstract void afterDraw(Canvas canvas);

}
