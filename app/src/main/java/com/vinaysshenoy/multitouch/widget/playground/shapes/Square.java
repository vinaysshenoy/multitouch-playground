package com.vinaysshenoy.multitouch.widget.playground.shapes;

import android.graphics.Canvas;
import android.graphics.Color;

import com.vinaysshenoy.multitouch.widget.playground.Shape;

/**
 * Created by vinaysshenoy on 05/08/17.
 */

public class Square extends Shape {

    private final float size;

    public Square(String name, float size) {
        super(name);
        this.size = size;
        bounds.set(0F, 0F, size, size);
        drawPaint.setColor(Color.RED);
    }

    @Override
    protected void drawSelf(Canvas canvas, boolean isSelected) {
        canvas.drawRect(bounds, drawPaint);
        if(isSelected) {
            canvas.drawRect(bounds, selectedPaint);
        }
    }
}
