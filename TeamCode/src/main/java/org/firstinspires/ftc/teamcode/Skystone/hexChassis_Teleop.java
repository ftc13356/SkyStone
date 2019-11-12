package org.firstinspires.ftc.teamcode.Skystone;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

/**
 * <h1>first SkyStone TeleOp program</h1>
 * for testing hexChassis that Nathan and Andrew wrote
 * <p>
 * Giving proper comments in your program makes it more
 * user friendly and it is assumed as a high quality code.
 *
 * @author Aiden Ma
 * @version 1.0
 * @since 2019-10-19
 */
@TeleOp(name = "hexChassis_Teleop X")
public class hexChassis_Teleop extends LinearOpMode {

    private hexChassis robot = new hexChassis();
    private ElapsedTime runtime = new ElapsedTime();


    public hexChassis_Teleop() {

    }

    @Override
    public void runOpMode() {

        telemetry.addData("Status", "Ready to go");
        telemetry.update();
        robot.initChassis(this);

        waitForStart();
        robot.clawClamp(true);

        while (!isStopRequested()) {

            float left_stick_y = -gamepad1.left_stick_y;
            float left_stick_x = -gamepad1.left_stick_x;//idk "-" sign
            float right_stick_x = -gamepad1.right_stick_x;
            float motor_lift_up = gamepad2.right_trigger;
            float motor_lift_down = gamepad2.left_trigger;
            boolean stone_claw_up = gamepad2.right_bumper;
            boolean stone_claw_down = gamepad2.left_bumper;
            telemetry.addData("Motor", "left_y (%.2f), left_x (%.2f)", left_stick_y, left_stick_x);
            telemetry.update();

            boolean testing = false;

            if (left_stick_y == 1.00) {
                telemetry.addData("Motor", " FORWARD left_y (%.2f)", left_stick_y);
                telemetry.update();
                if (!testing) robot.moveForwardTeleop(8);
            } else if (left_stick_y == -1.00) {
                telemetry.addData("Motor", " BACKWARD left_y(%.2f)", left_stick_y);
                telemetry.update();
                if (!testing) robot.moveBackwardTeleop(8);
            } else if (left_stick_x == -1.00) {
                telemetry.addData("Motor", " SIDEWAYS RIGHT left_x (%.2f)", left_stick_x);
                telemetry.update();
                if (!testing) robot.moveRightTeleop(5);
            } else if (left_stick_x == 1.00) {
                telemetry.addData("Motor", " SIDEWAYS LEFT left_x (%.2f)", left_stick_x);
                telemetry.update();
                if (!testing) robot.moveLeftTeleop(5);
            } else if (right_stick_x == -1.00) {
                telemetry.addData("Motor", " TURN RIGHT right_x (%.2f)", right_stick_x);
                telemetry.update();
                if (!testing) robot.inPlaceTurnTeleop(90, false);
            } else if (right_stick_x == 1.00) {
                telemetry.addData("Motor", " TURN LEFT right_x (%.2f)", right_stick_x);
                telemetry.update();
                if (!testing) robot.inPlaceTurnTeleop(90, true);
            } else if (left_stick_y== 0.00){
                telemetry.addData("stop", " FORWARD left_y (%.2f)", left_stick_y);
                telemetry.update();
                if (!testing) robot.stopAllMotors();
            }

            if (motor_lift_up == 1.00) {
                telemetry.addData("Motor", " RACK UP right_trigger (%.2f)", motor_lift_up);
                telemetry.update();
                if (!testing) robot.liftAutonomous(2);
            } else if (motor_lift_down == 1.00) {
                telemetry.addData("Motor", " RACK DOWN left_trigger (%.2f)", motor_lift_down);
                telemetry.update();
                if (!testing) robot.liftAutonomous(-2);
            } else if (stone_claw_up) {
                telemetry.addData("Servo", " CLAW UP left_bumper");
                telemetry.update();
                if (!testing) robot.clawClamp(true);
            } else if (stone_claw_down) {
                telemetry.addData("Servo", " CLAW DOWN right_bumper");
                telemetry.update();
                if (!testing) robot.clawClamp(false);
            }

            //telemetry.addData("Motor", "left (%.2f), right (%.2f)", left_stick_y, left_stick_x);
            //telemetry.update();




        }
    }
}

