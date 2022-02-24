package com.sdm.week4;

// Created by TanSiewLan2020

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.SurfaceView;

public class StarTextEntity implements EntityBase{

    // Paint object
    Paint paint = new Paint();
    private int red = 0, green = 0, blue = 0;

    private boolean isDone = false;
    private boolean isInit = false;

    int frameCount;
    long lastTime = 0;
    int points = 0;

    Typeface myfont;

    @Override
    public boolean IsDone() {
        return isDone;
    }

    @Override
    public void SetIsDone(boolean _isDone) {
        isDone = _isDone;
    }

    @Override
    public void Init(SurfaceView _view) {

        // Week 8 Use my own fonts
        myfont = Typeface.createFromAsset(_view.getContext().getAssets(), "fonts/Gemcut.otf");
        // myfont = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL);
        isInit = true;

    }

    @Override
    public void Update(float _dt) {

        // get actual fps

        frameCount++;

        long currentTime = System.currentTimeMillis();

        lastTime = currentTime;

    }

    @Override
    public void Render(Canvas _canvas)
    {

        Paint paint = new Paint();
        paint.setARGB(255, 0,0,0);
        //paint.setStrokeWidth(200);
        paint.setTypeface(myfont);
        paint.setTextSize(70);
        _canvas.drawText(": " + points, 150, 1900, paint);


    }

    public void DecreasePoints()
    {
        points--;
    }

    @Override
    public boolean IsInit() {
        return true;
    }

    @Override
    public int GetRenderLayer() {
        return LayerConstants.RENDERTEXT_LAYER;
    }

    @Override
    public void SetRenderLayer(int _newLayer) {
        return;
    }

    @Override
    public ENTITY_TYPE GetEntityType(){ return ENTITY_TYPE.ENT_TEXT;}

    public static StarTextEntity Create()
    {
        StarTextEntity result = new StarTextEntity();
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_TEXT);
        return result;
    }

}


