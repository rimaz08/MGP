package com.sdm.week4;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

public class Leaderboard implements EntityBase{

    private Bitmap popUP,ScaledbmpUP;
    private float xPos = 0, yPos = 0;

    private boolean isDone = false;
    private boolean isInit = false;
    private boolean toRender = false;

    int ScreenWidth, ScreenHeight;

    Typeface myfont;

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

        popUP = ResourceManager.Instance.GetBitmap(R.drawable.popupmenu);

        DisplayMetrics metrics = _view.getResources().getDisplayMetrics();
        ScreenWidth = metrics.widthPixels;
        ScreenHeight = metrics.heightPixels;

        ScaledbmpUP = Bitmap.createScaledBitmap(popUP, (int) (ScreenWidth)-6, (int)(ScreenWidth)-10, true);

        xPos = ScreenWidth/2;
        yPos = ScreenHeight/2;

        myfont = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL);

        isInit = true;
    }

    @Override
    public void Update(float _dt) {
        if(SmurfEntityDraggable.getInstance().getDead()) toRender = true;
        else {toRender = false; return;}
    }

    @Override
    public void Render(Canvas _canvas) {
        if (toRender == true) {
            _canvas.drawBitmap(ScaledbmpUP, xPos - ScaledbmpUP.getWidth() * 0.5f, yPos - ScaledbmpUP.getHeight() * 0.5f, null);
            String scoreText = String.format("Score : %d", GameSystem.Instance.GetIntFromSave("Score"));

            Paint paint = new Paint();
            paint.setARGB(255, 0,0,0);
            paint.setTypeface(myfont);
            paint.setTextSize(70);

            _canvas.drawText(scoreText, 20, 600, paint);
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
    public ENTITY_TYPE GetEntityType(){ return ENTITY_TYPE.ENT_LEADERBOARD;}

    public static Leaderboard Create()
    {
        Leaderboard result = new Leaderboard();
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_POPUP);
        return result;
    }
}