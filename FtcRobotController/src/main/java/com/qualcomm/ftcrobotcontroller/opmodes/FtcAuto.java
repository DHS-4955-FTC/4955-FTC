package com.qualcomm.ftcrobotcontroller.opmodes;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.util.Range;
import java.lang.Math;






public class FtcAuto extends OpMode {
    final static double MOTOR_POWER = 0.25; // Higher values will cause the robot to move faster
    final static double LIGHT_THRESHOLD = 0.5;



    DcMotor right_motor;
    DcMotor left_motor;
    LightSensor reflectedLight;

    public FtcAuto() {

    }


    @Override
    public void init() {


        right_motor = hardwareMap.dcMotor.get("right_motor");
        left_motor = hardwareMap.dcMotor.get("left_motor");
        right_motor.setDirection(DcMotor.Direction.REVERSE);

        reflectedLight = hardwareMap.lightSensor.get("light_sensor");
        reflectedLight.enableLed(true);


    }






    @Override
    public void loop () {
        double reflection = 0.0;
        double left, right = 0.0;

        reflection = reflectedLight.getLightDetected();

        while(){
            if (reflection < LIGHT_THRESHOLD) {
			/*
			 * if reflection is less than the threshold value, then assume we are above dark spot.
			 * turn to the right.
			 */
                left = MOTOR_POWER;
                right = 0.0;
            }

            else {
			/*
			 * assume we are over a light spot.
			 * turn to the left.
			 */
                left = 0.0;
                right = MOTOR_POWER;
            }
        }



        telemetry.addData("Text", "*** Robot Data***");
        telemetry.addData("time", "elapsed time: " + Double.toString(this.time));


    }



    @Override
    public void stop() {

    }

}


