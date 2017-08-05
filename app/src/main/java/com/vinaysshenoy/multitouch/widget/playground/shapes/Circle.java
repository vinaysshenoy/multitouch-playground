package com.vinaysshenoy.multitouch.widget.playground.shapes;

import android.graphics.Canvas;
import android.graphics.Color;

import com.vinaysshenoy.multitouch.widget.playground.Shape;

/**
 * Created by vinaysshenoy on 05/08/17.
 */

public class Circle extends Shape {

    private final float radius;

    public Circle(String name, float radius) {
        super(name);
        this.radius = radius;
        bounds.set(0F, 0F, radius * 2, radius * 2);
        drawPaint.setColor(Color.GREEN);
    }

    @Override
    protected void drawSelf(Canvas canvas, boolean isSelected) {
        canvas.drawCircle(bounds.centerX(), bounds.centerY(), radius, drawPaint);
        if (isSelected) {
            canvas.drawCircle(bounds.centerX(), bounds.centerY(), radius, selectedPaint);
        }
    }
}
