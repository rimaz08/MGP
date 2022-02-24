package com.sdm.week4;

import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceView;

import java.util.Random;

public class SmurfEntityDraggable implements EntityBase {
    public static SmurfEntityDraggable single_instance = null;
    public static SmurfEntityDraggable getInstance(){
        if (single_instance == null) {
            single_instance = new SmurfEntityDraggable();
            EntityManager.Instance.AddEntity(single_instance, ENTITY_TYPE.ENT_SMURF);
        }
        return single_instance;
    }

    private boolean isDone = false;
    private float xPos, yPos, offset;
    private int score = 0;
    private boolean imdead = false;
    private Sprite spritesmurf = null;   // New on Week 8

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
        //week 8 => create new sprite instance
        spritesmurf = new Sprite(ResourceManager.Instance.GetBitmap(R.drawable.smurf_sprite),4,4, 8 );
        //week 8=>randomise position
        DisplayMetrics metrics = _view.getResources().getDisplayMetrics();
        xPos = metrics.widthPixels * 0.5f;
        yPos = metrics.heightPixels * 0.8f;
    }

    @Override
    public void Update(float _dt) {
        if (GameSystem.Instance.GetIsPaused()){
            return;
        }

        // wk8=> update sprite animation frame based on timing
        spritesmurf.Update(_dt);

        //wk8=>Dragging code --
        if (TouchManager.Instance.HasTouch())  // Touch and drag
        {
            // Check collision with the smurf sprite
            float imgRadius1 = spritesmurf.GetWidth() * 0.5f;
            //Log.v("imgrad","s"+imgRadius1);
            if (Collision.SphereToSphere(TouchManager.Instance.GetPosX(),
                    TouchManager.Instance.GetPosY(),
                    0.0f, xPos, yPos,
                    imgRadius1) )
            {
                xPos = TouchManager.Instance.GetPosX();
                yPos = TouchManager.Instance.GetPosY();
            }
        }
        //Wk8=>End Dragging Code
        GameSystem.Instance.SetIsPaused(imdead);
    }

    @Override
    public void Render(Canvas _canvas) {
        //wk 8=>draw sprite using xpos,ypos, must cast in int
        spritesmurf.Render(_canvas, (int)xPos, (int)yPos);
    }

    @Override
    public boolean IsInit() {
        return spritesmurf != null;
    } //wk 8=>update to ret sprite variable

    @Override
    public int GetRenderLayer() {
        return LayerConstants.SMURF_LAYER;
    } //wk 8=>update smurf layer

    @Override
    public void SetRenderLayer(int _newLayer) { }

    @Override
    public ENTITY_TYPE GetEntityType() {
        return ENTITY_TYPE.ENT_SMURF;
    } //Week 8=>Update ent type

    public float getXPos(){return this.xPos;}
    public float getYPos(){return this.yPos;}

    public int getScore(){return this.score;}
    public int setScore(int toSet){return score = toSet; }

    public boolean getDead(){return this.imdead; }
    public boolean setDead(boolean toSet){return imdead = toSet; };
}