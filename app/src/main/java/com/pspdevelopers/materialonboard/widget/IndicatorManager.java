package com.pspdevelopers.materialonboard.widget;

import com.pspdevelopers.materialonboard.widget.animation.AnimationManager;
import com.pspdevelopers.materialonboard.widget.animation.controller.ValueController;
import com.pspdevelopers.materialonboard.widget.animation.data.Value;
import com.pspdevelopers.materialonboard.widget.draw.DrawManager;
import com.pspdevelopers.materialonboard.widget.draw.data.Indicator;

import androidx.annotation.Nullable;

public class IndicatorManager implements ValueController.UpdateListener {

    private DrawManager drawManager;
    private AnimationManager animationManager;
    private Listener listener;

    IndicatorManager(@Nullable Listener listener) {
        this.listener = listener;
        this.drawManager = new DrawManager();
        this.animationManager = new AnimationManager(drawManager.indicator(), this);
    }

    public AnimationManager animate() {
        return animationManager;
    }

    public Indicator indicator() {
        return drawManager.indicator();
    }

    public DrawManager drawer() {
        return drawManager;
    }

    @Override
    public void onValueUpdated(@Nullable Value value) {
        drawManager.updateValue(value);
        if (listener != null) {
            listener.onIndicatorUpdated();
        }
    }

    interface Listener {
        void onIndicatorUpdated();
    }
}
