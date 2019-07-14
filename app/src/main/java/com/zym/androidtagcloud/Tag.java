package com.zym.androidtagcloud;

import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.Log;

import java.util.Random;

public class Tag {

    private static final int TEXT_SIZE_MAX = 60;
    private static final int TEXT_SIZE_MIN = 10;
    private static final String TAG = Tag.class.getSimpleName();
    public String text;
    public int strWidth;
    public Paint paint = new Paint();
    public Path path = new Path();
    public float x, y;
    public int alpha;
    public Random random = new Random();
    public float scale = TEXT_SIZE_MIN + random.nextInt(TEXT_SIZE_MAX - TEXT_SIZE_MIN + 1);
    public int randomColor = 0xff000000 | random.nextInt(0x0077ffff);

    public Rect getRect() {
        return new Rect((int) x, (int) y, (int) (x + strWidth),
                (int) (y + scale));
    }

    public void setPaint() {
        paint.setColor(randomColor);
        paint.setTextSize(scale);
        strWidth = (int) Math.ceil(paint.measureText(text));
    }

    public void setPath() {
        path.moveTo(getRect().left, getRect().top);
        path.lineTo(getRect().right, getRect().bottom);

        Log.i(TAG, "setPath, tagText: " + text);
        Log.i(TAG, "setPath, moveTo startX: " + getRect().left + ", startY: " + getRect().top);
        Log.i(TAG, "setPath, lineTo endX: " + getRect().right + ", endY: " + getRect().bottom);
    }

}
