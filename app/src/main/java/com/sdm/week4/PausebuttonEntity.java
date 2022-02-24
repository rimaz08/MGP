package com.sdm.week4;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

public class PausebuttonEntity implements EntityBase{

    private Bitmap bmpP,bmpUP,ScaledbmpP,ScaledbmpUP;
    private float xPos = 0, yPos = 0;

    private boolean isDone = false;
    private boolean isInit = false;
    private boolean Paused = false;

    int ScreenWidth, ScreenHeight;

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

        bmpP = ResourceManager.Instance.GetBitmap(R.drawable.pause);
        bmpUP = ResourceManager.Instance.GetBitmap(R.drawable.pause1);

        DisplayMetrics metrics = _view.getResources().getDisplayMetrics();
        ScreenWidth = metrics.widthPixels;
        ScreenHeight = metrics.heightPixels;

        ScaledbmpP = Bitmap.createScaledBitmap(bmpP, (int) (ScreenWidth)/12, (int)(ScreenWidth)/7, true);
        ScaledbmpUP = Bitmap.createScaledBitmap(bmpUP, (int) (ScreenWidth)/12, (int)(ScreenWidth)/7, true);

        xPos = ScreenWidth - 150;
        yPos = 150;

        isInit = true;
    }

    @Override
    public void Update(float _dt) {
        //if (GameSystem.Instance.GetIsPaused()) return;
        buttonDelay += _dt;

        if (TouchManager.Instance.HasTouch()) {
            if (TouchManager.Instance.IsDown()) {
                // Check Collision of button here!!
                float imgRadius = ScaledbmpP.getHeight() * 0.5f;

                if (Collision.SphereToSphere(TouchManager.Instance.GetPosX(),
                        TouchManager.Instance.GetPosY(),
                        0.0f, xPos, yPos, imgRadius)
                        && buttonDelay >= 0.25) {
                    boolean toSet = false;
                    if (GameSystem.Instance.GetIsPaused() == false) {
                        GameSystem.Instance.SetIsPaused(true);
                    }
                    else if (GameSystem.Instance.GetIsPaused() == true) {
                        GameSystem.Instance.SetIsPaused(false);
                    }
                    buttonDelay = 0;
                }
            }
        }
    }

    @Override
    public void Render(Canvas _canvas) {

        if (Paused == false)
            _canvas.drawBitmap(ScaledbmpP,xPos - ScaledbmpP.getWidth() * 0.5f, yPos - ScaledbmpP.getHeight() * 0.5f, null);
        else
            _canvas.drawBitmap(ScaledbmpUP,xPos - ScaledbmpUP.getWidth() * 0.5f, yPos - ScaledbmpUP.getHeight() * 0.5f, null);


    }

    @Override
    public boolean IsInit() {

        return isInit;
    }

    @Override
    public int GetRenderLayer() {
        return LayerConstants.PAUSEBUTTON_LAYER;
    }

    @Override
    public void SetRenderLayer(int _newLayer) {
        return;
    }

    @Override
    public ENTITY_TYPE GetEntityType(){ return ENTITY_TYPE.ENT_PAUSE;}

    public static PausebuttonEntity Create()
    {
        PausebuttonEntity result = new PausebuttonEntity();
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_PAUSE);
        return result;
    }
}