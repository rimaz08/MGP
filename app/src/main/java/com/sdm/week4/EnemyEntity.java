package com.sdm.week4;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.SurfaceView;
import com.sdm.week4.EnemyBulletEntity;

import java.util.Random;

public class EnemyEntity implements EntityBase {
    private Bitmap bmpP, ScaledbmpP;
    private float xPos = 0, yPos = 0;
    private float timer = 0, maxTimer = 2;
    private boolean isDone = false;
    private boolean isInit = false;

    int ScreenWidth, ScreenHeight;

    Random ranGen = new Random(); //wk 8=>Random Generator

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

        bmpP = ResourceManager.Instance.GetBitmap(R.drawable.pause);

        DisplayMetrics metrics = _view.getResources().getDisplayMetrics();
        ScreenWidth = metrics.widthPixels;
        ScreenHeight = metrics.heightPixels;

        ScaledbmpP = Bitmap.createScaledBitmap(bmpP, (int) (ScreenWidth)/12, (int)(ScreenWidth)/7, true);

        xPos = ScreenWidth/2;
        yPos = 0;

        isInit = true;
    }

    @Override
    public void Update(float _dt) {
        if(GameSystem.Instance.GetIsPaused()) return;

        timer -= _dt;
        if (timer <= 0) {
            EnemyBulletEntity.Create();
            EnemyBulletEntity.Create();
            EnemyBulletEntity.Create();
            timer = maxTimer;
        }
    }

    @Override
    public void Render(Canvas _canvas) {

        _canvas.drawBitmap(ScaledbmpP,xPos - ScaledbmpP.getWidth() * 0.5f, yPos - ScaledbmpP.getHeight() * 0.5f, null);

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
    public ENTITY_TYPE GetEntityType(){ return ENTITY_TYPE.ENT_ENEMY;}

    public float GetYPosition(){ return yPos;};

    public static EnemyEntity Create()
    {
        EnemyEntity result = new EnemyEntity();
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_ENEMY);
        return result;
    }
}