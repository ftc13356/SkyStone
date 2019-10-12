package org.firstinspires.ftc.teamcode.Skystone;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class basicChassis extends LinearOpMode {
    public DcMotor left;
    public DcMotor right;
    private float speed = 37.5f;
    public void runOpMode() {

    }

    public basicChassis() {

    }

    public void initChassis() {
        left = hardwareMap.dcMotor.get("LeftMotor");
        right = hardwareMap.dcMotor.get("RightMotor");
    }

    public void moveForward(double distance) {
        double sleepTime = (distance / speed * 1000);
        left.setPower(.5);
        right.setPower(-.5);
        sleep((long) sleepTime);
        left.setPower(0);
        right.setPower(0);
    }
    public void moveBackward(double distance) {
        double sleepTime = (distance / speed * 1000);
        left.setPower(-.5);
        right.setPower(.5);
        sleep((long) sleepTime);
        left.setPower(0);
        right.setPower(0);
    }
    //@direction: true = left, false = right
    public void inPlaceTurn(double amount, boolean direction) {

        double degrees = amount/45*333;

        if (direction == true){
            left.setPower(-1);
            right.setPower(-1);
            sleep((long) degrees);

        } else {
            left.setPower(1);
            right.setPower(1);
            sleep((long)degrees);
        }
        left.setPower(0);
        right.setPower(0);
    }

    //@direction: true = left, false = right
    public void normalTurn(double amount, boolean direction) {

        double degrees = amount/45*333;

        if (direction == true){
            left.setPower(1);
            right.setPower(0);
            sleep((long) degrees);

        } else {
            left.setPower(1);
            right.setPower(0);
            sleep((long)degrees);
        }
        left.setPower(0);
        right.setPower(0);
    }
}
