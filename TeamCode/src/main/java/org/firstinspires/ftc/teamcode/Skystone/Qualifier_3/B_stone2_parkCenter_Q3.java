package org.firstinspires.ftc.teamcode.Skystone.Qualifier_3;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
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
 * @since 2020 Jan-7
 */

//@Disabled
@Autonomous(name = "B_stone2_parkCenter_Q3")
public class B_stone2_parkCenter_Q3 extends LinearOpMode {
    RobotA robot = new RobotA();
    private ElapsedTime runtime = new ElapsedTime();
    private Servo stone_claw_servo;


    public B_stone2_parkCenter_Q3() {

    }

    @Override
    public void runOpMode() {
        robot.initChassis(this);
        telemetry.addData("Status", "InitComplete, Ready to Start");
        telemetry.update();

        //Wait for Start
        while (!opModeIsActive() && !isStopRequested()) {
            telemetry.addData("status", "waiting for start command...");
            telemetry.update();
        }

        //Main Autonomous program
        robot.moveForward(30, 1); // robot approaches stones
        robot.moveForward(5, 0.5); // slows down
        robot.clawClampPosition(0); // robot grabs stone
        sleep(500); // just in case
        robot.liftPosition(1.2); // lifts stone slightly
        robot.moveBackward(12, 1); // backs away from stones
        robot.AbsoluteTurnIMU(90, 1.0); //positive is left // turns. Robot should be facing tape
        robot.liftPosition(0.85); // lowers lift slightly
        robot.moveForward(36, 1); //crosses tape
        robot.clawClampPosition(1); // unclamps stone
        robot.moveBackward(41, 1); // moves to get second stone
        robot.AbsoluteTurnIMU(0, 1); // gets ready to get next stone
        robot.liftPosition(0); // lowers lift to ground
        robot.moveForward(8, 1); // approaches stones
        robot.moveForward(6, 0.5); // slows down
        robot.clawClampPosition(0); // robot grabs 2nd stone
        sleep(500); // just in case
        robot.liftPosition(1.2); // lifts stone slightly
        robot.moveBackward(16, 1); // backs away from stones
        robot.inPlaceTurnIMU(90, 1); // turns. Robot should be facing tape
        robot.liftPosition(0.85); // lowers lift
        robot.moveForward(43, 1); // crosses tape
        robot.clawClampPosition(1); // unclamps stone
        sleep(500);
        robot.moveBackwardUntilBlue(); // parks on tape
        robot.clawClampPosition(0); // just in case

    }
}
