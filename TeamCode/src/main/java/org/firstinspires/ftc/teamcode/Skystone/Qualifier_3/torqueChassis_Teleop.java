package org.firstinspires.ftc.teamcode.Skystone.Qualifier_3;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Skystone.Qualifier_1.RobotB;

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
@TeleOp(name = "torqueChassis_Teleop X")
public class torqueChassis_Teleop extends LinearOpMode {

    private RobotA robot = new RobotA();
    private ElapsedTime runtime = new ElapsedTime();
    private double motor_power = 1.0; //Drivetrain motor
    private boolean claw_is_up = true;
    private boolean move_claw = true;
    private boolean right_stick_is_up = true;
    private boolean move_right_stick = true;
    private boolean left_stick_is_up = true;
    private boolean move_left_stick = true;

    public torqueChassis_Teleop() {

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

            float left_stick_y = -gamepad1.left_stick_y; //TODO: What are they used for?
            float left_stick_x = -gamepad1.left_stick_x;//idk "-" sign
            float right_stick_x = -gamepad1.right_stick_x;
            boolean stick_right = gamepad1.right_bumper;
            boolean stick_left = gamepad1.left_bumper;
            boolean x_button = gamepad1.x;
            boolean y_button = gamepad1.y;
            boolean b_button = gamepad1.b;
            boolean a_button = gamepad1.a;
            boolean lift_ground = gamepad2.a;
            boolean lift_little = gamepad2.b;
            boolean lift_level_1 = gamepad2.x;
            boolean lift_level_2 = gamepad2.y;
            boolean claw = gamepad2.left_bumper;
            float lift_up_manual = gamepad2.left_trigger;
            boolean lift_level_3 = gamepad2.right_bumper;
            float lift_down_manual = gamepad2.right_trigger;

            boolean testing = false;

            if (claw == true) {
                move_claw = true;

                if (claw_is_up == true) {
                    claw_is_up = false;
                } else if (claw_is_up == false) {
                    claw_is_up = true;
                }
            } else {
                move_claw = false;
            }

            if (stick_right == true) {
                move_right_stick = true;

                if (right_stick_is_up == true) {
                    right_stick_is_up = false;
                } else if (right_stick_is_up == false) {
                    right_stick_is_up = true;
                }
            } else {
                move_right_stick = false;
            }

            if (stick_left == true) {
                move_left_stick = true;

                if (left_stick_is_up == true) {
                    left_stick_is_up = false;
                } else if (left_stick_is_up == false) {
                    left_stick_is_up = true;
                }
            } else {
                move_left_stick = false;
            }
            // telemetry.addData("Motor", "left_y (%.2f), left_x (%.2f)", left_stick_y, left_stick_x);
            //telemetry.update();

            if (x_button) {
                motor_power = 0.4;
            }
            if (y_button) {
                motor_power = 1.0;
            }
            if (b_button) {
                robot.clawClamp(true);
                robot.moveBackwardTeleop(0.2, 0.10);
                sleep(1000);
                robot.stopAllMotors();
                robot.liftPosition(0);
            }
            if (a_button) {
                robot.clawClamp(false);
                robot.liftPosition(1.2);

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
                if (!testing) robot.inPlaceTurnTeleop(45, false, motor_power);
            } else if (right_stick_x == 1.00) {
                telemetry.addData("Motor", " TURN LEFT right_x (%.2f)", right_stick_x);
                telemetry.update();
                if (!testing) robot.inPlaceTurnTeleop(45, true, motor_power);
            } else {
                telemetry.addData("STOP", " FORWARD left_y (%.2f)", left_stick_y);
                telemetry.update();
                if (!testing) robot.stopAllMotors();
            }

            if (lift_up_manual != 0 || lift_down_manual != 0) {
                float liftSpeed = -lift_down_manual + lift_up_manual;
                telemetry.addData("Lift", "Lift speed :", liftSpeed);
                telemetry.update();
                if (!testing) robot.liftTeleopPower(liftSpeed);
            /*} else if (lift_up_manual != 0){
                telemetry.addData("Lift", "Lift goes up");
                telemetry.update();
                if (!testing) robot.liftTeleop(true);
            } else if (lift_down_manual != 0) {
                telemetry.addData("Lift", "Lift goes down ");
                telemetry.update();
                if (!testing) robot.liftTeleop(false);*/
            } else {
                telemetry.addData("Lift", " Lift stops");
                telemetry.update();
                if (!testing) robot.stopLift();
            }
            if (lift_ground) {
                telemetry.addData("Lift", " Lift goes to ground");
                telemetry.update();
                if (!testing) robot.stopAllMotors();
                if (!testing) robot.liftPosition(0);
            } else if (lift_little) {
                telemetry.addData("Lift", " Lift up slightly(1.2)");
                telemetry.update();
                if (!testing) robot.stopAllMotors();
                if (!testing) robot.liftPosition(1.2);
            } else if (lift_level_1) {
                telemetry.addData("Lift", " Lift goes to level 1 (5)");
                telemetry.update();
                if (!testing) robot.stopAllMotors();
                if (!testing) robot.liftPosition(5);
            } else if (lift_level_2) {
                telemetry.addData("Lift", " Lift goes to level 2 (8.25)");
                telemetry.update();
                if (!testing) robot.stopAllMotors();
                if (!testing) robot.liftPosition(8.25);
            } else if (lift_level_3) {
                telemetry.addData("Lift", " Lift goes to ground (11.75)");
                telemetry.update();
                if (!testing) robot.stopAllMotors();
                if (!testing) robot.liftPosition(11.75);
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
            //following are the code that handles the raising and lowering of the puller for foundation
            if (move_right_stick == true) {
                if (right_stick_is_up) {
                    telemetry.addData("Servo", " STICK UP  right_trigger");
                    telemetry.update();
                    if (!testing) robot.moveFoundationRightdown(true);
                } else if (right_stick_is_up == false) {
                    telemetry.addData("Servo", " STICK DOWN  right_trigger");
                    telemetry.update();
                    if (!testing) robot.moveFoundationRightdown(false);
                }
            } else if (move_left_stick == true) {
                if (left_stick_is_up) {
                    telemetry.addData("Servo", " STICK UP  left_trigger");
                    telemetry.update();
                    if (!testing) robot.moveFoundationLefttdown(true);
                } else if (left_stick_is_up == false) {
                    telemetry.addData("Servo", " STICK DOWN  left_trigger");
                    telemetry.update();
                    if (!testing) robot.moveFoundationLefttdown(false);
                }
            }


        }
    }
}

