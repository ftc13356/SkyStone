package org.firstinspires.ftc.teamcode.Skystone.Qualifier_1;

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
 * @author Aamod
 * @version 1.3
 * @since 2019-Dec-2
 */
@Autonomous(name = "B_stone2_parkCenter")
public class B_stone2_parkCenter extends LinearOpMode {
    RobotB robot = new RobotB();
    private ElapsedTime runtime = new ElapsedTime();
    private Servo stone_claw_servo;


    public B_stone2_parkCenter() {

    }

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Ready to go");
        telemetry.update();
        robot.initChassis(this);
        telemetry.addData("Status", "InitComplete, Ready to Start");
        telemetry.update();

        //Wait for Start
        while (!opModeIsActive() && !isStopRequested()) {
            telemetry.addData("status", "waiting for start command...");
            telemetry.update();
        }

        //Main Autonomous program
        robot.moveForward(40, 1); // robot approaches foundation
        robot.moveForward(5, 0.5);
        robot.clawClampPosition(0); // robot grabs stone
        sleep(500); // just in
        robot.liftPosition(3);
        robot.moveBackward(13, 1); // robot moves away from foundation
        robot.inPlaceTurn(130, true, 1.0);
        robot.liftPosition(0.85); // robot lifts stone so it doesn't drag
        robot.moveForward(50, 1);
        robot.clawClampPosition(1);
        robot.moveBackward(60, 1);
        robot.inPlaceTurn(115, false, 1);
        robot.liftPosition(0.5);
        robot.moveForward(12, 1);
        robot.moveForward(4, 0.5);
        robot.clawClampPosition(0); // robot grabs 2nd stone
        sleep(500);
        robot.liftPosition(3);
        //sleep(100);
        robot.moveBackward(18, 1);
        robot.inPlaceTurn(130, true, 1);
        robot.liftPosition(0.85);
        robot.moveForward(60, 1);
        robot.clawClampPosition(1);
        robot.moveBackward(15, 1);
        robot.clawClampPosition(0);
        /*
        //robot.moveForward(100,1); // robot gets close to foundation
        robot.liftAutonomous(4); // robot lifts stone
        robot.inPlaceTurn(90, false, 1);
        robot.moveForward(15, 1); // robot should be in front of foundation
        robot.moveForward(8, 0.35); // robot touches foundation
        robot.liftAutonomous(-3); // robot lowers stone
        robot.clawClampPosition(1); // robot releases stone
        robot.moveBackward(5, 1); // robot backs up
        robot.moveLeft(10, 1);
        robot.inPlaceTurn(30, true, 1);
        robot.moveForward(5, 0.35); // robot should be touching foundation
        robot.liftAutonomous(-2);
        robot.clawClampPosition(0); // robot is grabbing foundation
        robot.moveBackward(20, 0.75); // robot backs up towards building site
        robot.inPlaceTurn(150, true, 1); // foundation should be in building site
        robot.clawClampPosition(1); // robot unclamps from foundation
        robot.inPlaceTurn(45, true, 1);
        robot.moveBackward(25, 1); // robot should be on tape
        robot.clawClampPosition(0); // just in case

         */

    }
}
