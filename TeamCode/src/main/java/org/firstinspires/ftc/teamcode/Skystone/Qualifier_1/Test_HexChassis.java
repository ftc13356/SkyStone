package org.firstinspires.ftc.teamcode.Skystone.Qualifier_1;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "Test_HexChassis")
public class Test_HexChassis extends LinearOpMode{

    RobotB robot = new RobotB(); //TODO: private?
    private ElapsedTime runtime = new ElapsedTime();

    public void SkyStoneAutonomous_Test_HexChassis() {
    }

    @Override
    public void runOpMode() {

        telemetry.addData("Status", "Ready to go");
        telemetry.update();
        robot.initChassis(this);

        //****** Wait for Start *******
        while (!opModeIsActive() && !isStopRequested()) {
            telemetry.addData("status", "waiting for start command...");
            telemetry.update();
        }

        //******* Lift Mechanism *******//
        robot.liftAutonomous(4);
        robot.clawClamp(false); //Clamp
        sleep(1500);
        robot.clawClamp(true); //Unclamp
        sleep(500);
        robot.liftAutonomous(-4);
        robot.liftPosition(4);
        robot.liftPosition(0);
        sleep(500);

        telemetry.addData("Order:", "LeftFront, RightFront, LeftBack, RightBack");
        telemetry.update();
        robot.moveMotorLeftFront(5);
        robot.moveMotorRightFront(5);
        robot.moveMotorLeftBack(5);
        robot.moveMotorRightBack(5);
        sleep(1000);
        telemetry.addData("Status:", "MoveForward 5");
        telemetry.update();
        robot.moveForward(5,1);
        sleep(1000);
        telemetry.addData("Status:", "MoveBackward 5");
        telemetry.update();
        robot.moveBackward(5,1);
        sleep(1000);
        telemetry.addData("Status:", "MoveForward -5");
        telemetry.update();
        robot.moveForward(-5,1);
        sleep(1000);
        telemetry.addData("Status:", "MoveBackward -5");
        telemetry.update();
        robot.moveBackward(-5,1);
        sleep(1000);
        telemetry.addData("Status:", "MoveRight 5");
        telemetry.update();
        robot.moveRight(5,1);
        sleep(1000);
        telemetry.addData("Status:", "MoveLeft 5");
        telemetry.update();
        robot.moveLeft(5,1);
        sleep(1000);
        telemetry.addData("Status:", "MoveRight -5");
        telemetry.update();
        robot.moveRight(-5,1);
        sleep(1000);
        telemetry.addData("Status:", "MoveLeft -5");
        robot.moveLeft(-5,1);
        sleep(1000);
        telemetry.addData("Status:", "Turn inPlace Left");
        telemetry.update();
        robot.inPlaceTurn(90, true,1); //Left = true
        sleep(1000);
        telemetry.addData("Status:", "Turn inPlace Right");
        telemetry.update();
        robot.inPlaceTurn(90, false,1); //Right = false
        sleep(1000);
        telemetry.addData("Status:", "Turn normalTurn Left");
        telemetry.update();
        robot.inPlaceTurn(180, false,1); //Right = false
        sleep(1000);
        telemetry.addData("Status:", "Turn normalTurn Left");
        telemetry.update();
        robot.inPlaceTurn(180, true ,1 ); //Right = false
        sleep(1000);
        telemetry.addData("Status:", "Turn normalTurn Left");
        telemetry.update();



        /*robot.normalTurn(360, true); //Left = true
        sleep(2000);
        telemetry.addData("Status:", "Turn normalTurn Right");
        telemetry.update();
        robot.normalTurn(360, false); //Right = false
        */

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
