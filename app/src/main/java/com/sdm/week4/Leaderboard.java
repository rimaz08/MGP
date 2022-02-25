package com.sdm.week4;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

// Created by TanSiewLan2021
//test

public class Leaderboard extends Activity implements OnClickListener, StateBase {  //Using StateBase class

    private Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide Title
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Hide Top Bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.leaderboard);
		  StateManager.Instance.AddState(new Leaderboard());
    }

    @Override
    //Invoke a callback event in the view
    public void onClick(View v)
    {
        // Intent = action to be performed.
        // Intent is an object provides runtime binding.
        // new instance of this object intent

        Intent intent = new Intent();


    }

    @Override
    public void Render(Canvas _canvas) {
        EntityManager.Instance.Render(_canvas);

        String scoreText = String.format("SCORE : %d",GameSystem.Instance.GetIntFromSave("Score"));

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(64);

        _canvas.drawText("score: " + scoreText, 10, 220, paint);
    }
	
    @Override
    public void OnEnter(SurfaceView _view) {
    }
	
    @Override
    public void OnExit() {
    }
	
    @Override
    public void Update(float _dt) {
    }
	
    @Override
    public String GetName() {
        return "Leaderboard";
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
