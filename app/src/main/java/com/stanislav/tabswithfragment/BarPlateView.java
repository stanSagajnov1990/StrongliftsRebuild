package com.stanislav.tabswithfragment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;

public class BarPlateView extends View {
    
    private Paint paint;

    public BarPlateView(Context context) {
        super(context);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(300,300);
        setLayoutParams(params);

        // create the Paint and set its color        
        paint = new Paint();
        paint.setColor(Color.GRAY);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLUE);
        canvas.drawCircle(200, 200, 100, paint);
    }

}