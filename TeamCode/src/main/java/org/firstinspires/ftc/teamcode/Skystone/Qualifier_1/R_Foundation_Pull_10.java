package org.firstinspires.ftc.teamcode.Skystone.Qualifier_1;

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
 * @author  Aamod
 * @version 1.0
 * @since   2019-Nov-19
 *
 */

@Autonomous(name = "R_Foundation_Pull_10")
public class R_Foundation_Pull_10 extends LinearOpMode {
    RobotB robot = new RobotB();
    private ElapsedTime runtime = new ElapsedTime();
    private Servo stone_claw_servo;


    public R_Foundation_Pull_10() {

    }

    @Override
    public void runOpMode(){

        telemetry.addData("Status", "Ready to go");
        telemetry.update();
        robot.initChassis(this);
        telemetry.addData("Status", "InitComplete, Ready to Start");
        telemetry.update();
        waitForStart();

        robot.moveForward(40, 1);
        robot.clawClampPosition(0.3);
        sleep(750);
        robot.moveForward(2, 0.5);
        robot.inPlaceTurn(45,true,0.5);
        robot.moveBackward(32,1);
        robot.inPlaceTurn(30,true,0.5);
        robot.moveBackward(13,0.5);
        robot.clawClampPosition(1);
        sleep(1500);
    }
}
