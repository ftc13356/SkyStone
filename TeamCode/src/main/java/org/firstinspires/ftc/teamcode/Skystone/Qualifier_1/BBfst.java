package org.firstinspires.ftc.teamcode.Skystone.Qualifier_1;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
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
 * @since   2019-10-12
 */
@Autonomous(name = "BBfst")
public class BBfst extends LinearOpMode {

    private hexChassis robot   = new hexChassis();
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

        waitForStart();

        robot.moveForward(33.8,0.75);
        robot.inPlaceTurn(15,false,1);

        stone_claw_servo.setPosition(-6);
        sleep(1500);
        stone_claw_servo.setPosition(-5);
        sleep(1500);
        robot.moveBackward(35.5,1);
        stone_claw_servo.setPosition(7);
        sleep(1500);
        //robot.moveForward(4);
        //robot.inPlaceTurn(145,false);
        robot.moveRight(15,.85);
        robot.inPlaceTurn(15,false,1);
        robot.moveRight(15,.85);
        robot.inPlaceTurn(15,false,1);
        robot.moveRight(10, .85);
        robot.moveForward(8, 1);
        robot.inPlaceTurn(90,false,1);
        robot.moveForward(80,1);
        robot.inPlaceTurn(70,true,1);
        robot.moveForward(10,1);
        stone_claw_servo.setPosition(-6);
        sleep(1500);
        robot.liftAutonomous(0.90);
        robot.moveBackward(25,1);
        robot.inPlaceTurn(80,true,1);
        robot.moveForward(50,1);
        robot.moveBackward(20,1);
        stop();

    }
}
