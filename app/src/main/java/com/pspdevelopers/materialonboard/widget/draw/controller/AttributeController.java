package com.pspdevelopers.materialonboard.widget.draw.controller;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

import com.pspdevelopers.materialonboard.R;
import com.pspdevelopers.materialonboard.widget.animation.type.AnimationType;
import com.pspdevelopers.materialonboard.widget.animation.type.BaseAnimation;
import com.pspdevelopers.materialonboard.widget.animation.type.ColorAnimation;
import com.pspdevelopers.materialonboard.widget.animation.type.FillAnimation;
import com.pspdevelopers.materialonboard.widget.animation.type.ScaleAnimation;
import com.pspdevelopers.materialonboard.widget.draw.data.Indicator;
import com.pspdevelopers.materialonboard.widget.draw.data.Orientation;
import com.pspdevelopers.materialonboard.widget.utils.DensityUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AttributeController {

    private static final int DEFAULT_IDLE_DURATION = 3000;
    private Indicator indicator;

    public AttributeController(@NonNull Indicator indicator) {
        this.indicator = indicator;
    }

    public void init(@NonNull Context context, @Nullable AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PageIndicatorView, 0, 0);
        initCountAttribute(typedArray);
        initColorAttribute(typedArray);
        initAnimationAttribute(typedArray);
        initSizeAttribute(typedArray);
        typedArray.recycle();
    }

    private void initCountAttribute(@NonNull TypedArray typedArray) {
        int viewPagerId = typedArray.getResourceId(R.styleable.PageIndicatorView_indicator_viewPager, View.NO_ID);
        boolean autoVisibility = typedArray.getBoolean(R.styleable.PageIndicatorView_indicator_autoVisibility, true);
        boolean dynamicCount = typedArray.getBoolean(R.styleable.PageIndicatorView_indicator_dynamicCount, false);
        int count = typedArray.getInt(R.styleable.PageIndicatorView_indicator_count, Indicator.COUNT_NONE);

        if (count == Indicator.COUNT_NONE) {
            count = Indicator.DEFAULT_COUNT;
        }

        int position = typedArray.getInt(R.styleable.PageIndicatorView_indicator_select, 0);
        if (position < 0) {
            position = 0;
        } else if (count > 0 && position > count - 1) {
            position = count - 1;
        }

        indicator.setViewPagerId(viewPagerId);
        indicator.setAutoVisibility(autoVisibility);
        indicator.setDynamicCount(dynamicCount);
        indicator.setCount(count);

        indicator.setSelectedPosition(position);
        indicator.setSelectingPosition(position);
        indicator.setLastSelectedPosition(position);
    }

    private void initColorAttribute(@NonNull TypedArray typedArray) {
        int unselectedColor = typedArray.getColor(R.styleable.PageIndicatorView_indicator_unselectedColor, Color.parseColor(ColorAnimation.DEFAULT_UNSELECTED_COLOR));
        int selectedColor = typedArray.getColor(R.styleable.PageIndicatorView_indicator_selectedColor, Color.parseColor(ColorAnimation.DEFAULT_SELECTED_COLOR));

        indicator.setUnselectedColor(unselectedColor);
        indicator.setSelectedColor(selectedColor);
    }

    private void initAnimationAttribute(@NonNull TypedArray typedArray) {
        boolean interactiveAnimation = typedArray.getBoolean(R.styleable.PageIndicatorView_indicator_interactiveAnimation, false);
        long animationDuration = (long) typedArray.getInt(R.styleable.PageIndicatorView_indicator_animationDuration, BaseAnimation.DEFAULT_ANIMATION_TIME);
        if (animationDuration < 0) {
            animationDuration = 0;
        }

        int animIndex = typedArray.getInt(R.styleable.PageIndicatorView_indicator_animationType, AnimationType.NONE.ordinal());
        AnimationType animationType = getAnimationType(animIndex);


        boolean fadeOnIdle = typedArray.getBoolean(R.styleable.PageIndicatorView_indicator_fadeOnIdle, false);
        long idleDuration = (long) typedArray.getInt(R.styleable.PageIndicatorView_indicator_idleDuration, DEFAULT_IDLE_DURATION);

        indicator.setAnimationDuration(animationDuration);
        indicator.setInteractiveAnimation(interactiveAnimation);
        indicator.setAnimationType(animationType);
        indicator.setFadeOnIdle(fadeOnIdle);
        indicator.setIdleDuration(idleDuration);
    }

    private void initSizeAttribute(@NonNull TypedArray typedArray) {
        int orientationIndex = typedArray.getInt(R.styleable.PageIndicatorView_indicator_orientation, Orientation.HORIZONTAL.ordinal());
        Orientation orientation;

        if (orientationIndex == 0) {
            orientation = Orientation.HORIZONTAL;
        } else {
            orientation = Orientation.VERTICAL;
        }

        int radius = (int) typedArray.getDimension(R.styleable.PageIndicatorView_indicator_radius, DensityUtils.dpToPx(Indicator.DEFAULT_RADIUS_DP));
        if (radius < 0) {
            radius = 0;
        }

        int padding = (int) typedArray.getDimension(R.styleable.PageIndicatorView_indicator_padding, DensityUtils.dpToPx(Indicator.DEFAULT_PADDING_DP));
        if (padding < 0) {
            padding = 0;
        }

        float scaleFactor = typedArray.getFloat(R.styleable.PageIndicatorView_indicator_scaleFactor, ScaleAnimation.DEFAULT_SCALE_FACTOR);
        if (scaleFactor < ScaleAnimation.MIN_SCALE_FACTOR) {
            scaleFactor = ScaleAnimation.MIN_SCALE_FACTOR;

        } else if (scaleFactor > ScaleAnimation.MAX_SCALE_FACTOR) {
            scaleFactor = ScaleAnimation.MAX_SCALE_FACTOR;
        }

        int stroke = (int) typedArray.getDimension(R.styleable.PageIndicatorView_indicator_strokeWidth, DensityUtils.dpToPx(FillAnimation.DEFAULT_STROKE_DP));
        if (stroke > radius) {
            stroke = radius;
        }

        indicator.setRadius(radius);
        indicator.setOrientation(orientation);
        indicator.setPadding(padding);
        indicator.setScaleFactor(scaleFactor);
        indicator.setStroke(stroke);
    }

    private AnimationType getAnimationType(int index) {
        switch (index) {
            case 0:
                return AnimationType.NONE;
            case 1:
                return AnimationType.COLOR;
            case 2:
                return AnimationType.LINE;
            case 3:
                return AnimationType.THIN_LINE;
            case 4:
                return AnimationType.SWAP;
        }

        return AnimationType.NONE;
    }
}
