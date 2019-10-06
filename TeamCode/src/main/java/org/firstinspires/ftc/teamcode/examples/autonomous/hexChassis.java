package org.firstinspires.ftc.teamcode.examples.autonomous;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * <h2>Hex Motor Chassis</h2>
 * Purpose:
 * <p> Contains the variables and functions specific to an all hex motor chassis
 * <p> so that we can switch from an all andymark motor chassis without changing any code!
 */

public class hexChassis extends baseChassis {

    /**
     * Creates a hex chassis object
     */
    hexChassis() {
        // Set hex motor chassis encoder variables
        counts_per_motor_rev = 288;

        counts_per_inch = (counts_per_motor_rev * drive_gear_reduction) /
                (wheel_diameter * Math.PI);

        counts_per_degree = counts_per_inch * robot_diameter * Math.PI / 360;
    }

    /**
     * Sets hex motor chassis motor direction
     */
    @Override
    public void initializeMotors(HardwareMap hardwareMap) {
        super.initializeMotors(hardwareMap);

        motorLeftFront.setDirection(DcMotor.Direction.REVERSE);
        motorRightFront.setDirection(DcMotor.Direction.FORWARD);
        motorLeftBack.setDirection(DcMotor.Direction.FORWARD);
        motorRightBack.setDirection(DcMotor.Direction.REVERSE);
    }

    /**
     * Hex Motor Chassis Specific encoderDrive()
     * @param driveFB Inches to move forward or backward (forward: +, backward: -)
     * @param turnDegrees Degrees to turn left or right (right: +, left: -)
     * @param speed Speed of robot (min: 0, max: 1)
     * @param opModeIsActive Type "opModeIsActive()" boolean in autonomousFrame (program extending LinerOpMode)
     * @param frame Type "this" to pass in autonomousFrame (when calling in autonomousFrame)
     *              so this function can access it
     */
    public void encoderDrive(double driveFB, double turnDegrees, double speed,
                             boolean opModeIsActive, autonomousFrame frame) {

        // Defines Target Position Variables
        int newLeftFrontTarget;
        int newRightFrontTarget;
        int newLeftBackTarget;
        int newRightBackTarget;

        double motorLeftFrontEncoder;
        double motorRightFrontEncoder;
        double motorLeftBackEncoder;
        double motorRightBackEncoder;

        turnDegrees = turnDegrees * counts_per_degree / counts_per_inch;

        // How many counts needed to move to specified location
        motorLeftFrontEncoder = (-driveFB - turnDegrees) * counts_per_inch;
        motorRightFrontEncoder = (driveFB - turnDegrees) * counts_per_inch;
        motorLeftBackEncoder = (-driveFB - turnDegrees) * counts_per_inch;
        motorRightBackEncoder = (driveFB - turnDegrees) * counts_per_inch;

        if (opModeIsActive) {

            // Total count- adds current count to count needed to reach location
            newLeftFrontTarget = motorLeftFront.getCurrentPosition() + (int) (motorLeftFrontEncoder);
            newRightFrontTarget = motorRightFront.getCurrentPosition() + (int) (motorRightFrontEncoder);
            newLeftBackTarget = motorLeftBack.getCurrentPosition() + (int) (motorLeftBackEncoder);
            newRightBackTarget = motorRightBack.getCurrentPosition() + (int) (motorRightBackEncoder);

            // Tell motors target count
            motorLeftFront.setTargetPosition(newLeftFrontTarget);
            motorRightFront.setTargetPosition(newRightFrontTarget);
            motorLeftBack.setTargetPosition(newLeftBackTarget);
            motorRightBack.setTargetPosition(newRightBackTarget);

            // Tell motors move until the target count
            motorLeftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motorRightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motorLeftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motorRightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // Tell motors power at which to move at
            motorLeftFront.setPower(Math.abs(speed));
            motorRightFront.setPower(Math.abs(speed));
            motorLeftBack.setPower(Math.abs(speed));
            motorRightBack.setPower(Math.abs(speed));

            // Waits until all motors have reached the target count
            while (opModeIsActive && (motorLeftFront.isBusy() || motorRightFront.isBusy() ||
                    motorLeftBack.isBusy() || motorRightBack.isBusy())) {

                // Displays Target and Current Positions
                //frame.telemetry.addData("Target Value", "Running to %7d :%7d :%7d :%7d",
                //        newLeftFrontTarget, newRightFrontTarget, newLeftBackTarget, newRightBackTarget);
                //frame.telemetry.addData("Current Value", "Running at %7d :%7d: %7d :%7d",
                //        motorLeftFront.getCurrentPosition(),
                //        motorRightFront.getCurrentPosition(),
                //        motorLeftBack.getCurrentPosition(),
                //        motorRightBack.getCurrentPosition());
                //frame.telemetry.update();
            }

            // Tells motors to stop
            motorLeftFront.setPower(0);
            motorRightFront.setPower(0);
            motorLeftBack.setPower(0);
            motorRightBack.setPower(0);

            // Changes motor mode back to default
            motorLeftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            motorRightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            motorLeftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            motorRightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }
}
