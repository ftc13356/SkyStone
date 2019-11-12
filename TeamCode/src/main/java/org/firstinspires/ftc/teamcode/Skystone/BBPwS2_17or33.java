package org.firstinspires.ftc.teamcode.Skystone;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotor;


/**
 * <h1> SkyStone autonomous program</h1>
 * <p>
 * Giving proper comments in your program makes it more
 * user friendly and it is assumed as a high quality code.
 *
 * @author  Aamod
 * @version 1.2
 * @since   2019-Nov-8
 */
@Autonomous(name = "BBPwS2_17or33")
public class BBPwS2_17or33 extends LinearOpMode{

    hexChassis robot = new hexChassis();
    private ElapsedTime runtime = new ElapsedTime();
    private Servo stone_claw_servo;


    public BBPwS2_17or33() {

    }

    /**
     * This method is for the autonomous operation of the robot on the foundation side.
     * Turn Right 90 degrees
     * Forward
     * Turn Left 90 Degrees
     * Forward
     * Arm Down
     * Turn Left 90 Degrees
     * Forward
     * Move Sideways Right
     * Forward
     * Open arm
     * Move Sideways Left
     * Turn Left 180 Degrees
     * Forward
     * Turn Left 90 Degrees
     * Forward
     * Grab Block
     * Turn Left 90 Degrees
     * Forward
     * Turn Right 90 Degrees
     * Forward
     * Open Arm
     * Backward
     * Turn Right 90 Degrees
     * Forward
     * Park on Line Near Wall
     */
    @Override
    public void runOpMode() {

        telemetry.addData("Status", "Ready to go");
        telemetry.update();
        robot.initChassis(this);
        telemetry.addData("Status", "InitComplete, Ready to Start");
        telemetry.update();
        waitForStart();

        //true = left, right = false
        robot.moveForward(15);
        robot.inPlaceTurn(90, false);
        robot.moveForward(100);
        robot.inPlaceTurn(90, true);
        robot.moveForward(17);
        //robot.stone_claw_servo.setPosition(0.0);
        //robot.clawClampPosition(0.0);
        robot.clawClamp(false);
        sleep(500);
        robot.liftAutonomous(4);
        robot.moveBackward(10);
        robot.inPlaceTurn(90, true);
        robot.moveForward(60);
        robot.moveRight(20);
        robot.moveForward(10);
        //robot.clawClampPosition(0.8);
        robot.clawClamp(true);
        sleep(300);
        robot.moveLeft(25);
        robot.inPlaceTurn(180, true);
        robot.moveForward(80);
        robot.inPlaceTurn(90, true);
        robot.moveForward(15);
        robot.liftAutonomous(-4);
        robot.clawClampPosition(0.0);
        robot.moveBackward(10);
        robot.inPlaceTurn(90,true);
        robot.moveForward(60);
        robot.inPlaceTurn(90,false);
        robot.moveForward(10);
        //robot.clawClampPosition(0.8);
        robot.clawClamp(true);
        robot.moveBackward(5);
        robot.inPlaceTurn(90, false);
        robot.moveForward(30);
        //now robot should be parked on line
    }
}