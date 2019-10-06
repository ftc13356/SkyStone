package org.firstinspires.ftc.teamcode.examples.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * <h2>Autonomous Frame</h2>
 * Purpose:
 * <p> Contains all the common variables and functions in autonomous programs
 * so that they are neat and organized
 *
 * @version 7.0 on 2/1/19
 * @author Jonthan Ma and Ansh Gandhi
 */

@Autonomous(name="Basic Autonomous Frame")

@Disabled
public abstract class autonomousFrame extends LinearOpMode {

    // VERSION NUMBER(MAJOR.MINOR) - DATE
    // DO BEFORE EVERY COMMIT!
    private static final String autonomousVersionNumber = "1.0 - 10/6/19";

    // Initialize Motors, Servos, and Sensor Variables
    private hexChassis chassis = new hexChassis();

    private CRServo leftIntake;
    private CRServo rightIntake;
    private DcMotor intakeAngleMotor;

    /**
     * Prints version number of autonomous program
     */
    public void versionPrint() {
        telemetry.addData("Autonomous Program Version", autonomousVersionNumber);
        telemetry.update();
    }

    /**
     * Maps motors, servos, and sensors to their names in the robot config file
     *
     * <p> Sets chassis specific motor direction and 0 power behavior
     */
    public void initializeRobot() {
        chassis.initializeMotors(hardwareMap);
        leftIntake = hardwareMap.crservo.get("leftIntake");
        rightIntake = hardwareMap.crservo.get("rightIntake");
        intakeAngleMotor = hardwareMap.dcMotor.get("intakeAngle");

        intakeAngleMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intakeAngleMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }


    /**
     * **Not used directly by autonomous programs**
     * <p> See
     * {@linkplain autonomousFrame#forward(double, double)},
     * {@linkplain autonomousFrame#backward(double, double)},
     * {@linkplain autonomousFrame#left(double, double)}, and
     * {@linkplain autonomousFrame#right(double, double)}
     * <p>
     * <p> Allows robot to be programmed to go forward, backward a certain amount of inches
     * <p> Allows robot to be programmed to turn left and right a certain number of degrees
     * @param driveFB Inches to move forward or backward (forward: +, backward: -)
     * @param turnDegrees Degrees to turn left or right (right: +, left: -)
     * @param speed Speed of robot (min: 0, max: 1)
     */
    public void encoderDrive(double driveFB, double turnDegrees, double speed) {

        // get function from current chassis
        chassis.encoderDrive(driveFB, turnDegrees, speed, opModeIsActive(), this);
    }

    /**
     * Moves robot forward
     * @param distance Inches forward
     * @param speed Speed of robot
     */
    public void forward(double distance, double speed) {
        encoderDrive(distance, 0, speed);
    }

    /**
     * Moves robot backward
     * @param distance Inches backward
     * @param speed Speed of robot
     */
    public void backward(double distance, double speed) {
        encoderDrive(-distance, 0, speed);
    }

    /**
     * Turns robot left
     * @param degrees Degrees left
     * @param speed Speed of robot
     */
    public void left(double degrees, double speed) {
        encoderDrive(0, -degrees, speed);
    }

    /**
     * Turns robot right
     * @param degrees Degrees right
     * @param speed Speed of robot
     */
    public void right(double degrees, double speed) {
        encoderDrive(0, degrees, speed);
    }

    /**
     * Moves the robot's intake
     * @param newAngleMotorTarget the encoder value to ove the intake to
     */
    public void moveIntake(int newAngleMotorTarget) {

        if (opModeIsActive()) {
            intakeAngleMotor.setTargetPosition(newAngleMotorTarget);
            intakeAngleMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            intakeAngleMotor.setPower(0.75);
            while (opModeIsActive() && intakeAngleMotor.isBusy()) {
                if ((intakeAngleMotor.getCurrentPosition()<= newAngleMotorTarget - 50)
                        && (intakeAngleMotor.getCurrentPosition()>= -newAngleMotorTarget + 50)) {
                    newAngleMotorTarget = intakeAngleMotor.getCurrentPosition();
                }
                telemetry.addData("Target Value", newAngleMotorTarget);
                telemetry.addData("Current Value", intakeAngleMotor.getCurrentPosition());
                telemetry.update();
            }
            intakeAngleMotor.setPower(0);
            intakeAngleMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    /***
     * Expels the team marker to the depot by spinning the intake wheels
     */
    public void expelMarker() {
        leftIntake.setPower(1);
        rightIntake.setPower(-1);

        sleep(2000);

        leftIntake.setPower(0);
        rightIntake.setPower(0);
    }
}