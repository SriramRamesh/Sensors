package com.example.android.sensors;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton img_one=(ImageButton)findViewById(R.id.imageButton);
        ImageButton img_two=(ImageButton)findViewById(R.id.imageButton2);
        ImageButton img_three=(ImageButton)findViewById(R.id.imageButton3);
    }
    public void Gyroscope(View v)
    {
        Intent in=new Intent(this,Gyroscope.class);
        startActivity(in);
    }
    public void Accelerometer(View v)
    {
        Intent in=new Intent(this,Accelerometer.class);
        startActivity(in);
    }
    public void Proximity(View v)
    {
        Intent in=new Intent(this,Proximity.class);
        startActivity(in);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_accelerometer, menu);
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
