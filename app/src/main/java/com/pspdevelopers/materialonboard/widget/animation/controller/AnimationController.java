package com.pspdevelopers.materialonboard.widget.animation.controller;

import com.pspdevelopers.materialonboard.widget.animation.type.AnimationType;
import com.pspdevelopers.materialonboard.widget.animation.type.BaseAnimation;
import com.pspdevelopers.materialonboard.widget.draw.data.Indicator;
import com.pspdevelopers.materialonboard.widget.utils.CoordinatesUtils;

import androidx.annotation.NonNull;

public class AnimationController {

    private ValueController valueController;
    private ValueController.UpdateListener listener;

    private BaseAnimation runningAnimation;
    private Indicator indicator;

    private float progress;
    private boolean isInteractive;

    public AnimationController(@NonNull Indicator indicator, @NonNull ValueController.UpdateListener listener) {
        this.valueController = new ValueController(listener);
        this.listener = listener;
        this.indicator = indicator;
    }

    public void interactive(float progress) {
        this.isInteractive = true;
        this.progress = progress;
        animate();
    }

    public void basic() {
        this.isInteractive = false;
        this.progress = 0;
        animate();
    }

    public void end() {
        if (runningAnimation != null) {
            runningAnimation.end();
        }
    }

    private void animate() {
        AnimationType animationType = indicator.getAnimationType();
        switch (animationType) {
            case NONE:
                listener.onValueUpdated(null);
                break;

            case COLOR:
                colorAnimation();
                break;

            case LINE:
                lineAnimation();
                break;

            case THIN_LINE:
                thinLineAnimation();
                break;

            case SWAP:
                swapAnimation();
                break;
        }
    }

    private void colorAnimation() {
        int selectedColor = indicator.getSelectedColor();
        int unselectedColor = indicator.getUnselectedColor();
        long animationDuration = indicator.getAnimationDuration();

        BaseAnimation animation = valueController
                .color()
                .with(unselectedColor, selectedColor)
                .duration(animationDuration);

        if (isInteractive) {
            animation.progress(progress);
        } else {
            animation.start();
        }

        runningAnimation = animation;
    }

    private void lineAnimation() {
        int fromPosition = indicator.isInteractiveAnimation() ? indicator.getSelectedPosition() : indicator.getLastSelectedPosition();
        int toPosition = indicator.isInteractiveAnimation() ? indicator.getSelectingPosition() : indicator.getSelectedPosition();

        int from = CoordinatesUtils.getCoordinate(indicator, fromPosition);
        int to = CoordinatesUtils.getCoordinate(indicator, toPosition);
        boolean isRightSide = toPosition > fromPosition;

        int radiusPx = indicator.getRadius();
        long animationDuration = indicator.getAnimationDuration();

        BaseAnimation animation = valueController
                .worm()
                .with(from, to, radiusPx, isRightSide)
                .duration(animationDuration);

        if (isInteractive) {
            animation.progress(progress);
        } else {
            animation.start();
        }

        runningAnimation = animation;
    }

    private void thinLineAnimation() {
        int fromPosition = indicator.isInteractiveAnimation() ? indicator.getSelectedPosition() : indicator.getLastSelectedPosition();
        int toPosition = indicator.isInteractiveAnimation() ? indicator.getSelectingPosition() : indicator.getSelectedPosition();

        int from = CoordinatesUtils.getCoordinate(indicator, fromPosition);
        int to = CoordinatesUtils.getCoordinate(indicator, toPosition);
        boolean isRightSide = toPosition > fromPosition;

        int radiusPx = indicator.getRadius();
        long animationDuration = indicator.getAnimationDuration();

        BaseAnimation animation = valueController
                .thinWorm()
                .with(from, to, radiusPx, isRightSide)
                .duration(animationDuration);

        if (isInteractive) {
            animation.progress(progress);
        } else {
            animation.start();
        }

        runningAnimation = animation;
    }

    private void swapAnimation() {
        int fromPosition = indicator.isInteractiveAnimation() ? indicator.getSelectedPosition() : indicator.getLastSelectedPosition();
        int toPosition = indicator.isInteractiveAnimation() ? indicator.getSelectingPosition() : indicator.getSelectedPosition();

        int from = CoordinatesUtils.getCoordinate(indicator, fromPosition);
        int to = CoordinatesUtils.getCoordinate(indicator, toPosition);
        long animationDuration = indicator.getAnimationDuration();

        BaseAnimation animation = valueController
                .swap()
                .with(from, to)
                .duration(animationDuration);

        if (isInteractive) {
            animation.progress(progress);
        } else {
            animation.start();
        }

        runningAnimation = animation;
    }

}

