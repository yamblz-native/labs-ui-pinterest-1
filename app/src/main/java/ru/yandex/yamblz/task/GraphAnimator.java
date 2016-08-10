package ru.yandex.yamblz.task;

@SuppressWarnings("WeakerAccess")
public interface GraphAnimator {
    long getDuration();

    float getDataPoint(int i, long time);

    int getColor(long time);
}
