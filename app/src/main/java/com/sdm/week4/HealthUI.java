package com.sdm.week4;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

public class HealthUI implements EntityBase{

    private Sprite spritesmurf = null;
    private float xPos = 0, yPos = 0;

    private boolean isDone = false;
    private boolean isInit = false;
    private boolean Paused = false;

    int ScreenWidth, ScreenHeight;

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

        spritesmurf = new Sprite(ResourceManager.Instance.GetBitmap(R.drawable.player),1,2, 8 );

        DisplayMetrics metrics = _view.getResources().getDisplayMetrics();
        ScreenWidth = metrics.widthPixels;
        ScreenHeight = metrics.heightPixels;

        xPos = 100;
        yPos = ScreenHeight - 200;

        isInit = true;
    }

    @Override
    public void Update(float _dt) {
        spritesmurf.Update(_dt);
    }

    @Override
    public void Render(Canvas _canvas) {

        spritesmurf.Render(_canvas, (int)xPos, (int)yPos);

    }

    @Override
    public boolean IsInit() {

        return isInit;
    }

    @Override
    public int GetRenderLayer() {
        return LayerConstants.STARUI_LAYER;
    }

    @Override
    public void SetRenderLayer(int _newLayer) {
        return;
    }

    @Override
    public ENTITY_TYPE GetEntityType(){ return ENTITY_TYPE.ENT_STARUI;}

    public static HealthUI Create()
    {
        HealthUI result = new HealthUI();
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_STARUI);
        return result;
    }
}