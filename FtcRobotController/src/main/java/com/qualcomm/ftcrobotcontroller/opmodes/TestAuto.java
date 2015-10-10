package com.qualcomm.ftcrobotcontroller.opmodes;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;
import java.lang.Math;






public class TestAuto extends OpMode {



    DcMotor right_motor;
    DcMotor left_motor;

    public TestAuto() {

    }


    @Override
    public void init() {


        right_motor = hardwareMap.dcMotor.get("right_motor");
        left_motor = hardwareMap.dcMotor.get("left_motor");
        right_motor.setDirection(DcMotor.Direction.REVERSE);


    }






        @Override
        public void loop () {



            if(this.time <= 2)
            {
                right_motor.setPower(.5);
                left_motor.setPower(.5);
            }
            else if(this.time > 2 && this.time < 3)
            {
                right_motor.setPower(.5);
                left_motor.setPower(-.5);
            }
            else if(this.time > 3 && this.time < 5)
            {
                right_motor.setPower(.5);
                left_motor.setPower(.5);
            }
            else
            {
                right_motor.setPower(0);
                left_motor.setPower(0);
            }



        telemetry.addData("Text", "*** Robot Data***");
        telemetry.addData("time", "elapsed time: " + Double.toString(this.time));


    }



   @Override
    public void stop() {

    }

}


