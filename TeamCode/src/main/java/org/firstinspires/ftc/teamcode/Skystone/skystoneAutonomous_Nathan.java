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

        //place robot on tile 2
        waitForStart();
        robot.moveForward(6);
        sleep(1000);
        robot.moveBackward(6);
        sleep(1000);
        robot.moveLeft(6);
        sleep(1000);
        robot.moveRight(6);
//        //myChassis.initChassis();
//        robot.inPlaceTurn(90, false);
//        sleep(50);
//        robot.moveForward(16);
//        sleep(50);
//        robot.moveForward(16);
//        sleep(50);
//        robot.moveForward(16);
//        sleep(50);
//        robot.inPlaceTurn(90, true);
//        sleep(50);
//        robot.moveForward(16);
//        sleep(50);
//        //robot puts claw down and picks up block
//        //pull claw back up
//        robot.inPlaceTurn(90, true);
//        sleep(50);
//        robot.moveForward(16);
//        sleep(50);
//        robot.moveForward(16);
//        sleep(50);
//        robot.moveForward(16);
//        sleep(50);
//        robot.inPlaceTurn(90, false);
//        sleep(50);
//        //robot puts claw down
//        //release claw
//        //clamp claw around the base of foundation
//        robot.moveBackward(16);

        stop();

    }
}