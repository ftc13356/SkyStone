package org.firstinspires.ftc.teamcode.Skystone;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
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
    final double robot_diameter = 14.0;
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
//countsperinch = 23 ticks
        //counts_per_degree = counts_per_inch * robot_diameter * Math.PI / 360;
    }

    public void initChassis(LinearOpMode opMode) {

        op = opMode;
        hardwareMap = op.hardwareMap;

        motorLeftFront = hardwareMap.dcMotor.get("motorLeftFront");
        motorRightFront = hardwareMap.dcMotor.get("motorRightFront");
        motorLeftBack = hardwareMap.dcMotor.get("motorLeftBack");
        motorRightBack = hardwareMap.dcMotor.get("motorRightBack");

        motorLeftFront.setDirection(DcMotor.Direction.REVERSE);
        motorRightFront.setDirection(DcMotor.Direction.FORWARD);
        motorLeftBack.setDirection(DcMotor.Direction.FORWARD);
        motorRightBack.setDirection(DcMotor.Direction.REVERSE);
    }

    public void moveForward(double distance) {
        double sleepTime = (distance / speed * 1000);
        double ticksToMove = counts_per_inch * distance;
        left.setPower(.5);
        right.setPower(-.5);
        op.sleep((long) sleepTime);
        left.setPower(0);
        right.setPower(0);
    }
    public void moveBackward(double distance) {
        double sleepTime = (distance / speed * 1000);
        left.setPower(-.5);
        right.setPower(.5);
        op.sleep((long) sleepTime);
        left.setPower(0);
        right.setPower(0);
    }

    //@direction: true = left, false = right
    public void inPlaceTurn(double degrees, boolean direction) {

        double timeInMilliSec = degrees/45*333;

        if (direction == true){
            left.setPower(-1);
            right.setPower(-1);
            op.sleep((long) timeInMilliSec);
        } else {
            left.setPower(1);
            right.setPower(1);
            op.sleep((long) timeInMilliSec);
        }
        left.setPower(0);
        right.setPower(0);
    }

    //@direction: true = left, false = right
    //degrees means the angle of turning
    public void normalTurn(double degrees, boolean direction) {

        double timeInMilliSec = degrees/45*333;

        if (direction == true){
            left.setPower(1);
            right.setPower(0);
            op.sleep((long) timeInMilliSec);
        } else {
            left.setPower(1);
            right.setPower(0);
            op.sleep((long) timeInMilliSec);
        }
        //stop
        left.setPower(0);
        right.setPower(0);
    }
}

