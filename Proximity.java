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


public class Proximity extends Activity implements SensorEventListener{

    @Override
    public void onSensorChanged(SensorEvent event){
        if(event.sensor.getType()!=Sensor.TYPE_PROXIMITY)
            return;
        TextView textView=(TextView)findViewById(R.id.textView6);
        textView.setText(""+event.values[0]+"cm");
    }
    @Override
    public void onAccuracyChanged(Sensor sensor,int accuracy){

    }
    @Override
    public void onResume()
    {
        super.onResume();
        sensorManager.registerListener(this,proximity,SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    public void onPause(){
        super.onPause();
        sensorManager.unregisterListener(this,proximity);
    }
    Sensor proximity;
    SensorManager sensorManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
        proximity=sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_proximity, menu);
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
