package org.firstinspires.ftc.teamcode.Skystone;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "Test_HexChassis")
public class SkyStoneAutonomous_Test_HexChassis extends LinearOpMode{

    hexChassis robot = new hexChassis(); //TODO: private?
    private ElapsedTime runtime = new ElapsedTime();

    public void SkyStoneAutonomous_Test_HexChassis() {
    }

    @Override
    public void runOpMode() {

        telemetry.addData("Status", "Ready to go");
        telemetry.update();
        robot.initChassis(this);
        waitForStart();

        //******* Lift Mechanism *******//
        robot.liftAutonomous(1);
        sleep(500);
        robot.liftAutonomous(-1);

        telemetry.addData("Order:", "LeftFront, RightFront, LeftBack, RightBack");
        telemetry.update();
        robot.moveMotorLeftFront(5);
        robot.moveMotorRightFront(5);
        robot.moveMotorLeftBack(5);
        robot.moveMotorRightBack(5);
        sleep(2000);
        telemetry.addData("Status:", "MoveForward 5");
        telemetry.update();
        robot.moveForward(5);
        sleep(2000);
        telemetry.addData("Status:", "MoveBackward 5");
        telemetry.update();
        robot.moveBackward(5);
        sleep(2000);
        telemetry.addData("Status:", "MoveForward -5");
        telemetry.update();
        robot.moveForward(-5);
        sleep(2000);
        telemetry.addData("Status:", "MoveBackward -5");
        telemetry.update();
        robot.moveBackward(-5);
        sleep(2000);
        telemetry.addData("Status:", "MoveRight 5");
        telemetry.update();
        robot.moveRight(5);
        sleep(2000);
        telemetry.addData("Status:", "MoveLeft 5");
        telemetry.update();
        robot.moveLeft(5);
        sleep(2000);
        telemetry.addData("Status:", "MoveRight -5");
        telemetry.update();
        robot.moveRight(-5);
        sleep(2000);
        telemetry.addData("Status:", "MoveLeft -5");
        telemetry.update();
        robot.moveLeft(-5);

        //move sideways left
        /*
        sleep(3000);
        telemetry.addData("Status:", "MoveBackward -5");
        telemetry.update();
        robot.moveBackward(-5);
        robot.moveBackward(70);
        telemetry.addData("What is Happening", "Robot drives towards Sksytones");
i        telemetry.update();
        sleep(500);
        */
        /*
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
