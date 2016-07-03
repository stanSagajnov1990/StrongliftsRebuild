package com.stanislav.tabswithfragment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;

public class BarPlateView extends View {

    private final Rect bar;
    private final Rect barEdge;
    private final Rect plateOne;
    private final Rect plateTwo;
    private Paint paint;

    public BarPlateView(Context context) {
        super(context);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(300, 300);
        setLayoutParams(params);

        bar = new Rect(0, 140, 220, 170);
        barEdge = new Rect(60, 120, 100, 190);

        plateOne = new Rect(100,60,140,250);
        plateTwo = new Rect(140,60,180,250);


        // create the Paint and set its color        
        paint = new Paint();
        paint.setColor(Color.GRAY);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        //canvas.drawCircle(200, 200, 100, paint);

        Paint barPaint = new Paint();
        barPaint.setColor(Color.LTGRAY);
        canvas.drawRect(bar, barPaint);

        Paint barEdgePaint = new Paint();
        barEdgePaint.setColor(Color.GRAY);
        canvas.drawRect(barEdge, barEdgePaint);

        Paint plateOnePaint = new Paint();
        plateOnePaint.setColor(Color.RED);
        canvas.drawRect(plateOne,plateOnePaint);
    }

}