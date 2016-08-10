package ru.yandex.yamblz.task;

public interface GraphAnimator {
    long getDuration();

    float getDataPoint(int i, long time);
}
