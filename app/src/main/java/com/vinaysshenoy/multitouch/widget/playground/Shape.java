package com.vinaysshenoy.multitouch.widget.playground;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;

import static com.vinaysshenoy.multitouch.Utils.dpToPx;

/**
 * Created by vinaysshenoy on 05/08/17.
 */

public abstract class Shape {

    protected final RectF bounds;
    protected final Paint drawPaint;
    protected final Paint selectedPaint;
    private final Matrix matrix;

    protected Shape() {
        bounds = new RectF();
        matrix = new Matrix();

        drawPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        drawPaint.setStyle(Paint.Style.FILL);
        drawPaint.setStrokeWidth(dpToPx(1.0F));

        selectedPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        selectedPaint.setStyle(Paint.Style.FILL);
        selectedPaint.setStrokeWidth(dpToPx(1.0F));
        selectedPaint.setColor(Color.DKGRAY);
        selectedPaint.setAlpha(127);

    }

    public final void draw(Canvas canvas, boolean isSelected) {
        final int saveCount = canvas.save();
        canvas.concat(matrix);
        drawSelf(canvas, isSelected);
        canvas.restoreToCount(saveCount);
    }

    public boolean isPointInBounds(PointF pointF) {
        return bounds.contains(pointF.x, pointF.y);
    }

    public final Matrix matrix() {
        return matrix;
    }

    protected abstract void drawSelf(Canvas canvas, boolean isSelected);

}
