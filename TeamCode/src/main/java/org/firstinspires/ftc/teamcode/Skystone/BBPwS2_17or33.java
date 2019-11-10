package org.firstinspires.ftc.teamcode.Skystone;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotor;


/**
 * <h1> SkyStone autonomous program</h1>
 * <p>
 * Giving proper comments in your program makes it more
 * user friendly and it is assumed as a high quality code.
 *
 * @author  Aamod
 * @version 1.0
 * @since   2019-Nov-8
 */
@Autonomous(name = "BBPwS2_17or33")
public class BBPwS2_17or33 extends LinearOpMode{

    hexChassis robot = new hexChassis();
    private ElapsedTime runtime = new ElapsedTime();

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
        robot.inPlaceTurn(90, false);
        robot.moveForward(10);
        robot.inPlaceTurn(90, true);
        robot.moveForward(10);
        //arm down
        robot.moveBackward(5);
        robot.inPlaceTurn(90, true);
        robot.moveForward(50);
        //move sideways right
        robot.moveForward(5);
        //open arm
        //move sideways left
        robot.inPlaceTurn(180, true);
        robot.moveForward(70);
        robot.inPlaceTurn(90, true);
        robot.moveForward(10);
        //arm down
        robot.moveBackward(5);
        robot.inPlaceTurn(90,true);
        robot.moveForward(40);
        robot.inPlaceTurn(90,false);
        robot.moveForward(5);
        //open arm
        robot.moveBackward(5);
        robot.inPlaceTurn(90, false);
        robot.moveForward(30);
        //now robot should be parked on line
    }
}