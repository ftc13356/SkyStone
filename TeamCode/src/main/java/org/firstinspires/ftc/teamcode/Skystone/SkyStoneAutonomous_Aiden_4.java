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
@Autonomous(name = "Skystone Demo4")
@Disabled
public class SkyStoneAutonomous_Aiden_4 extends LinearOpMode {

    basicChassis         robot   = new basicChassis();
    private ElapsedTime  runtime = new ElapsedTime();

    public void SkyStoneAutonomous_Aiden_4(){

    }


    /**
     * This method is for te autonomous operation of the robot on the Blue Alliance SkyStone side.
     * 1st move forward 42 in.
     * 2nd inPlaceTurn 130 degrees to the left
     * 3rd move forward 60 in.
     * 4th inPlaceTurn 130 degrees to the right
     * 5th move forward 10 in.
     * 6th move backward 28 in.
     */
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Ready to go"); telemetry.update();

        robot.initChassis(this);

        waitForStart();
        robot.moveForward(42);
        robot.inPlaceTurn(130,true);
        robot.moveForward(60);
        robot.inPlaceTurn(130,false);
        robot.moveForward(10);
        robot.moveBackward(28);
        stop();

    }
}