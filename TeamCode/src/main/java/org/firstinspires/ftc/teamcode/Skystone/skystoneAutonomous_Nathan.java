package org.firstinspires.ftc.teamcode.Skystone;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "skystoneAutonomous_Nathan")
public class skystoneAutonomous_Nathan extends LinearOpMode {


    hexChassis         robot     = new hexChassis();
    private ElapsedTime  runtime = new ElapsedTime();

    public void skystoneAutonomous_Nathan(){

    }
    @Override
    public void runOpMode() {

        telemetry.addData("Status", "Ready to go"); telemetry.update();

        robot.initChassis(this);

        waitForStart();
//        myChassis.initChassis();
        robot.inPlaceTurn(45, true);
        sleep(1000);
        robot.inPlaceTurn(45, true);
        sleep(1000);
        robot.inPlaceTurn(45, true);
        sleep(1000);
        robot.inPlaceTurn(45, true);
        sleep(1000);
        robot.inPlaceTurn(180, true);
        sleep(1000);
        robot.inPlaceTurn(45, false);
        sleep(1000);
        robot.inPlaceTurn(45, false);
        sleep(1000);
        robot.inPlaceTurn(45, false);
        sleep(1000);
        robot.inPlaceTurn(45, false);
        sleep(1000);
        robot.inPlaceTurn(180, false);
        sleep(1000);
//        robot.moveBackward(12);
//        sleep(2000);
//        robot.moveForward(12);
//        sleep(2000);
//        robot.inPlaceTurn(90, true);
//        sleep(2000);
//        robot.moveForward(12);
//        sleep(2000);
//        robot.inPlaceTurn(180,true);
//        sleep(2000);
//        robot.inPlaceTurn(90,false);
//        sleep(2000);
//        robot.moveBackward(12);
//        robot.inPlaceTurn(90, true);
//        sleep(2000);

        stop();

    }
}