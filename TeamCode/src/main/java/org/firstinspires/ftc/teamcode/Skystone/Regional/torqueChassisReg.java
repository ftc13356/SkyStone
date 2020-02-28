package org.firstinspires.ftc.teamcode.Skystone.Regional;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.StringWriter;
import java.io.PrintWriter;


public class torqueChassisReg {
    //initialize motor
    DcMotorEx motorLeftFront;
    DcMotorEx motorRightFront;
    DcMotorEx motorLeftBack;
    DcMotorEx motorRightBack;


    // these encoder variables vary depending on chassis type
    double counts_per_motor_rev = 0;
    double counts_per_inch = 0;
    double counts_per_degree = 0;
    double liftheight = 0;

    //variables for lifting mechanism
    double counts_per_motor_tetrix = 0;
    double counts_per_inch_lift = 0;

    // Initialize Encoder Variables
    final double robot_diameter = 10.5;
    final double drive_gear_reduction = 1.0;
    final double wheel_diameter = 3.0;

    // Motor Speed for various Operations
    final double motor_speed_fb = 1; //forward & backward
    final double motor_speed_side = 0.75;
    final double motor_speed_turn = 0.75;

    /* local OpMode members. */
    private LinearOpMode op              = null;
    private HardwareMap  hardwareMap     = null;
    private ElapsedTime  period          = new ElapsedTime();

    private float speed = 37.5f;

    BNO055IMU               imu;
    Orientation             lastAngles = new Orientation();
    double                  globalAngle, power = .30, correction;
    double IMUgain = 0.005;
    //set true to enable imu vice versa
    final boolean enableIMU = false;

    public torqueChassisReg() {
        /******* hex motors ******/
        //counts_per_motor_rev = 600;
        //counts_per_inch = (counts_per_motor_rev / (wheel_diameter * Math.PI));
        //counts_per_inch = 288 / (4 * Math.PI);
        //counts_per_inch = 23 ticks

        // 23 * 14 * 3.14 / 360 = 2.8 ticks
        counts_per_degree = counts_per_inch * robot_diameter * Math.PI / 360;

        counts_per_motor_tetrix = 480;
        counts_per_inch = (counts_per_motor_tetrix / (wheel_diameter * Math.PI));
        counts_per_degree = counts_per_inch * robot_diameter * Math.PI / 360;

    }

