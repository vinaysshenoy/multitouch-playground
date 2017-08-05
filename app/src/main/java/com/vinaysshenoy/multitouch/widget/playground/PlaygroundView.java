package com.vinaysshenoy.multitouch.widget.playground;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vinaysshenoy on 05/08/17.
 */

public class PlaygroundView extends View {

    private static final String TAG = "PlaygroundView";

    private List<Shape> shapes;
    private Matrix matrix;
    private Matrix operationsMatrix;

    private TouchState touchState;

    private Shape currentTouchedShape;

    private PointF prevTouchPoint;
    private PointF curTouchPoint;

    private RectF sourceRect;
    private RectF mappedRect;

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
        shapes = new ArrayList<>(10);
        matrix = new Matrix();
        operationsMatrix = new Matrix();
        curTouchPoint = new PointF();
        prevTouchPoint = new PointF();
        sourceRect = new RectF();
        mappedRect = new RectF();
        touchState = TouchState.IDLE;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        sourceRect.set(0, 0, w, h);
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
            shape.draw(canvas, shape == currentTouchedShape);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        curTouchPoint.set(event.getX(), event.getY());
        switch (event.getActionMasked()) {

            case MotionEvent.ACTION_DOWN: {
                currentTouchedShape = findShapeThatContainsCurrentTouchPoint();
                if (currentTouchedShape == null) {
                    Log.d(TAG, "Touching Board!");
                    touchState = TouchState.TOUCHING_BOARD;
                } else {
                    Log.d(TAG, "Touching Shape: " + currentTouchedShape.name);
                    touchState = TouchState.TOUCHING_SHAPE;
                }
                prevTouchPoint.set(curTouchPoint);
                break;
            }

            case MotionEvent.ACTION_UP: {
                touchState = TouchState.IDLE;
                currentTouchedShape = null;
                break;
            }

            case MotionEvent.ACTION_CANCEL: {
                touchState = TouchState.IDLE;
                currentTouchedShape = null;
                break;
            }

            case MotionEvent.ACTION_MOVE: {
                if (touchState == TouchState.TOUCHING_BOARD) {
                    touchState = TouchState.MOVING_BOARD;
                } else if (touchState == TouchState.TOUCHING_SHAPE) {
                    touchState = TouchState.MOVING_SHAPE;
                }
                handleMove();
                prevTouchPoint.set(curTouchPoint);
                break;
            }
        }

        invalidate();
        return true;
    }

    private void handleMove() {
        switch (touchState) {

            case MOVING_BOARD: {
                handleMovingBoard();
                break;
            }

            case MOVING_SHAPE: {
                handleMovingShape();
                break;
            }

            default: {
                //No handling to do here
                break;
            }
        }
    }

    private void handleMovingBoard() {
        final float deltaX = curTouchPoint.x - prevTouchPoint.x;
        final float deltaY = curTouchPoint.y - prevTouchPoint.y;
        matrix.preTranslate(deltaX, deltaY);
        invalidate();
    }

    private void handleMovingShape() {
        final float deltaX = curTouchPoint.x - prevTouchPoint.x;
        final float deltaY = curTouchPoint.y - prevTouchPoint.y;
        currentTouchedShape.matrix().preTranslate(deltaX, deltaY);
        invalidate();
    }

    private Shape findShapeThatContainsCurrentTouchPoint() {

        //Test in reverse order because we want the ones which will be drawn on top to be tested first
        for (int shapeIndex = shapes.size() - 1; shapeIndex >= 0; shapeIndex--) {
            final Shape shape = shapes.get(shapeIndex);

            operationsMatrix.set(matrix);
            operationsMatrix.postConcat(shape.matrix());
            operationsMatrix.mapRect(mappedRect, shape.bounds());

            if (mappedRect.contains(curTouchPoint.x, curTouchPoint.y)) {
                return shape;
            }
        }

        return null;
    }

    private enum TouchState {
        IDLE,
        TOUCHING_BOARD,
        TOUCHING_SHAPE,
        MOVING_BOARD,
        MOVING_SHAPE
    }
}
