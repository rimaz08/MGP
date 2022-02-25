package com.sdm.week4;

import android.app.Activity;
import android.app.GameManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceView;

import java.io.IOException;
import java.lang.Object;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.Key;
import java.util.Collections;
import java.util.Map;

// Created by TanSiewLan2021

public class MainGameSceneState implements StateBase {
    private float timer = 0.0f;

    @Override
    public String GetName() {
        return "MainGame";
    }

    @Override
    public void OnEnter(SurfaceView _view)
    {
        RenderBackground.Create();

        RenderTextEntity.Create();
        HealthTextEntity.Create();
        StarTextEntity.Create();
        PausebuttonEntity.Create();
        PopupMenu.Create();
        SliderEntity.Create();
        StarUI.Create();
        HealthUI.Create();

        SmurfEntityDraggable.getInstance();
        EnemyEntity.getInstance();
        //EnemyBulletEntity.Create();
        // Example to include another Renderview for Pause Button
        //StateManager.Instance.AddState(new MainGameSceneState());
    }

    @Override
    public void OnExit() {
        EntityManager.Instance.Clean();
        GamePage.Instance.finish();
    }

    @Override
    public void Render(Canvas _canvas)
    {
        EntityManager.Instance.Render(_canvas);

    }

    @Override
    public void Update(float _dt) {



        EntityManager.Instance.Update(_dt);
        if (SmurfEntityDraggable.getInstance().getDead())
        {
            AudioManager.Instance.PlayAudio(R.raw.failed,AudioManager.Instance.GetVolumeLevel());
            GameSystem.Instance.SaveEditBegin();
            GameSystem.Instance.SetIntInSave("Score",SmurfEntityDraggable.getInstance().getScore());
            GameSystem.Instance.SaveEditEnd();
            StateManager.Instance.ChangeState("Mainmenu");

            //StateManager.Instance.Clean();
            //StateManager.Instance.AddState(new MainGameSceneState());
            //SmurfEntityDraggable.getInstance().setHealth(5);
            //.Instance.Start("MainGame");


        }

        if (TouchManager.Instance.IsDown()) {
			
            //Example of touch on screen in the main game to trigger back to Main menu
        }
    }
}



