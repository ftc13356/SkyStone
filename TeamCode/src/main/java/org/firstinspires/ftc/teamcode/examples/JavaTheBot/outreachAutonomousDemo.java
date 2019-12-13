package org.firstinspires.ftc.teamcode.examples.JavaTheBot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Disabled
@Autonomous(name = "Autonomous Demo")
public class outreachAutonomousDemo extends LinearOpMode {

    // Create motors in program
    private DcMotor left;
    private DcMotor right;

    @Override
    public void runOpMode() {

        left = hardwareMap.dcMotor.get("LeftMotor");
        right = hardwareMap.dcMotor.get("RightMotor");

        waitForStart();

        left.setPower(1);
        right.setPower(-1);
        sleep(2000);

        left.setPower(-1);
        right.setPower(1);
        sleep(2000);

        left.setPower(1);
        right.setPower(1);
        sleep(2000);

        stop();

    }
}