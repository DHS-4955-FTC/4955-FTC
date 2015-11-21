/* Copyright (c) 2014 Qualcomm Technologies Inc

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Qualcomm Technologies Inc nor the names of its contributors
may be used to endorse or promote products derived from this software without
specific prior written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. */

package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;
import com.qualcomm.robotcore.util.Range;

/**
 * TeleOp Mode
 * <p>
 * Enables control of the robot via the gamepad Alan's a butt
 */
public class ServoTester extends OpMode {

    final static double LEFT_MIN_RANGE  = 0.90;
    final static double LEFT_MAX_RANGE  = 0;
    final static double RIGHT_MIN_RANGE  = 0;
    final static double RIGHT_MAX_RANGE  = .50;

    double leftPosition;
    double rightPosition;

    // amount to change the arm servo position.
    double servoDelta = 0.005;


    Servo servo_right;
    Servo servo_left;

    /**
     * Constructor
     */
    public ServoTester() {

    }

    /*
     * Code to run when the op mode is first enabled goes here
     *
     * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#start()
     */
    @Override
    public void init() {


	servo_left = hardwareMap.servo.get("servo_left");
    servo_right = hardwareMap.servo.get("servo_right");

        servo_left.setPosition(LEFT_MAX_RANGE);
        servo_right.setPosition(RIGHT_MIN_RANGE);



    }


    @Override
    public void loop() {

        if(gamepad1.dpad_left)
        {
            leftPosition -= servoDelta;
        }
        if (gamepad1.dpad_right) {

            leftPosition += servoDelta;
        }
       // servo_left.setPosition(servoPosition);
        servo_right.setPosition(leftPosition);



        telemetry.addData("Text", "*** Robot Data***");
        telemetry.addData("servo", "servo:  " + String.format("%.2f", leftPosition));

    }


    @Override
    public void stop() {

    }




}
