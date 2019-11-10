package org.firstinspires.ftc.teamcode.Skystone;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "Skystone Demo Andrew")
public class skystoneAutonomous_Andrew extends LinearOpMode {


    // hexChassis_Andrew robot = new hexChassis_Andrew(this);
    hexChassis_Andrew robot = new hexChassis_Andrew();
    private ElapsedTime runtime = new ElapsedTime();

    public void skystoneAutonomous_Andrew(){

    }

    @Override
    public void runOpMode() {

        telemetry.addData("Status", "Ready to go"); telemetry.update();

        // robot.initChassis(this);

        robot.initChassis(this);

        waitForStart();
        robot.encoderDriveFB(10, 2.0);
        sleep(1000);
//
//
        robot.encoderDriveFB(-10, 2.0);
        sleep(1000);
//
//        robot.encoderInPlaceTurn(45, true);
//
//
//        waitForStart();
//        robot.encoderDriveFB(15, 1.0);
//
//        sleep(2000);
//
//
//        robot.encoderDriveFB(-15, 1.0);
//        sleep(2000);
//        robot.diagonallyMove(true, 10);

        robot.diagonallyMove(true, 10);


        stop();

    }
}