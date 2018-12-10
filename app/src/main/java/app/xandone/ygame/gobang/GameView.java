package app.xandone.ygame.gobang;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import app.xandone.ygame.R;
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

    private List<Point> freePoints;
    private Paint mPaint;
    private Canvas mBufferCanvas;
    private Bitmap mBufferBitmap;

    private HumanPlayer mHuman;
    private Bitmap mWhiteBtm;
    private Bitmap mBlackBtm;

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
        mHuman = new HumanPlayer(Bitmap.createScaledBitmap(mWhiteBtm, Config.GRID_WIDTH, Config.GRID_WIDTH, false));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawLine(mBufferCanvas);
        canvas.drawBitmap(mBufferBitmap, 0, 0, mPaint);
        mHuman.draw(canvas, mPaint);
    }

    private void drawLine(Canvas canvas) {
        int row = mWidth / Config.GRID_WIDTH;
        int col = mHeight / Config.GRID_WIDTH;
        int offerX = mWidth - row * Config.GRID_WIDTH;
        int offerY = mHeight - col * Config.GRID_WIDTH;
        for (int i = 0; i <= col; i++) {
            canvas.drawLine(0, i * Config.GRID_WIDTH, mWidth - offerX, i * Config.GRID_WIDTH, mPaint);
        }
        for (int i = 0; i <= row; i++) {
            canvas.drawLine(i * Config.GRID_WIDTH, 0, i * Config.GRID_WIDTH, mHeight - offerY, mPaint);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                float x = event.getX();
                float y = event.getY();
                int point_x = Math.round(x / Config.GRID_WIDTH) * Config.GRID_WIDTH - Config.GRID_WIDTH / 2;
                int point_y = Math.round(y / Config.GRID_WIDTH) * Config.GRID_WIDTH - Config.GRID_WIDTH / 2;
                Point point = new Point(point_x, point_y);
                mHuman.play(point);
                invalidate();
                break;
        }
        return true;
    }
}
