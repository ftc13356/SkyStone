package org.firstinspires.ftc.teamcode.SkystoneOffSeason.Chassis2;

import android.media.FaceDetector;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Chassis2_Teleop ")
public class Chassis2_Teleop extends LinearOpMode {
    private Robot robot = new Robot();
    private DcMotor motorLeftFront;
    private DcMotor motorLeftBack;
    private DcMotor motorRightFront;
    private DcMotor motorRightBack;

    @Override
    public void runOpMode() {

        double magnitude;
        double angleInRadian;
        double angleInDegree;
        boolean slowMode = false;

        motorLeftFront = hardwareMap.dcMotor.get("motorLeftFront");
        motorLeftBack = hardwareMap.dcMotor.get("motorLeftBack");
        motorRightFront = hardwareMap.dcMotor.get("motorRightFront");
        motorRightBack = hardwareMap.dcMotor.get("motorRightBack");

        telemetry.addData("Status", "Ready to go");
        telemetry.update();
        robot.initChassis(this);

        //Aiden - during competition day robot disconnected so we are trying this code
        while (!opModeIsActive() && !isStopRequested()) {
            telemetry.addData("status", "waiting for start command...");
            telemetry.update();
        }

        while (!isStopRequested()) {

            float left_stick_y = -gamepad1.left_stick_y;
            float left_stick_x = -gamepad1.left_stick_x;
            boolean x_button = gamepad1.x;
            boolean a_button = gamepad1.a;

            angleInRadian = Math.atan2(left_stick_y, left_stick_x);
            angleInDegree = Math.toDegrees(angleInRadian);

            if (a_button) { //click a to turn on slowmode
                slowMode = true;
            }
            if (x_button) { //click x to turn off slow mode
                slowMode = false;
            }

            if (slowMode == true) {
                if (left_stick_x == 0 && left_stick_y == 0) {
                    magnitude = 0;
                }
                else {
                    magnitude = 0.3;
                }
            }
            else {
                magnitude = Math.sqrt(Math.pow(left_stick_x, 2) + Math.sqrt(Math.pow(left_stick_y, 2)));
            }
            multidirectionalMove(magnitude, angleInDegree);

        }
            idle();
    }

    /**
     * moves in any direction
     * @param   power   from values -1 through 1
     * @param   angle   angle in degrees
     * @return  void
     */
    public void multidirectionalMove(double power, double angle) {

        float right_stick_x = -gamepad1.right_stick_x;

        double angleInRadian;
        angleInRadian = Math.toRadians(angle);

        motorLeftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorLeftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        motorLeftBack.setPower(Math.sin(angleInRadian - Math.PI/4) * power - right_stick_x);
        motorRightBack.setPower(Math.sin(angleInRadian + Math.PI/4) * power + right_stick_x);
        motorLeftFront.setPower(Math.sin(angleInRadian + Math.PI/4) * power - right_stick_x);
        motorRightFront.setPower(Math.sin(angleInRadian - Math.PI/4) * power + right_stick_x);

    }

}