    public void init(LinearOpMode opMode) {

        op = opMode;
        hardwareMap = op.hardwareMap;

        // Chassis motors
        motorLeftFront = (DcMotorEx) hardwareMap.dcMotor.get("motorLeftFront");
        motorRightFront = (DcMotorEx) hardwareMap.dcMotor.get("motorRightFront");
        motorLeftBack = (DcMotorEx) hardwareMap.dcMotor.get("motorLeftBack");
        motorRightBack = (DcMotorEx) hardwareMap.dcMotor.get("motorRightBack");

        // IMU
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();

        parameters.mode                = BNO055IMU.SensorMode.IMU;
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled      = false;

        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);
        lastAngles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        // make sure the imu gyro is calibrated before continuing.
        while (!op.isStopRequested() && !imu.isGyroCalibrated())
        {
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

        motorLeftFront.setDirection(DcMotor.Direction.FORWARD);
        motorRightFront.setDirection(DcMotor.Direction.REVERSE);
        motorLeftBack.setDirection(DcMotor.Direction.FORWARD);
        motorRightBack.setDirection(DcMotor.Direction.REVERSE);
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

    public void moveForwardTeleop(double power, double distance) { //TODO Remove this function and move all calls to function without distance parameter
        // Changes motor mode back to default
        motorLeftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorLeftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //was 0.70
        motorLeftBack.setPower(power);
        motorRightBack.setPower(power);
        motorLeftFront.setPower(power);
        motorRightFront.setPower(power);
    }

    public void moveForwardTeleop(double power) {
        // Changes motor mode back to default
        motorLeftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorLeftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //was 0.70
        motorLeftBack.setPower(power);
        motorRightBack.setPower(power);
        motorLeftFront.setPower(power);
        motorRightFront.setPower(power);

    }

    public void moveBackwardTeleop(double power, double distance) {
        // Changes motor mode back to default
        motorLeftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorLeftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //was -0.80
        motorLeftBack.setPower(-power);
        motorRightBack.setPower(-power);
        motorLeftFront.setPower(-power);
        motorRightFront.setPower(-power);

    }

    public void moveRightTeleop(double power, double distance) {
        // Changes motor mode back to default
        motorLeftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorLeftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //this was 1.0 and -1.0
        motorLeftBack.setPower(power*0.95);
        motorRightBack.setPower(-power);
        motorLeftFront.setPower(-power*0.85);
        motorRightFront.setPower(power);

    }

    public void moveLeftTeleop(double power, double distance) {
        // Changes motor mode back to default
        motorLeftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorLeftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //was -1.0 and 1.0
        motorLeftBack.setPower(-power*0.95); //TODO: change back & use encoders for sideways
        motorRightBack.setPower(power);
        motorLeftFront.setPower(power*0.85);
        motorRightFront.setPower(-power);

    }


    //@direction: true = left, false = right
    public void inPlaceTurnTeleop(double degrees, boolean direction, double power) {
        // Changes motor mode back to default
        motorLeftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorLeftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        if (direction == true) {
            motorLeftBack.setPower(-power);
            motorLeftFront.setPower(-power);
            motorRightBack.setPower(power);
            motorRightFront.setPower(power);
        } else {
            motorLeftBack.setPower(power);
            motorLeftFront.setPower(power);
            motorRightBack.setPower(-power);
            motorRightFront.setPower(-power);
        }
    }
    //not tested yet
    public void normalTurnTeleop(double degrees, boolean direction, double power) {
        if (direction == true) {
            motorLeftBack.setPower(power);
            motorLeftFront.setPower(power);
            motorRightBack.setPower(0.0);
            motorRightFront.setPower(0.0);
        } else {
            motorLeftBack.setPower(0.0);
            motorLeftFront.setPower(0.0);
            motorRightBack.setPower(power);
            motorRightFront.setPower(power);
        }
    }
//    public void fasterMoveForwardIMU(double distance, double power) {
//        double ticksToMove = counts_per_inch * distance;
//        double newLeftBackTargetPosition = motorLeftBack.getCurrentPosition() + ticksToMove;
//        double newLeftFrontTargetPosition = motorLeftFront.getCurrentPosition() + ticksToMove;
//        double newRightBackTargetPosition = motorRightBack.getCurrentPosition() + ticksToMove;
//        double newRightFrontTargetPosition = motorRightFront.getCurrentPosition() + ticksToMove;
//        double currentPosition = 0;
//        double deltaPosition = 0;
//        double currentAngle = 0;
//        double startingAngle = 0;
//
//        startingAngle = 0;
//
//        motorRightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        motorRightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        motorLeftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        motorLeftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//
//
//        currentPosition = motorLeftBack.getCurrentPosition();
//        deltaPosition = newLeftBackTargetPosition - currentPosition; //interchangable based on which way robot turns
//
//        while (op.opModeIsActive() && (deltaPosition >= 0)) {
//            currentPosition = motorLeftBack.getCurrentPosition();
//            deltaPosition = newLeftBackTargetPosition - currentPosition;
//            currentAngle = getAngle();
//            correction = (currentAngle-startingAngle) * IMUgain;//gain
//            motorRightBack.setPower(power - correction);
//            motorRightFront.setPower(power + correction);
//            motorLeftBack.setPower(power - correction);
//            motorLeftFront.setPower(power + correction);
//            op.telemetry.addData("current pos", currentPosition + "delta pos", deltaPosition);
//            op.telemetry.update();
//            op.idle();
//        }
//        motorLeftBack.setPower(0);
//        motorRightBack.setPower(0);
//        motorLeftFront.setPower(0);
//        motorRightFront.setPower(0);
//    }

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
            correction = (currentAngle-startingAngle) * .008;//gain
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
        motorLeftBack.setTargetPosition((int)newLeftBackTargetPosition);
        motorLeftFront.setTargetPosition((int)newLeftFrontTargetPosition);
        motorRightBack.setTargetPosition((int)newRightBackTargetPosition);
        motorRightFront.setTargetPosition((int)newRightFrontTargetPosition);

        op.telemetry.addData("ticks: ", (int)ticksToMove +
                "LB: " + (int)newLeftBackTargetPosition + "LF: " + (int)newLeftFrontTargetPosition +
                "RB: " + (int)newRightBackTargetPosition + "LB: " + (int)newRightFrontTargetPosition);
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
                motorRightFront.isBusy()))
        {
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

    private void resetAngle()
    {
        lastAngles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        globalAngle = 0;
    }

    double getAngle()
    {
        // We experimentally determined the Z axis is the axis we want to use for heading angle.
        // We have to process the angle because the imu works in euler angles so the Z axis is
        // returned as 0 to +180 or 0 to -180 rolling back to -179 or +179 when rotation passes
        // 180 degrees. We detect this transition and track the total cumulative angle of rotation.

        Orientation angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        //op.telemetry.addData("first angle: ", (int)angles.firstAngle);
        //op.telemetry.update();
        //op.sleep(1000);
        double deltaAngle = angles.firstAngle - lastAngles.firstAngle;

        if (deltaAngle <= -180) //If the angle is -180, it should be 180, because they are at the same point. The acceptable angles are (-180, 180]
            deltaAngle += 360;
        else if (deltaAngle > 180)
            deltaAngle -= 360;

        globalAngle += deltaAngle;

        lastAngles = angles;

        return globalAngle;
    }

    private double checkDirection()
    {
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

//    public void fasterMoveBackwardIMU(double distance, double power) {
//        double ticksToMove = counts_per_inch * distance;
//        double newLeftBackTargetPosition = motorLeftBack.getCurrentPosition() - ticksToMove;
//        double newLeftFrontTargetPosition = motorLeftFront.getCurrentPosition() - ticksToMove;
//        double newRightBackTargetPosition = motorRightBack.getCurrentPosition() - ticksToMove;
//        double newRightFrontTargetPosition = motorRightFront.getCurrentPosition() - ticksToMove;
//        double currentPosition = 0;
//        double deltaPosition = 0;
//        double currentAngle = 0;
//        double startingAngle = 0;
//
//        startingAngle = 0;
//
//        motorRightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        motorRightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        motorLeftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        motorLeftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//
//        currentPosition = motorLeftBack.getCurrentPosition();
//        deltaPosition = currentPosition - newLeftBackTargetPosition; //interchangable based on which way robot turns
//
//        while (op.opModeIsActive() && (deltaPosition >= 0)) {
//            currentPosition = motorLeftBack.getCurrentPosition();
//            deltaPosition = currentPosition - newLeftBackTargetPosition;
//            currentAngle = getAngle();
//            correction = (currentAngle-startingAngle) * .05;//gain
//            motorRightBack.setPower(-power + correction);
//            motorRightFront.setPower(-power - correction);
//            motorLeftBack.setPower(-power + correction);
//            motorLeftFront.setPower(-power - correction);
//            op.telemetry.addData("current pos", currentPosition + "delta pos", deltaPosition);
//            op.telemetry.update();
//            op.idle();
//        }
//        motorLeftBack.setPower(0);
//        motorRightBack.setPower(0);
//        motorLeftFront.setPower(0);
//        motorRightFront.setPower(0);
//    }

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
            correction = (currentAngle-startingAngle) * .005;//gain
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
        motorLeftBack.setTargetPosition((int)newLeftBackTargetPosition);
        motorLeftFront.setTargetPosition((int)newLeftFrontTargetPosition);
        motorRightBack.setTargetPosition((int)newRightBackTargetPosition);
        motorRightFront.setTargetPosition((int)newRightFrontTargetPosition);

        op.telemetry.addData("ticks: ", (int)ticksToMove +
                "LB: " + (int)newLeftBackTargetPosition + "LF: " + (int)newLeftFrontTargetPosition +
                "RB: " + (int)newRightBackTargetPosition + "LB: " + (int)newRightFrontTargetPosition);
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
                motorRightFront.isBusy()))
        {
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

    public void fastermoveRightIMU(double distance, double power) {
double ticksToMove = counts_per_inch * distance;
    double newLeftBackTargetPosition = motorLeftBack.getCurrentPosition() + ticksToMove;
    double newLeftFrontTargetPosition = motorLeftFront.getCurrentPosition() - ticksToMove;
    double newRightBackTargetPosition = motorRightBack.getCurrentPosition() - ticksToMove;
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

        while (op.opModeIsActive() && deltaPosition >= 0) {
        currentPosition = motorLeftBack.getCurrentPosition();
        deltaPosition = newLeftBackTargetPosition - currentPosition;
        currentAngle = getAngle();
        correction = (currentAngle-startingAngle) * IMUgain*9;//gain
        motorRightBack.setPower(-power - correction);
        motorRightFront.setPower(power - correction);
        motorLeftBack.setPower(power + correction);
        motorLeftFront.setPower(-power + correction);
        op.telemetry.addData("current pos", currentPosition + "delta pos", deltaPosition);
        op.telemetry.update();
        op.idle();
    }
        motorLeftBack.setPower(0);
        motorRightBack.setPower(0);
        motorLeftFront.setPower(0);
        motorRightFront.setPower(0);
    }

    public void moveRightIMU(double distance, double power) {
        double ticksToMove = counts_per_inch * distance;
        double newLeftBackTargetPosition = motorLeftBack.getCurrentPosition() + ticksToMove;
        double newLeftFrontTargetPosition = motorLeftFront.getCurrentPosition() - ticksToMove;
        double newRightBackTargetPosition = motorRightBack.getCurrentPosition() - ticksToMove;
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

        while (op.opModeIsActive() && deltaPosition >= 0) {
            currentPosition = motorLeftBack.getCurrentPosition();
            deltaPosition = newLeftBackTargetPosition - currentPosition;
            currentAngle = getAngle();
            correction = (currentAngle-startingAngle) * IMUgain;//gain
            motorRightBack.setPower(-power - correction);
            motorRightFront.setPower(power - correction);
            motorLeftBack.setPower(power + correction);
            motorLeftFront.setPower(-power + correction);
            op.telemetry.addData("current pos", currentPosition + "delta pos", deltaPosition);
            op.telemetry.update();
            op.idle();
        }
        motorLeftBack.setPower(0);
        motorRightBack.setPower(0);
        motorLeftFront.setPower(0);
        motorRightFront.setPower(0);
    }

    public void moveRightIMU(double distance, double power, double startingAngle,double gain, double maxCorrection) {
        double ticksToMove = counts_per_inch * distance;
        double newLeftBackTargetPosition = motorLeftBack.getCurrentPosition() + ticksToMove;
        double currentPosition = 0;
        double deltaPosition = 0;
        double currentAngle = 0;
        double maxcorrection=0.16*power;

        op.telemetry.addData("moveRightIMU:", "Inside it %d\n",1);
        op.telemetry.update();
        //op.sleep(3000);
        motorRightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorLeftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorLeftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        try
        {
            //Create File
            File myFTCfile = new File("/storage/emulated/0/tmp/Right_g"+gain+"p"+power+"m"+maxCorrection+".csv");
            if (myFTCfile.createNewFile()) {
                op.telemetry.addData("moveRightIMU:", "File created:%S\n","Right_g"+gain+"p"+power+"m"+maxCorrection);
                op.telemetry.update();
            } else {
                op.telemetry.addData("moveRightIMU:", "File already exists:%S\n","Right"+gain+power+maxCorrection);
                op.telemetry.update();
            }
            //op.sleep(3000);
            //FileWriteHandle
            FileWriter wFTCfile = new FileWriter(myFTCfile);
            wFTCfile.write("Time,CurrentAngle,Correction,LFpower,LBpower,RFpower,RBpower,deltapos,currentpos,targetpos,gain,power\n");
            currentPosition = motorLeftBack.getCurrentPosition();
            deltaPosition = ticksToMove;
            currentAngle = getAngle();
            correction = (currentAngle - startingAngle) * gain * power;//gain is 0.06 for power 0.85
            wFTCfile.write(System.currentTimeMillis() + ","+currentAngle+","+correction+","+
                    motorLeftFront.getPower()+","+
                    motorLeftBack.getPower()+","+
                    motorRightFront.getPower()+","+
                    motorRightBack.getPower()+","+
                    deltaPosition+","+
                    currentPosition+","+
                    newLeftBackTargetPosition+","+
                    gain+","+power+"\n");


            while (op.opModeIsActive() && (deltaPosition >= 0)) {
                currentPosition = motorLeftBack.getCurrentPosition();
                deltaPosition = newLeftBackTargetPosition - currentPosition;
                currentAngle = getAngle();
                correction = (currentAngle - startingAngle) * gain * power;//gain is 0.06 for power 0.85
                if (correction > maxcorrection) { correction = maxcorrection; }
                if (correction < -maxcorrection) { correction = -maxcorrection; }
                motorRightBack.setPower(-power - correction);
                motorRightFront.setPower(power - correction);
                motorLeftBack.setPower(power + correction);
                motorLeftFront.setPower(-power + correction);

                wFTCfile.write(System.currentTimeMillis() + ","+currentAngle+","+correction+","+
                        motorLeftFront.getPower()+","+
                        motorLeftBack.getPower()+","+
                        motorRightFront.getPower()+","+
                        motorRightBack.getPower()+","+
                        deltaPosition+","+
                        currentPosition+","+
                        newLeftBackTargetPosition+","+
                        gain+","+power+"\n");

                op.telemetry.addData("RT", "A:%3.3f, C:%2.2f, P:%2.2f\n", currentAngle, correction, power);
                //op.telemetry.addData("current pos", currentPosition + "delta pos", deltaPosition);
                op.telemetry.update();
                op.idle();
            }

            stopAllMotorsSideways();
            wFTCfile.close();
        } catch (IOException e) {
            StringWriter outError = new StringWriter();
            e.printStackTrace(new PrintWriter(outError));
            String errorString = outError.toString();
            op.telemetry.addData("Error: ","In try-catch moveRightIMU \n %s", errorString );
            op.telemetry.update();
            //op.sleep(3000);
        }
    }

    public void moveRight(double distance, double power) {
        double ticksToMove = counts_per_inch * distance;
        double newLeftBackTargetPosition = motorLeftBack.getCurrentPosition() + ticksToMove;
        double newLeftFrontTargetPosition = motorLeftFront.getCurrentPosition() - ticksToMove;
        double newRightBackTargetPosition = motorRightBack.getCurrentPosition() - ticksToMove;
        double newRightFrontTargetPosition = motorRightFront.getCurrentPosition() + ticksToMove;
        motorLeftBack.setTargetPosition((int)newLeftBackTargetPosition);
        motorLeftFront.setTargetPosition((int)newLeftFrontTargetPosition);
        motorRightBack.setTargetPosition((int)newRightBackTargetPosition);
        motorRightFront.setTargetPosition((int)newRightFrontTargetPosition);

        motorRightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorRightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorLeftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorLeftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        op.telemetry.addData("ticks: ", (int)ticksToMove +
                "LB: " + (int)newLeftBackTargetPosition + "LF: " + (int)newLeftFrontTargetPosition +
                "RB: " + (int)newRightBackTargetPosition + "LB: " + (int)newRightFrontTargetPosition);
        op.telemetry.update();

        motorLeftBack.setPower(power);
        motorRightBack.setPower(-power);
        motorLeftFront.setPower(-power);
        motorRightFront.setPower(power);

        while (op.opModeIsActive() && (motorLeftBack.isBusy() && motorLeftFront.isBusy() && motorRightBack.isBusy() &&
                motorRightFront.isBusy()))
        {
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
        stopAllMotors();

        // Changes motor mode back to default
        motorLeftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorLeftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void testIMU() {
        while (true) {
            op.telemetry.addData("angle: ", (int)getAngle());
            op.telemetry.update();
            op.sleep(1000);
        }
    }

   public void fastermoveLeftIMU(double distance, double power) {
double ticksToMove = counts_per_inch * distance;
    double newLeftBackTargetPosition = motorLeftBack.getCurrentPosition() - ticksToMove;
    double newLeftFrontTargetPosition = motorLeftFront.getCurrentPosition() + ticksToMove;
    double newRightBackTargetPosition = motorRightBack.getCurrentPosition() + ticksToMove;
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
    deltaPosition = currentPosition - newLeftBackTargetPosition;

        while (op.opModeIsActive() && (deltaPosition >= 0)) {
        currentPosition = motorLeftBack.getCurrentPosition();
        deltaPosition = currentPosition - newLeftBackTargetPosition ;
        currentAngle = getAngle();
        correction = (currentAngle-startingAngle) * IMUgain*9;//gain
        motorRightBack.setPower(power - correction);
        motorRightFront.setPower(-power - correction);
        motorLeftBack.setPower(-power + correction);
        motorLeftFront.setPower(power + correction);
        op.telemetry.addData("del ", deltaPosition + "cur "+ currentPosition);
        op.telemetry.update();
        op.idle();
    }
    stopAllMotors();
    }

    public void moveLeftIMU(double distance, double power) {
        double ticksToMove = counts_per_inch * distance;
        double newLeftBackTargetPosition = motorLeftBack.getCurrentPosition() - ticksToMove;
        double newLeftFrontTargetPosition = motorLeftFront.getCurrentPosition() + ticksToMove;
        double newRightBackTargetPosition = motorRightBack.getCurrentPosition() + ticksToMove;
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
        deltaPosition = currentPosition - newLeftBackTargetPosition;

        while (op.opModeIsActive() && (deltaPosition >= 0)) {
            currentPosition = motorLeftBack.getCurrentPosition();
            deltaPosition = currentPosition - newLeftBackTargetPosition ;
            currentAngle = getAngle();
            correction = (currentAngle-startingAngle) * IMUgain;//gain
            motorRightBack.setPower(power - correction);
            motorRightFront.setPower(-power - correction);
            motorLeftBack.setPower(-power + correction);
            motorLeftFront.setPower(power + correction);
            op.telemetry.addData("del ", deltaPosition + "cur "+ currentPosition);
            op.telemetry.update();
            op.idle();
        }
        stopAllMotors();
    }

    public void moveLeftIMU(double distance, double power, double startingAngle, double gain, double maxCorrection) {
        double ticksToMove = counts_per_inch * distance;
        double newLeftBackTargetPosition = motorLeftBack.getCurrentPosition() - ticksToMove;
        double currentPosition = 0;
        double deltaPosition = 0;
        double currentAngle = 0;
        double maxcorrection=0.16*power;

        motorRightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorLeftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorLeftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        currentPosition = motorLeftBack.getCurrentPosition();
        deltaPosition = ticksToMove;

        while (op.opModeIsActive() && (deltaPosition >= 0)) {
            currentPosition = motorLeftBack.getCurrentPosition();
            deltaPosition = currentPosition - newLeftBackTargetPosition ;
            currentAngle = getAngle();
            correction = (currentAngle-startingAngle) * gain*power;//gain=0.06 for power 0.85
            if (correction > maxcorrection) {correction = maxcorrection;}
            if (correction < -maxcorrection) {correction = -maxcorrection;}
            motorRightBack.setPower(power - correction);
            motorRightFront.setPower(-power - correction);
            motorLeftBack.setPower(-power + correction);
            motorLeftFront.setPower(power + correction);
            op.telemetry.addData("del ", deltaPosition + "cur "+ currentPosition);
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
        motorLeftBack.setTargetPosition((int)newLeftBackTargetPosition);
        motorLeftFront.setTargetPosition((int)newLeftFrontTargetPosition);
        motorRightBack.setTargetPosition((int)newRightBackTargetPosition);
        motorRightFront.setTargetPosition((int)newRightFrontTargetPosition);

        op.telemetry.addData("ticks: ", (int)ticksToMove +
                "LB: " + (int)newLeftBackTargetPosition + "LF: " + (int)newLeftFrontTargetPosition +
                "RB: " + (int)newRightBackTargetPosition + "LB: " + (int)newRightFrontTargetPosition);
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
                motorRightFront.isBusy()))
        {
            correction = checkDirection();

            motorRightBack.setPower(power + correction);
            motorRightFront.setPower(power - correction);
            motorLeftBack.setPower(power + correction);
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
    //@direction: true = left, false = right
    public void inPlaceTurn(double degrees, boolean direction, double power) {

        double ticksToTurn = (degrees * counts_per_degree);
        double newLeftBackTargetPosition;
        double newLeftFrontTargetPosition;
        double newRightBackTargetPosition;
        double newRightFrontTargetPosition;

        //turn left = left wheels go reverse; vice versa
        if (direction == true) {
            newLeftBackTargetPosition = motorLeftBack.getCurrentPosition() - ticksToTurn;
            newLeftFrontTargetPosition = motorLeftFront.getCurrentPosition() - ticksToTurn;
            newRightBackTargetPosition = motorRightBack.getCurrentPosition() + ticksToTurn;
            newRightFrontTargetPosition = motorRightFront.getCurrentPosition() + ticksToTurn;
        } else {
            newLeftBackTargetPosition = motorLeftBack.getCurrentPosition() + ticksToTurn;
            newLeftFrontTargetPosition = motorLeftFront.getCurrentPosition() + ticksToTurn;
            newRightBackTargetPosition = motorRightBack.getCurrentPosition() - ticksToTurn;
            newRightFrontTargetPosition = motorRightFront.getCurrentPosition() - ticksToTurn;
        }
        motorLeftBack.setTargetPosition((int)newLeftBackTargetPosition);
        motorLeftFront.setTargetPosition((int)newLeftFrontTargetPosition);
        motorRightBack.setTargetPosition((int)newRightBackTargetPosition);
        motorRightFront.setTargetPosition((int)newRightFrontTargetPosition);

        motorLeftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorLeftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorRightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorRightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motorLeftBack.setPower(power);
        motorLeftFront.setPower(power);
        motorRightBack.setPower(power);
        motorRightFront.setPower(power);

        while (op.opModeIsActive() && (motorLeftBack.isBusy() && motorLeftFront.isBusy() && motorRightBack.isBusy() &&
                motorRightFront.isBusy()))
        {
            //op.telemetry.addData("encoder-fwd", motorLeftBack.getCurrentPosition() + "  busy=" + motorLeftBack.isBusy());
            //op.telemetry.update();
            op.idle();
        }
        motorLeftBack.setPower(0);
        motorLeftFront.setPower(0);
        motorRightBack.setPower(0);
        motorRightFront.setPower(0);

        // Changes motor mode back to default
        motorLeftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorLeftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void inPlaceTurnIMU(double degrees, double power) { //degress is positive for left and negative for right

        motorLeftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorLeftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        double currentAngle = getAngle();
        double newDegrees = currentAngle + degrees; // Add degrees to current angle to get new position
        double error = degrees;
        double gain = -0.005;
        double leftPower = power*gain*error;
        double rightPower = -power*gain*error;

        if (leftPower>1) {leftPower=1;}
        if (rightPower>1) {rightPower=1;}
        if (leftPower<-1) {leftPower=-1;}
        if (rightPower<-1) {rightPower=-1;}

        if (newDegrees>180){newDegrees=newDegrees-360;}
        if (newDegrees<=-180){newDegrees=newDegrees+360;}


        op.telemetry.addData("TurnIMU", "Angle"+currentAngle+ "Error"+ error+"LP"+leftPower+ "RP"+ rightPower);
        op.telemetry.update();
        //op.sleep(500);
        motorLeftBack.setPower(leftPower);
        motorLeftFront.setPower(leftPower);
        motorRightBack.setPower(rightPower);
        motorRightFront.setPower(rightPower);

        while (op.opModeIsActive() && (error > 3 || error < -3))
        {
            currentAngle = getAngle();
            error = newDegrees - currentAngle;
            leftPower = power*gain*error;
            rightPower = -power*gain*error;
            //op.telemetry.addData("TurnIMU", "Angle"+currentAngle+ "Error"+ error+"LP"+leftPower+ "RP"+ rightPower);
            //op.telemetry.update();
            if (leftPower>1) {leftPower=1;}
            if (rightPower>1) {rightPower=1;}
            if (leftPower<-1) {leftPower=-1;}
            if (rightPower<-1) {rightPower=-1;}
            motorLeftBack.setPower(leftPower);
            motorLeftFront.setPower(leftPower);
            motorRightBack.setPower(rightPower);
            motorRightFront.setPower(rightPower);
            op.idle();
        }

        motorLeftBack.setPower(0);
        motorLeftFront.setPower(0);
        motorRightBack.setPower(0);
        motorRightFront.setPower(0);

        currentAngle = getAngle();
        error = newDegrees - currentAngle;
        //op.telemetry.addData("TurnIMU", "Angle"+currentAngle+ "Error"+ error+"LP"+leftPower+ "RP"+ rightPower);
        //op.telemetry.update();
    }

    public void AbsoluteTurnIMU(double degrees, double power) { //degress is positive for left and negative for right

        motorLeftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorLeftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        double currentAngle = getAngle();
        double newDegrees =  degrees;
        double error = degrees-currentAngle;
        double gain = -0.0025;
        double leftPower = power*gain*error;
        double rightPower = -leftPower; // power*gain*error

        if (leftPower>1) {leftPower=1;}
        if (rightPower>1) {rightPower=1;}
        if (leftPower<-1) {leftPower=-1;}
        if (rightPower<-1) {rightPower=-1;}

        if (newDegrees>180){newDegrees=newDegrees-360;}
        if (newDegrees<=-180){newDegrees=newDegrees+360;}

        motorLeftBack.setPower(leftPower);
        motorLeftFront.setPower(leftPower);
        motorRightBack.setPower(rightPower);
        motorRightFront.setPower(rightPower);

        while (op.opModeIsActive() && (error > 2 || error < -2))
        {
            currentAngle = getAngle();
            error = degrees - currentAngle;
            leftPower = power*gain*error*2;
            rightPower = -power*gain*error*2;
            //op.telemetry.addData("TurnIMU", "Angle"+currentAngle+ "Error"+ error+"LP"+leftPower+ "RP"+ rightPower);
            //op.telemetry.update();
            if (leftPower>1) {leftPower=1;}
            if (rightPower>1) {rightPower=1;}
            if (leftPower<-1) {leftPower=-1;}
            if (rightPower<-1) {rightPower=-1;}
            motorLeftBack.setPower(leftPower);
            motorLeftFront.setPower(leftPower);
            motorRightBack.setPower(rightPower);
            motorRightFront.setPower(rightPower);
            op.idle();
        }

        motorLeftBack.setPower(0);
        motorRightFront.setPower(0);
        motorLeftFront.setPower(0);
        motorRightBack.setPower(0);

        /*currentAngle = getAngle();
        op.sleep(1000);
        error = degrees - currentAngle;
        op.telemetry.addData("TurnIMU", "CA"+currentAngle+" FA"+getAngle()+" WA"+ degrees+" P:"+power);
        op.telemetry.update();
        op.sleep(5000);*/
        //op.telemetry.addData("TurnIMU", "Angle"+currentAngle+ "Error"+ error+"LP"+leftPower+ "RP"+ rightPower);
        //op.telemetry.update();
    }
    //will move until it detects blue/red, momentum causse bug


    /******** Left Front Motor **********/
    public void moveMotorLeftFront(double distance){
        double ticksToMove = counts_per_inch * distance;
        double ticksLocationToMove = motorLeftFront.getCurrentPosition() + ticksToMove;
        motorLeftFront.setTargetPosition((int)ticksLocationToMove); //TODO : Check for rounding
        motorLeftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorLeftFront.setPower(0.5);
        while (op.opModeIsActive() && motorLeftFront.isBusy())
        {
            op.telemetry.addData("lifting ", motorLeftFront.getCurrentPosition() + " busy=" + motorLeftFront.isBusy());
            op.telemetry.update();
            op.idle();
        }
        //brake
        motorLeftFront.setPower(0);
        motorLeftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    /******** Right Front Motor **********/
    public void moveMotorRightFront(double distance){
        double ticksToMove = counts_per_inch * distance;
        double ticksLocationToMove = motorRightFront.getCurrentPosition() + ticksToMove;
        motorRightFront.setTargetPosition((int)ticksLocationToMove); //TODO : Check for rounding
        motorRightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorRightFront.setPower(0.5);
        while (op.opModeIsActive() && motorRightFront.isBusy())
        {
            op.telemetry.addData("lifting ", motorRightFront.getCurrentPosition() + " busy=" + motorRightFront.isBusy());
            op.telemetry.update();
            op.idle();
        }
        //brake
        motorRightFront.setPower(0);
        motorRightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    /******** Left Back Motor **********/
    public void moveMotorLeftBack(double distance){
        double ticksToMove = counts_per_inch * distance;
        double ticksLocationToMove = motorLeftBack.getCurrentPosition() + ticksToMove;
        motorLeftBack.setTargetPosition((int)ticksLocationToMove); //TODO : Check for rounding
        motorLeftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorLeftBack.setPower(0.5);
        while (op.opModeIsActive() && motorLeftBack.isBusy())
        {
            op.telemetry.addData("lifting ", motorLeftBack.getCurrentPosition() + " busy=" + motorLeftBack.isBusy());
            op.telemetry.update();
            op.idle();
        }
        //brake
        motorLeftBack.setPower(0);
        motorLeftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    /******** Right Back Motor **********/
    public void moveMotorRightBack(double distance){
        double ticksToMove = counts_per_inch * distance;
        double ticksLocationToMove = motorRightBack.getCurrentPosition() + ticksToMove;
        motorRightBack.setTargetPosition((int)ticksLocationToMove); //TODO : Check for rounding
        motorRightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorRightBack.setPower(0.5);
        while (op.opModeIsActive() && motorRightBack.isBusy())
        {
            op.telemetry.addData("lifting ", motorRightBack.getCurrentPosition() + " busy=" + motorRightBack.isBusy());
            op.telemetry.update();
            op.idle();
        }
        //brake
        motorRightBack.setPower(0);
        motorRightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}