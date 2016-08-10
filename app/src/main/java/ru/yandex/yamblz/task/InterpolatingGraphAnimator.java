package ru.yandex.yamblz.task;

import android.view.animation.Interpolator;

public class InterpolatingGraphAnimator implements GraphAnimator {
    private float fromDataPoints[], toDataPoints[];
    private long duration;
    private Interpolator interpolator;

    public InterpolatingGraphAnimator(float[] fromDataPoints, float[] toDataPoints,
                                      long duration, Interpolator interpolator) {
        this.fromDataPoints = fromDataPoints;
        this.toDataPoints = toDataPoints;
        this.duration = duration;
        this.interpolator = interpolator;
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
}
