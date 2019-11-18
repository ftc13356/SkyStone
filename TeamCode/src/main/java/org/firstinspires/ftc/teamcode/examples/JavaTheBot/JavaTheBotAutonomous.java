package org.firstinspires.ftc.teamcode.examples.JavaTheBot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
@Autonomous(name="JavaTheBotAutonomous")

@Disabled
public class JavaTheBotAutonomous extends LinearOpMode {
    int x;
    int y;
    DcMotor LeftMotor;
    DcMotor RightMotor;
    Servo Servo1;


    @Override
    public void runOpMode() {

        LeftMotor = hardwareMap.dcMotor.get("LeftMotor");
        RightMotor = hardwareMap.dcMotor.get("RightMotor");
        Servo1= hardwareMap.servo.get("Servo1");
        LeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //LeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        RightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //RightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        waitForStart();
        LeftMotor.setPower(0.2);
        RightMotor.setPower(-0.2);
        sleep(1000);
        LeftMotor.setPower(-0.55);
        RightMotor.setPower(-0.55);
        sleep(1000);
        LeftMotor.setPower(0.185);
        RightMotor.setPower(-0.185);
        sleep(1000);
        LeftMotor.setPower(0.55);
        RightMotor.setPower(0.55);
        sleep(1000);
        LeftMotor.setPower(0.2);
        RightMotor.setPower(-0.2);
        sleep(1000);
        Servo1.setPosition(1.0);
        sleep(1000);
        Servo1.setPosition(-1.0);
        sleep(1000);
        LeftMotor.setPower(-0.4);
        RightMotor.setPower(0.4);
        sleep(1000);

        stop();

    }
}