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
import ru.yandex.yamblz.task.FloatFunction;
import ru.yandex.yamblz.task.GraphView;
import ru.yandex.yamblz.task.InterpolatingGraphAnimator;
import ru.yandex.yamblz.task.Utils;

public class ContentFragment extends BaseFragment {
    private final Interpolator interpolator = new AnticipateOvershootInterpolator(3);
    private final int duration = 700;
    private final RectF sinRect = new RectF(-10, 5, 10, -5);
    private final RectF atanRect = new RectF(-5, 5, 5, -5);
    private final RectF funcRect = new RectF(-1, 1, 1, -0.5f);
    private final int sin1Color = Color.RED;
    private final int sin2Color = Color.GREEN;
    private final int atanColor = Color.BLUE;
    private final int funcColor = Color.CYAN;

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
        animateFunction(sin1Color, sinRect, arg -> (float) Math.sin(arg));
    }

    @OnClick(R.id.func_2)
    void func2(@SuppressWarnings("UnusedParameters") View view) {
        animateFunction(sin2Color, sinRect, arg -> (float) Math.sin(arg + Math.PI));
    }

    @OnClick(R.id.func_3)
    void func3(@SuppressWarnings("UnusedParameters") View view) {
        animateFunction(atanColor, atanRect, arg -> (float) Math.atan(arg));
    }

    @OnClick(R.id.func_4)
    void func4(@SuppressWarnings("UnusedParameters") View view) {
        animateFunction(funcColor, funcRect,
                arg -> (float) (arg != 0 ? (arg * Math.sin(1 / arg)) : 0));
    }

    private void animateFunction(int color, RectF rect, FloatFunction function) {
        float[] newDataPoints = Utils.generateDataPoints(graphView.getDataPoints().length,
                rect, function);
        float[] oldDataPoints = Arrays
                .copyOf(graphView.getDataPoints(), graphView.getDataPoints().length);
        graphView.animateGraph(new InterpolatingGraphAnimator(
                oldDataPoints, newDataPoints,
                graphView.getColor(), color,
                duration, interpolator));
    }
}
