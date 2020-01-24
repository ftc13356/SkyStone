package org.firstinspires.ftc.teamcode.Skystone.Qualifier_3;

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
 * @author  Aiden Ma
 * @version 2.0
 * @since   2020-01-09
 */


@Autonomous(name = "BBfst_Q3")
public class BBfst_Q3 extends LinearOpMode {

    private RobotA robot   = new RobotA();
    private ElapsedTime  runtime = new ElapsedTime();
    private Servo stone_claw_servo;

    /**
     * This method is for te autonomous operation of the robot on the Blue Alliance foundation side.
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

        stone_claw_servo = hardwareMap.servo.get("stone_claw_servo");

        telemetry.addData("Status", "Ready to go"); telemetry.update();

        robot.initChassis(this);

        // Aiden - during competition day robot dissconnected so we are trying this code
        while (!opModeIsActive() && !isStopRequested()) {
            telemetry.addData("status", "waiting for start command...");
            telemetry.update();
        }

        /*robot.moveForward(37,0.55);
        robot.inPlaceTurn(100,true,1);
        robot.moveForward(20,0.85);
        robot.inPlaceTurn(100,false,1);
        robot.moveForward(4.5,0.45);
        robot.inPlaceTurn(15,false,1);

        stone_claw_servo.setPosition(-6);
        sleep(1000);
        stone_claw_servo.setPosition(-5);
        sleep(1000);
        robot.moveBackward(39,1);
        stone_claw_servo.setPosition(7);
        sleep(200);
        //robot.moveForward(4);
        robot.moveBackward(3.0,0.20);
        robot.inPlaceTurn(107,false,.85);
        robot.moveRight(12,0.85);
        robot.inPlaceTurn(20,false,1);
        robot.moveForward(68,1);
        sleep(100);
        stone_claw_servo.setPosition(-5.5);
        sleep(100);
        //stop();

        robot.moveForward(26,0.5);
        robot.moveLeft(18,1.0);
        robot.inPlaceTurn(175,true,0.6);
        robot.moveBackward(6,0.3);
        robot.moveFoundationLefttdown(true);
        robot.moveFoundationRightdown(true);
        robot.moveForward(37,0.7);
        robot.moveFoundationLefttdown(false);
        robot.moveFoundationRightdown(false);
        robot.moveForward(1,0.2);
        robot.moveLeft(20,1.0);
        robot.moveBackward(22,0.5);
        robot.moveForward(15,0.5);
    */
        robot.liftPosition(2.5);
        robot.moveForward(25,1);
        robot.moveLeft(10,1.0);
        robot.moveForward(5,0.25);
        robot.moveFoundationRightdown(false);
        robot.moveFoundationLefttdown(false);
        robot.moveBackward(15,1);
        robot.inPlaceTurn(120,true,1.0);
        robot.moveForwardUntilBlue();
    }

}