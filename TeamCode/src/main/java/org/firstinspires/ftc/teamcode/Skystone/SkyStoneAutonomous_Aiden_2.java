package org.firstinspires.ftc.teamcode.Skystone;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

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
@Autonomous(name = "Skystone Demo")
public class SkyStoneAutonomous_Aiden_2 extends basicChassis {

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

        waitForStart();
        inPlaceTurn(85,true);
        moveForward(18);
        inPlaceTurn(85,false);
        moveForward(24);
        //need servo program to clamp on foundation
        moveBackward(24);
        //need servo program to unclamp foundation
        inPlaceTurn(85,true);
        moveBackward(76);
        inPlaceTurn(90,false);
        moveForward(50);
        inPlaceTurn(130,true);
        moveForward(46);
        inPlaceTurn(40,false);
        moveBackward(22);

        stop();

    }
}
