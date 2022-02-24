package com.sdm.week4;

import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

import java.util.Random;

public class SliderEntity implements EntityBase {
    private boolean isDone = false;
    private float xPos, yPos, offset;
    private Sprite Slider = null;   // New on Week 8
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
        //week 8 => create new sprite instance
        Slider = new Sprite(ResourceManager.Instance.GetBitmap(R.drawable.slider),1,1, 8 );
        //week 8=>randomise position
        DisplayMetrics metrics = _view.getResources().getDisplayMetrics();
        ScreenWidth = metrics.widthPixels;
        ScreenHeight = metrics.heightPixels;

        xPos = ScreenWidth/2;
        yPos = (ScreenHeight/2) +135;
    }

    @Override
    public void Update(float _dt) {
        if(GameSystem.Instance.GetIsPaused()) toRender = true;
        else {toRender = false; return;}
        // wk8=> update sprite animation frame based on timing
        Slider.Update(_dt);

        //wk8=>Dragging code --
        if (TouchManager.Instance.HasTouch())  // Touch and drag
        {
            // Check collision with the smurf sprite
            float imgRadius1 = Slider.GetWidth() * 0.5f;
            //Log.v("imgrad","s"+imgRadius1);
            if (Collision.SphereToSphere(TouchManager.Instance.GetPosX(),
                    TouchManager.Instance.GetPosY(),
                    2.0f, xPos, yPos,
                    imgRadius1) )
            {
                if (xPos <= 180)
                {
                    xPos = 181;
                }
                else if (xPos >= (ScreenWidth-180))
                {
                    xPos = ScreenWidth-181;
                }
                else
                    xPos = TouchManager.Instance.GetPosX();
                //yPos = TouchManager.Instance.GetPosY();
            }
        }
        //Wk8=>End Dragging Code
    }

    @Override
    public void Render(Canvas _canvas) {
        //wk 8=>draw sprite using xpos,ypos, must cast in int
        if (toRender == true) {
            Slider.Render(_canvas, (int) xPos, (int) yPos);
        }
    }

    @Override
    public boolean IsInit() {
        return Slider != null;
    } //wk 8=>update to ret sprite variable

    @Override
    public int GetRenderLayer() {
        return LayerConstants.SLIDER_LAYER;
    } //wk 8=>update smurf layer

    @Override
    public void SetRenderLayer(int _newLayer) { }

    @Override
    public ENTITY_TYPE GetEntityType() {
        return ENTITY_TYPE.ENT_SLIDER;
    } //Week 8=>Update ent type

    public static SliderEntity Create() {
        SliderEntity result = new SliderEntity(); //wek 8
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_SLIDER); //wk8=>update ent tyep
        return result;
    }
}