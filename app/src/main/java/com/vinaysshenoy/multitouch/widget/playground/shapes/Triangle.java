package com.vinaysshenoy.multitouch.widget.playground.shapes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;

import com.vinaysshenoy.multitouch.widget.playground.Shape;

/**
 * Created by vinaysshenoy on 05/08/17.
 */

public class Triangle extends Shape {

    private final float lengthOfBase;
    private final Path path;

    public Triangle(float lengthOfBase) {
        super();
        this.lengthOfBase = lengthOfBase;
        bounds.set(0F, 0F, lengthOfBase, lengthOfBase);

        //Create a Path that will draw a triangle within bounds
        path = new Path();
        path.moveTo(bounds.centerX(), 0F);
        path.lineTo(bounds.right, bounds.bottom);
        path.lineTo(bounds.left, bounds.bottom);
        path.lineTo(bounds.centerX(), 0F);
        path.close();

        drawPaint.setColor(Color.BLUE);
    }

    @Override
    protected void drawSelf(Canvas canvas, boolean isSelected) {
        canvas.drawPath(path, drawPaint);
        if(isSelected) {
            canvas.drawPath(path, selectedPaint);
        }
    }
}
