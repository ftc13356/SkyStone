package org.firstinspires.ftc.teamcode.Skystone;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Skystone Demo")
public class skystoneAutonomous extends LinearOpMode {

    @Override
    public void runOpMode() {

        basicChassis myChassis = new basicChassis();

        waitForStart();
        myChassis.left = hardwareMap.dcMotor.get("LeftMotor");
        myChassis.right = hardwareMap.dcMotor.get("RightMotor");
//        myChassis.initChassis();
        myChassis.moveForward(12);
        sleep(2000);

        myChassis.inPlaceTurn(90, true);
        sleep(2000);

        myChassis.moveForward(12);
        sleep(2000);

        myChassis.normalTurn(90, false);
        sleep(2000);


        stop();

    }
}