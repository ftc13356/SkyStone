package org.firstinspires.ftc.teamcode.Skystone;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.examples.autonomous.autonomousFrame;

/**
 * Author: Andrew
 * Change Proposal: Need permission by author to make changes. Slack me to discuss first
 *                  This is because your Changes could possible disrupt my other code unexpectedly
 *                  Thanks for your understanding
 */

public class hexChassis_Andrew extends LinearOpMode {

    public DcMotor leftFront;
    public DcMotor leftBack;
    public DcMotor rightFront;
    public DcMotor rightBack;

    private LinearOpMode myOP             = null;
    private HardwareMap myHaredwareMap     = null;
    private ElapsedTime period          = new ElapsedTime();

    //private float speed = 37.5f;
    final static double ROBOT_DIAMETER = 14.0;
    final static double CALLIBRATION = 1.0;
    final static double WHEEL_DIAMETER = 4.0;

    public final static double TICK_MARKS = 288;
    public final static double TICKS_PER_INCH = TICK_MARKS * CALLIBRATION/(WHEEL_DIAMETER*Math.PI);
    public final static double TICKS_PER_DEGREE = TICK_MARKS*ROBOT_DIAMETER*Math.PI/360;

    /**
     This is the constructor that initializes the motors
     */
    public hexChassis_Andrew() {

        /*
        myOP = opMode;
        myHaredwareMap = myOP.hardwareMap;

        leftFront = myHaredwareMap.dcMotor.get("motorLeftFront");
        leftBack = myHaredwareMap.dcMotor.get("motorRightFront");
        rightFront = myHaredwareMap.dcMotor.get("motorLeftBack");
        rightBack = myHaredwareMap.dcMotor.get("motorRightBack");

         */

    }


    public void initChassis(LinearOpMode opMode) {

        myOP = opMode;
        myHaredwareMap = myOP.hardwareMap;

        leftFront = myHaredwareMap.dcMotor.get("motorLeftFront");
        leftBack = myHaredwareMap.dcMotor.get("motorLeftBack");
        rightFront = myHaredwareMap.dcMotor.get("motorRightFront");
        rightBack = myHaredwareMap.dcMotor.get("motorRightBack");
    }

    /**
     * Hex Motor Chassis Specific encoderDrive()
     * @param distance Inches to move forward or backward (forward: +, backward: -)
     */

    public void encoderDriveFB(double distance, double callibration) {
        int newTarget;


        newTarget = (int)(Math.round(distance * TICKS_PER_INCH) * callibration);

        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setTargetPosition(newTarget);
        rightFront.setTargetPosition(newTarget);

        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        if (newTarget > 0) {
            leftFront.setPower(-0.5);
            leftBack.setPower(-0.5);
            rightFront.setPower(0.5);
            rightBack.setPower(0.5);
        } else {
            leftFront.setPower(0.5);
            leftBack.setPower(0.5);
            rightFront.setPower(0.5);
            rightBack.setPower(0.5);
        }

        while (leftFront.isBusy()) {

        }

        leftFront.setPower(0);
        leftBack.setPower(0);
        rightFront.setPower(0);
        rightBack.setPower(0);


    }

    /*
    public void inPlaceTurn(boolean direction) {
        if (boolean direction == true) {

        }

    }
    */


    /**
     * Hex Motor Chassis Specific encoderDrive()
     */

    public void runOpMode() {

    }



    public void encoderInPlaceTurn(double turnDegree, boolean turnDirection) {
        int newTarget;
        newTarget = (int)Math.round(TICKS_PER_DEGREE * turnDegree);

        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);



        leftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        if (turnDirection) {
            leftFront.setTargetPosition(newTarget);
            rightFront.setTargetPosition(-newTarget);
            leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftFront.setPower(-0.5);
            leftBack.setPower(-0.5);
            rightFront.setPower(-0.5);
            rightBack.setPower(-0.5);
        } else {
            leftFront.setTargetPosition(-newTarget);
            rightFront.setTargetPosition(newTarget);
            leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftFront.setPower(0.5);
            leftBack.setPower(0.5);
            rightFront.setPower(0.5);
            rightBack.setPower(0.5);
        }

        while (leftFront.isBusy()) {

        }

        leftFront.setPower(0);
        leftBack.setPower(0);
        rightFront.setPower(0);
        rightBack.setPower(0);

    }

    public void diagonallyMove(boolean direction, double distance) {

        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        if (direction == true) {
            leftFront.setPower(0.5);
            leftBack.setPower(0);
            rightFront.setPower(0);
            rightBack.setPower(0.5);
        } else {
            leftFront.setPower(0);
            leftBack.setPower(.5);
            rightFront.setPower(.5);
            rightBack.setPower(0);
        }

        while (leftFront.isBusy()) {

        }
        leftFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);
        leftFront.setPower(0);
    }


    public void lateralDrift(boolean direction, double distance) {

        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        if (direction == true) {
            leftFront.setPower(-0.25);
            leftBack.setPower(0.25);
            rightFront.setPower(0.25);
            rightBack.setPower(-0.25);
        } else {
            leftFront.setPower(-0.25);
            leftBack.setPower(-0.25);
            rightFront.setPower(0.25);
            rightBack.setPower(0.25);
        }

        while (leftFront.isBusy()) {

        }
        leftFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);
        leftFront.setPower(0);
    }
    /*
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
    */
}