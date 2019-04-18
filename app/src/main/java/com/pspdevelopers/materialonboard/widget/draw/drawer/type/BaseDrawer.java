package com.pspdevelopers.materialonboard.widget.draw.drawer.type;

import android.graphics.Paint;

import com.pspdevelopers.materialonboard.widget.draw.data.Indicator;

import androidx.annotation.NonNull;

class BaseDrawer {

    Paint paint;
    Indicator indicator;

    BaseDrawer(@NonNull Paint paint, @NonNull Indicator indicator) {
        this.paint = paint;
        this.indicator = indicator;
    }
}
