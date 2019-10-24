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
        robot.encoderDriveFB(5, 1.0);

        sleep(2000);


        robot.encoderDriveFB(-5, 1.0);
        sleep(2000);



        stop();

    }
}