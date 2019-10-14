package org.firstinspires.ftc.teamcode.Skystone;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


public class skystoneAutonomous_Warren extends LinearOpMode {
    private DcMotor left;
    private DcMotor right;
    Servo Servo1;
    private float speed = 37.5f;
    public void runOpMode() {

    }

    public void basicChassis() {
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
        right.setPower(1);
    }
    public void RunOpMode(){
        //path 1
        /*waitForStart();
        Servo1.setPosition(1.0);
        moveForward(12);
        Servo1.setPosition(-1.0);
        moveBackward(8);
        Servo1.setPosition(1.0);
        moveBackward(4);
        inPlaceTurn(90,true);
        moveForward(70);
        inPlaceTurn(90,false);
        moveForward(12);
        inPlaceTurn(90,true);
        moveForward(44);
        stop();*/
        //path 2
        /*waitForStart();
        Servo1.setPosition(1.0);
        moveForward(12);
        Servo1.setPosition(-1.0);
        moveBackward(8);
        Servo1.setPosition(1.0);
        moveBackward(4);
        inPlaceTurn(90,true);
        moveForward(18);
        stop();*/
        //path 3
        /*waitForStart();
        Servo1.setPosition(1.0);
        moveForward(10);
        inPlaceTurn(90,true);
        moveForward(70);
        inPlaceTurn(90,true);
        Servo1.setPosition(-1.0);
        moveBackward(8);
        Servo1.setPosition(1.0);
        moveBackward(4);
        inPlaceTurn(90,true);
        moveForward(70);
        inPlaceTurn(90,true);
        moveForward(10);
        inPlaceTurn(90,true);
        moveForward(70);
        inPlaceTurn(90, true);
        inPlaceTurn(90, true);
        moveForward(18);

        stop();*/
        //path 4
        /*waitForStart();
        Servo1.setPosition(1.0);
        moveForward(29);
        Servo1.setPosition(-1.0);
        moveBackward(25);
        Servo1.setPosition(1.0);
        moveBackward(4);
        normalTurn(90,left);
        moveForward(36);

        stop();*/
    }
}
