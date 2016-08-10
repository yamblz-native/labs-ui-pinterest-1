package ru.yandex.yamblz.task;

import android.graphics.RectF;

public class Utils {
    public static float[] generateDataPoints(int size, RectF displayRect,
                                             FloatFunction function) {
        float[] dataPoints = new float[size];

        float rectWidth = displayRect.width();

        for (int i = 0; i < size; ++i) {
            dataPoints[i] =
                    (function.evaluate(displayRect.left + ((float) i) / (size - 1) * rectWidth) -
                            displayRect.bottom) / -displayRect.height();
        }

        return dataPoints;
    }
}
