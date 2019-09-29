package org.firstinspires.ftc.teamcode.examples.JavaTheBot;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="JavaTheBotTeleOp")

public class JavaTheBotLinear extends LinearOpMode {
    //Comment
    DcMotor left;
    DcMotor right;

    @Override
    public void runOpMode() {
        left = hardwareMap.dcMotor.get("left");
        right = hardwareMap.dcMotor.get("right");

        waitForStart();

        double driveFW = gamepad1.left_stick_y;
        double turn = gamepad1.right_stick_x;

        double leftPower = driveFW - turn;
        double rightPower = -driveFW - turn;

        left.setPower(leftPower);
        right.setPower(rightPower);
    }
}