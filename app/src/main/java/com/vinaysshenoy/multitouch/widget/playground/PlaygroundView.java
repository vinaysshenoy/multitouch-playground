package com.vinaysshenoy.multitouch.widget.playground;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vinaysshenoy on 05/08/17.
 */

public class PlaygroundView extends View {

    private List<Shape> shapes;
    private Matrix matrix;

    public PlaygroundView(Context context) {
        super(context);
        init(context, null);
    }

    public PlaygroundView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public PlaygroundView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public PlaygroundView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.shapes = new ArrayList<>(10);
        this.matrix = new Matrix();
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.concat(matrix);
        for (Shape shape : shapes) {
            shape.draw(canvas, false);
        }

    }
}
