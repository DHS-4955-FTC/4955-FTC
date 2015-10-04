package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;





public class TestAuto extends OpMode {

    final static double MOTOR_POWER = 0.15; // Higher values will cause the robot to move faster
    final static double HOLD_IR_SIGNAL_STRENGTH = 0.20; // Higher values will cause the robot to follow closer
    final static double LIGHT_THRESHOLD = 0.5;

    DcMotor right_motor;
    DcMotor left_motor;

    public TestAuto() {

    }


    @Override
    public void init() {


        right_motor = hardwareMap.dcMotor.get("right_motor");
        left_motor = hardwareMap.dcMotor.get("left_motor");
        left_motor.setDirection(DcMotor.Direction.REVERSE);

    }

        @Override public void loop ()

        {
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


