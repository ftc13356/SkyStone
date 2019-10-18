package org.firstinspires.ftc.teamcode.examples;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.examples.autonomous.autonomousFrame;

@Autonomous(name = "Encoder Test")
@Disabled
public class encoderTest extends autonomousFrame {

    @Override
    public void runOpMode() {

        versionPrint();
        initializeRobot();

        telemetry.addData("Status", "Ready"); telemetry.update();
        waitForStart();

        encoderDrive(12, 0, 1);
        encoderDrive(0, 90, 0.5);

        forward(9, 0.3);
        backward(1, 1);
        left(180, 0.45);
        right(45, 0.8);

        forward(-10, 1);
        right(-90, 1);

        telemetry.addData("Status", "Everything executed"); telemetry.update();
        stop();
    }
}