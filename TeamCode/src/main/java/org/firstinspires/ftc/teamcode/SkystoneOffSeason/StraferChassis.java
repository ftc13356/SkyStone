package org.firstinspires.ftc.teamcode.SkystoneOffSeason;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class StraferChassis {

    StraferChassis drivetrain = new StraferChassis();

    DcMotor motorLeftFront;
    DcMotor motorRightFront;
    DcMotor motorLeftBack;
    DcMotor motorRightBack;

    double counts_per_inch = 0;

    final double wheel_diameter = 3.0;

    double counts_per_motor_goBilda = 0;

    private LinearOpMode op = null;
    private HardwareMap hardwareMap = null;
    private ElapsedTime period = new ElapsedTime();

    BNO055IMU imu;
    Orientation lastAngles = new Orientation();
    double globalAngle, power = .30, correction;
    //set true to enable imu vice versa
    final boolean enableIMU = true;

    public StraferChassis() {
        counts_per_motor_goBilda = 500;
        counts_per_inch = (counts_per_motor_goBilda / (wheel_diameter * Math.PI));
    }

    public void init(LinearOpMode opMode) {

        op = opMode;
        hardwareMap = op.hardwareMap;

        // Chassis motors
        motorLeftFront = hardwareMap.dcMotor.get("motorLeftFront");
        motorRightFront = hardwareMap.dcMotor.get("motorRightFront");
        motorLeftBack = hardwareMap.dcMotor.get("motorLeftBack");
        motorRightBack = hardwareMap.dcMotor.get("motorRightBack");

        // IMU
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();

        parameters.mode = BNO055IMU.SensorMode.IMU;
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled = false;

        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);
        lastAngles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        // make sure the imu gyro is calibrated before continuing.
        while (!op.isStopRequested() && !imu.isGyroCalibrated()) {
            op.sleep(50);
            op.idle();
        }

        op.telemetry.addData("Mode", "waiting for start");
        op.telemetry.addData("imu calib status", imu.getCalibrationStatus().toString());
        op.telemetry.update();
        op.sleep(2500); //TODO Remove it

        // Chassis Motors
        motorLeftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorLeftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorRightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorRightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        motorLeftFront.setDirection(DcMotor.Direction.REVERSE);
        motorRightFront.setDirection(DcMotor.Direction.FORWARD);
        motorLeftBack.setDirection(DcMotor.Direction.REVERSE);
        motorRightBack.setDirection(DcMotor.Direction.FORWARD);
        // reset encoder count kept by left motor.
        motorLeftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorLeftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorRightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorRightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }

    public void stopAllMotors() {
        motorLeftBack.setPower(0);
        motorRightBack.setPower(0);
        motorLeftFront.setPower(0);
        motorRightFront.setPower(0);
    }

    public void stopAllMotorsSideways() {
        motorLeftBack.setPower(0);
        motorLeftFront.setPower(0);
        motorRightFront.setPower(0);
        motorRightBack.setPower(0);
    }

    public double getAngle() {
        return drivetrain.getAngle();
    }

    private double checkDirection() {
        // The gain value determines how sensitive the correction is to direction changes.
        // You will have to experiment with your robot to get small smooth direction changes
        // to stay on a straight line.
        double correction, angle, gain = .1;

        angle = getAngle();

        /*if (angle == 0)
            correction = 0;             // no adjustment.
        else
            correction = -angle;        // reverse sign of angle for correction.

        correction = correction * gain;*/

        if (enableIMU == false) {
            correction = 0;
        } else {
            correction = angle * (-gain);
        }
        return correction;
    }


    /******** Left Front Motor **********/
    public void moveMotorLeftFront(double distance) {
        double ticksToMove = counts_per_inch * distance;
        double ticksLocationToMove = motorLeftFront.getCurrentPosition() + ticksToMove;
        motorLeftFront.setTargetPosition((int) ticksLocationToMove); //TODO : Check for rounding
        motorLeftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorLeftFront.setPower(0.5);
        motorLeftFront.setPower(0);
        motorLeftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    /******** Right Front Motor **********/
    public void moveMotorRightFront(double distance) {
        double ticksToMove = counts_per_inch * distance;
        double ticksLocationToMove = motorRightFront.getCurrentPosition() + ticksToMove;
        motorRightFront.setTargetPosition((int) ticksLocationToMove); //TODO : Check for rounding
        motorRightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorRightFront.setPower(0.5);
        motorRightFront.setPower(0);
        motorRightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    /******** Left Back Motor **********/
    public void moveMotorLeftBack(double distance) {
        double ticksToMove = counts_per_inch * distance;
        double ticksLocationToMove = motorLeftBack.getCurrentPosition() + ticksToMove;
        motorLeftBack.setTargetPosition((int) ticksLocationToMove); //TODO : Check for rounding
        motorLeftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorLeftBack.setPower(0.5);
        motorLeftBack.setPower(0);
        motorLeftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    /******** Right Back Motor **********/
    public void moveMotorRightBack(double distance) {
        double ticksToMove = counts_per_inch * distance;
        double ticksLocationToMove = motorRightBack.getCurrentPosition() + ticksToMove;
        motorRightBack.setTargetPosition((int) ticksLocationToMove); //TODO : Check for rounding
        motorRightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorRightBack.setPower(0.5);
        //brake
        motorRightBack.setPower(0);
        motorRightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }


    public void moveForwardIMU(double distance, double power) {
        double ticksToMove = counts_per_inch * distance;
        double newLeftBackTargetPosition = motorLeftBack.getCurrentPosition() + ticksToMove;
        double newLeftFrontTargetPosition = motorLeftFront.getCurrentPosition() + ticksToMove;
        double newRightBackTargetPosition = motorRightBack.getCurrentPosition() + ticksToMove;
        double newRightFrontTargetPosition = motorRightFront.getCurrentPosition() + ticksToMove;
        double currentPosition = 0;
        double deltaPosition = 0;
        double currentAngle = 0;
        double startingAngle = 0;

        startingAngle = getAngle();

        motorRightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorLeftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorLeftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        currentPosition = motorLeftBack.getCurrentPosition();
        deltaPosition = newLeftBackTargetPosition - currentPosition; //interchangable based on which way robot turns

        while (op.opModeIsActive() && (deltaPosition >= 0)) {
            currentPosition = motorLeftBack.getCurrentPosition();
            deltaPosition = newLeftBackTargetPosition - currentPosition;
            currentAngle = getAngle();
            correction = (currentAngle - startingAngle) * .008;//gain
            motorRightBack.setPower(power - correction);
            motorRightFront.setPower(power + correction);
            motorLeftBack.setPower(power - correction);
            motorLeftFront.setPower(power + correction);
            op.telemetry.addData("current pos", currentPosition + "delta pos", deltaPosition);
            op.telemetry.update();
            op.idle();
        }
        motorLeftBack.setPower(0);
        motorRightBack.setPower(0);
        motorLeftFront.setPower(0);
        motorRightFront.setPower(0);
    }

    public void moveForward(double distance, double power) {
        double ticksToMove = counts_per_inch * distance;
        double newLeftBackTargetPosition = motorLeftBack.getCurrentPosition() + ticksToMove;
        double newLeftFrontTargetPosition = motorLeftFront.getCurrentPosition() + ticksToMove;
        double newRightBackTargetPosition = motorRightBack.getCurrentPosition() + ticksToMove;
        double newRightFrontTargetPosition = motorRightFront.getCurrentPosition() + ticksToMove;
        motorLeftBack.setTargetPosition((int) newLeftBackTargetPosition);
        motorLeftFront.setTargetPosition((int) newLeftFrontTargetPosition);
        motorRightBack.setTargetPosition((int) newRightBackTargetPosition);
        motorRightFront.setTargetPosition((int) newRightFrontTargetPosition);

        op.telemetry.addData("ticks: ", (int) ticksToMove +
                "LB: " + (int) newLeftBackTargetPosition + "LF: " + (int) newLeftFrontTargetPosition +
                "RB: " + (int) newRightBackTargetPosition + "LB: " + (int) newRightFrontTargetPosition);
        op.telemetry.update();

        motorRightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorRightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorLeftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorLeftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motorRightFront.setPower(power);
        motorLeftFront.setPower(power);
        motorRightBack.setPower(power);
        motorLeftBack.setPower(power);

        while (op.opModeIsActive() && (motorLeftBack.isBusy() && motorLeftFront.isBusy() && motorRightBack.isBusy() &&
                motorRightFront.isBusy())) {
            correction = checkDirection();

            motorRightBack.setPower(power - correction);
            motorRightFront.setPower(power - correction);
            motorLeftBack.setPower(power + correction);
            motorLeftFront.setPower(power + correction);
            op.telemetry.addData("correction", correction);
            op.telemetry.update();
            op.idle();
        }

        motorLeftBack.setPower(0);
        motorRightBack.setPower(0);
        motorRightFront.setPower(0);
        motorLeftFront.setPower(0);

        // Changes motor mode back to default
        motorLeftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorLeftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void moveBackwardIMU(double distance, double power) {
        double ticksToMove = counts_per_inch * distance;
        double newLeftBackTargetPosition = motorLeftBack.getCurrentPosition() - ticksToMove;
        double newLeftFrontTargetPosition = motorLeftFront.getCurrentPosition() - ticksToMove;
        double newRightBackTargetPosition = motorRightBack.getCurrentPosition() - ticksToMove;
        double newRightFrontTargetPosition = motorRightFront.getCurrentPosition() - ticksToMove;
        double currentPosition = 0;
        double deltaPosition = 0;
        double currentAngle = 0;
        double startingAngle = 0;

        startingAngle = getAngle();

        motorRightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorLeftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorLeftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        currentPosition = motorLeftBack.getCurrentPosition();
        deltaPosition = currentPosition - newLeftBackTargetPosition; //interchangable based on which way robot turns

        while (op.opModeIsActive() && (deltaPosition >= 0)) {
            currentPosition = motorLeftBack.getCurrentPosition();
            deltaPosition = currentPosition - newLeftBackTargetPosition;
            currentAngle = getAngle();
            correction = (currentAngle - startingAngle) * .005;//gain
            motorRightBack.setPower(-power + correction);
            motorRightFront.setPower(-power - correction);
            motorLeftBack.setPower(-power + correction);
            motorLeftFront.setPower(-power - correction);
            op.telemetry.addData("current pos", currentPosition + "delta pos", deltaPosition);
            op.telemetry.update();
            op.idle();
        }
        motorLeftBack.setPower(0);
        motorRightBack.setPower(0);
        motorLeftFront.setPower(0);
        motorRightFront.setPower(0);
    }

    public void moveBackward(double distance, double power) {
        double ticksToMove = counts_per_inch * distance;
        double newLeftBackTargetPosition = motorLeftBack.getCurrentPosition() - ticksToMove;
        double newLeftFrontTargetPosition = motorLeftFront.getCurrentPosition() - ticksToMove;
        double newRightBackTargetPosition = motorRightBack.getCurrentPosition() - ticksToMove;
        double newRightFrontTargetPosition = motorRightFront.getCurrentPosition() - ticksToMove;
        motorLeftBack.setTargetPosition((int) newLeftBackTargetPosition);
        motorLeftFront.setTargetPosition((int) newLeftFrontTargetPosition);
        motorRightBack.setTargetPosition((int) newRightBackTargetPosition);
        motorRightFront.setTargetPosition((int) newRightFrontTargetPosition);

        op.telemetry.addData("ticks: ", (int) ticksToMove +
                "LB: " + (int) newLeftBackTargetPosition + "LF: " + (int) newLeftFrontTargetPosition +
                "RB: " + (int) newRightBackTargetPosition + "LB: " + (int) newRightFrontTargetPosition);
        op.telemetry.update();

        motorRightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorRightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorLeftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorLeftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motorLeftBack.setPower(power);
        motorRightBack.setPower(power);
        motorLeftFront.setPower(power);
        motorRightFront.setPower(power);

        while (op.opModeIsActive() && (motorLeftBack.isBusy() && motorLeftFront.isBusy() && motorRightBack.isBusy() &&
                motorRightFront.isBusy())) {
            correction = checkDirection();

            motorRightBack.setPower(power + correction);
            motorRightFront.setPower(power + correction);
            motorLeftBack.setPower(power - correction);
            motorLeftFront.setPower(power - correction);
            op.telemetry.addData("correction", correction);
            op.telemetry.update();
            op.idle();
        }
        motorLeftBack.setPower(0);
        motorRightBack.setPower(0);
        motorRightFront.setPower(0);
        motorLeftFront.setPower(0);

        // Changes motor mode back to default
        motorLeftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorLeftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void moveLeftIMU(double distance, double power, double startingAngle, double gain, double maxCorrection) {
        double ticksToMove = counts_per_inch * distance;
        double newLeftBackTargetPosition = motorLeftBack.getCurrentPosition() - ticksToMove;
        double currentPosition = 0;
        double deltaPosition = 0;
        double currentAngle = 0;
        double maxcorrection = 0.16 * power;

        motorRightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorLeftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorLeftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        currentPosition = motorLeftBack.getCurrentPosition();
        deltaPosition = ticksToMove;

        while (op.opModeIsActive() && (deltaPosition >= 0)) {
            currentPosition = motorLeftBack.getCurrentPosition();
            deltaPosition = currentPosition - newLeftBackTargetPosition;
            currentAngle = getAngle();
            correction = (currentAngle - startingAngle) * gain * power;//gain=0.06 for power 0.85
            if (correction > maxcorrection) {
                correction = maxcorrection;
            }
            if (correction < -maxcorrection) {
                correction = -maxcorrection;
            }
            motorRightBack.setPower(power - correction);
            motorRightFront.setPower(-power - correction);
            motorLeftBack.setPower(-power + correction);
            motorLeftFront.setPower(power + correction);
            op.telemetry.addData("del ", deltaPosition + "cur " + currentPosition);
            op.telemetry.update();
            op.idle();
        }
        stopAllMotorsSideways();
    }

    public void moveLeft(double distance, double power) {
        double ticksToMove = counts_per_inch * distance;
        double newLeftBackTargetPosition = motorLeftBack.getCurrentPosition() - ticksToMove;
        double newLeftFrontTargetPosition = motorLeftFront.getCurrentPosition() + ticksToMove;
        double newRightBackTargetPosition = motorRightBack.getCurrentPosition() + ticksToMove;
        double newRightFrontTargetPosition = motorRightFront.getCurrentPosition() - ticksToMove;
        motorLeftBack.setTargetPosition((int) newLeftBackTargetPosition);
        motorLeftFront.setTargetPosition((int) newLeftFrontTargetPosition);
        motorRightBack.setTargetPosition((int) newRightBackTargetPosition);
        motorRightFront.setTargetPosition((int) newRightFrontTargetPosition);

        op.telemetry.addData("ticks: ", (int) ticksToMove +
                "LB: " + (int) newLeftBackTargetPosition + "LF: " + (int) newLeftFrontTargetPosition +
                "RB: " + (int) newRightBackTargetPosition + "LB: " + (int) newRightFrontTargetPosition);
        op.telemetry.update();
        //op.sleep(5000);

        motorRightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorRightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorLeftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorLeftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motorLeftBack.setPower(power);
        motorRightBack.setPower(power);
        motorLeftFront.setPower(power);
        motorRightFront.setPower(power);

        while (op.opModeIsActive() && (motorLeftBack.isBusy() && motorLeftFront.isBusy() && motorRightBack.isBusy() &&
                motorRightFront.isBusy())) {
            correction = checkDirection();

            motorRightBack.setPower(power + correction);
            motorRightFront.setPower(power - correction);
            motorLeftBack.setPower(power + correction);
            motorLeftFront.setPower(power - correction);
            op.telemetry.addData("correction", correction);
            op.telemetry.update();
            op.idle();
        }

//        motorLeftBack.setPower(0);
//        motorRightBack.setPower(0);
//        motorRightFront.setPower(0);
//        motorLeftFront.setPower(0);
        stopAllMotorsSideways();

        // Changes motor mode back to default
        motorLeftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorLeftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void moveRightIMU(double distance, double power, double startingAngle, double gain, double maxCorrection) {
        double ticksToMove = counts_per_inch * distance;
        double newLeftBackTargetPosition = motorLeftBack.getCurrentPosition() - ticksToMove;
        double currentPosition = 0;
        double deltaPosition = 0;
        double currentAngle = 0;
        double maxcorrection = 0.16 * power;

        motorRightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorLeftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorLeftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        currentPosition = motorLeftBack.getCurrentPosition();
        deltaPosition = ticksToMove;

        while (op.opModeIsActive() && (deltaPosition >= 0)) {
            currentPosition = motorLeftBack.getCurrentPosition();
            deltaPosition = newLeftBackTargetPosition - currentPosition;
            currentAngle = getAngle();
            correction = (currentAngle - startingAngle) * gain * power;//gain is 0.06 for power 0.85
            if (correction > maxcorrection) {
                correction = maxcorrection;
            }
            if (correction < -maxcorrection) {
                correction = -maxcorrection;
            }
            motorRightBack.setPower(-power - correction);
            motorRightFront.setPower(power - correction);
            motorLeftBack.setPower(power + correction);
            motorLeftFront.setPower(-power + correction);
        }
        stopAllMotorsSideways();
    }

    public void moveRight(double distance, double power) {
        double ticksToMove = counts_per_inch * distance;
        double newLeftBackTargetPosition = motorLeftBack.getCurrentPosition() + ticksToMove;
        double newLeftFrontTargetPosition = motorLeftFront.getCurrentPosition() - ticksToMove;
        double newRightBackTargetPosition = motorRightBack.getCurrentPosition() - ticksToMove;
        double newRightFrontTargetPosition = motorRightFront.getCurrentPosition() + ticksToMove;
        motorLeftBack.setTargetPosition((int) newLeftBackTargetPosition);
        motorLeftFront.setTargetPosition((int) newLeftFrontTargetPosition);
        motorRightBack.setTargetPosition((int) newRightBackTargetPosition);
        motorRightFront.setTargetPosition((int) newRightFrontTargetPosition);

        motorRightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorRightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorLeftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorLeftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        op.telemetry.addData("ticks: ", (int) ticksToMove +
                "LB: " + (int) newLeftBackTargetPosition + "LF: " + (int) newLeftFrontTargetPosition +
                "RB: " + (int) newRightBackTargetPosition + "LB: " + (int) newRightFrontTargetPosition);
        op.telemetry.update();

        motorLeftBack.setPower(power);
        motorRightBack.setPower(-power);
        motorLeftFront.setPower(-power);
        motorRightFront.setPower(power);

        while (op.opModeIsActive() && (motorLeftBack.isBusy() && motorLeftFront.isBusy() && motorRightBack.isBusy() &&
                motorRightFront.isBusy())) {
            correction = checkDirection();

            motorRightBack.setPower(power - correction);
            motorRightFront.setPower(power + correction);
            motorLeftBack.setPower(power - correction);
            motorLeftFront.setPower(power + correction);
            op.telemetry.addData("correction", correction);
            op.telemetry.update();
            op.idle();
        }
//        motorLeftBack.setPower(0);
//        motorRightBack.setPower(0);
//        motorRightFront.setPower(0);
//        motorLeftFront.setPower(0);
        stopAllMotorsSideways();

        // Changes motor mode back to default
        motorLeftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorLeftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}
