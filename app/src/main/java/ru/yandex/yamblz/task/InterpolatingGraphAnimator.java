package ru.yandex.yamblz.task;

import android.view.animation.Interpolator;

public class InterpolatingGraphAnimator implements GraphAnimator {
    private float fromDataPoints[], toDataPoints[];
    private int fromColor, toColor;
    private long duration;
    private Interpolator interpolator;

    public InterpolatingGraphAnimator(float[] fromDataPoints, float[] toDataPoints,
                                      int fromColor, int toColor,
                                      long duration, Interpolator interpolator) {
        this.fromDataPoints = fromDataPoints;
        this.toDataPoints = toDataPoints;
        this.fromColor = fromColor;
        this.toColor = toColor;
        this.duration = duration;
        this.interpolator = interpolator;
    }

    private static float restrict(float arg) {
        if (arg < 0) {
            return 0;
        }
        if (arg > 1) {
            return 1;
        }
        return arg;
    }

    @Override
    public long getDuration() {
        return duration;
    }

    @Override
    public float getDataPoint(int i, long time) {
        return fromDataPoints[i] + (toDataPoints[i] - fromDataPoints[i]) *
                interpolator.getInterpolation(((float) time) / duration);
    }

    @Override
    public int getColor(long time) {
        int color = 0;
        for (int i = 0; i < 4; ++i) {
            final int fromComponent = 0xFF & (fromColor >>> (8 * i));
            final int toComponent = 0xFF & (toColor >>> (8 * i));
            color |= (fromComponent + (int) ((toComponent - fromComponent) *
                    restrict(interpolator.getInterpolation(((float) time) / duration))))
                    << (i * 8);
        }
        return color;
    }
}
