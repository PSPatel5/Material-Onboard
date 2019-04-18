package com.pspdevelopers.materialonboard.widget.draw.drawer;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.pspdevelopers.materialonboard.widget.animation.data.Value;
import com.pspdevelopers.materialonboard.widget.draw.data.Indicator;
import com.pspdevelopers.materialonboard.widget.draw.drawer.type.BasicDrawer;
import com.pspdevelopers.materialonboard.widget.draw.drawer.type.ColorDrawer;
import com.pspdevelopers.materialonboard.widget.draw.drawer.type.LineDrawer;
import com.pspdevelopers.materialonboard.widget.draw.drawer.type.SwapDrawer;
import com.pspdevelopers.materialonboard.widget.draw.drawer.type.ThinLineDrawer;

import androidx.annotation.NonNull;

public class Drawer {

    private BasicDrawer basicDrawer;
    private ColorDrawer colorDrawer;
    private LineDrawer lineDrawer;
    private ThinLineDrawer thinLineDrawer;
    private SwapDrawer swapDrawer;


    private int position;
    private int coordinateX;
    private int coordinateY;

    public Drawer(@NonNull Indicator indicator) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);

        basicDrawer = new BasicDrawer(paint, indicator);
        colorDrawer = new ColorDrawer(paint, indicator);
        lineDrawer = new LineDrawer(paint, indicator);
        thinLineDrawer = new ThinLineDrawer(paint, indicator);
        swapDrawer = new SwapDrawer(paint, indicator);
    }

    public void setup(int position, int coordinateX, int coordinateY) {
        this.position = position;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    public void drawBasic(@NonNull Canvas canvas) {
        if (colorDrawer != null) {
            basicDrawer.draw(canvas, position, coordinateX, coordinateY);
        }
    }

    public void drawColor(@NonNull Canvas canvas, @NonNull Value value) {
        if (colorDrawer != null) {
            colorDrawer.draw(canvas, value, position, coordinateX, coordinateY);
        }
    }


    public void drawLine(@NonNull Canvas canvas, @NonNull Value value) {
        if (lineDrawer != null) {
            lineDrawer.draw(canvas, value, coordinateX, coordinateY);
        }
    }

    public void drawThinLine(@NonNull Canvas canvas, @NonNull Value value) {
        if (thinLineDrawer != null) {
            thinLineDrawer.draw(canvas, value, coordinateX, coordinateY);
        }
    }

    public void drawSwap(@NonNull Canvas canvas, @NonNull Value value) {
        if (swapDrawer != null) {
            swapDrawer.draw(canvas, value, position, coordinateX, coordinateY);
        }
    }

}
