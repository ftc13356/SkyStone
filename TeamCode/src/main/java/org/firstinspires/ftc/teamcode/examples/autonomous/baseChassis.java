package org.firstinspires.ftc.teamcode.examples.autonomous;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * <h2>Base Chassis</h2>
 * Purpose:
 * <p> Contains all the common variables and functions involving the chassis
 * <p> so that we can easily create organized chassis programs
 */

public abstract class baseChassis {

    // Initialize Motors
    DcMotor motorLeftFront;
    DcMotor motorRightFront;
    DcMotor motorLeftBack;
    DcMotor motorRightBack;

    // Initialize Encoder Variables
    final double robot_diameter = 14.0;
    final double drive_gear_reduction = 1.0;
    final double wheel_diameter = 4.0;

    // these encoder variables vary depending on chassis type
    double counts_per_motor_rev = 0;
    double counts_per_inch = 0;
    double counts_per_degree = 0;

    /**
     * Sets 0 power behavior
     * <p> Maps chassis motors to their names in the robot config file
     * @param hardwareMap hardware map of robot in autonomousFrame (program extending LinerOpMode)
     */
    public void initializeMotors(HardwareMap hardwareMap) {
        motorLeftFront = hardwareMap.dcMotor.get("motorLeftFront");
        motorRightFront = hardwareMap.dcMotor.get("motorRightFront");
        motorLeftBack = hardwareMap.dcMotor.get("motorLeftBack");
        motorRightBack = hardwareMap.dcMotor.get("motorRightBack");

        motorLeftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorLeftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorRightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorRightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
}
