package com.sdm.week4;

import android.graphics.Canvas;
import android.view.SurfaceView;

// Created by TanSiewLan2021

public interface EntityBase
{
 	 //used for entities such as background
    enum ENTITY_TYPE{
        ENT_PLAYER,
        ENT_SMURF,
        ENT_PAUSE,
        ENT_TEXT,
         ENT_ENEMY,
        ENT_ENEMYBULLET,
         ENT_BLANK,
         ENT_STAR,
         ENT_STARUI,
         ENT_POPUP,
         ENT_SLIDER,
         ENT_LEADERBOARD,
        ENT_NEXT,
        ENT_DEFAULT,
    }

    boolean IsDone();
    void SetIsDone(boolean _isDone);

    void Init(SurfaceView _view);
    void Update(float _dt);
    void Render(Canvas _canvas);

    boolean IsInit();

    int GetRenderLayer();
    void SetRenderLayer(int _newLayer);

	ENTITY_TYPE GetEntityType();
}
