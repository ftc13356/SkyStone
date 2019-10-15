package org.firstinspires.ftc.teamcode.Skystone;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
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
@Autonomous(name = "Skystone Demo1")
public class SkyStoneAutonomous_Aiden_1 extends LinearOpMode {

    basicChassis         robot   = new basicChassis();
    private ElapsedTime  runtime = new ElapsedTime();

    public void SkyStoneAutonomous_Aiden_1 () {

    }
    /**
     * This method is for te autonomous operation of the robot on the foundation side.
     * 1st you inPlaceTurn left
     * 2nd you go forward ~ 18 in.
     * 3rd you turn right
     * 4th you go forward ~ 24 in. to clamp the foundation
     * 5th you back up with the foundation clamped
     * 6th the unclamp the foundation
     * 7th you inPlaceTurn left
     * 8th you back up onto the blue/red line
     */
    @Override
    public void runOpMode() {

        telemetry.addData("Status", "Ready to go"); telemetry.update();

        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        robot.initChassis(this);

        waitForStart();
        robot.moveForward(8);
        robot.inPlaceTurn(130,true);
        sleep((long) 100);
        robot.moveForward(15);
        robot.inPlaceTurn(115,false);
        robot.moveForward(16);
        //need servo program to clamp on foundation
        robot.moveBackward(24);
        //need servo program to unclamp foundation
        robot.inPlaceTurn(90,true);
        robot.moveBackward(70);

        stop();
    }
}
