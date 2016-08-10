package ru.yandex.yamblz.ui.fragments;

import android.graphics.Color;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.Interpolator;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.OnClick;
import ru.yandex.yamblz.R;
import ru.yandex.yamblz.task.GraphView;
import ru.yandex.yamblz.task.InterpolatingGraphAnimator;
import ru.yandex.yamblz.task.Utils;

public class ContentFragment extends BaseFragment {
    private final Interpolator interpolator = new AnticipateOvershootInterpolator(3);
    private final int duration = 700;
    private final RectF sinRect = new RectF(-10, 5, 10, -5);
    private final RectF atanRect = new RectF(-5, 5, 5, -5);
    private final int sin1Color = Color.RED;
    private final int sin2Color = Color.GREEN;
    private final int atanColor = Color.BLUE;

    @BindView(R.id.graph_view)
    GraphView graphView;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_content, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final float[] dataPoints = new float[100];
        Arrays.fill(dataPoints, 0.5f);
        graphView.setDataPoints(dataPoints);
        graphView.setStrokeWidth(10);
        graphView.setColor(Color.BLACK);
    }

    @OnClick(R.id.func_1)
    void func1(@SuppressWarnings("UnusedParameters") View view) {
        float[] newDataPoints = Utils.generateDataPoints(graphView.getDataPoints().length,
                sinRect, arg -> (float) Math.sin(arg));
        float[] oldDataPoints = Arrays
                .copyOf(graphView.getDataPoints(), graphView.getDataPoints().length);
        graphView.animateGraph(new InterpolatingGraphAnimator(
                oldDataPoints, newDataPoints,
                graphView.getColor(), sin1Color,
                duration, interpolator));
    }

    @OnClick(R.id.func_2)
    void func2(@SuppressWarnings("UnusedParameters") View view) {
        float[] newDataPoints = Utils.generateDataPoints(graphView.getDataPoints().length,
                sinRect, arg -> (float) Math.sin(arg + Math.PI));
        float[] oldDataPoints = Arrays
                .copyOf(graphView.getDataPoints(), graphView.getDataPoints().length);
        graphView.animateGraph(new InterpolatingGraphAnimator(
                oldDataPoints, newDataPoints,
                graphView.getColor(), sin2Color,
                duration, interpolator));
    }

    @OnClick(R.id.func_3)
    void func3(@SuppressWarnings("UnusedParameters") View view) {
        float[] newDataPoints = Utils.generateDataPoints(graphView.getDataPoints().length,
                atanRect, arg -> (float) Math.atan(arg));
        float[] oldDataPoints = Arrays
                .copyOf(graphView.getDataPoints(), graphView.getDataPoints().length);
        graphView.animateGraph(new InterpolatingGraphAnimator(
                oldDataPoints, newDataPoints,
                graphView.getColor(), atanColor,
                duration, interpolator));
    }
}
