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
import android.widget.Toast;

import app.xandone.ygame.R;
import app.xandone.ygame.Utils;

/**
 * author: xandone
 * created on: 2017/9/15 16:31
 */

public class GobangView extends View {
    private Context mContext;
    private Paint mPaintBg;
    private Paint mPaint;
    private Bitmap mBitmapBg;
    private Canvas mCavasBuffer;

    private int mWidth, mHeight;
    private int mDefaultSize;
    private int mPieceSize;

    private Piece mWhitePiece;
    private Piece mBlackPiece;
    private Bitmap mWhiteBtm;
    private Bitmap mBlackBtm;
    private boolean isWhite;

    public static final int MAX_LINE_W = 8;
    public static final int MAX_LINE_H = 10;

    public GobangView(Context context) {
        this(context, null);
    }

    public GobangView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GobangView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    public void init() {
        mPaintBg = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintBg.setDither(true);
        mPaintBg.setStyle(Paint.Style.STROKE);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.STROKE);

        mDefaultSize = Utils.dp2px(mContext, 100);

        mWhiteBtm = BitmapFactory.decodeResource(getResources(), R.drawable.p2);
        mBlackBtm = BitmapFactory.decodeResource(getResources(), R.drawable.p1);

        isWhite = true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmapBg, 0, 0, mPaint);
        mWhitePiece.drawAll(mPaint, canvas);
        mBlackPiece.drawAll(mPaint, canvas);
    }

    public void drawbgBuffer(Canvas canvas) {
        mPaint.setColor(Color.BLACK);
        mPieceSize = mWidth / MAX_LINE_W;
        int startX = 0;
        int startY = 0;
        int endX = mPieceSize * MAX_LINE_W;
        int endY = 0;
        for (int i = 0; i <= MAX_LINE_H; i++) {
            startY = i * mPieceSize;
            endY = i * mPieceSize;
            canvas.drawLine(startX, startY, endX, endY, mPaint);
        }
        startY = 0;
        endY = mPieceSize * MAX_LINE_H;
        for (int i = 0; i <= MAX_LINE_W; i++) {
            startX = i * mPieceSize;
            endX = i * mPieceSize;
            canvas.drawLine(startX, startY, endX, endY, mPaint);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w = measureDimension(widthMeasureSpec);
        int h = measureDimension(heightMeasureSpec);
        setMeasuredDimension(w, h);
    }

    public int measureDimension(int measureDir) {
        int size;
        int specMode = MeasureSpec.getMode(measureDir);
        int specSize = MeasureSpec.getSize(measureDir);

        switch (specMode) {
            case MeasureSpec.AT_MOST:
                size = Math.min(mDefaultSize, specSize);
                break;
            case MeasureSpec.EXACTLY:
                size = specSize;
                break;
            case MeasureSpec.UNSPECIFIED:
                size = mDefaultSize;
                break;
            default:
                size = mDefaultSize;
                break;
        }
        return size;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mBitmapBg = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCavasBuffer = new Canvas(mBitmapBg);
        drawbgBuffer(mCavasBuffer);

        mWhitePiece = new Piece(Bitmap.createScaledBitmap(mWhiteBtm, mPieceSize, mPieceSize, false));
        mBlackPiece = new Piece(Bitmap.createScaledBitmap(mBlackBtm, mPieceSize, mPieceSize, false));

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                int x = (int) event.getX();
                int y = (int) event.getY();
                int point_x = x / mPieceSize * mPieceSize - mPieceSize / 2;
                int point_y = y / mPieceSize * mPieceSize - mPieceSize / 2;
                Point point = new Point(point_x, point_y);
                if (isWhite) {
                    if (mWhitePiece.contains(point)) {
                        return true;
                    }
                    mWhitePiece.addPonit(point);
                } else {
                    if (mBlackPiece.contains(point)) {
                        return true;
                    }
                    mBlackPiece.addPonit(point);
                }
                isWhite = !isWhite;
                invalidate();
                break;
        }

        return true;
    }
}
