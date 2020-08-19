package org.firstinspires.ftc.teamcode.SkystoneOffSeason.Chassis2;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "Chassis2_Teleop ")
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
    double magnitude = Math.sqrt(Math.pow(left_stick_x, 2) + Math.sqrt(Math.pow(left_stick_y, 2)));
    double inverseTangent = Math.atan2(left_stick_y, left_stick_x);

    public void runOpMode() {

        telemetry.addData("Status", "Ready to go");
        telemetry.update();
        robot.initChassis(this);

        //Aiden - during competition day robot disconnected so we are trying this code
        while (!opModeIsActive() && !isStopRequested()) {
            telemetry.addData("status", "waiting for start command...");
            telemetry.update();
        }

        while (!isStopRequested()) {

            telemetry.addData("Motor", " idk)", magnitude);
            telemetry.update();
            multidirectionalMove(magnitude);

        }
            idle();
    }
        public void multidirectionalMove(double power) {
            motorLeftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            motorRightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            motorLeftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            motorRightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    
            motorLeftBack.setPower(Math.sin(inverseTangent - Math.PI/4) * magnitude + right_stick_x);
            motorRightBack.setPower(Math.sin(inverseTangent + Math.PI/4) * magnitude + right_stick_x);
            motorLeftFront.setPower(Math.sin(inverseTangent + Math.PI/4) * magnitude + right_stick_x);
            motorRightFront.setPower(Math.sin(inverseTangent - Math.PI/4) * magnitude + right_stick_x);
    
        }


}


