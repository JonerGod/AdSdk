package com.idouzi.ad.ui;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

/**
 * Created by liuxueping on 2018/4/19.
 */

public class TextImageView extends AppCompatImageView{
    String drawText ="";
    int currentIndex  =0;
    ValueAnimator animator;
    String text  ="测试数据正在进行中。。。";
    public TextImageView(Context context) {
        super(context);
    }

    public TextImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(!TextUtils.isEmpty(drawText)){
            drawText(canvas,drawText);
        }

    }
    private void drawText(Canvas canvas,String text){
        Paint paint  = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(60);
        paint.setColor(Color.GREEN);
        int x = getWidth()/2;
        Paint.FontMetricsInt mFontMetricsInt  =paint.getFontMetricsInt();
        int y = (getHeight() - mFontMetricsInt.ascent - mFontMetricsInt.descent) / 2;
        canvas.drawText(text,x,y,paint);
    }
    public void loadText(){
        if(animator!=null){
            if(!animator.isRunning()){
                animator.start();
            }
            return;
        }
        animator  = ValueAnimator.ofInt(0,text.length()-1);
        animator.setDuration(text.length()*300);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int index = (int) animation.getAnimatedValue()+1;
                if(index!=currentIndex){
                    drawText = text.substring(0,index);
                    currentIndex = index;

                    if(currentIndex==text.length()){
                        if (animator != null) {
                            animator.end();
                        }
                    }
                    invalidate();
                }

            }
        });
        animator.start();
    }
}
