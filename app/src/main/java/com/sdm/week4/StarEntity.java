package com.sdm.week4;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

import java.util.Random;

public class StarEntity implements EntityBase{

    private Sprite starEntity = null;
    private float xPos = 0, yPos = 0;

    private boolean isDone = false;
    private boolean isInit = false;
    private boolean Paused = false;

    int ScreenWidth, ScreenHeight;

    Random ranGen = new Random(); //wk 8=>Random Generator

    private float buttonDelay = 0;

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

        starEntity = new Sprite(ResourceManager.Instance.GetBitmap(R.drawable.star), 1, 2, 16);

        DisplayMetrics metrics = _view.getResources().getDisplayMetrics();
        ScreenWidth = metrics.widthPixels;
        ScreenHeight = metrics.heightPixels;

        xPos = ranGen.nextFloat() * _view.getWidth();
        yPos = 0;

        isInit = true;
    }

    @Override
    public void Update(float _dt) {
        if(GameSystem.Instance.GetIsPaused()) return;

        yPos += _dt * 200;
        starEntity.Update(_dt);
        float imgRadius1 = starEntity.GetWidth() * 0.5f;
        if(yPos > + ScreenHeight + imgRadius1) {
            isDone = true;
            return;

        }

        if (Collision.SphereToSphere(xPos, yPos,imgRadius1,
                SmurfEntityDraggable.getInstance().getXPos(), SmurfEntityDraggable.getInstance().getYPos(), 0.2f)){
            SmurfEntityDraggable.getInstance().setScore(SmurfEntityDraggable.getInstance().getScore() + 1);
            SmurfEntityDraggable.getInstance().setInvincible();
            isDone = true;
        }
    }

    @Override
    public void Render(Canvas _canvas) {
        starEntity.Render(_canvas, (int)xPos, (int)yPos);

    }

    @Override
    public boolean IsInit() {

        return isInit;
    }

    @Override
    public int GetRenderLayer() {
        return LayerConstants.ENEMYBULLET_LAYER;
    }

    @Override
    public void SetRenderLayer(int _newLayer) {
        return;
    }

    @Override
    public ENTITY_TYPE GetEntityType(){ return ENTITY_TYPE.ENT_STAR;}

    public float GetYPosition(){ return yPos;};

    public static StarEntity Create()
    {
        StarEntity result = new StarEntity();
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_STAR);
        return result;
    }
}