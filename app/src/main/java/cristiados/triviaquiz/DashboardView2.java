package cristiados.triviaquiz;

import androidx.core.content.*;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;

import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DashboardView2 extends View {

    private int mRadius; 
    private int mStartAngle = 150;
    private int mSweepAngle = 240;
    private int mMin = 0; 
    private int mMax = 1000; 
    private int mSection = 10; 
    private int mPortion = 3; 
    private String mHeaderText = "MEJOR LOGRO ADQUIRIDO";
    private int mCreditValue = 650; 
    private int i_5 = 800;
    private int i_4 = 600;
    private int i_3 = 400;
    private int i_2 = 200;
    private int i_1 =  0;
    private int mSolidCreditValue = mCreditValue; 
    private int mSparkleWidth;
    private int mProgressWidth; 
    private float mLength1; 
    private int mCalibrationWidth; //
    private float mLength2; 
    private String best_date;
    private int mPadding;
    private float mCenterX, mCenterY; 
    private Paint mPaint;
    private RectF mRectFProgressArc;
    private RectF mRectFCalibrationFArc;
    private RectF mRectFTextArc;
    private Path mPath;
    private Rect mRectText;
    private String[] mTexts;
    public int mBackgroundColor;
    private int[] mBgColors;
    private Typeface tb;
    private boolean isAnimFinish = true;
    private float mAngleWhenAnim;

    public DashboardView2(Context context) {
        this(context, null);
    }

    public DashboardView2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DashboardView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        mSparkleWidth = dp2px(10);
        mProgressWidth = dp2px(3);
        mCalibrationWidth = dp2px(10);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeCap(Paint.Cap.ROUND);

        mRectFProgressArc = new RectF();
        mRectFCalibrationFArc = new RectF();
        mRectFTextArc = new RectF();
        mPath = new Path();
        mRectText = new Rect();

        mTexts  = new String[11];
        for (int i = 0; i < 11 ;i++ ) {
            mTexts[i] = "" + i*(mMax /10);
        }        
        
        mBgColors = new int[]{ContextCompat.getColor(getContext(), R.color.color_red),
                ContextCompat.getColor(getContext(), R.color.color_orange),
                ContextCompat.getColor(getContext(), R.color.color_yellow),
                ContextCompat.getColor(getContext(), R.color.color_green),
                ContextCompat.getColor(getContext(), R.color.color_blue)};
        mBackgroundColor = mBgColors[0];

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mPadding = Math.max(
                Math.max(getPaddingLeft(), getPaddingTop()),
                Math.max(getPaddingRight(), getPaddingBottom())
        );
        setPadding(mPadding, mPadding, mPadding, mPadding);

        mLength1 = mPadding + mSparkleWidth / 2f + dp2px(8);
        mLength2 = mLength1 + mCalibrationWidth + dp2px(1) + dp2px(5);

        int width = resolveSize(dp2px(220), widthMeasureSpec);
        mRadius = (width - mPadding * 2) / 2;

        setMeasuredDimension(width, width - dp2px(30));

        mCenterX = mCenterY = getMeasuredWidth() / 2f;
        mRectFProgressArc.set(
                mPadding + mSparkleWidth / 2f,
                mPadding + mSparkleWidth / 2f,
                getMeasuredWidth() - mPadding - mSparkleWidth / 2f,
                getMeasuredWidth() - mPadding - mSparkleWidth / 2f
        );

        mRectFCalibrationFArc.set(
                mLength1 + mCalibrationWidth / 2f,
                mLength1 + mCalibrationWidth / 2f,
                getMeasuredWidth() - mLength1 - mCalibrationWidth / 2f,
                getMeasuredWidth() - mLength1 - mCalibrationWidth / 2f
        );

        mPaint.setTextSize(sp2px(12));
        mPaint.getTextBounds("0", 0, "0".length(), mRectText);
        mRectFTextArc.set(
                mLength2 + mRectText.height(),
                mLength2 + mRectText.height(),
                getMeasuredWidth() - mLength2 - mRectText.height(),
                getMeasuredWidth() - mLength2 - mRectText.height()
        );
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(mBackgroundColor);

        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mProgressWidth);
        mPaint.setAlpha(80);
        canvas.drawArc(mRectFProgressArc, mStartAngle + 1, mSweepAngle - 2, false, mPaint);

        mPaint.setAlpha(255);
        if (isAnimFinish) {
            mPaint.setShader(generateSweepGradient());
            canvas.drawArc(mRectFProgressArc, mStartAngle + 1,
                    calculateRelativeAngleWithValue(mCreditValue) - 2, false, mPaint);
            float[] point = getCoordinatePoint(
                    mRadius - mSparkleWidth / 2f,
                    mStartAngle + calculateRelativeAngleWithValue(mCreditValue)
            );
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setShader(generateRadialGradient(point[0], point[1]));
            canvas.drawCircle(point[0], point[1], mSparkleWidth / 2f, mPaint);
        } else {
            mPaint.setShader(generateSweepGradient());
            canvas.drawArc(mRectFProgressArc, mStartAngle + 1,
                    mAngleWhenAnim - mStartAngle - 2, false, mPaint);

            float[] point = getCoordinatePoint(
                    mRadius - mSparkleWidth / 2f,
                    mAngleWhenAnim
            );
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setShader(generateRadialGradient(point[0], point[1]));
            canvas.drawCircle(point[0], point[1], mSparkleWidth / 2f, mPaint);
        }

        mPaint.setShader(null);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.WHITE);
        mPaint.setAlpha(80);
        mPaint.setStrokeCap(Paint.Cap.SQUARE);
        mPaint.setStrokeWidth(mCalibrationWidth);
        canvas.drawArc(mRectFCalibrationFArc, mStartAngle + 3, mSweepAngle - 6, false, mPaint);

        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(dp2px(2));
        mPaint.setAlpha(120);
        float x0 = mCenterX;
        float y0 = mPadding + mLength1 + dp2px(1);
        float x1 = mCenterX;
        float y1 = y0 + mCalibrationWidth;
        canvas.save();
        canvas.drawLine(x0, y0, x1, y1, mPaint);
        float degree = mSweepAngle / mSection;
        for (int i = 0; i < mSection / 2; i++) {
            canvas.rotate(-degree, mCenterX, mCenterY);
            canvas.drawLine(x0, y0, x1, y1, mPaint);
        }
        canvas.restore();
        
        canvas.save();
        for (int i = 0; i < mSection / 2; i++) {
            canvas.rotate(degree, mCenterX, mCenterY);
            canvas.drawLine(x0, y0, x1, y1, mPaint);
        }
        canvas.restore();

        mPaint.setStrokeWidth(dp2px(1));
        mPaint.setAlpha(80);
        mPaint.setTypeface(tb);
        float x2 = mCenterX;
        float y2 = y0 + mCalibrationWidth - dp2px(2);
        
        canvas.save();
        canvas.drawLine(x0, y0, x2, y2, mPaint);
        degree = mSweepAngle / (mSection * mPortion);
        for (int i = 0; i < (mSection * mPortion) / 2; i++) {
            canvas.rotate(-degree, mCenterX, mCenterY);
            canvas.drawLine(x0, y0, x2, y2, mPaint);
        }
        canvas.restore();
        canvas.save();
        for (int i = 0; i < (mSection * mPortion) / 2; i++) {
            canvas.rotate(degree, mCenterX, mCenterY);
            canvas.drawLine(x0, y0, x2, y2, mPaint);
        }
        canvas.restore();

        mPaint.setTextSize(sp2px(20));
        mPaint.setTextAlign(Paint.Align.LEFT);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTypeface(tb);
        mPaint.setAlpha(160);
        for (int i = 0; i < mTexts.length; i++) {
            mPaint.getTextBounds(mTexts[i], 0, mTexts[i].length(), mRectText);
            float θ = (float) (180 * mRectText.width() / 2 /
                    (Math.PI * (mRadius - mLength2 - mRectText.height())));

            mPath.reset();
            mPath.addArc(
                    mRectFTextArc,
                    mStartAngle + i * (mSweepAngle / mSection) - θ,
                    mSweepAngle
            );
            canvas.drawTextOnPath(mTexts[i], mPath, 0, 0, mPaint);
        }

        mPaint.setAlpha(255);
        mPaint.setTextSize(sp2px(60));
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setTypeface(tb);
        String value = String.valueOf(mSolidCreditValue);
        canvas.drawText(value, mCenterX, mCenterY + dp2px(30), mPaint);

        mPaint.setAlpha(160);
        mPaint.setTextSize(sp2px(20));
        mPaint.setTypeface(tb);
        canvas.drawText(mHeaderText, mCenterX, mCenterY - dp2px(20), mPaint);

        mPaint.setAlpha(255);
        mPaint.setTextSize(sp2px(30));
        mPaint.setTypeface(tb);
        canvas.drawText(calculateCreditDescription(), mCenterX, mCenterY + dp2px(55), mPaint);

        mPaint.setAlpha(160);
        mPaint.setTypeface(tb);
        mPaint.setTextSize(sp2px(20));


        canvas.drawText(best_date, mCenterX, mCenterY + dp2px(70), mPaint);
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                Resources.getSystem().getDisplayMetrics());
    }

    private int sp2px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,
                Resources.getSystem().getDisplayMetrics());
    }

    private SweepGradient generateSweepGradient() {
        SweepGradient sweepGradient = new SweepGradient(mCenterX, mCenterY,
                new int[]{Color.argb(0, 255, 255, 255), Color.argb(200, 255, 255, 255)},
                new float[]{0, calculateRelativeAngleWithValue(mCreditValue) / 360}
        );
        Matrix matrix = new Matrix();
        matrix.setRotate(mStartAngle - 1, mCenterX, mCenterY);
        sweepGradient.setLocalMatrix(matrix);

        return sweepGradient;
    }

    private RadialGradient generateRadialGradient(float x, float y) {
        return new RadialGradient(x, y, mSparkleWidth / 2f,
                new int[]{Color.argb(255, 255, 255, 255), Color.argb(80, 255, 255, 255)},
                new float[]{0.4f, 1},
                Shader.TileMode.CLAMP
        );
    }

    private float[] getCoordinatePoint(float radius, float angle) {
        float[] point = new float[2];

        double arcAngle = Math.toRadians(angle);
        if (angle < 90) {
            point[0] = (float) (mCenterX + Math.cos(arcAngle) * radius);
            point[1] = (float) (mCenterY + Math.sin(arcAngle) * radius);
        } else if (angle == 90) {
            point[0] = mCenterX;
            point[1] = mCenterY + radius;
        } else if (angle > 90 && angle < 180) {
            arcAngle = Math.PI * (180 - angle) / 180.0;
            point[0] = (float) (mCenterX - Math.cos(arcAngle) * radius);
            point[1] = (float) (mCenterY + Math.sin(arcAngle) * radius);
        } else if (angle == 180) {
            point[0] = mCenterX - radius;
            point[1] = mCenterY;
        } else if (angle > 180 && angle < 270) {
            arcAngle = Math.PI * (angle - 180) / 180.0;
            point[0] = (float) (mCenterX - Math.cos(arcAngle) * radius);
            point[1] = (float) (mCenterY - Math.sin(arcAngle) * radius);
        } else if (angle == 270) {
            point[0] = mCenterX;
            point[1] = mCenterY - radius;
        } else {
            arcAngle = Math.PI * (360 - angle) / 180.0;
            point[0] = (float) (mCenterX + Math.cos(arcAngle) * radius);
            point[1] = (float) (mCenterY - Math.sin(arcAngle) * radius);
        }

        return point;
    }

    private float calculateRelativeAngleWithValue(int value) {
        float degreePerSection = 1f * mSweepAngle / mSection;
        if (value > i_5) {
            return 8 * degreePerSection + 2 * degreePerSection / 200 * (value - i_5);
        } else if (value > i_4) {
            return 6 * degreePerSection + 2 * degreePerSection / 200 * (value - i_4);
        } else if (value > i_3) {
            return 4 * degreePerSection + 2 * degreePerSection / 200 * (value - i_3);
        } else if (value > i_2) {
            return 2 * degreePerSection + 2 * degreePerSection / 200 * (value - i_2);
        } else {
            return 2 * degreePerSection / 200 * (value - i_1);
        }
    }

    private String calculateCreditDescription() {
        if (mSolidCreditValue > i_5) {
            return "Alma Heroica";
        } else if (mSolidCreditValue > i_4) {
            return "Alma Ferviente ";
        } else if (mSolidCreditValue > i_3) {
            return "Alma Piadosa";
        } else if (mSolidCreditValue > i_2) {
            return "Alma Buena";
        }
        return "Alma Creyente";
    }

    private SimpleDateFormat mDateFormat;

    private String getFormatTimeStr() {
        if (mDateFormat == null) {
            mDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        }
        return String.format("Fecha:%s", mDateFormat.format(new Date()));
    }

    public int getCreditValue() {
        return mCreditValue;
    }

    public void setBest_date(String bestdate){
        best_date = bestdate;
    }
    public void setCreditValue(int creditValue) {
        if (mSolidCreditValue == creditValue || creditValue < mMin || creditValue > mMax) {
            return;
        }

        mSolidCreditValue = creditValue;
        mCreditValue = creditValue;
        postInvalidate();
    }
    public void setMBackgroundColor(int set){
        mBackgroundColor = set;
    }

    public void setCreditValueWithAnim(int creditValue) {
        if (creditValue < mMin || creditValue > mMax || !isAnimFinish) {
            return;
        }

        mSolidCreditValue = creditValue;

        ValueAnimator creditValueAnimator = ValueAnimator.ofInt(i_1, mSolidCreditValue);
        creditValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCreditValue = (int) animation.getAnimatedValue();
                postInvalidate();
            }
        });

        
        float degree = calculateRelativeAngleWithValue(mSolidCreditValue);

        ValueAnimator degreeValueAnimator = ValueAnimator.ofFloat(mStartAngle, mStartAngle + degree);
        degreeValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mAngleWhenAnim = (float) animation.getAnimatedValue();
            }
        });

        ObjectAnimator colorAnimator = ObjectAnimator.ofInt(this, "mBackgroundColor", mBgColors[0], mBgColors[0]);
        long delay = 1000;
        if (mSolidCreditValue > i_5) {
            colorAnimator.setIntValues(mBgColors[0], mBgColors[1], mBgColors[2], mBgColors[3], mBgColors[4]);
            delay = 3000;
        } else if (mSolidCreditValue > i_4) {
            colorAnimator.setIntValues(mBgColors[0], mBgColors[1], mBgColors[2], mBgColors[3]);
            delay = 2500;
        } else if (mSolidCreditValue > i_3) {
            colorAnimator.setIntValues(mBgColors[0], mBgColors[1], mBgColors[2]);
            delay = 2000;
        } else if (mSolidCreditValue > i_2) {
            colorAnimator.setIntValues(mBgColors[0], mBgColors[1]);
            delay = 1500;
        }
        colorAnimator.setEvaluator(new ArgbEvaluator());
        colorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mBackgroundColor = (int) animation.getAnimatedValue();
            }
        });

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet
                .setDuration(delay)
                .playTogether(creditValueAnimator, degreeValueAnimator, colorAnimator);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                isAnimFinish = false;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                isAnimFinish = true;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
                isAnimFinish = true;
            }
        });
        animatorSet.start();
    }

    public void setTypeFont(Typeface typefont) {
        tb = typefont;
    }
}
