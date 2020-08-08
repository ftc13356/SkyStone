package org.firstinspires.ftc.teamcode.SkystoneOffSeason.Chassis2;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name = "StraferChassisTest")
public class Chassis2Test extends LinearOpMode{

    Robot robot = new Robot();
    private ElapsedTime runtime = new ElapsedTime();

    public void StraferChassisTest() {
    }

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Ready for Init");
        telemetry.update();
        robot.initChassis(this);
        /****** Wait for Start *******/
        while (!opModeIsActive() && !isStopRequested()) {
            telemetry.addData("Status", "Waiting for start command...");
            telemetry.update();
        }
        sleep(500);
//        robot.moveMotorLeftFront(-10);
//        sleep(1000);
//        robot.moveMotorRightFront(-10);
//        sleep(1000);
//        robot.moveMotorRightBack(-10);
//        sleep(2500);
//        robot.moveMotorLeftBack(-10);
//        sleep(1000);

//        robot.moveForward(30,0.8);
//        sleep(1000);
//        robot.moveBackward(30,0.8);
//        sleep(3000);
//        robot.moveForwardIMU(50,0.8);
//        sleep(1000);
//        robot.moveBackwardIMU(50,0.8);
//        sleep(3000);
//
//        robot.moveLeft(50,0.8);
//        sleep(1000);
//        robot.moveRight(50,0.8);
//        sleep(3000);
//        robot.moveLeftIMU(70, 0.8, 0, 0.15, 0.2);
//        sleep(1000);
//        robot.moveRightIMU(70, 0.8, 0, 0.1, 0.2);
//        sleep(1000);

//        robot.moveAngle(30,20);
//        sleep(5000);
//        robot.moveAngle3(30,30);
//        sleep(5000);
//        robot.moveAngle3(30,20);

//        robot.move(30, 0,0,0.5);
//        sleep(500);
//        robot.move(0,30,0,0.5);
//        sleep(500);
//        robot.move(0,-30,0,0.5);
//        sleep(500);
//        robot.move(0,0,30,0.5);
//        sleep(500);

        //robot.move(0,0,1080,0.5);
//        sleep(30000);
//        robot.move(0,30,0,0.5,0,0);
//        sleep(100);
//        robot.move(0,-30,0,0.5,0,0);

        //robot.moveAngle(-50,0.4226497, 1, 1, 0.4226497);
        //robot.moveAngle(-50,1, 0.4226497, 0.4226497, 1);
        //robot.moveAngle(50, 0.7321,1, 1, 0.7321);
        //robot.move(5, 5, 5, 0.5, 0.5, 0.5);

//        robot.moveAngle2(30,60);
//        sleep(3000);
//        robot.moveAngle2(30,0,0);
//        sleep(3000);
//        robot.moveAngle2(30,180,0);
//        sleep(3000);
//        robot.moveAngle2(30,90,0);
//        sleep(3000);
//        robot.moveAngle2(30,-90,0);
//        sleep(3000);
//        robot.moveAngle2(30,30,0);
//        sleep(3000);
//        robot.moveAngle2(30,-30,0);
//        sleep(3000);
//        robot.moveAngle2(-30,-120);
//        robot.moveAngle2(-30,60);
//        sleep(3000);
//        robot.moveAngle2(-30,-60);
        sleep(2000);
        robot.moveVuforiaWebcam(-30,30,0);
        sleep(2000);

    }
}