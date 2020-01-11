package org.firstinspires.ftc.teamcode.Skystone.Qualifier_3;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * <h1> SkyStone autonomous program</h1>
 * <p>
 * Giving proper comments in your program makes it more
 * user friendly and it is assumed as a high quality code.
 *
 * @author Aamod
 * @version 1.0
 * @since 2020-Jan-7
 */

//@Disabled
@Autonomous(name = "R_stone2_parkCenter_Q3")
public class R_stone2_parkCenter_Q3 extends LinearOpMode {
    RobotA robot = new RobotA();
    private ElapsedTime runtime = new ElapsedTime();
    private Servo stone_claw_servo;


    public R_stone2_parkCenter_Q3() {

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
        //Main Autonomous
        robot.moveForward(40, 1); // robot approaches foundation
        robot.moveForward(5, 0.5);
        robot.clawClampPosition(0); // robot grabs stone
        sleep(500); // just in
        robot.liftPosition(3);
        robot.moveBackward(13, 1); // robot moves away from foundation
        robot.inPlaceTurn(110, false, 1.0);
        robot.liftPosition(0.85); // robot lifts stone so it doesn't drag
        robot.moveForward(50, 1);
        robot.clawClampPosition(1);
        robot.moveBackward(60, 1);
        robot.inPlaceTurn(95, true, 1);
        robot.liftPosition(0.5);
        robot.moveForward(12, 1);
        robot.moveForward(4, 0.5);
        robot.clawClampPosition(0); // robot grabs 2nd stone
        sleep(500);
        robot.liftPosition(3);
        //sleep(100);
        robot.moveBackward(18, 1);
        robot.inPlaceTurn(110, false, 1);
        robot.liftPosition(0.85);
        robot.moveForward(65, 1);
        robot.clawClampPosition(1);
        robot.moveBackward(16, 1);
        robot.clawClampPosition(0);
    }
}