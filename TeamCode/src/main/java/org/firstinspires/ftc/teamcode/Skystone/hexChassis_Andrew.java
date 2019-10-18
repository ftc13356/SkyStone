package org.firstinspires.ftc.teamcode.Skystone;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.examples.autonomous.autonomousFrame;

public abstract class hexChassis_Andrew extends basicChassis {

    static final double TICK_MARKS = 228;

    double quarterTurn = TICK_MARKS/4;

    public void startMotors() {
        left.setDirection(DcMotor.Direction.REVERSE);
        left.setDirection(DcMotor.Direction.FORWARD);
        right.setDirection(DcMotor.Direction.FORWARD);
        right.setDirection(DcMotor.Direction.REVERSE);

    }

    public void drive(double driveFB, double turnDegrees, double speed,
                      boolean opModeIsActive, autonomousFrame frame) {
        int newLeftFrontTarget;
        int newRightFrontTarget;
        int newLeftBackTarget;
        int newRightBackTarget;

        double motorLeftFrontEncoder;
        double motorRightFrontEncoder;
        double motorLeftBackEncoder;
        double motorRightBackEncoder;
    }

}