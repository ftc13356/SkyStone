package org.firstinspires.ftc.teamcode.Skystone.Unused;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Skystone.Qualifier_1.RobotB;


/**
 * <h1> SkyStone autonomous program</h1>
 * <p>
 * Giving proper comments in your program makes it more
 * user friendly and it is assumed as a high quality code.
 *
 * @author  Aamod
 * @version 1.0
 * @since   2019-Dec-2
 */
@Autonomous(name = "stone1_foundation_parkCenter")
public class stone1_foundation_parkCenter extends LinearOpMode{
   RobotB robot = new RobotB();
    private ElapsedTime runtime = new ElapsedTime();
    private Servo stone_claw_servo;


    public stone1_foundation_parkCenter() {

    }
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Ready to go");
        telemetry.update();
        robot.initChassis(this);
        telemetry.addData("Status", "InitComplete, Ready to Start");
        telemetry.update();
        while (!opModeIsActive() && !isStopRequested()) {
            telemetry.addData("status", "waiting for start command...");
            telemetry.update();
        }

        robot.moveForward(24,1); // robot approaches foundation
        robot.clawClampPosition(1); // robot grabs stone
        sleep(500); // just in case
        robot.moveBackward(8,1); // robot moves away from foundation
        robot.liftAutonomous(1.2); // robot lifts stone so it doesn't drag
        robot.inPlaceTurn(90,true,1);
        robot.moveForward(90,1); // robot gets close to foundation
        robot.liftAutonomous(5); // robot lifts stone
        robot.inPlaceTurn(90,false,1);
        robot.moveForward(8,1); // robot should be in front of foundation
        robot.moveForward(4, 0.35); // robot touches foundation
        robot.liftAutonomous(4); // robot lowers stone
        robot.clawClampPosition(1); // robot releases stone
        robot.moveBackward(5,1); // robot backs up
        robot.moveLeft(5,1);
        robot.moveForward(2,0.35); // robot should be touching foundation
        robot.clawClampPosition(0); // robot is grabbing foundation
        robot.moveBackward(15,0.75); // robot backs up towards building site
        robot.inPlaceTurn(100,true, 1); // foundation should be in building site
        robot.clawClampPosition(1); // robot unclamps from foundation
        robot.inPlaceTurn(20,true,1);
        robot.moveBackward(15,1); // robot should be on tape
        robot.clawClampPosition(0); // just in case
    }
}
