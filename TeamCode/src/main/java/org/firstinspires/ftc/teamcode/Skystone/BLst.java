package org.firstinspires.ftc.teamcode.Skystone;

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
 * @version 1.0
 * @since   2019-10-12
 */
@Autonomous(name = "BLst")
public class BLst extends LinearOpMode {

    private basicChassis robot   = new basicChassis();
    private ElapsedTime  runtime = new ElapsedTime();
    private Servo stone_claw_servo;


    /**
     * This method is for te autonomous operation of the robot on the Blue Alliance SkyStone side.
     * 1st move forward 42 in
     * 2nd inPlaceTurn 130 degrees left
     * 3rd move forward 46 in.
     * 4th inPlaceTurn 40 degrees right
     * 5th move backward 22 in. onto the blue line
     */
    @Override
    public void runOpMode() {

        stone_claw_servo = hardwareMap.servo.get("stone_claw_servo");


        telemetry.addData("Status", "Ready to go"); telemetry.update();

        robot.initChassis(this);

        waitForStart();
        robot.moveForward(28);
        stone_claw_servo.setPosition(-6);
        sleep(2500);
        robot.moveBackward(12);
        robot.inPlaceTurn(90,true);
        robot.moveForward(70);
        stone_claw_servo.setPosition(7.5);
        sleep(2500);
        robot.moveBackward(30);

        stop();


    }
}