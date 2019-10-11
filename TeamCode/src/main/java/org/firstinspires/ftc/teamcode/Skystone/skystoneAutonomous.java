package org.firstinspires.ftc.teamcode.Skystone;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Skystone Demo")
public class skystoneAutonomous extends LinearOpMode {

    // Create motors in program
    private DcMotor left;
    private DcMotor right;

    @Override
    public void runOpMode() {

        basicChassis myChassis = new basicChassis();

        waitForStart();

        myChassis.moveForward(12);

        myChassis.moveForward(5);
        stop();

    }
}