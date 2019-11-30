package org.firstinspires.ftc.teamcode.Skystone.Qualifier_1;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

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

    private RobotB robot = new RobotB();
    private ElapsedTime runtime = new ElapsedTime();
    private double motor_power = 0.70;
    private boolean claw_is_up = true;
    private boolean move_claw = true;

    public hexChassis_Teleop() {

    }

    @Override
    public void runOpMode() {

        telemetry.addData("Status", "Ready to go");
        telemetry.update();
        robot.initChassis(this);

        //Aiden - during competition day robot disconnected so we are trying this code
        while (!opModeIsActive() && !isStopRequested()) {
            telemetry.addData("status", "waiting for start command...");
            telemetry.update();
        }
        //robot.clawClamp(true);

        while (!isStopRequested()) {

            float left_stick_y = -gamepad1.left_stick_y;
            float left_stick_x = -gamepad1.left_stick_x;//idk "-" sign
            float right_stick_x = -gamepad1.right_stick_x;
            boolean x_button = gamepad1.x;
            boolean y_button = gamepad1.y;
            boolean a_button = gamepad1.a;
            boolean motor_lift_up = gamepad2.right_bumper;
            float motor_lift_down = gamepad2.right_trigger;

            if (gamepad2.left_trigger == 1.00) {
                move_claw = true;

                if (claw_is_up == true) {
                    claw_is_up = false;
                } else if (claw_is_up == false) {
                    claw_is_up = true;
                }
            } else {
                move_claw = false;
            }

            // telemetry.addData("Motor", "left_y (%.2f), left_x (%.2f)", left_stick_y, left_stick_x);
            //telemetry.update();

            boolean testing = false;

            if (x_button) {
                motor_power = 0.3;
            }
            if (y_button) {
                motor_power = 0.70;
            }
            if (a_button){
                motor_power = 1.0;
            }
            if (left_stick_y == 1.00) {
                telemetry.addData("Motor", " FORWARD left_y (%.2f)", left_stick_y);
                telemetry.update();
                if (!testing) robot.moveForwardTeleop(motor_power, 8);
            } else if (left_stick_y == -1.00) {
                telemetry.addData("Motor", " BACKWARD left_y(%.2f)", left_stick_y);
                telemetry.update();
                if (!testing) robot.moveBackwardTeleop(motor_power, 8);
            } else if (left_stick_x == -1.00) {
                telemetry.addData("Motor", " SIDEWAYS RIGHT left_x (%.2f)", left_stick_x);
                telemetry.update();
                if (!testing) robot.moveRightTeleop(motor_power, 5);
            } else if (left_stick_x == 1.00) {
                telemetry.addData("Motor", " SIDEWAYS LEFT left_x (%.2f)", left_stick_x);
                telemetry.update();
                if (!testing) robot.moveLeftTeleop(motor_power, 5);
            } else if (right_stick_x == -1.00) {
                telemetry.addData("Motor", " TURN RIGHT right_x (%.2f)", right_stick_x);
                telemetry.update();
                if (!testing) robot.inPlaceTurnTeleop(90, false, motor_power);
            } else if (right_stick_x == 1.00) {
                telemetry.addData("Motor", " TURN LEFT right_x (%.2f)", right_stick_x);
                telemetry.update();
                if (!testing) robot.inPlaceTurnTeleop(90, true, motor_power);
            } else if (left_stick_y == 0.00) {
                telemetry.addData("STOP", " FORWARD left_y (%.2f)", left_stick_y);
                telemetry.update();
                if (!testing) robot.stopAllMotors();
            }

            if (motor_lift_up) {
                telemetry.addData("Motor", " RACK UP right_bumper");
                telemetry.update();
                if (!testing) robot.liftAutonomous(1.25);
            } else if (motor_lift_down == 1.00) {
                telemetry.addData("Motor", " RACK DOWN right_trigger (%.2f)", motor_lift_down);
                telemetry.update();
                if (!testing) robot.liftAutonomous(-1.25);
            } else if (motor_lift_up == false) {
                telemetry.addData("STOP", " LIFT right_bumper (%.2f)");
                telemetry.update();
                if (!testing) robot.stopLift();
            }

            if (move_claw == true) {
                if (claw_is_up) {
                    telemetry.addData("Servo", " CLAW UP left_bumper");
                    telemetry.update();
                    if (!testing) robot.clawClamp(true);
                } else if (claw_is_up == false) {
                    telemetry.addData("Servo", " CLAW DOWN left_trigger");
                    telemetry.update();
                    if (!testing) robot.clawClamp(false);
                }
            }

            //telemetry.addData("Motor", "left (%.2f), right (%.2f)", left_stick_y, left_stick_x);
            //telemetry.update();


        }
    }
}

