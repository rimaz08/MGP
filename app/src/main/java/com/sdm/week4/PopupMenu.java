package com.sdm.week4;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

import java.util.Random;

public class PopupMenu implements EntityBase{

    private Bitmap popUP,ScaledbmpUP;
    private float xPos = 0, yPos = 0;

    private boolean isDone = false;
    private boolean isInit = false;
    private boolean toRender = false;

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

        popUP = ResourceManager.Instance.GetBitmap(R.drawable.popupmenu2);

        DisplayMetrics metrics = _view.getResources().getDisplayMetrics();
        ScreenWidth = metrics.widthPixels;
        ScreenHeight = metrics.heightPixels;

        ScaledbmpUP = Bitmap.createScaledBitmap(popUP, (int) (ScreenWidth)-6, (int)(ScreenWidth)-10, true);

        xPos = ScreenWidth/2;
        yPos = ScreenHeight/2;

        isInit = true;
    }

    @Override
    public void Update(float _dt) {
        if(GameSystem.Instance.GetIsPaused()) toRender = true;
        else {toRender = false; return;}
    }

    @Override
    public void Render(Canvas _canvas) {
        if (toRender == true) {
            _canvas.drawBitmap(ScaledbmpUP, xPos - ScaledbmpUP.getWidth() * 0.5f, yPos - ScaledbmpUP.getHeight() * 0.5f, null);
        }
    }

    @Override
    public boolean IsInit() {

        return isInit;
    }

    @Override
    public int GetRenderLayer() {
        return LayerConstants.POPUP_LAYER;
    }

    @Override
    public void SetRenderLayer(int _newLayer) {
        return;
    }

    @Override
    public ENTITY_TYPE GetEntityType(){ return ENTITY_TYPE.ENT_POPUP;}

    public static PopupMenu Create()
    {
        PopupMenu result = new PopupMenu();
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_POPUP);
        return result;
    }
}