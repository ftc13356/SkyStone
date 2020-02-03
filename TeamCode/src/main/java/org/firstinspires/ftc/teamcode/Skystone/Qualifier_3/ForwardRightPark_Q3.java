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
 * @author  Aamod
 * @version 1.3
 * @since   2020-Jan-7
 *
 */
@Autonomous(name = "ForwardRightPark_Q3")
public class ForwardRightPark_Q3 extends LinearOpMode{

    RobotA robot = new RobotA();
    private ElapsedTime runtime = new ElapsedTime();
    private Servo stone_claw_servo;


    public ForwardRightPark_Q3() {

    }

    @Override
    public void runOpMode() {
        stone_claw_servo = hardwareMap.servo.get("stone_claw_servo");

        robot.initChassis(this);

        // Aiden - during competition day robot dissconnected so we are trying this code
        while (!opModeIsActive() && !isStopRequested()) {
            telemetry.addData("status", "waiting for start command...");
            telemetry.update();
        }

        sleep(21500);
        robot.moveForward(31, 1);
        robot.AbsoluteTurnIMU(-90, 1);
        robot.moveForward(15,1);
        robot.clawClampPosition(0.25);
        sleep(1500);
    }

}


