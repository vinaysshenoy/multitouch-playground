package com.vinaysshenoy.multitouch.widget.matrixtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import static com.vinaysshenoy.multitouch.Utils.dpToPx;

/**
 * Created by vinaysshenoy on 05/08/17.
 */

public class MatrixTestView extends View {

    private static final String TAG = "MatrixTestView";

    //Total drawing area available to the View
    private RectF drawRect;

    //The content rect that will be drawn
    private RectF content;

    //The rect to hold the transformed content rect
    private RectF mappedContent;

    private Paint contentPaint;
    private Paint centerMarkerPaint;

    private float centerMarkerRadius;

    //Matrix to hold the transformations
    private Matrix contentTransformMatrix;

    public MatrixTestView(Context context) {
        super(context);
        init(context, null);
    }

    public MatrixTestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MatrixTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public MatrixTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet set) {
        drawRect = new RectF();
        content = new RectF();
        mappedContent = new RectF();

        contentPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        contentPaint.setStyle(Paint.Style.FILL);
        contentPaint.setStrokeWidth(dpToPx(1.0F));
        contentPaint.setColor(Color.LTGRAY);

        centerMarkerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        centerMarkerPaint.setStyle(Paint.Style.FILL);
        centerMarkerPaint.setStrokeWidth(dpToPx(1.0F));
        centerMarkerPaint.setColor(Color.BLACK);

        centerMarkerRadius = dpToPx(8F);
        contentTransformMatrix = new Matrix();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w > 0 && h > 0) {
            drawRect.set(0, 0, w, h);

            //Initialize some value to the content rect
            content.set(drawRect);
            content.inset(w / 3, h / 3);

            contentTransformMatrix.preTranslate(content.width() / 2F, 0F);
            contentTransformMatrix.preTranslate(0F, content.height() / 2F);

            contentTransformMatrix.mapRect(mappedContent, content);
            contentTransformMatrix.postScale(1.2F, 1.2F, mappedContent.left, mappedContent.top);

            //Apply the matrix transform on the content rect and store it in the mapped rect
            contentTransformMatrix.mapRect(mappedContent, content);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (drawRect.width() > 0F && drawRect.height() > 0F) {
            canvas.drawRect(mappedContent, contentPaint);
            canvas.drawCircle(drawRect.centerX(), drawRect.centerY(), centerMarkerRadius, centerMarkerPaint);
        }
    }
}
