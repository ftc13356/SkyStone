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
 * @version 1.3
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
        robot.moveForward(15,1);
        robot.moveRight(100,.75);
        //robot.inPlaceTurn(90, false, 1);
        //robot.moveForward(100, 1);
        //robot.inPlaceTurn(90, true, 1);
        robot.moveForward(17, 1);
        //robot.stone_claw_servo.setPosition(0.0);
        //robot.clawClampPosition(0.0);
        robot.clawClamp(false);
        sleep(500);
        robot.liftAutonomous(4);
        robot.moveBackward(10, 1);
        robot.moveLeft(70,.75);
        //robot.inPlaceTurn(90, true);
        //robot.moveForward(60);
        //robot.moveRight(20);
        robot.moveForward(10, 1);
        //robot.clawClampPosition(0.8);
        robot.liftAutonomous(-4);
        robot.clawClamp(true);
        robot.liftAutonomous(-1);
        sleep(300);
        robot.clawClamp(false);
        robot.moveBackward(15,1);
        robot.moveForward(3,1);
        robot.moveRight(15,.75);
        /*robot.inPlaceTurn(90, true, .75);
        robot.moveForward(15, 1);
        robot.liftAutonomous(-4);
        robot.clawClampPosition(0.0);
        robot.moveBackward(10, 1);
        robot.inPlaceTurn(90,true, .75);
        robot.moveForward(60, 1);
        robot.inPlaceTurn(90,false, .75);
        robot.moveForward(10, 1);
        //robot.clawClampPosition(0.8);
        robot.clawClamp(true);
        robot.moveBackward(5, 1);
        robot.inPlaceTurn(90, false, .75);
        robot.moveForward(30, 1);*/
        //now robot should be parked on line
    }
}