package org.firstinspires.ftc.teamcode.Skystone.Unused;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Skystone.Qualifier_1.hexChassis;

/**
 * <h1>first SkyStone autonomous program</h1>
 * for testing basicChassis that Nathan and Andrew wrote
 * <p>
 * Giving proper comments in your program makes it more
 * user friendly and it is assumed as a high quality code.
 *
 * @author Andrew
 * @version 1.0
 * @since 2019-11-11
 */
@Disabled
@Autonomous(name = "BBfst")

public class RLfst extends LinearOpMode {

    private hexChassis robot = new hexChassis();
    private ElapsedTime runtime = new ElapsedTime();
    private Servo stone_claw_servo;

    /**
     * This method is for the autonomous operation of the robot on the Red Alliance foundation side.
     * 1st you inPlaceTurn left
     * 2nd you go forward ~ 18 in.
     * 3rd you turn right
     * 4th you go forward ~ 24 in. to clamp the foundation
     * 5th you back up with the foundation clamped
     * 6th the unclamp the foundation
     * 7th you inPlaceTurn left
     * 8th move backward ~ 76 in.
     * 9th inPlaceTurn 90 degrees right
     * 10th move forward ~ 50 in.
     * 11th inPlaceTurn 130 degrees left
     * 12th move forward ~ 46 in.
     * 13th inPlaceTurn 90 degrees left
     * 8th you back up ~ 22 in. onto the blue line
     */
    @Override
    public void runOpMode() {

        //place the robot by 2 tiles

        stone_claw_servo = hardwareMap.servo.get("stone_claw_servo");

        telemetry.addData("Status", "Ready to go");
        telemetry.update();

        robot.initChassis(this);


        waitForStart();

        /*
        //commented out by Aiden bcs. code does not compile for anyone
        robot.moveForward(24);

        stone_claw_servo.setPosition(-4);

        robot.moveBackward(24);

        stone_claw_servo.setPosition(4);

        robot.moveRight(96);

        robot.moveBackward(24);

        robot.moveForward(48);

        stone_claw_servo.setPosition(-4);

        robot.moveBackward(48);

        robot.moveLeft(96);

        stone_claw_servo.setPosition(4);

        robot.moveForwardUntilBlue();

        stop();
*/
    }
}
