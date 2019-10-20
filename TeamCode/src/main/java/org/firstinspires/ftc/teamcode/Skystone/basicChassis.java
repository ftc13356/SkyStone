package org.firstinspires.ftc.teamcode.Skystone;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class basicChassis {

    /* local OpMode members. */
    private LinearOpMode op = null;
    private HardwareMap  hardwareMap = null;
    private ElapsedTime  period = new ElapsedTime();

    public DcMotor left;
    public DcMotor right;
    private float speed = 37.5f;
    final static double ROBOT_DIAMETER = 14.0;
    final static double CALIBRATION = 1.0;
    final static double WHEEL_DIAMETER = 4.0;

    public basicChassis() {

    }

    public void initChassis(LinearOpMode opMode) {
        op = opMode;
        hardwareMap = op.hardwareMap;

        left = hardwareMap.dcMotor.get("LeftMotor");
        right = hardwareMap.dcMotor.get("RightMotor");
    }

    public void moveForward(double distance) {
        double sleepTime = (distance / speed * 1000);
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

        if (direction){
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

        if (direction){
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
