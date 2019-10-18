package org.firstinspires.ftc.teamcode.Skystone;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.examples.autonomous.autonomousFrame;

public abstract class hexChassis_Andrew extends basicChassis {

    public DcMotor leftFront;
    public DcMotor leftBack;
    public DcMotor rightFront;
    public DcMotor rightBack;

    public final static double TICK_MARKS = 288;
    public final static double TICKS_PER_INCH = TICK_MARKS * CALLIBRATION/(WHEEL_DIAMETER*Math.PI);
    public final static double TICKS_PER_DEGREE = TICK_MARKS*ROBOT_DIAMETER*Math.PI/360;

    public void startMotors() {


        leftFront.setDirection(DcMotor.Direction.REVERSE);
        leftFront.setDirection(DcMotor.Direction.FORWARD);
        leftBack.setDirection(DcMotor.Direction.REVERSE);
        leftBack.setDirection(DcMotor.Direction.FORWARD);

        rightFront.setDirection(DcMotor.Direction.FORWARD);
        rightFront.setDirection(DcMotor.Direction.REVERSE);
        rightBack.setDirection(DcMotor.Direction.FORWARD);
        rightBack.setDirection(DcMotor.Direction.REVERSE);


    }

    /**
     * Hex Motor Chassis Specific encoderDrive()
     * @param driveFB Inches to move forward or backward (forward: +, backward: -)
     * @param speed Speed of robot (min: 0, max: 1)
     * @param opModeIsActive Type "opModeIsActive()" boolean in autonomousFrame (program extending LinerOpMode)
     */
    public void encoderDriveFB(double driveFB, double speed, boolean opModeIsActive) {
        int newLeftFrontTarget;
        int newRightFrontTarget;
        int newLeftBackTarget;
        int newRightBackTarget;

        double motorLeftFrontEncoder;
        double motorRightFrontEncoder;
        double motorLeftBackEncoder;
        double motorRightBackEncoder;
    }

<<<<<<< HEAD
}
=======
    public void driveFB(double distance, double speed, boolean direction) {

            leftFront.setDirection(DcMotor.Direction.FORWARD);
            leftBack.setDirection(DcMotor.Direction.FORWARD);
            leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightFront.setDirection(DcMotor.Direction.REVERSE);
            rightBack.setDirection(DcMotor.Direction.REVERSE);
            rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            waitForStart();




        }

    }


    /**
     * Hex Motor Chassis Specific encoderDrive()
     * @param driveFB Inches to move forward or backward (forward: +, backward: -)
     * @param speed Speed of robot (min: 0, max: 1)
     */

    public void encoderInPlaceTurn(double turnDegree, boolean turnDirection) {


    }

}
>>>>>>> a32ef5f554c512c1d6a553e5710028d2a517802c
