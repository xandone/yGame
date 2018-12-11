package app.xandone.ygame.play;

import android.graphics.Point;

import java.util.List;

/**
 * author: Admin
 * created on: 2018/12/10 14:15
 * description:
 */
public interface PlayImpl {
    void play(Point point, List<Point> enemys);

    void repent();

    List<Point> getMyPoint();

    List<Point> getMyChess();

    boolean isWin();

    void reset();
}
