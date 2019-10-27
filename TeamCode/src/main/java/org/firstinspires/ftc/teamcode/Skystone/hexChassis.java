package org.firstinspires.ftc.teamcode.Skystone;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class hexChassis {
    //initialize motor
    DcMotor motorLeftFront;
    DcMotor motorRightFront;
    DcMotor motorLeftBack;
    DcMotor motorRightBack;

    // these encoder variables vary depending on chassis type
    double counts_per_motor_rev = 0;
    double counts_per_inch = 0;
    double counts_per_degree = 0;

    // Initialize Encoder Variables
    final double robot_diameter = 10.5;
    final double drive_gear_reduction = 1.0;
    final double wheel_diameter = 4.0;

    /* local OpMode members. */
    private LinearOpMode op              = null;
    private HardwareMap  hardwareMap     = null;
    private ElapsedTime  period          = new ElapsedTime();

    private float speed = 37.5f;

    public hexChassis() {
        counts_per_motor_rev = 288;

     //   counts_per_inch = (counts_per_motor_rev / (wheel_diameter * Math.PI));
        counts_per_inch = 288 / (4 * 3.14);
//counts_per_inch = 23 ticks

        // 23 * 14 * 3.14 / 360 = 2.8 ticks
        counts_per_degree = counts_per_inch * robot_diameter * Math.PI / 360;
    }

    public void initChassis(LinearOpMode opMode) {

        op = opMode;
        hardwareMap = op.hardwareMap;

        motorLeftFront = hardwareMap.dcMotor.get("motorLeftFront");
        motorRightFront = hardwareMap.dcMotor.get("motorRightFront");
        motorLeftBack = hardwareMap.dcMotor.get("motorLeftBack");
        motorRightBack = hardwareMap.dcMotor.get("motorRightBack");

        motorLeftFront.setDirection(DcMotor.Direction.FORWARD);
        motorRightFront.setDirection(DcMotor.Direction.FORWARD);
        motorLeftBack.setDirection(DcMotor.Direction.FORWARD);
        motorRightBack.setDirection(DcMotor.Direction.FORWARD);
        // reset encoder count kept by left motor.
        motorLeftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorLeftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorRightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorRightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }


    public void moveForward(double distance) {
        double ticksToMove = counts_per_inch * distance;
        double newLeftBackTargetPosition = motorLeftBack.getCurrentPosition() + ticksToMove;
        double newLeftFrontTargetPosition = motorLeftFront.getCurrentPosition() + ticksToMove;
        double newRightBackTargetPosition = motorRightBack.getCurrentPosition() + ticksToMove;
        double newRightFrontTargetPosition = motorRightFront.getCurrentPosition() + ticksToMove;
        motorLeftBack.setTargetPosition((int)newLeftBackTargetPosition);
        motorLeftFront.setTargetPosition((int)newLeftFrontTargetPosition);
        motorRightBack.setTargetPosition((int)newRightBackTargetPosition);
        motorRightFront.setTargetPosition((int)newRightFrontTargetPosition);

        motorRightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorRightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorLeftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorLeftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motorLeftBack.setPower(0.5);
        motorRightBack.setPower(0.5);
        motorLeftFront.setPower(0.5);
        motorRightFront.setPower(0.5);

        while (op.opModeIsActive() && motorLeftBack.isBusy())
        {
            op.telemetry.addData("encoder-fwd", motorLeftBack.getCurrentPosition() + "  busy=" + motorLeftBack.isBusy());
            op.telemetry.update();
            op.idle();
        }
        motorLeftBack.setPower(0);
        motorRightBack.setPower(0);
        motorRightFront.setPower(0);
        motorLeftFront.setPower(0);
    }
    public void moveBackward(double distance) {
        double ticksToMove = counts_per_inch * distance;
        double newLeftBackTargetPosition = motorLeftBack.getCurrentPosition() - ticksToMove;
        double newLeftFrontTargetPosition = motorLeftFront.getCurrentPosition() - ticksToMove;
        double newRightBackTargetPosition = motorRightBack.getCurrentPosition() - ticksToMove;
        double newRightFrontTargetPosition = motorRightFront.getCurrentPosition() - ticksToMove;
        motorLeftBack.setTargetPosition((int)newLeftBackTargetPosition);
        motorLeftFront.setTargetPosition((int)newLeftFrontTargetPosition);
        motorRightBack.setTargetPosition((int)newRightBackTargetPosition);
        motorRightFront.setTargetPosition((int)newRightFrontTargetPosition);

        motorRightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorRightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorLeftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorLeftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motorLeftBack.setPower(0.5);
        motorRightBack.setPower(0.5);
        motorLeftFront.setPower(0.5);
        motorRightFront.setPower(0.5);

        while (op.opModeIsActive() && motorLeftBack.isBusy())
        {
            op.telemetry.addData("encoder-fwd", motorLeftBack.getCurrentPosition() + "  busy=" + motorLeftBack.isBusy());
            op.telemetry.update();
            op.idle();
        }
        motorLeftBack.setPower(0);
        motorRightBack.setPower(0);
        motorRightFront.setPower(0);
        motorLeftFront.setPower(0);
    }
//    public void moveBackward(double distance) {
//        double sleepTime = (distance / speed * 1000);
//        left.setPower(-.5);
//        right.setPower(.5);
//        op.sleep((long) sleepTime);
//        left.setPower(0);
//        right.setPower(0);
//    }

    //@direction: true = left, false = right
    public void inPlaceTurn(double degrees, boolean direction) {

        double ticksToTurn = (degrees * counts_per_degree);
        double newLeftBackTargetPosition;
        double newLeftFrontTargetPosition;
        double newRightBackTargetPosition;
        double newRightFrontTargetPosition;

        //turn left = left wheels go reverse; vice versa
        if (direction == true) {
            newLeftBackTargetPosition = motorLeftBack.getCurrentPosition() - ticksToTurn;
            newLeftFrontTargetPosition = motorLeftFront.getCurrentPosition() - ticksToTurn;
            newRightBackTargetPosition = motorRightBack.getCurrentPosition() + ticksToTurn;
            newRightFrontTargetPosition = motorRightFront.getCurrentPosition() + ticksToTurn;
        } else {
            newLeftBackTargetPosition = motorLeftBack.getCurrentPosition() + ticksToTurn;
            newLeftFrontTargetPosition = motorLeftFront.getCurrentPosition() + ticksToTurn;
            newRightBackTargetPosition = motorRightBack.getCurrentPosition() - ticksToTurn;
            newRightFrontTargetPosition = motorRightFront.getCurrentPosition() - ticksToTurn;
        }
        motorLeftBack.setTargetPosition((int)newLeftBackTargetPosition);
        motorLeftFront.setTargetPosition((int)newLeftFrontTargetPosition);
        motorRightBack.setTargetPosition((int)newRightBackTargetPosition);
        motorRightFront.setTargetPosition((int)newRightFrontTargetPosition);

        motorRightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorRightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorLeftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorLeftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motorLeftBack.setPower(0.25);
        motorRightBack.setPower(0.25);
        motorLeftFront.setPower(0.25);
        motorRightFront.setPower(0.25);

        while (op.opModeIsActive() && motorLeftBack.isBusy())
        {
            op.telemetry.addData("encoder-fwd", motorLeftBack.getCurrentPosition() + "  busy=" + motorLeftBack.isBusy());
            op.telemetry.update();
            op.idle();
        }
        motorLeftBack.setPower(0);
        motorRightBack.setPower(0);
        motorRightFront.setPower(0);
        motorLeftFront.setPower(0);
    }

    //@direction: true = left, false = right
    //degrees means the angle of turning
    public void normalTurn(double degrees, boolean direction) {

        double ticksToTurn = (degrees * counts_per_degree);
        double newLeftBackTargetPosition;
        double newLeftFrontTargetPosition;
        double newRightBackTargetPosition;
        double newRightFrontTargetPosition;

        //turn left = left wheel don't move; vice versa
        if (direction == true) {
            newLeftBackTargetPosition = motorLeftBack.getCurrentPosition();
            newLeftFrontTargetPosition = motorLeftFront.getCurrentPosition();
            newRightBackTargetPosition = motorRightBack.getCurrentPosition() + ticksToTurn;
            newRightFrontTargetPosition = motorRightFront.getCurrentPosition() + ticksToTurn;
        } else {
            newLeftBackTargetPosition = motorLeftBack.getCurrentPosition() + ticksToTurn;
            newLeftFrontTargetPosition = motorLeftFront.getCurrentPosition() + ticksToTurn;
            newRightBackTargetPosition = motorRightBack.getCurrentPosition();
            newRightFrontTargetPosition = motorRightFront.getCurrentPosition();
        }
        motorLeftBack.setTargetPosition((int)newLeftBackTargetPosition);
        motorLeftFront.setTargetPosition((int)newLeftFrontTargetPosition);
        motorRightBack.setTargetPosition((int)newRightBackTargetPosition);
        motorRightFront.setTargetPosition((int)newRightFrontTargetPosition);

        motorRightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorRightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorLeftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorLeftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motorLeftBack.setPower(0.5);
        motorRightBack.setPower(0.5);
        motorLeftFront.setPower(0.5);
        motorRightFront.setPower(0.5);

        while (op.opModeIsActive() && motorLeftBack.isBusy())
        {
            op.telemetry.addData("encoder-fwd", motorLeftBack.getCurrentPosition() + "  busy=" + motorLeftBack.isBusy());
            op.telemetry.update();
            op.idle();
        }
        motorLeftBack.setPower(0);
        motorRightBack.setPower(0);
        motorRightFront.setPower(0);
        motorLeftFront.setPower(0);
    }
}

