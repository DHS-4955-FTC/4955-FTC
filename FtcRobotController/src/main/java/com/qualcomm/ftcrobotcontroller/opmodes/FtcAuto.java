

package com.qualcomm.ftcrobotcontroller.opmodes;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.Range;
import java.lang.Math;






public class FtcAuto extends OpMode {

    final static double LIGHT_THRESHOLD = 0.5;
    //Sets a value for how light the line  needs to be to follow.




    Servo servo_right;
    Servo servo_left;


// Declares motors, sensors, and servos
    DcMotor frontright_motor;
    DcMotor backright_motor;
    DcMotor frontleft_motor;
    DcMotor backleft_motor;
    LightSensor reflectedLight;
    TouchSensor touchSensor;

    public FtcAuto() {

    }


    @Override
    public void init() {

        servo_left = hardwareMap.servo.get("servo_left");
        servo_right = hardwareMap.servo.get("servo_right");

        servo_left.setPosition(.90);
        servo_right.setPosition(0);

// Initializes motors, sensors, and servos
        frontright_motor = hardwareMap.dcMotor.get("frontright_motor");
        backright_motor = hardwareMap.dcMotor.get("backright_motor");
        frontleft_motor = hardwareMap.dcMotor.get("frontleft_motor");
        backleft_motor = hardwareMap.dcMotor.get("backleft_motor");
        frontright_motor.setDirection(DcMotor.Direction.REVERSE);
        backright_motor.setDirection(DcMotor.Direction.REVERSE);;

        touchSensor = hardwareMap.touchSensor.get("sensor_touch");

        reflectedLight = hardwareMap.lightSensor.get("sensor_light");
        reflectedLight.enableLed(true);


    }






    @Override
    public void loop () {

        double reflection = 0.0;
        double left, right = 0.0;


        reflection = reflectedLight.getLightDetected();

        if(this.time < 1.5)
        {
            frontright_motor.setPower(1);
            backright_motor.setPower(1);
            frontleft_motor.setPower(1);
            backleft_motor.setPower(1);
        }
        else if(this.time > 1.5 && this.time < 2.05)
        {
            frontright_motor.setPower(-1);
            backright_motor.setPower(-1);
            frontleft_motor.setPower(1);
            backleft_motor.setPower(1);
        }
        else if(this.time > 2.05 && this.time < 5)
        {
            frontright_motor.setPower(1);
            backright_motor.setPower(1);
            frontleft_motor.setPower(1);
            backleft_motor.setPower(1);
        }



        else{



            if(touchSensor.isPressed()) {

                frontright_motor.setPower(0);
                backright_motor.setPower(0);
                frontleft_motor.setPower(0);
                backleft_motor.setPower(0);

            }
            else {
                if (reflection < LIGHT_THRESHOLD) {
			/*
			 * if reflection is less than the threshold value, then assume we are above dark spot.
			 * turn to the right.
			 */
                    frontright_motor.setPower(-.25);
                    backright_motor.setPower(-.25);
                    frontleft_motor.setPower(0.5);
                    backleft_motor.setPower(0.5);
                }
                else {
			/*
			 * assume we are over a light spot.
			 * turn to the left.
			 */
                    frontright_motor.setPower(0.5);
                    backright_motor.setPower(0.5);
                    frontleft_motor.setPower(-.25);
                    backleft_motor.setPower(-.25);
                }
            }
            }

        



        telemetry.addData("Text", "*** Robot Data***");
        telemetry.addData("time", "elapsed time: " + Double.toString(this.time));


    }



    @Override
    public void stop() {

    }

}


