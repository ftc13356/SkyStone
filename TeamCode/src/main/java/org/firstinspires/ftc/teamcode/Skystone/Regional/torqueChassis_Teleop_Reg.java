package org.firstinspires.ftc.teamcode.Skystone.Regional;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.function.ToDoubleBiFunction;

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
@TeleOp(name = "torqueChassis_Teleop_Reg ")
public class torqueChassis_Teleop_Reg extends LinearOpMode {

    private Robot_Reg robot = new Robot_Reg();
    private ElapsedTime runtime = new ElapsedTime();
    private double motor_power = 1.0; //Drivetrain motor
    private boolean claw_is_up = true;
    private boolean move_claw = true;
    private boolean both_sticks_is_up = true;
    private boolean move_both_sticks = true;
    private boolean capstone_stick_f_is_up = true;
    private boolean move_capstone_f = true;


    public torqueChassis_Teleop_Reg() {

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
            float extend_tape_manual = gamepad1.left_trigger;
            float retract_tape_manual = gamepad1.left_trigger;
            boolean both_sticks = gamepad1.right_bumper;
            boolean capstone_stick_foundation = gamepad1.left_bumper;
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
            boolean flip = gamepad2.dpad_down;

            boolean testing = false;


            telemetry.addData("position", gamepad1.left_trigger);
            //claw
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
            //foundation sticks
            if (both_sticks == true) {
                move_both_sticks = true;

                if (both_sticks_is_up == true) {
                    both_sticks_is_up = false;
                } else if (both_sticks_is_up == false) {
                    both_sticks_is_up = true;
                }
            } else {
                move_both_sticks = false;
            }
            //capstone stick to foundation
            if (capstone_stick_foundation == true) {
                move_capstone_f = true;

                if (capstone_stick_f_is_up == true) {
                    capstone_stick_f_is_up = false;
                } else if (capstone_stick_f_is_up == false) {
                    capstone_stick_f_is_up = true;
                }
            } else {
                move_capstone_f = false;
            }


            // telemetry.addData("Motor", "left_y (%.2f), left_x (%.2f)", left_stick_y, left_stick_x);
            //telemetry.update();

            if (x_button) {
                motor_power = 0.3;
            }
            if (y_button) {
                motor_power = 0.7;
            }

            //unclamps claw
            //moves backward
            if (b_button) {
                motor_power = 1.0;
            }
            if (a_button) {
                robot.tapePosition(0);

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
                if (!testing) robot.liftPosition(0, gamepad2);
            } else if (lift_little) {
                telemetry.addData("Lift", " Lift up slightly(1.2)");
                telemetry.update();
                if (!testing) robot.stopAllMotors();
                if (!testing) robot.liftPosition(1.2, gamepad2);
            } else if (lift_level_1) {
                telemetry.addData("Lift", " Lift goes to level 1 (5)");
                telemetry.update();
                if (!testing) robot.stopAllMotors();
                if (!testing) robot.liftPosition(5, gamepad2);
            } else if (lift_level_2) {
                telemetry.addData("Lift", " Lift goes to level 2 (8.25)");
                telemetry.update();
                if (!testing) robot.stopAllMotors();
                if (!testing) robot.liftPosition(8.25, gamepad2);
            } else if (lift_level_3) {
                telemetry.addData("Lift", " Lift goes to ground (11.75)");
                telemetry.update();
                if (!testing) robot.stopAllMotors();
                if (!testing) robot.liftPosition(11.75, gamepad2);
            }

            if (extend_tape_manual != 0 || retract_tape_manual != 0) {
                float tapeSpeed = -retract_tape_manual + extend_tape_manual;
                telemetry.addData("Tape", "Tape speed :", tapeSpeed);
                telemetry.update();
                if (!testing) robot.tapeTeleopPower(-5);
            } else {
                telemetry.addData("Tape", " Tape stops");
                telemetry.update();
                if (!testing) robot.stopTape();
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

                if (flip == true) {
                    telemetry.addData("Flip", " Flipping");
                    telemetry.update();
                    if (!testing) {
                        robot.liftPosition(1.2, gamepad2);
                        robot.clawClampPosition(0);
                        robot.moveForward(3, 1);
                        robot.liftPosition(0.1);
                        robot.moveForward(1, 1);
                        robot.clawClampPosition(1);
                    }
                }

                //following are the code that handles the raising and lowering of the puller for foundation
                if (move_both_sticks == true) {
                    if (both_sticks_is_up) {
                        telemetry.addData("Servo", " BOTH STICKS UP  right_bumper");
                        telemetry.update();
                        if (!testing) robot.moveFoundationRightdown(true);
                        if (!testing) robot.moveFoundationLefttdown(true);
                    } else if (both_sticks_is_up == false) {
                        telemetry.addData("Servo", " BOTH STICKS DOWN  right_bumper");
                        telemetry.update();
                        if (!testing) robot.moveFoundationRightdown(false);
                        if (!testing) robot.moveFoundationLefttdown(false);
                    }
                }
                //following are the code that handles the raising and lowering stick for the capstone
                if (move_capstone_f == true) {
                    if (capstone_stick_f_is_up) {
                        telemetry.addData("Servo", " CAPSTONE STICK FOUNDATION UP  left_bumper");
                        telemetry.update();
                        if (!testing) robot.moveCapstoneStickdownToFoundtion(true);
                        if (!testing) robot.moveCapstoneStickdownToFoundtion(true);
                    } else if (capstone_stick_f_is_up == false) {
                        telemetry.addData("Servo", " CAPSTONE STICK FOUNDATION DOWN   left_bumper");
                        telemetry.update();
                        if (!testing) robot.moveCapstoneStickdownToFoundtion(false);
                        if (!testing) robot.moveCapstoneStickdownToFoundtion(false);
                    }
                }


            }
        }
    }

