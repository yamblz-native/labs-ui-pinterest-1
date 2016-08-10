package ru.yandex.yamblz.task;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;

public class GraphView extends View {

    private float dataPoints[];

    private GraphAnimator graphAnimator;
    private long startAnimationTime;

    private Path path = new Path();
    private Paint paint = new Paint();

    {
        paint.setAntiAlias(true);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStyle(Paint.Style.STROKE);
    }

    public GraphView(Context context) {
        super(context);
    }

    public GraphView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GraphView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public float[] getDataPoints() {
        return dataPoints;
    }

    public void setDataPoints(float dataPoints[]) {
        this.dataPoints = dataPoints;
    }

    public int getColor() {
        return paint.getColor();
    }

    public void setColor(int color) {
        paint.setColor(color);
    }

    public void setStrokeWidth(float width) {
        paint.setStrokeWidth(width);
    }

    public void animateGraph(GraphAnimator graphAnimator) {
        this.graphAnimator = graphAnimator;
        startAnimationTime = SystemClock.uptimeMillis();
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (graphAnimator != null) {
            long currentTime = Math.min(SystemClock.uptimeMillis(),
                    startAnimationTime + graphAnimator.getDuration());
            long animationTime = currentTime - startAnimationTime;

            for (int i = 0; i < dataPoints.length; ++i) {
                dataPoints[i] = graphAnimator.getDataPoint(i, animationTime);
            }
            paint.setColor(graphAnimator.getColor(animationTime));

            if (animationTime == graphAnimator.getDuration()) {
                graphAnimator = null;
            } else {
                invalidate();
            }
        }

        path.rewind();
        path.moveTo(0, getViewPoint(0, canvas));
        for (int i = 1; i < dataPoints.length; ++i) {
            path.lineTo(((float) i) / (dataPoints.length - 1) * canvas.getWidth(),
                    getViewPoint(i, canvas));
        }
        canvas.drawPath(path, paint);
    }

    private float getViewPoint(int i, Canvas canvas) {
        return canvas.getHeight() - dataPoints[i] * canvas.getHeight();
    }
}
