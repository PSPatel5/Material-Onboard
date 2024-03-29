package com.pspdevelopers.materialonboard.widget.animation;

import com.pspdevelopers.materialonboard.widget.animation.controller.AnimationController;
import com.pspdevelopers.materialonboard.widget.animation.controller.ValueController;
import com.pspdevelopers.materialonboard.widget.draw.data.Indicator;

import androidx.annotation.NonNull;

public class AnimationManager {

    private AnimationController animationController;

    public AnimationManager(@NonNull Indicator indicator, @NonNull ValueController.UpdateListener listener) {
        this.animationController = new AnimationController(indicator, listener);
    }

    public void basic() {
        if (animationController != null) {
            animationController.end();
            animationController.basic();
        }
    }

    public void interactive(float progress) {
        if (animationController != null) {
            animationController.interactive(progress);
        }
    }

    public void end() {
        if (animationController != null) {
            animationController.end();
        }
    }
}
