package wang.index.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import wang.index.util.Utils;

/**
 * 创建日期: 15/10/6 下午8:55
 * 作者:wanghao
 * 描述:快速索引 用于 根据字母快速定位到联系人
 */

public class QuickIndexBar extends View {
    private static final String[] LETTERS = new String[]{
            "A", "B", "C", "D", "E", "F",
            "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R",
            "S", "T", "U", "V", "W", "X",
            "Y", "Z"};


    private Paint paint;
    float cellWidth;//控件的宽
    float cellHeight;//控件的高度
    int touchindex = -1;//点击字母的 位置
    public onLetterListener letterListener;

    public interface onLetterListener {
        public void onLetter(String letter);
    }


    public onLetterListener getLetterListener() {
        return letterListener;
    }

    public void setLetterListener(onLetterListener letterListener) {
        this.letterListener = letterListener;
    }

    public QuickIndexBar(Context context) {
        this(context, null);
    }

    public QuickIndexBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QuickIndexBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < LETTERS.length; i++) {

            String text = LETTERS[i];

            //获取字体的宽高
            Rect bounds = new Rect();
            paint.getTextBounds(text, 0, text.length(), bounds);

            //计算 位置
            int x = (int) (cellWidth / 2.0f - paint.measureText(text) / 2.0f);
            int y = (int) (cellHeight / 2.0f + bounds.height() / 2 + i * cellHeight);

            paint.setColor( touchindex == i?Color.GRAY:Color.BLACK);
            canvas.drawText(text, x, y, paint);

        }


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        cellWidth = getMeasuredWidth();
        int mHeight = getMeasuredHeight();
        cellHeight = (mHeight * 1.0f / LETTERS.length);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float y = -1;
        int index = -1;

        switch (MotionEventCompat.getActionMasked(event)) {
//        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                y = event.getY();
                index = (int) (y * 1.0 / cellHeight);
                System.out.println(index + "--" + y + "==" + cellHeight);
                if (index >= 0 && index < LETTERS.length) {

                    if (index != touchindex) {
                        if (letterListener != null) {
                            letterListener.onLetter(LETTERS[index]);
                        }
                        touchindex = index;
                    }
                }

                break;
            case MotionEvent.ACTION_MOVE:
                y = event.getY();
                index = (int) (y * 1.0 / cellHeight);

                if (index >= 0 && index < LETTERS.length) {

                    if (index != touchindex) {
                        if (letterListener != null) {
                            letterListener.onLetter(LETTERS[index]);
                        }
//                        Utils.showToast(getContext(), LETTERS[index]);
                        touchindex = index;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                index = -1;
                touchindex = -1;
                break;
            default:
                break;
        }
        invalidate();

        return true;
    }
}
