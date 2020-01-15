package org.firstinspires.ftc.teamcode.Skystone.Qualifier_3;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class FoundationPuller {
    private LinearOpMode op = null;
    private HardwareMap hardwareMap = null;
    private ElapsedTime period = new ElapsedTime();
    Servo foundationMoverRight;
    Servo foundationMoverLeft;

    public void initChassis(LinearOpMode opMode) {

        op = opMode;
        hardwareMap = op.hardwareMap;
        //foundation servo 1
          foundationMoverRight = hardwareMap.servo.get("foundationMoverRight");
        //foundation servo 2
         foundationMoverLeft = hardwareMap.servo.get("foundationMoverLeft");
        //starting position
         foundationMoverRight.setPosition(0.0);
          foundationMoverLeft.setPosition(0.0);
    }
    //true = unclamp, false = clamp
    public void moveFoundationRightdown(boolean direction) {
        if (direction == true) {
            foundationMoverRight.setPosition(0.45);
        } else {
            foundationMoverRight.setPosition(0.0);
        }
        op.sleep(200);

    }

    /*public void foundationStickRightPosition(double right_stick_position) {
        op.telemetry.addData("claw position :", right_stick_position);
        op.telemetry.update();
        foundationMoverRight.setPosition(right_stick_position);
        op.sleep(50);
    }
*/
    public void  moveFoundationLeftdown(boolean direction) {
        if (direction == true) {
            foundationMoverLeft.setPosition(0.45);
        } else {
            foundationMoverLeft.setPosition(0.0);
        }
        op.sleep(200);

    }
    /*//true = unclamp, false = clamp
    public void foundationStickLeftPosition(double left_stick_position) {
        op.telemetry.addData("claw position :", left_stick_position);
        op.telemetry.update();
        foundationMoverLeft.setPosition(left_stick_position);
        op.sleep(50);
    }*/
}

