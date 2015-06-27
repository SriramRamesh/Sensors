package com.example.android.sensors;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class Gyroscope extends Activity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor gyroscope;
    private float xRotation,yRotation,zRotation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyroscope);
        sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
        gyroscope=sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() != Sensor.TYPE_GYROSCOPE)
            return;
        xRotation=event.values[0];
        yRotation=event.values[1];
        zRotation=event.values[2];
        SetVals();
    }
    private void SetVals()
     {
         TextView t1=(TextView)findViewById(R.id.textView2);
         TextView t2=(TextView)findViewById(R.id.textView3);
         TextView t3=(TextView)findViewById(R.id.textView4);
         t1.setText("X:"+xRotation);
         t2.setText("Y="+yRotation);
         t3.setText("Z="+zRotation);
     }

    @Override
    protected void onResume(){
        super.onResume();
        sensorManager.registerListener(this,gyroscope,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause(){
        super.onPause();
        sensorManager.unregisterListener(this,gyroscope);
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gyroscope, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
