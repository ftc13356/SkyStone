package org.firstinspires.ftc.teamcode.examples;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Claw Test")
@Disabled
public class clawTest extends LinearOpMode {

    private Servo clawLeft;
    private Servo clawRight;

    @Override
    public void  runOpMode() {
        clawLeft = hardwareMap.servo.get("clawLeft");
        clawRight = hardwareMap.servo.get("clawRight");

        double leftPosition = 0;
        double rightPosition = 1 - leftPosition;

        waitForStart();

        while (opModeIsActive()) {
            if(gamepad1.a) {
                leftPosition = 0.6;
                telemetry.addData("Servo Status", "Closed");
            }
            else if(gamepad1.b) {
                leftPosition = 0;
                telemetry.addData("Servo Status", "Open");
            }

            clawLeft.setPosition(leftPosition);
            clawRight.setPosition(rightPosition);

            telemetry.update();

            idle();
        }
    }
}
