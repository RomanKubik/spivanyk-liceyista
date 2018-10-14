package com.roman.kubik.songer.data.shaker;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.roman.kubik.songer.domain.shaker.ShakeDetector;
import com.roman.kubik.songer.domain.shaker.ShakeEvent;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class ShakeDetectorImpl implements ShakeDetector, SensorEventListener {

    private static final float SHAKE_THRESHOLD_GRAVITY = 2.7F;
    
    private final PublishSubject<ShakeEvent> shakeSubject = PublishSubject.create();

    public ShakeDetectorImpl(Context context) {
        SensorManager sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager != null) {
            Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
        }
    }

    @Override
    public Observable<ShakeEvent> shake() {
        return shakeSubject;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // ignore
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        float gX = x / SensorManager.GRAVITY_EARTH;
        float gY = y / SensorManager.GRAVITY_EARTH;
        float gZ = z / SensorManager.GRAVITY_EARTH;

        // gForce will be close to 1 when there is no movement.
        float gForce = (float) Math.sqrt(gX * gX + gY * gY + gZ * gZ);

        if (gForce > SHAKE_THRESHOLD_GRAVITY) {
            shakeSubject.onNext(new ShakeEvent());
        }

    }
}
