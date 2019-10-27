package org.firstinspires.ftc.teamcode.Skystone;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

/**
 * <h1>first ATnT_Outreach_basicChassis program</h1>
 * for ATnT Outreach using basicChassis that Nathan and Andrew wrote
 * <p>
 * This teleop program goesforward, backward, right, and left
 * has been tested 3 times
 *
 * @author Aiden Ma
 * @version 1.0
 * @since 2019-10-26
 */
@TeleOp(name = "ATnT_Outreach_basicChassis")
public class ATnT_Outreach_basicChassis extends LinearOpMode {

    private basicChassis robot = new basicChassis();
    private ElapsedTime runtime = new ElapsedTime();

    // this is for stopping the robot after time is over
    private double play_time = 60;
    private double timeLeft;
    private double startTime = runtime.seconds();

    public void SkyStoneAutonomous_Aiden_1() {

    }

    @Override
    public void runOpMode() {

        double leftPower;
        double rightPower;

        telemetry.addData("Status", "Ready to go");
        telemetry.update();
        robot.initChassis(this);


        waitForStart();

        runtime.reset();


        while (!isStopRequested()) {

            timeLeft = play_time + startTime - getRuntime();


            float left_stick_y = -gamepad1.left_stick_y;
            float right_stick_x = -gamepad1.right_stick_x;
            //boolean
            telemetry.addData("Motor", "left (%.2f), right (%.2f)", left_stick_y, right_stick_x);
            telemetry.update();


            if ((left_stick_y == 1.00) && (right_stick_x == -0.00)) {
                telemetry.addData("Motor", " FORWARD left (%.2f), right (%.2f)", left_stick_y, right_stick_x);
                telemetry.update();
                robot.moveForward(3);
            } else if ((left_stick_y == -1.00) && (right_stick_x == -0.00)) {
                telemetry.addData("Motor", " BACKWARD left (%.2f), right (%.2f)", left_stick_y, right_stick_x);
                telemetry.update();
                robot.moveBackward(3);
            } else if ((left_stick_y == -0.00) && (right_stick_x == -1.00)) {
                telemetry.addData("Motor", " TURN RIGHT left (%.2f), right (%.2f)", left_stick_y, right_stick_x);
                telemetry.update();
                robot.inPlaceTurn(2, false);
            } else if ((left_stick_y == -0.00) && (right_stick_x == 1.00)) {
                telemetry.addData("Motor", " TURN LEFT left (%.2f), right (%.2f)", left_stick_y, right_stick_x);
                telemetry.update();
                robot.inPlaceTurn(2, true);
            }

            telemetry.addData("Motor", "left (%.2f), right (%.2f)", left_stick_y, right_stick_x);
            telemetry.update();

            if (timeLeft >= 0) {
                telemetry.addData("Driving Status", "Time Left- %.1f", timeLeft);
                telemetry.update();

            }else{
                telemetry.addData("Game Over", "Time Left- %.1f", timeLeft);
                telemetry.update();

                break;
            }//
        }
    }
}
