package org.firstinspires.ftc.teamcode.Skystone;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "Warren Test")
public class skystoneAutonomous_Warren extends LinearOpMode {
    private Servo claw1;
    basicChassis robot = new basicChassis();
    private ElapsedTime runtime = new ElapsedTime();

    public void skystoneAutonomous_Warren() {

    }
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Ready to go");
        telemetry.update();

        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        robot.initChassis(this);

        //path 1
        /*waitForStart();
        Servo1.setPosition(1.0);
        moveForward(12);
        Servo1.setPosition(-1.0);
        moveBackward(8);
        Servo1.setPosition(1.0);
        moveBackward(4);
        inPlaceTurn(90,true);
        moveForward(70);
        inPlaceTurn(90,false);
        moveForward(12);
        inPlaceTurn(90,true);
        moveForward(44);
        stop();*/
        //path 2
        /*waitForStart();
        Servo1.setPosition(1.0);
        moveForward(12);
        Servo1.setPosition(-1.0);
        moveBackward(8);
        Servo1.setPosition(1.0);
        moveBackward(4);
        inPlaceTurn(90,true);
        moveForward(18);
        stop();*/
        //path 3
        /*waitForStart();
        Servo1.setPosition(1.0);
        moveForward(10);
        inPlaceTurn(90,true);
        moveForward(70);
        inPlaceTurn(90,true);
        Servo1.setPosition(-1.0);
        moveBackward(8);
        Servo1.setPosition(1.0);
        moveBackward(4);
        inPlaceTurn(90,true);
        moveForward(70);
        inPlaceTurn(90,true);
        moveForward(10);
        inPlaceTurn(90,true);
        moveForward(70);
        inPlaceTurn(90, true);
        inPlaceTurn(90, true);
        moveForward(18);

        stop();*/
        //path 4
        /*waitForStart();
        robot.clawClamp(true);
        robot.moveForward(35);
        sleep(1000);
        robot.clawClamp(false);
        sleep(1000);
        robot.moveBackward(25);
        sleep(1000);
        robot.clawClamp(true);
        robot.moveBackward(4);
        sleep(1000);
        robot.inPlaceTurn(90,false);
        sleep(1000);
        robot.moveForward(40);*/

        robot.moveForward(5);
        robot.clawClamp(true);
        robot.clawClamp(false);
        robot.moveBackward(5);
        stop();
        waitForStart();
        //robot.tapeIsRed();


        stop();
    }

}