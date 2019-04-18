package com.pspdevelopers.materialonboard.widget.draw.controller;

import android.graphics.Canvas;
import android.view.MotionEvent;

import com.pspdevelopers.materialonboard.widget.animation.data.Value;
import com.pspdevelopers.materialonboard.widget.animation.type.AnimationType;
import com.pspdevelopers.materialonboard.widget.draw.data.Indicator;
import com.pspdevelopers.materialonboard.widget.draw.drawer.Drawer;
import com.pspdevelopers.materialonboard.widget.utils.CoordinatesUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DrawController {

    private Value value;
    private Drawer drawer;
    private Indicator indicator;
    private ClickListener listener;

    public DrawController(@NonNull Indicator indicator) {
        this.indicator = indicator;
        this.drawer = new Drawer(indicator);
    }

    public void updateValue(@Nullable Value value) {
        this.value = value;
    }

    public void setClickListener(@Nullable ClickListener listener) {
        this.listener = listener;
    }

    public void touch(@Nullable MotionEvent event) {
        if (event == null) {
            return;
        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                onIndicatorTouched(event.getX(), event.getY());
                break;
            default:
        }
    }

    private void onIndicatorTouched(float x, float y) {
        if (listener != null) {
            int position = CoordinatesUtils.getPosition(indicator, x, y);
            if (position >= 0) {
                listener.onIndicatorClicked(position);
            }
        }
    }

    public void draw(@NonNull Canvas canvas) {
        int count = indicator.getCount();

        for (int position = 0; position < count; position++) {
            int coordinateX = CoordinatesUtils.getXCoordinate(indicator, position);
            int coordinateY = CoordinatesUtils.getYCoordinate(indicator, position);
            drawIndicator(canvas, position, coordinateX, coordinateY);
        }
    }

    private void drawIndicator(
            @NonNull Canvas canvas,
            int position,
            int coordinateX,
            int coordinateY) {

        boolean interactiveAnimation = indicator.isInteractiveAnimation();
        int selectedPosition = indicator.getSelectedPosition();
        int selectingPosition = indicator.getSelectingPosition();
        int lastSelectedPosition = indicator.getLastSelectedPosition();

        boolean selectedItem = !interactiveAnimation && (position == selectedPosition || position == lastSelectedPosition);
        boolean selectingItem = interactiveAnimation && (position == selectedPosition || position == selectingPosition);
        boolean isSelectedItem = selectedItem | selectingItem;
        drawer.setup(position, coordinateX, coordinateY);

        if (value != null && isSelectedItem) {
            drawWithAnimation(canvas);
        } else {
            drawer.drawBasic(canvas);
        }
    }

    private void drawWithAnimation(@NonNull Canvas canvas) {
        AnimationType animationType = indicator.getAnimationType();
        switch (animationType) {
            case NONE:
                drawer.drawBasic(canvas);
                break;

            case COLOR:
                drawer.drawColor(canvas, value);
                break;

            case LINE:
                drawer.drawLine(canvas, value);
                break;

            case THIN_LINE:
                drawer.drawThinLine(canvas, value);
                break;

            case SWAP:
                drawer.drawSwap(canvas, value);
                break;
        }
    }

    public interface ClickListener {

        void onIndicatorClicked(int position);
    }
}
