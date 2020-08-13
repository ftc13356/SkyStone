package org.firstinspires.ftc.teamcode.SkystoneOffSeason.Chassis2;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Chassis2_Teleop extends LinearOpMode {
    private Robot robot = new Robot();
    double power = .3;
    double angle = 0;
    private DcMotor motorLeftFront;
    private DcMotor motorLeftBack;
    private DcMotor motorRightFront;
    private DcMotor motorRightBack;
    float left_stick_y = -gamepad1.left_stick_y;
    float left_stick_x = -gamepad1.left_stick_x;
    float right_stick_x = -gamepad1.right_stick_x;
    double magnitude = Math.sqrt(Math.pow(left_stick_x, 2) + Math.pow(left_stick_y, 2));
    double inverseTangent = Math.atan2(left_stick_y, left_stick_x);


    public void multidirectionalMove(double power) {
        // Changes motor mode back to default
        motorLeftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorLeftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        motorLeftBack.setPower(Math.sin(inverseTangent - Math.PI/4) * magnitude);
        motorRightBack.setPower(Math.sin(inverseTangent + Math.PI/4) * magnitude);
        motorLeftFront.setPower(Math.sin(inverseTangent + Math.PI/4) * magnitude);
        motorRightFront.setPower(Math.sin(inverseTangent - Math.PI/4) * magnitude);

    }

    public void runOpMode() throws InterruptedException {
        motorLeftFront = hardwareMap.dcMotor.get("motorLeftFront");
        motorLeftBack = hardwareMap.dcMotor.get("motorLeftBack");
        motorRightFront = hardwareMap.dcMotor.get("motorRightFront");
        motorRightBack = hardwareMap.dcMotor.get("motorRightBack");

        while (opModeIsActive()) {


        if (magnitude > 0 && magnitude <= 0.1) {
            telemetry.addData("Motor", " FORWARD left_y (%.2f)", left_stick_y);
            telemetry.update();
            multidirectionalMove(magnitude);
        } else if (magnitude > 0.1 && magnitude <= 0.2) {
            telemetry.addData("Motor", " FORWARD left_y(%.2f)", left_stick_y);
            telemetry.update();
            multidirectionalMove(magnitude);
        } else if (magnitude > 0.2 && magnitude <= 0.3) {
            telemetry.addData("Motor", " FORWARD left_y(%.2f)", left_stick_y);
            telemetry.update();
            multidirectionalMove(magnitude);
        } else if (magnitude > 0.3 && magnitude <= 0.4) {
            telemetry.addData("Motor", " FORWARD left_y(%.2f)", left_stick_y);
            telemetry.update();
            multidirectionalMove(magnitude);
        } else if (magnitude > 0.4 && magnitude <= 0.5) {
            telemetry.addData("Motor", " FORWARD left_y(%.2f)", left_stick_y);
            telemetry.update();
            multidirectionalMove(magnitude);
        } else if (magnitude > 0.5 && magnitude <= 0.6) {
            telemetry.addData("Motor", " FORWARD left_y(%.2f)", left_stick_y);
            telemetry.update();
            multidirectionalMove(magnitude);
        } else if (magnitude > 0.6 && magnitude <= 0.7) {
            telemetry.addData("Motor", " FORWARD left_y(%.2f)", left_stick_y);
            telemetry.update();
            multidirectionalMove(magnitude);
        } else if (magnitude > 0.7 && magnitude <= 0.8) {
            telemetry.addData("Motor", " FORWARD left_y(%.2f)", left_stick_y);
            telemetry.update();
            multidirectionalMove(magnitude);
        } else if (magnitude > 0.8 && magnitude <= 0.9) {
            telemetry.addData("Motor", " FORWARD left_y(%.2f)", left_stick_y);
            telemetry.update();
            multidirectionalMove(magnitude);
        } else if (magnitude > 0.9 && magnitude < 1) {
            telemetry.addData("Motor", " FORWARD left_y(%.2f)", left_stick_y);
            telemetry.update();
            multidirectionalMove(magnitude);
        } else if (left_stick_x == -1.00) {
            telemetry.addData("Motor", " SIDEWAYS RIGHT left_x (%.2f)", left_stick_x);
            telemetry.update();
            multidirectionalMove(power);
        } else if (left_stick_x == 1.00) {
            telemetry.addData("Motor", " SIDEWAYS LEFT left_x (%.2f)", left_stick_x);
            telemetry.update();
            multidirectionalMove(power);
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
            idle();
        }
    }
}


