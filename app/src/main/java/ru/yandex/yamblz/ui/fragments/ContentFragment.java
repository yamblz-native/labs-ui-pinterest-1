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

import java.util.Arrays;

import butterknife.BindView;
import butterknife.OnClick;
import ru.yandex.yamblz.R;
import ru.yandex.yamblz.task.GraphView;
import ru.yandex.yamblz.task.InterpolatingGraphAnimator;
import ru.yandex.yamblz.task.Utils;

public class ContentFragment extends BaseFragment {
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
        graphView.setDataPoints(new float[100]);
        graphView.setBounds(new RectF(-5, 5, 5, -5));
        graphView.setStrokeWidth(10);
        graphView.setPaintColor(Color.BLACK);
    }

    @OnClick(R.id.func_1)
    void func1(@SuppressWarnings("UnusedParameters") View view) {
        float[] newDataPoints = Utils.generateDataPoints(graphView.getDataPoints().length,
                graphView.getBounds(), arg -> (float) Math.sin(arg));
        float[] oldDataPoints = Arrays
                .copyOf(graphView.getDataPoints(), graphView.getDataPoints().length);
        graphView.animateGraph(new InterpolatingGraphAnimator(
                oldDataPoints, newDataPoints, 500, new AnticipateOvershootInterpolator(5)));
    }

    @OnClick(R.id.func_2)
    void func2(@SuppressWarnings("UnusedParameters") View view) {
        float[] newDataPoints = Utils.generateDataPoints(graphView.getDataPoints().length,
                graphView.getBounds(), arg -> (float) Math.sin(arg + Math.PI));
        float[] oldDataPoints = Arrays
                .copyOf(graphView.getDataPoints(), graphView.getDataPoints().length);
        graphView.animateGraph(new InterpolatingGraphAnimator(
                oldDataPoints, newDataPoints, 500, new AnticipateOvershootInterpolator(5)));
    }

    @OnClick(R.id.func_3)
    void func3(@SuppressWarnings("UnusedParameters") View view) {
        float[] newDataPoints = Utils.generateDataPoints(graphView.getDataPoints().length,
                graphView.getBounds(), arg -> (float) Math.atan(arg));
        float[] oldDataPoints = Arrays
                .copyOf(graphView.getDataPoints(), graphView.getDataPoints().length);
        graphView.animateGraph(new InterpolatingGraphAnimator(
                oldDataPoints, newDataPoints, 500, new AnticipateOvershootInterpolator(5)));
    }
}
