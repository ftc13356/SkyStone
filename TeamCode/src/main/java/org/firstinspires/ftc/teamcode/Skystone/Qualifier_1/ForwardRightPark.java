package org.firstinspires.ftc.teamcode.Skystone.Qualifier_1;

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
 * @author  Aamod
 * @version 1.0
 * @since   2019-Nov-16
 *
 */
@Disabled
@Autonomous(name = "ForwardRightPark")
public class ForwardRightPark extends LinearOpMode{

    RobotB robot = new RobotB();
    private ElapsedTime runtime = new ElapsedTime();
    private Servo stone_claw_servo;


    public ForwardRightPark() {

    }

    @Override
    public void runOpMode() {
        stone_claw_servo = hardwareMap.servo.get("stone_claw_servo");

        telemetry.addData("Status", "Ready to go"); telemetry.update();

        robot.initChassis(this);

        // Aiden - during competition day robot dissconnected so we are trying this code
        while (!opModeIsActive() && !isStopRequested()) {
            telemetry.addData("status", "waiting for start command...");
            telemetry.update();
        }

        sleep(21500);
        robot.moveForward(35, 1);
        robot.inPlaceTurn(90, false, 1);
        robot.moveForward(15,1);
        robot.clawClampPosition(0.25);
        sleep(1500);
    }

}


