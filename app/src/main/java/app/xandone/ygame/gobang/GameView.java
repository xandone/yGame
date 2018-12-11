package app.xandone.ygame.gobang;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import app.xandone.ygame.R;
import app.xandone.ygame.base.Sprite;
import app.xandone.ygame.conifg.Config;
import app.xandone.ygame.play.GobangImpl;
import app.xandone.ygame.player.HumanPlayer;

/**
 * author: xandone
 * created on: 2018/12/10 20:40
 */
public class GameView extends View implements GobangImpl {
    private int mWidth;
    private int mHeight;
    private int mRow;
    private int mCol;

    private List<Point> freePoints;
    private Paint mPaint;
    private Canvas mBufferCanvas;
    private Bitmap mBufferBitmap;

    private HumanPlayer mHuman;
    private Sprite AIPlayer;
    private Bitmap mWhiteBtm;
    private Bitmap mBlackBtm;

    private boolean isHuman;


    private int win_type = -1;
    public static final int TYPE_HUMAN_WIN = 1;
    public static final int TYPE_AI_WIN = 2;

    public GameView(Context context) {
        this(context, null);
    }

    public GameView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GameView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        freePoints = new ArrayList<>();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLACK);

        mWhiteBtm = BitmapFactory.decodeResource(getResources(), R.drawable.p2);
        mBlackBtm = BitmapFactory.decodeResource(getResources(), R.drawable.p1);

        isHuman = true;
    }

    @Override
    public List<Point> getFreePoints() {
        return freePoints;
    }

    @Override
    public int getW() {
        return mWidth;
    }

    @Override
    public int getH() {
        return mHeight;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mBufferBitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
        mBufferCanvas = new Canvas(mBufferBitmap);
        mHuman = new HumanPlayer(Bitmap.createScaledBitmap(mWhiteBtm, Config.GRID_WIDTH, Config.GRID_WIDTH, false), mWidth, mHeight);
        AIPlayer = new HumanPlayer(Bitmap.createScaledBitmap(mBlackBtm, Config.GRID_WIDTH, Config.GRID_WIDTH, false), mWidth, mHeight);

        mRow = mWidth / Config.GRID_WIDTH;
        mCol = mHeight / Config.GRID_WIDTH;

        freePoints.clear();
        for (int i = 0; i < mRow; i++) {
            for (int j = 0; j < mCol; j++) {
                freePoints.add(new Point(i, j));
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawLine(mBufferCanvas);
        canvas.drawBitmap(mBufferBitmap, 0, 0, mPaint);
        mHuman.draw(canvas, mPaint);
        AIPlayer.draw(canvas, mPaint);
    }

    private void drawLine(Canvas canvas) {
        int offerX = mWidth - mRow * Config.GRID_WIDTH;
        int offerY = mHeight - mCol * Config.GRID_WIDTH;
        for (int i = 0; i <= mCol; i++) {
            canvas.drawLine(0, i * Config.GRID_WIDTH, mWidth - offerX, i * Config.GRID_WIDTH, mPaint);
        }
        for (int i = 0; i <= mRow; i++) {
            canvas.drawLine(i * Config.GRID_WIDTH, 0, i * Config.GRID_WIDTH, mHeight - offerY, mPaint);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                float x = event.getX();
                float y = event.getY();
                int point_x = Math.round(x / Config.GRID_WIDTH);
                int point_y = Math.round(y / Config.GRID_WIDTH);
                Point point = new Point(point_x, point_y);
                if (!isFreeBlock(point)) {
                    return true;
                }
                if (isHuman) {
                    mHuman.play(point);
                    changeFreeChess(point);
                    swithChess();
                    win_type = TYPE_HUMAN_WIN;
                    refreshPanel(mHuman.isWin());
                } else {
                    win_type = TYPE_AI_WIN;
                    AIPlayer.play(point);
                    changeFreeChess(point);
                    swithChess();
                    refreshPanel(AIPlayer.isWin());
                }
                break;
        }
        return true;
    }

    private void swithChess() {
        isHuman = !isHuman;
    }

    private void refreshPanel(boolean isWin) {
        invalidate();
        if (!isWin) {
            return;
        }
        switch (win_type) {
            case TYPE_HUMAN_WIN:
                showWinDialog("恭喜人类赢了");
                break;
            case TYPE_AI_WIN:
                showWinDialog("恭喜AI赢了");
                break;
        }
    }

    private void showWinDialog(String msg) {
        new AlertDialog.Builder(getContext())
                .setTitle("恭喜")
                .setMessage(msg)
                .setCancelable(true)
                .show();
    }

    private void changeFreeChess(Point point) {
        freePoints.remove(point);
    }

    private boolean isFreeBlock(Point point) {
        if (freePoints.contains(point)) {
            return true;
        } else {
            return false;
        }
    }

}
