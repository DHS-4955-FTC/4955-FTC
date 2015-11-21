package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;




/**
 * TeleOp Mode
 * <p>
 * Enables control of the robot via the gamepad
 */
public class StandardTeleOp extends OpMode {

    final static double LIGHT_THRESHOLD = 0.5;

   final static double LEFT_MIN_RANGE  = 0.90;
    final static double LEFT_MAX_RANGE  = 0.1;
    final static double RIGHT_MIN_RANGE  = 0.001;
    final static double RIGHT_MAX_RANGE  = .50;

    double leftPosition;
    double rightPosition;

    // amount to change the arm servo position.
    double servoDelta = 0.1;


    Servo servo_right;
    Servo servo_left;
    //



    /*
     * Note: the configuration of the servos is such that
     * as the arm servo approaches 0, the arm position moves up (away from the floor).
     * Also, as the claw servo approaches 0, the claw opens up (drops the game element).
     */


    DcMotor frontright_motor;
    DcMotor backright_motor;
    DcMotor frontleft_motor;
    DcMotor backleft_motor;


    /**
     * Constructor
     */
    public StandardTeleOp() {

    }

    /*
     * Code to run when the op mode is initialized goes here
     *
     * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#init()
     */
    @Override
    public void init() {


        servo_left = hardwareMap.servo.get("servo_left");
        servo_right = hardwareMap.servo.get("servo_right");

        servo_left.setPosition(LEFT_MIN_RANGE);
        servo_right.setPosition(RIGHT_MIN_RANGE);


		/*
		 * Use the hardwareMap to get the dc motors and servos by name. Note
		 * that the names of the devices must match the names used when you
		 * configured your robot and created the configuration file.
		 */


        frontright_motor = hardwareMap.dcMotor.get("frontright_motor");
        backright_motor = hardwareMap.dcMotor.get("backright_motor");
        frontleft_motor = hardwareMap.dcMotor.get("frontleft_motor");
        backleft_motor = hardwareMap.dcMotor.get("backleft_motor");
        frontright_motor.setDirection(DcMotor.Direction.REVERSE);
        backright_motor.setDirection(DcMotor.Direction.REVERSE);


    }

    /*
     * This method will be called repeatedly in a loop
     *
     * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#run()
     */

    @Override
    public void loop() {

       /* if (gamepad2.right_bumper)
        {
            leftPosition =+ servoDelta;
            rightPosition =+ servoDelta;
        }

        else if (gamepad2.left_bumper)
        {
            leftPosition =- servoDelta;
            rightPosition =- servoDelta;
        }
        else
        {
            leftPosition = LEFT_MAX_RANGE;
            rightPosition = RIGHT_MIN_RANGE;

        }

        leftPosition = Range.clip(leftPosition, 1, -1);
        rightPosition = Range.clip(rightPosition, 1, -1);

        servo_left.setPosition(leftPosition);
        servo_right.setPosition(rightPosition);

*/
        float right = -gamepad1.right_stick_y;
        float left = -gamepad1.left_stick_y;


        // clip the right/left values so that the values never exceed +/- 1
        right = Range.clip(right, -.9, .9);
        left = Range.clip(left, -.9, .9);

        // scale the joystick value to make it easier to control
        // the robot more precisely at slower speeds.
        right = (float) scaleInput(right);
        left = (float) scaleInput(left);

        // write the values to the motors

        frontright_motor.setPower(right);
        backright_motor.setPower(right);
        frontleft_motor.setPower(left);
        backleft_motor.setPower(left);





        telemetry.addData("left tgt pwr", "left  pwr: " + String.format("%.2f", left));
        telemetry.addData("right tgt pwr", "right pwr: " + String.format("%.2f", right));
        telemetry.addData("leftServo", "Left Servo:  " + String.format("%.2f", leftPosition));
        telemetry.addData("rightServo", "Right Servo:  " + String.format("%.2f", rightPosition));
    }


    /*
     * Code to run when the op mode is first disabled goes here
     *
     * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#stop()
     */
    @Override
    public void stop() {

    }


    double scaleInput(double dVal)  {
        double[] scaleArray = { 0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
                0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00 };

        // get the corresponding index for the scaleInput array.
        int index = (int) (dVal * 16.0);
        if (index < 0) {
            index = -index;
        } else if (index > 16) {
            index = 16;
        }

        double dScale = 0.0;
        if (dVal < 0) {
            dScale = -scaleArray[index];
        } else {
            dScale = scaleArray[index];
        }

        return dScale;
    }

}



