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
 * Change Proposal: Need permission by author to change this file. Slack me to discuss first.
 */

public abstract class hexChassis_Andrew extends LinearOpMode {

    public DcMotor leftFront;
    public DcMotor leftBack;
    public DcMotor rightFront;
    public DcMotor rightBack;

    private LinearOpMode op             = null;
    private HardwareMap myHaredwareMap     = null;
    private ElapsedTime period          = new ElapsedTime();

    private float speed = 37.5f;
    final static double ROBOT_DIAMETER = 14.0;
    final static double CALLIBRATION = 1.0;
    final static double WHEEL_DIAMETER = 4.0;

    public final static double TICK_MARKS = 288;
    public final static double TICKS_PER_INCH = TICK_MARKS * CALLIBRATION/(WHEEL_DIAMETER*Math.PI);
    public final static double TICKS_PER_DEGREE = TICK_MARKS*ROBOT_DIAMETER*Math.PI/360;

    public void hexChassis_Andrew() {
        
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


    public void driveFB() {

        leftFront = hardwareMap.dcMotor.get("leftMotor");
        leftBack = hardwareMap.dcMotor.get("leftMotor");
        rightFront = hardwareMap.dcMotor.get("leftMotor");
        rightBack = hardwareMap.dcMotor.get("leftMotor");

        leftFront.setDirection(DcMotor.Direction.FORWARD);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setDirection(DcMotor.Direction.FORWARD);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setDirection(DcMotor.Direction.REVERSE);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setDirection(DcMotor.Direction.REVERSE);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            waitForStart();




        }


    /**
     * Hex Motor Chassis Specific encoderDrive()
     * @param driveFB Inches to move forward or backward (forward: +, backward: -)
     * @param speed Speed of robot (min: 0, max: 1)
     */

    public void encoderInPlaceTurn(double turnDegree, boolean turnDirection) {


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
