package com.example.android.sensors;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;

public class Simulation extends View implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private float mSensorX;
    private float mSensorY;
    private float mSensorZ;
    private Display mDisplay;
    private long mSensorTimeStamp;
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() != Sensor.TYPE_ACCELEROMETER)
            return;
        switch (mDisplay.getRotation()) {
            case Surface.ROTATION_0:
                mSensorX = event.values[0];
                mSensorY = event.values[1];
                break;
            case Surface.ROTATION_90:
                mSensorX = -event.values[1];
                mSensorY = event.values[0];
                break;
            case Surface.ROTATION_180:
                mSensorX = -event.values[0];
                mSensorY = -event.values[1];
                break;
            case Surface.ROTATION_270:
                mSensorX = event.values[1];
                mSensorY = -event.values[0];
                break;
        }
        mSensorZ = event.values[2];
        mSensorTimeStamp = event.timestamp;
        //SetVals();
    }
   /* private void SetVals()
    {
        TextView t1=(TextView)findViewById(R.id.textView2);
        TextView t2=(TextView)findViewById(R.id.textView3);
        TextView t3=(TextView)findViewById(R.id.textView4);
        t1.setText("X:"+mSensorX);
        t2.setText("Y="+mSensorY);
        t3.setText("Z="+mSensorZ);
    }
*/
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

  private Bitmap bg;
  private Bitmap ball;
  private static final int BALL_SIZE = 32;
  private float mXOrigin;
  private float mYOrigin;
  private float mHorizontalBound;
  private float mVerticalBound;

    public Simulation(Context context) {
        super(context);
        mSensorManager=(SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer=mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        WindowManager windowManager=(WindowManager)context.getSystemService(context.WINDOW_SERVICE);
        mDisplay=windowManager.getDefaultDisplay();

        Bitmap tempball = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
        ball = Bitmap.createScaledBitmap(tempball, BALL_SIZE, BALL_SIZE, true);
        Options opts = new Options();
        opts.inDither = true;
        opts.inPreferredConfig = Bitmap.Config.RGB_565;
        bg = BitmapFactory.decodeResource(getResources(), R.drawable.bgd, opts);
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mXOrigin = w * 0.5f;
        mYOrigin = h * 0.5f;

        mHorizontalBound = (w - BALL_SIZE) * 0.5f;
        mVerticalBound = (h - BALL_SIZE) * 0.5f;
    }
    private Particle mBall = new Particle();

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(bg, 0, 0, null);
        mBall.updatePosition(mSensorX, mSensorY, mSensorZ, mSensorTimeStamp);
        mBall.resolveCollisionWithBounds(mHorizontalBound, mVerticalBound);

        canvas.drawBitmap(ball,
                (mXOrigin - BALL_SIZE/2) + mBall.mPosX,
                (mYOrigin - BALL_SIZE/2) - mBall.mPosY, null);

        invalidate();
    }
    public void startSimulation() {
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    public void stopSimulation() {
        mSensorManager.unregisterListener(this);
    }


}

