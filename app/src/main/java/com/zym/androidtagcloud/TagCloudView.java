package com.zym.androidtagcloud;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class TagCloudView extends View {

    private static final String TAG = TagCloudView.class.getSimpleName();

    public ArrayList<Tag> tags = new ArrayList<>();
    //    Paint paint = new Paint();
    private int TOP = 0;
    private int BOTTOM = 300;
    private int LEFT = 0;
    private int RIGHT = 300;
    boolean isDraw = false;
    boolean isNew = false;

    public TagCloudView(Context context) {
        super(context);
    }

    public TagCloudView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TagCloudView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TagCloudView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < tags.size(); i++) {
            Tag tag = tags.get(i);
//            int centerX = (tag.getRect().left + tag.getRect().right) / 2;
//            int centerY = (tag.getRect().top + tag.getRect().bottom) / 2;
//            canvas.rotate(1, centerX, centerY);
            canvas.drawText(tag.text, tag.getRect().left, tag.getRect().bottom, tag.paint);

//            canvas.drawPath(tag.path, tag.paint);
//            canvas.drawTextOnPath(tag.text, tag.path, 0, 20, tag.paint);
        }
        super.onDraw(canvas);
    }

    public void init() {
        int positionX = (BOTTOM + TOP) / 2;
        int positionY = (RIGHT + LEFT) / 2;
//        Log.i(TAG, "TOP:" + TOP + "BOTTOM:" + BOTTOM + "LEFT:" + LEFT + "RIGHT:"
//                + RIGHT + ", tags size:" + tags.size());

        Random random = new Random();
        for (int i = 0; i < tags.size(); i++) {
            Tag tag = tags.get(i);
            if (i % 2 == 0) {
                tag.x = positionX + random.nextInt((RIGHT - LEFT) / 10);
                tag.y = positionY + random.nextInt((BOTTOM - TOP) / 10);
            } else {
                tag.x = positionX - random.nextInt((RIGHT - LEFT) / 10);
                tag.y = positionY - random.nextInt((BOTTOM - TOP) / 10);
            }

            int x = 0;
            while (x < i) {
                isNew = false;
                // 新生成tag与之前的tag相比较判断是否交叉,交叉则重新生成tag,否则比较下一个直到与之前tag都不交叉
                while (Rect.intersects(tag.getRect(), tags.get(x).getRect())) {
                    int randomX = random.nextInt(RIGHT + LEFT);
                    int randomY = random.nextInt(BOTTOM + TOP);

                    while (randomX + tag.getRect().width() > RIGHT
                            || randomY + tag.getRect().height() > BOTTOM
                            || randomX < LEFT || randomY < TOP) {
                        randomX = random.nextInt(RIGHT + LEFT);
                        randomY = random.nextInt(BOTTOM + TOP);
                    }

                    tag.x = randomX;
                    tag.y = randomY;

                    isNew = true;
                }

                if (isNew) {
                    x = -1;
                }
                x++;
            }

            tag.setPath();

            Log.i(TAG, tag.text + ", left:" + tag.getRect().left + ", top:" + tag.getRect().top
                    + ", right:" + tag.getRect().right + ", bottom:" + tag.getRect().bottom);
        }

        isDraw = true;
    }

    public void addTag(@NonNull String text) {
        Tag tag = new Tag();
        tag.text = text;
        tag.setPaint();
        tags.add(tag);
    }

    public void start() {
        XRunnable runnable = new XRunnable();
        new Thread(runnable).start();
        if (isDraw) {
            postInvalidate();
        }
    }

    class XRunnable implements Runnable {

        @Override
        public void run() {
            init();
        }
    }

    public void setShowRect(int left, int top, int right, int bottom) {
        LEFT = left;
        TOP = top;
        RIGHT = right;
        BOTTOM = bottom;
    }
}
