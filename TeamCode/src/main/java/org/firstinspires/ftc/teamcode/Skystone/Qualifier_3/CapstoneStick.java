package org.firstinspires.ftc.teamcode.Skystone.Qualifier_3;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class CapstoneStick {
    private LinearOpMode op = null;
    private HardwareMap hardwareMap = null;
    private ElapsedTime period = new ElapsedTime();
    Servo capstoneStick;

    public void init(LinearOpMode opMode) {

        op = opMode;
        hardwareMap = op.hardwareMap;
        //foundation servo 1
        capstoneStick = hardwareMap.servo.get("capstoneStick");
        //starting position
        capstoneStick.setPosition(1.0);
    }
    //true = unclamp, false = clamp
    public void moveCapstoneStickdownToStone(boolean direction) {
        if (direction == true) {
            capstoneStick.setPosition(1.0);
        } else {
            capstoneStick.setPosition(0.35);
        }
        op.sleep(200);

    }
    public void moveCapstoneStickdownToFoundation(boolean direction) {
        if (direction == true) {
            capstoneStick.setPosition(1.0);
        } else {
            capstoneStick.setPosition(0.0);
        }
        op.sleep(200);

    }

    public void capstoneStickPosition(double capstone_stick_position) {
        op.telemetry.addData("stick position :", capstone_stick_position);
        op.telemetry.update();
        capstoneStick.setPosition(capstone_stick_position);
        op.sleep(50);
    }


}

