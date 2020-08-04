package org.firstinspires.ftc.teamcode.SkystoneOffSeason.Chassis2;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.robot.Robot;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Chassis2_Teleop extends LinearOpMode {
    private RobotChassis2 robot = new RobotChassis2();
    double power = .30;
    double angle = 0;



    public void runOpMode() throws InterruptedException{

        float left_stick_y = -gamepad1.left_stick_y;
        float left_stick_x = -gamepad1.left_stick_x;//idk "-" sign
        float right_stick_x = -gamepad1.right_stick_x;

        if (left_stick_y == 1.00) {
            telemetry.addData("Motor", " FORWARD left_y (%.2f)", left_stick_y);
            telemetry.update();
            robot.moveForwardTeleop(power, 8);
        } else if (left_stick_y == -1.00) {
            telemetry.addData("Motor", " BACKWARD left_y(%.2f)", left_stick_y);
            telemetry.update();
            robot.moveBackwardTeleop(power, 8);
        } else if (left_stick_x == -1.00) {
            telemetry.addData("Motor", " SIDEWAYS RIGHT left_x (%.2f)", left_stick_x);
            telemetry.update();
            robot.moveRightTeleop(power, 5);
        } else if (left_stick_x == 1.00) {
            telemetry.addData("Motor", " SIDEWAYS LEFT left_x (%.2f)", left_stick_x);
            telemetry.update();
            robot.moveLeftTeleop(power, 5);
//        } else if (left_stick_x == .5) { // TODO: FIGURE OUT WHICH WAY TO TURN JOYSTICK THAT WILL MAKE ROBOT GO DIAGONAL
//            telemetry.addData("Motor", " DIAGONAL RIGHT UP left_x (%.2f)", left_stick_x);
//            telemetry.update();
//            robot.moveDiagonalRightUpTeleop(power, 5);
//        } else if (left_stick_x == -.5) { // TODO: FIGURE OUT WHICH WAY TO TURN JOYSTICK THAT WILL MAKE ROBOT GO DIAGONAL
//            telemetry.addData("Motor", " DIAGONAL RIGHT Down left_x (%.2f)", left_stick_x);
//            telemetry.update();
//            robot.moveDiagonalRightDownTeleop(power, 5);
//        } else if (left_stick_x == .5) { // TODO: FIGURE OUT WHICH WAY TO TURN JOYSTICK THAT WILL MAKE ROBOT GO DIAGONAL
//            telemetry.addData("Motor", " DIAGONAL LEFT UP left_x (%.2f)", left_stick_x);
//            telemetry.update();
//            robot.moveDiagonalLeftUpTeleop(power, 5);
//        } else if (left_stick_x == .5) { // TODO: FIGURE OUT WHICH WAY TO TURN JOYSTICK THAT WILL MAKE ROBOT GO DIAGONAL
//            telemetry.addData("Motor", " DIAGONAL LEFT DOWN left_x (%.2f)", left_stick_x);
//            telemetry.update();
//            robot.moveDiagonalLeftDownTeleop(power, 5);
        } else if (right_stick_x == -1.00) {
            telemetry.addData("Motor", " TURN RIGHT right_x (%.2f)", right_stick_x);
            telemetry.update();
            robot.inPlaceTurnTeleop(45, false, power);
        } else if (right_stick_x == 1.00) {
            telemetry.addData("Motor", " TURN RIGHT right_x (%.2f)", right_stick_x);
            telemetry.update();
            robot.inPlaceTurnTeleop(45, true, power);
        } else {
            telemetry.addData("STOP", " FORWARD left_y (%.2f)", left_stick_y);
            telemetry.update();
            robot.stopAllMotors();
        }
        while(opModeIsActive())

            idle();
        
        }

}


