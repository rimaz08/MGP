package com.sdm.week4;

import android.graphics.Canvas;
import android.view.SurfaceView;

public class EntityBlank implements EntityBase {
    private float xPos = 0, yPos = 0;
    private float timer = 0, maxTimer = 2;
    private boolean isDone = false;
    private boolean isInit = false;

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
        isInit = true;
    }

    @Override
    public void Update(float _dt) {
        if(GameSystem.Instance.GetIsPaused()) return;
    }

    @Override
    public void Render(Canvas _canvas) {

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
    public ENTITY_TYPE GetEntityType(){ return ENTITY_TYPE.ENT_BLANK;}

    public float GetYPosition(){ return yPos;};

    public static EntityBlank Create()
    {
        EntityBlank result = new EntityBlank();
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_BLANK);
        return result;
    }
}