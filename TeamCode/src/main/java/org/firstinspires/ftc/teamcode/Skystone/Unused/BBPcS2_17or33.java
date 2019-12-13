package org.firstinspires.ftc.teamcode.Skystone.Unused;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Skystone.Qualifier_1.hexChassis;


/**
 * <h1>first SkyStone autonomous program</h1>
 * for testing basicChassis that Nathan and Andrew wrote
 * <p>
 * Giving proper comments in your program makes it more
 * user friendly and it is assumed as a high quality code.
 *
 * @author  Aamod
 * @version 1.0
 * @since   2019-Nov-9
 */

@Disabled
@Autonomous(name = "BBPcS2_17or33")

public class BBPcS2_17or33 extends LinearOpMode{

    private hexChassis robot = new hexChassis();
    private ElapsedTime runtime = new ElapsedTime();
    private Servo stone_claw_servo;

    public BBPcS2_17or33() {

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
     * Park on Line Near Center
     */
    @Override
    public void runOpMode() {

        telemetry.addData("Status", "Ready to go");
        telemetry.update();
        robot.initChassis(this);
        waitForStart();

        //true = left, right = false
        robot.inPlaceTurn(90, false,.75);
        robot.moveForward(80,1);
        robot.inPlaceTurn(90, true,.75);
        robot.moveForward(10,1);
        //arm down
        robot.moveBackward(5,1);
        robot.inPlaceTurn(90, true,.75);
        robot.moveForward(50,1);
        //move sideways right
        robot.moveForward(5,1);
        //open arm
        //move sideways left
        robot.inPlaceTurn(180, true,.75);
        robot.moveForward(70,1);
        robot.inPlaceTurn(90, true,.75);
        robot.moveForward(10,1);
        //arm down
        robot.moveBackward(5,1);
        robot.inPlaceTurn(90,true,.75);
        robot.moveForward(40,1);
        robot.inPlaceTurn(90,false,.75);
        robot.moveForward(5,1);
        //open arm
        robot.moveBackward(13,1);
        robot.inPlaceTurn(90, false,.75);
        robot.moveForward(30,1);
        //now robot should be parked on line
    }
}
