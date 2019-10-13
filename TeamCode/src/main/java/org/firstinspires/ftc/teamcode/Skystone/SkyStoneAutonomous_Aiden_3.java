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
 * @version 1.0
 * @since   2019-10-12
 */
@Autonomous(name = "Skystone Demo")
public class SkyStoneAutonomous_Aiden_3 extends basicChassis {

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

        waitForStart();
        moveForward(42);
        inPlaceTurn(130,true);
        moveForward(46);
        inPlaceTurn(40,false);
        moveBackward(22);
        stop();

    }
}
