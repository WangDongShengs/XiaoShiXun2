package com.wds.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class MyProgressBar extends View {
    private static final String TAG = "MyProgressBar";
    private float mHeight;
    private float mWidth;
    private int mRingColor;
    private float mRingWidth;
    private int mCircleColor;
    private float mTextSize;
    private int mTextColor;
    private int mStartAngle;
    private int mSweepAngle;
    private float mRadius;
    private float mCenterXY;
    private Paint mCirclePaint;
    private Paint mTextPaint;
    private String mProgress;
    private RectF mRectF;
    private Paint mSweepPaint;
    private final float mDy;

    public MyProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        /*for (int i = 0; i < attrs.getAttributeCount(); i++) {
            String attributeName = attrs.getAttributeName(i);
            //如果是引用类型的话,attributeValue就变成了@+数字的字符串
            String attributeValue = attrs.getAttributeValue(i);
            //getResources().getColor(attributeValue)
            //getResources().getString(attributeValue)
            Log.d(TAG, "MyProgressBar: "+"attributeName:"+attributeName+",attributeValue"+attributeValue);
        }*/
        //系统提供的一个帮助类,可以直接过去里面的属性值,就算是引用类型的,也可以直接获取
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyProgressBar);
        if (ta != null){
            //获取属性
            mHeight = ta.getDimension(R.styleable.MyProgressBar_android_layout_height, 200);
            mWidth = ta.getDimension(R.styleable.MyProgressBar_android_layout_width, 200);
            mRingColor = ta.getColor(R.styleable.MyProgressBar_ringColor, 0);
            mRingWidth = ta.getDimension(R.styleable.MyProgressBar_ringWidth, 10);
            mCircleColor = ta.getColor(R.styleable.MyProgressBar_circleColor, 0);
            mTextSize = ta.getDimension(R.styleable.MyProgressBar_android_textSize, 16);
            mTextColor = ta.getColor(R.styleable.MyProgressBar_android_textColor, 0);
            mStartAngle = ta.getInteger(R.styleable.MyProgressBar_startAngle, -90);
            mSweepAngle = ta.getInteger(R.styleable.MyProgressBar_sweepAngle, 0);

            ta.recycle();//释放资源
        }

        //获取圆的画笔
        mCirclePaint = new Paint();
        mCirclePaint.setColor(mCircleColor);
        mCirclePaint.setAntiAlias(true);//设置画笔抗锯齿
        //获取文本画笔
        mTextPaint = new Paint();
        mTextPaint.setColor(mTextColor);
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setTextAlign(Paint.Align.CENTER);//设置文本对齐方式

        //获取绘制文本对象
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        float v1 = fontMetrics.descent - fontMetrics.ascent;
        mDy = v1/2-fontMetrics.descent;

        float v = mSweepAngle * 1.0f / 360 * 100;
        mProgress = (int)v+" %";
        //获取圆环画笔
        mSweepPaint = new Paint();
        mSweepPaint.setAntiAlias(true);
        mSweepPaint.setColor(mRingColor);
        mSweepPaint.setStrokeWidth(mRingWidth);
        mSweepPaint.setStyle(Paint.Style.STROKE);//绘制圆环
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int max = (int) Math.max(mWidth, mHeight);
        setMeasuredDimension(max,max);
        //setMeasuredDimension((int) mWidth,(int)mHeight);
        mRadius = max*1.0f/4;
        mCenterXY = max *1.0f/2;

        float v = max * 0.9f;
        mRectF = new RectF(max*0.1f, max*0.1f,v , v);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //1.环里面的圆
        canvas.drawCircle(mCenterXY,mCenterXY,mRadius,mCirclePaint);
        //2.画文字
        canvas.drawText(mProgress,mCenterXY,mCenterXY+mDy,mTextPaint);

        //3.画进度,扇形
        canvas.drawArc(mRectF,mStartAngle,mSweepAngle,false,mSweepPaint);
    }

    /**
     *
     * @param progress 0-100
     */
    public void setProgress(int progress) {
        mSweepAngle =(int)(progress*3.6f) ;
        mProgress = progress+" %";
        invalidate();//只能在ui线程中调用
        //postInvalidate();// 可以在非ui线程中调用
    }
}
