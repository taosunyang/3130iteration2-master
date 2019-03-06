package com.kcb.timeTable;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.widget.TextView;

//Created by Yang Liu and Sunyang Tao
public class CornerTextView extends TextView {
    //background color &radius
    private int mBgColor = 0;
    private int mCornerSize = 0;

    public CornerTextView(Context context, int bgColor, int cornerSize) {
        super(context);
        mBgColor = bgColor;
        mCornerSize = cornerSize;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(mBgColor);
        paint.setAlpha(180);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRoundRect(new RectF(0, 0, getMeasuredWidth(), getMeasuredHeight()), mCornerSize, mCornerSize, paint);

        super.onDraw(canvas);
    }
}
