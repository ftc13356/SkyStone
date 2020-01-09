package org.firstinspires.ftc.teamcode.Skystone.Qualifier_1;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * <h1>first SkyStone autonomous program</h1>
 * for testing basicChassis that Nathan and Andrew wrote
 * <p>
 * Giving proper comments in your program makes it more
 * user friendly and it is assumed as a high quality code.
 *
 * @author  Aiden Ma
 * @version 2.0
 * @since   2019-10-12
 */
@Disabled
@Autonomous(name = "RBfst")
public class RBfst extends LinearOpMode {

    private RobotB robot   = new RobotB();
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

        robot.moveForward(38,0.55);
        robot.inPlaceTurn(100,false,1);
        robot.moveForward(14,0.85);
        robot.inPlaceTurn(100,true,1);
        robot.moveForward(4,0.45);
        robot.inPlaceTurn(25,true,1);

        stone_claw_servo.setPosition(-6);
        sleep(500);
        stone_claw_servo.setPosition(-5);
        sleep(500);
        robot.moveBackward(37,1);// home 35,qualifier 40
        stone_claw_servo.setPosition(7);
        sleep(200);
        //robot.moveForward(4);
        robot.moveBackward(2.5,0.20);
        robot.inPlaceTurn(112,true,.85);
        robot.moveLeft(15,0.85);
        robot.inPlaceTurn(15,true,1);
        //sleep(8000);
        robot.moveForward(72,1);
        sleep(100);
        stone_claw_servo.setPosition(-5.5);
        sleep(100);
    }
}
