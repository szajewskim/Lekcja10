package com.example.marek.zad10;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends Activity implements SensorEventListener {
    Sensor mSensor;
    TextView tv1;
    SensorManager manager;
    ListView lv;
    List<Sensor> sensor;
    ImageView image;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1=(TextView)findViewById(R.id.textView);
        manager=(SensorManager)getSystemService(SENSOR_SERVICE);
        List<Sensor> sensory = manager.getSensorList(Sensor.TYPE_ALL);
        for(int x=0;x<sensory.size();x++){
            tv1.setText(tv1.getText()+"\n"+sensory.get(x).getName());
        }


        mSensor = manager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
       // manager.registerListener(this,manager.getDefaultSensor(Sensor.TYPE_PROXIMITY),0,null);
        manager.registerListener(this,manager.getDefaultSensor(Sensor.TYPE_ORIENTATION),0,null);

        image = (ImageView) findViewById(R.id.imageView1);

    }
    protected void onResume(){
        super.onResume();
        manager.registerListener(this,mSensor,SensorManager.SENSOR_DELAY_NORMAL);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        String kierunek="";
        if(event.values[0]==0){
            image.setImageResource(R.drawable.n);
        }else if(event.values[0]<90){
            image.setImageResource(R.drawable.ne);
        }else if(event.values[0]==90){
            image.setImageResource(R.drawable.e);
        }else if(event.values[0]<180){
            image.setImageResource(R.drawable.se);
        }else if(event.values[0]==180){
            image.setImageResource(R.drawable.s);
        }else if(event.values[0]<270){
            image.setImageResource(R.drawable.sw);
        }else if(event.values[0]==270){
            image.setImageResource(R.drawable.w);
        }else if(event.values[0]<360){
            image.setImageResource(R.drawable.nw);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
