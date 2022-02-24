package com.sdm.week4;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

public class StarUI implements EntityBase{

    private Bitmap popUP,ScaledbmpUP;
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

        popUP = ResourceManager.Instance.GetBitmap(R.drawable.star);

        DisplayMetrics metrics = _view.getResources().getDisplayMetrics();
        ScreenWidth = metrics.widthPixels;
        ScreenHeight = metrics.heightPixels;

        ScaledbmpUP = Bitmap.createScaledBitmap(popUP, (int) (ScreenWidth)-1000, (int)(ScreenWidth)-1000, true);

        xPos = 100;
        yPos = ScreenHeight - 100;

        isInit = true;
    }

    @Override
    public void Update(float _dt) {

    }

    @Override
    public void Render(Canvas _canvas) {

        _canvas.drawBitmap(ScaledbmpUP,xPos - ScaledbmpUP.getWidth() * 0.5f , yPos - ScaledbmpUP.getHeight() * 0.5f , null);

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

    public static StarUI Create()
    {
        StarUI result = new StarUI();
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_STARUI);
        return result;
    }
}