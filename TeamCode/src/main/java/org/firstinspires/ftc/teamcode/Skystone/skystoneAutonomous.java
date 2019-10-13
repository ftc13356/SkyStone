package org.firstinspires.ftc.teamcode.Skystone;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Skystone Demo")
public class skystoneAutonomous extends LinearOpMode {


    basicChassis         robot   = new basicChassis();
    private ElapsedTime  runtime = new ElapsedTime();

    public void skystoneAutonomous(){

    }
    @Override
    public void runOpMode() {

        telemetry.addData("Status", "Ready to go"); telemetry.update();

        robot.initChassis(this);

        waitForStart();
//        myChassis.initChassis();
        robot.moveForward(12);
        sleep(2000);

        robot.inPlaceTurn(90, true);
        sleep(2000);

        robot.moveForward(12);
        sleep(2000);

        robot.normalTurn(90, false);
        sleep(2000);


        stop();

    }
}