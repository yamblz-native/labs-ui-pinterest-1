package ru.yandex.yamblz.task;

import android.graphics.RectF;

public class Utils {
    public static float[] generateDataPoints(int size, RectF baseRect,
                                             FloatFunction function) {
        float[] dataPoints = new float[size];

        float rectWidth = baseRect.width();

        for (int i = 0; i < size; ++i) {
            dataPoints[i] = function.evaluate(
                    baseRect.left + ((float) i) / (size - 1) * rectWidth);
        }

        return dataPoints;
    }
}
