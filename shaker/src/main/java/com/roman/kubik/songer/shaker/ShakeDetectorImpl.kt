package com.roman.kubik.songer.shaker

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
import kotlin.math.sqrt


class ShakeDetectorImpl @Inject constructor(context: Context) : ShakeDetector {

    private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as? SensorManager

    companion object {
        private const val SHAKE_THRESHOLD_GRAVITY = 2.7f
    }

    @ExperimentalCoroutinesApi
    override fun shakeDetected(): Flow<ShakeEvent> = callbackFlow {
        val sensorListener = object : SensorEventListener {
            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
                // ignore
            }

            override fun onSensorChanged(event: SensorEvent) {
                val x = event.values[0]
                val y = event.values[1]
                val z = event.values[2]
                val gX = x / SensorManager.GRAVITY_EARTH
                val gY = y / SensorManager.GRAVITY_EARTH
                val gZ = z / SensorManager.GRAVITY_EARTH

                // gForce will be close to 1 when there is no movement.
                val gForce = sqrt(gX * gX + gY * gY + (gZ * gZ).toDouble()).toFloat()
                if (gForce > SHAKE_THRESHOLD_GRAVITY) {
                    sendBlocking(ShakeEvent.SHAKE)
                }
            }
        }

        if (sensorManager != null) {
            val accelerometer: Sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
            sensorManager.registerListener(sensorListener, accelerometer, SensorManager.SENSOR_DELAY_UI)
        }

        awaitClose { sensorManager?.unregisterListener(sensorListener) }
    }
}