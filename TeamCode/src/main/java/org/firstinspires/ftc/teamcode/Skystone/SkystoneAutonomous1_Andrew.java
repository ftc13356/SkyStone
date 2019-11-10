package org.firstinspires.ftc.teamcode.Skystone;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

public class SkystoneAutonomous1_Andrew extends LinearOpMode {

    hexChassis_Andrew robot = new hexChassis_Andrew();
    private ElapsedTime runtime = new ElapsedTime();

    public void skystoneAutonomous_Andrew(){

    }

    @Override
    public void runOpMode() {

        waitForStart();

        robot.lateralDrift(true, 10);

        //clamp the foundation to the building zone

        robot.encoderDriveFB(8, 1);

        robot.lateralDrift(false, 10);


    }
}
