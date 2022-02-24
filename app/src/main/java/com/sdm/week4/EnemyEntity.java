package com.sdm.week4;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.SurfaceView;
import java.lang.Math;
import com.sdm.week4.EnemyBulletEntity;

import java.util.Random;

public class EnemyEntity implements EntityBase {
    public static EnemyEntity single_instance = null;
    public static EnemyEntity getInstance(){
        if (single_instance == null) {
            single_instance = new EnemyEntity();
            EntityManager.Instance.AddEntity(single_instance, ENTITY_TYPE.ENT_SMURF);
        }
        return single_instance;
    }

    private Sprite enemySprite = null;
    private float xPos = 0, yPos = 0;
    private float timer = 0, maxTimer = 5f;
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

        enemySprite = new Sprite(ResourceManager.Instance.GetBitmap(R.drawable.enemy), 1, 2, 16);
        DisplayMetrics metrics = _view.getResources().getDisplayMetrics();
        ScreenWidth = metrics.widthPixels;
        ScreenHeight = metrics.heightPixels;

        xPos = ScreenWidth/2;
        yPos = 0.1f * ScreenHeight;

        isInit = true;
    }

    @Override
    public void Update(float _dt) {
        if(GameSystem.Instance.GetIsPaused()) return;

        timer -= _dt;
        if (timer <= 0) {
            StarEntity.Create();
            EnemyBulletEntity.Create();
            EnemyBulletEntity.Create();
            timer = maxTimer;
            maxTimer *= Math.pow(0.5, _dt);
        }
        enemySprite.Update(_dt);
    }

    @Override
    public void Render(Canvas _canvas) {

        enemySprite.Render(_canvas, (int)xPos, (int)yPos);

    }

    @Override
    public boolean IsInit() {

        return isInit;
    }

    @Override
    public int GetRenderLayer() {
        return LayerConstants.ENEMY_LAYER;
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