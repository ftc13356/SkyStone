package org.firstinspires.ftc.teamcode.Skystone;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "SkyAuto Aamod HexC")
public class SkyStoneAutonomous_Aamod_HexChassis extends LinearOpMode{

    hexChassis robot = new hexChassis();
    private ElapsedTime runtime = new ElapsedTime();

    public void SkyStoneAutonomous_Aamod_HexChassis() {
    }

    @Override
    public void runOpMode() {

        telemetry.addData("Status", "Ready to go");
        telemetry.update();
        robot.initChassis(this);
        waitForStart();

        //******* Lift Mechanism *******//
        robot.liftAutonomous(5);
        sleep(500);
        ///robot.liftAutonomous(-4);


        //move sideways left
        /*robot.moveBackward(70);
        telemetry.addData("What is Happening", "Robot drives towards Sksytones");
        telemetry.update();
        sleep(500);
        robot.inPlaceTurn(90, true);
        telemetry.addData("What is Happening", "Prepares to drive towards Skystones");
        telemetry.update();
        sleep(500);
        robot.moveForward(20);
        telemetry.addData("What is Happening", "Robot is in front of Skystones");
        telemetry.update();
        sleep(500);
            //servo arm down
        robot.moveBackward(8);
        telemetry.addData("What is Happening", "Robot backs away from Skystones");
        telemetry.update();
        sleep(500);
        robot.inPlaceTurn(90, true);
        telemetry.addData("What is Happening", "Prepares to go towards foundation");
        telemetry.update();
        sleep(500);
        robot.moveForward(60);
        telemetry.addData("What is Happening", "Robot drives towards Sksytones");
        telemetry.update();
        sleep(500);
        robot.inPlaceTurn(45, false);
        sleep(500);
        robot.moveForward(5);
        sleep(500);
        robot.inPlaceTurn(90, true);
        sleep(500);
        robot.moveForward(5);
        sleep(500);
        robot.inPlaceTurn(45, true);
        sleep(500);
        robot.moveForward(12);
        sleep(500);
        // foundation is now in corner
        robot.moveBackward(5);
        sleep(500);
        robot.inPlaceTurn(90, true);
        sleep(500);
        robot.moveForward(12);
        sleep(500);*/
        stop();
    }
}
