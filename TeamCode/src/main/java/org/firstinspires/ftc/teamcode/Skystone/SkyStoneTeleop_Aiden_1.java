package org.firstinspires.ftc.teamcode.Skystone;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

/**
 * <h1>first SkyStone TeleOp program</h1>
 * for testing basicChassis that Nathan and Andrew wrote
 * <p>
 * Giving proper comments in your program makes it more
 * user friendly and it is assumed as a high quality code.
 *
 * @author Aiden Ma
 * @version 1.0
 * @since 2019-10-19
 */
@TeleOp(name = "SkyStone Teleop 1")
public class SkyStoneTeleop_Aiden_1 extends LinearOpMode {

    private basicChassis robot = new basicChassis();
    private ElapsedTime runtime = new ElapsedTime();


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

        while (!isStopRequested()) {

            double drive = -gamepad1.left_stick_y;
            double turn = gamepad1.right_stick_x;
            leftPower = Range.clip(drive + turn, -1.0, 1.0);
            rightPower = Range.clip(drive - turn, -1.0, 1.0);
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.update();

            if ((leftPower == 1.00) && (rightPower == -1.00)) {
                robot.inPlaceTurn(90, false);
            } else if ((leftPower == -1.00) && (rightPower == 1.00)) {
                robot.inPlaceTurn(90, true);
            } else if ((leftPower == 0.00) && (rightPower == -0.00)) {

            }

            //left.setPower(leftPower);
            //right.setPower(rightPower);
        }
    }
}