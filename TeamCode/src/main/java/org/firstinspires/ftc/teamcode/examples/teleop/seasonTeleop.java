package org.firstinspires.ftc.teamcode.examples.teleop;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Purpose: Main Teleop Program
 * Author: Jonathan Ma, Ansh Gandhi
 */
@Disabled
@TeleOp(name = "Relic Recovery Teleop Drivetrain and Arm")
public class seasonTeleop extends OpMode {

    // VERSION NUMBER(MAJOR.MINOR) - DATE
    // DO BEFORE EVERY COMMIT!
    private final String teleopVersionNumber = "1.0 - 10/6/18 ";

    // Creates instances of chassis, glyph claw, and arm
    private seasonChassis seasonChassis = new seasonChassis(this);
    private seasonArm seasonArm = new seasonArm(this);

    // Calls methods in chassis and arm
    @Override
    public void init() {
        telemetry.addData("Teleop Program Version", teleopVersionNumber);
        seasonChassis.init();
        seasonArm.init();
    }

    @Override
    public void loop() {
        seasonChassis.loop();
        seasonArm.loop();
    }
}