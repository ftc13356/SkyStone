package org.firstinspires.ftc.teamcode.Skystone;
import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class hexChassis {
    //initialize motor
    DcMotor motorLeftFront;
    DcMotor motorRightFront;
    DcMotor motorLeftBack;
    DcMotor motorRightBack;
    DcMotor motorLift;
    Servo stone_claw_servo;
    ColorSensor tape_color_sensor;

    // these encoder variables vary depending on chassis type
    double counts_per_motor_rev = 0;
    double counts_per_inch = 0;
    double counts_per_degree = 0;
    double liftheight = 0;

    //variables for lifting mechanism
    double counts_per_motor_tetrix = 0;
    double counts_per_inch_tetrix_lift = 0;

    // Initialize Encoder Variables
    final double robot_diameter = 10.5;
    final double drive_gear_reduction = 1.0;
    final double wheel_diameter = 4.0;

    // Motor Speed for various Operations
    final double motor_speed_fb = 0.5; //forward & backward
    final double motor_speed_side = 0.5;
    final double motor_speed_turn = 0.2;

    /* local OpMode members. */
    private LinearOpMode op              = null;
    private HardwareMap  hardwareMap     = null;
    private ElapsedTime  period          = new ElapsedTime();

    private float speed = 37.5f;

    public hexChassis() {
        /******* hex motors ******/
        counts_per_motor_rev = 288;
        //counts_per_inch = (counts_per_motor_rev / (wheel_diameter * Math.PI));
        counts_per_inch = 288 / (4 * Math.PI);
        //counts_per_inch = 23 ticks

        // 23 * 14 * 3.14 / 360 = 2.8 ticks
        counts_per_degree = counts_per_inch * robot_diameter * Math.PI / 360;

        /******* tetrix motor ********/
        counts_per_motor_tetrix = 1440; //TODO
        //counts_per_inch
        counts_per_inch_tetrix_lift = 550; //TODO
    }

    public void initChassis(LinearOpMode opMode) {

        op = opMode;
        hardwareMap = op.hardwareMap;

        // Chassis motors
        motorLeftFront = hardwareMap.dcMotor.get("motorLeftFront");
        motorRightFront = hardwareMap.dcMotor.get("motorRightFront");
        motorLeftBack = hardwareMap.dcMotor.get("motorLeftBack");
        motorRightBack = hardwareMap.dcMotor.get("motorRightBack");
        // Lifting motors
        motorLift = hardwareMap.dcMotor.get("motorLift");
        // Claw Servo
        stone_claw_servo = hardwareMap.servo.get("stone_claw_servo");
        // Color Sensor
//        tape_color_sensor = hardwareMap.colorSensor.get("C1");

        // Chassis Motors
        motorLeftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorLeftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorRightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorRightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        motorLeftFront.setDirection(DcMotor.Direction.REVERSE);
        motorRightFront.setDirection(DcMotor.Direction.REVERSE);
        motorLeftBack.setDirection(DcMotor.Direction.REVERSE);
        motorRightBack.setDirection(DcMotor.Direction.REVERSE);
        // reset encoder count kept by left motor.
        motorLeftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorLeftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorRightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorRightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Lifting Motors
        motorLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        motorLift.setDirection(DcMotor.Direction.FORWARD);
        motorLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //initialize claw
        clawClamp(true);
    }

    public void moveForwardTeleop(double distance) {
        // Changes motor mode back to default
        motorLeftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorLeftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        motorLeftBack.setPower(0.5);
        motorRightBack.setPower(0.5);
        motorLeftFront.setPower(0.5);
        motorRightFront.setPower(0.5);

    }

    public void moveBackwardTeleop(double distance) {
        // Changes motor mode back to default
        motorLeftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorLeftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        motorLeftBack.setPower(-0.5);
        motorRightBack.setPower(-0.5);
        motorLeftFront.setPower(-0.5);
        motorRightFront.setPower(-0.5);

    }

    public void moveRightTeleop(double distance) {
        // Changes motor mode back to default
        motorLeftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorLeftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        motorLeftBack.setPower(0.5);
        motorRightBack.setPower(-0.5);
        motorLeftFront.setPower(-0.5);
        motorRightFront.setPower(0.5);

    }

    public void moveLeftTeleop(double distance) {
        // Changes motor mode back to default
        motorLeftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorLeftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        motorLeftBack.setPower(-0.5);
        motorRightBack.setPower(0.5);
        motorLeftFront.setPower(0.5);
        motorRightFront.setPower(-0.5);

    }

    //@direction: true = left, false = right
    public void inPlaceTurnTeleop(double degrees, boolean direction) {
        // Changes motor mode back to default
        motorLeftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorLeftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        if (direction == true) {
            motorLeftBack.setPower(-0.8);
            motorRightBack.setPower(-0.8);
            motorLeftFront.setPower(0.8);
            motorRightFront.setPower(0.8);
        } else {
            motorLeftBack.setPower(0.8);
            motorRightBack.setPower(0.8);
            motorLeftFront.setPower(-0.8);
            motorRightFront.setPower(-0.8);
        }
    }

    public void moveForward(double distance) {
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

        motorRightFront.setPower(motor_speed_fb);
        motorLeftFront.setPower(motor_speed_fb);
        motorRightBack.setPower(motor_speed_fb);
        motorLeftBack.setPower(motor_speed_fb);

        while (op.opModeIsActive() && (motorLeftBack.isBusy() || motorLeftFront.isBusy() || motorRightBack.isBusy() ||
                motorRightFront.isBusy()))
        {
            op.telemetry.addData("encoder-fwd", motorLeftBack.getCurrentPosition() + "  busy=" + motorLeftBack.isBusy());
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

    public void moveBackward(double distance) {
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

        motorLeftBack.setPower(motor_speed_fb);
        motorRightBack.setPower(motor_speed_fb);
        motorLeftFront.setPower(motor_speed_fb);
        motorRightFront.setPower(motor_speed_fb);

        while (op.opModeIsActive() && (motorLeftBack.isBusy() || motorLeftFront.isBusy() || motorRightBack.isBusy() ||
                motorRightFront.isBusy()))
        {
//            op.telemetry.addData("encoder-fwd", motorLeftBack.getCurrentPosition() + "  busy=" + motorLeftBack.isBusy());
//            op.telemetry.update();
//            op.idle();
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

    public void moveRight(double distance) {
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
        op.sleep(5000);

        motorLeftBack.setPower(motor_speed_side);
        motorRightBack.setPower(motor_speed_side);
        motorLeftFront.setPower(motor_speed_side);
        motorRightFront.setPower(motor_speed_side);

        while (op.opModeIsActive() && (motorLeftBack.isBusy() || motorLeftFront.isBusy() || motorRightBack.isBusy() ||
                motorRightFront.isBusy()))
        {
    //        op.telemetry.addData("encoder-fwd", motorLeftBack.getCurrentPosition() + "  busy=" + motorLeftBack.isBusy());
    //        op.telemetry.update();
    //        op.idle();
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

    public void moveLeft(double distance) {
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
        op.sleep(5000);

        motorRightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorRightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorLeftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorLeftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motorLeftBack.setPower(motor_speed_side);
        motorRightBack.setPower(motor_speed_side);
        motorLeftFront.setPower(motor_speed_side);
        motorRightFront.setPower(motor_speed_side);

        while (op.opModeIsActive() && (motorLeftBack.isBusy() || motorLeftFront.isBusy() || motorRightBack.isBusy() ||
                motorRightFront.isBusy()))
        {
//            op.telemetry.addData("encoder-fwd", motorLeftBack.getCurrentPosition() + "  busy=" + motorLeftBack.isBusy());
//            op.telemetry.update();
//            op.idle();
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
    public void inPlaceTurn(double degrees, boolean direction) {

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

        motorRightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorRightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorLeftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorLeftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motorLeftBack.setPower(motor_speed_turn);
        motorRightBack.setPower(motor_speed_turn);
        motorLeftFront.setPower(motor_speed_turn);
        motorRightFront.setPower(motor_speed_turn);

        while (op.opModeIsActive() && (motorLeftBack.isBusy() || motorLeftFront.isBusy() || motorRightBack.isBusy() ||
                motorRightFront.isBusy()))
        {
            op.telemetry.addData("encoder-fwd", motorLeftBack.getCurrentPosition() + "  busy=" + motorLeftBack.isBusy());
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
    //degrees means the angle of turning
    public void normalTurn(double degrees, boolean direction) {

        double ticksToTurn = (degrees * counts_per_degree);
        double newLeftBackTargetPosition;
        double newLeftFrontTargetPosition;
        double newRightBackTargetPosition;
        double newRightFrontTargetPosition;

        //turn left = left wheel don't move; vice versa
        if (direction == true) {
            newLeftBackTargetPosition = motorLeftBack.getCurrentPosition();
            newLeftFrontTargetPosition = motorLeftFront.getCurrentPosition();
            newRightBackTargetPosition = motorRightBack.getCurrentPosition() + ticksToTurn;
            newRightFrontTargetPosition = motorRightFront.getCurrentPosition() + ticksToTurn;
        } else {
            newLeftBackTargetPosition = motorLeftBack.getCurrentPosition() + ticksToTurn;
            newLeftFrontTargetPosition = motorLeftFront.getCurrentPosition() + ticksToTurn;
            newRightBackTargetPosition = motorRightBack.getCurrentPosition();
            newRightFrontTargetPosition = motorRightFront.getCurrentPosition();
        }
        motorLeftBack.setTargetPosition((int) newLeftBackTargetPosition);
        motorLeftFront.setTargetPosition((int) newLeftFrontTargetPosition);
        motorRightBack.setTargetPosition((int) newRightBackTargetPosition);
        motorRightFront.setTargetPosition((int) newRightFrontTargetPosition);

        motorRightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorRightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorLeftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorLeftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motorLeftBack.setPower(0.5);
        motorRightBack.setPower(0.5);
        motorLeftFront.setPower(0.5);
        motorRightFront.setPower(0.5);

        while (op.opModeIsActive() && motorLeftBack.isBusy()) {
            op.telemetry.addData("encoder-fwd", motorLeftBack.getCurrentPosition() + "  busy=" + motorLeftBack.isBusy());
            op.telemetry.update();
            op.idle();
        }
        motorLeftBack.setPower(0);
        motorRightBack.setPower(0);
        motorRightFront.setPower(0);
        motorLeftFront.setPower(0);
    }

    //true = unclamp, false = clamp
    public void clawClamp(boolean direction) {
        if (direction == true) {
            stone_claw_servo.setPosition(1.0);
        } else {
            stone_claw_servo.setPosition(0.0);
        }
    }
    //0.0 is clamped, 1.0 is unclamped
    public void clawClampPosition(double claw_position) {
        op.telemetry.addData("claw position :", claw_position);
        op.telemetry.update();
        stone_claw_servo.setPosition(claw_position);
        op.sleep(100);
    }

    //detects if red or if blue returns true and false
    public boolean tapeIsRed() {
        boolean redded= false;
        float hsvValues[] = {0F, 0F, 0F};
        final double SCALE_FACTOR = 255;
        // Color.RGBToHSV((tape_color_sensor.red()), (tape_color_sensor.green()), (tape_color_sensor.blue()), hsvValues);

        Color.RGBToHSV((int) (tape_color_sensor.red() * SCALE_FACTOR),
                (int) (tape_color_sensor.green() * SCALE_FACTOR),
                (int) (tape_color_sensor.blue() * SCALE_FACTOR),
                hsvValues);
        if (hsvValues[0] >= 340 || hsvValues[0] <= 20) {
            redded = true;
            op.telemetry.addData("ColorSensorStatus", "Red");

        } else {
            op.telemetry.addData("ColorSensorStatus", "Unknown");
            redded = false;
        }
        op.telemetry.addLine()
                .addData("H", "%.3f", hsvValues[0])
                .addData("S", "%.3f", hsvValues[1])
                .addData("V", "%.3f", hsvValues[2]);
        op.telemetry.update();
        return redded;
    }

    public boolean tapeIsBlue() {
        boolean blued = false;
        float hsvValues[] = {0F, 0F, 0F};
        final double SCALE_FACTOR = 255;
        Color.RGBToHSV((int) (tape_color_sensor.red() * SCALE_FACTOR),
                (int) (tape_color_sensor.green() * SCALE_FACTOR),
                (int) (tape_color_sensor.blue() * SCALE_FACTOR),
                hsvValues);
        op.telemetry.addLine()
                .addData("H", "%.3f", hsvValues[0])
                .addData("S", "%.3f", hsvValues[1])
                .addData("V", "%.3f", hsvValues[2]);
        op.telemetry.update();
        op.sleep(1000);
        if (hsvValues[0] >= 200 && hsvValues[0] <= 275) {
            op.telemetry.addData("ColorSensorStatus", "Blue");
            blued = true;
        } else {
            op.telemetry.addData("ColorSensorStatus", "Unknown");
            blued = false;
        }
        op.telemetry.addLine()
                .addData("H", "%.3f", hsvValues[0])
                .addData("S", "%.3f", hsvValues[1])
                .addData("V", "%.3f", hsvValues[2]);
        op.telemetry.update();
        return blued;
    }
    //will move until it detects blue/red, momentum causse bug
    public void moveForwardUntilBlue(){
        motorLeftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorLeftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while(tapeIsBlue()== false){

            motorLeftBack.setPower(0.25);
            motorRightBack.setPower(0.25);
            motorLeftFront.setPower(0.25);
            motorRightFront.setPower(0.25);
        }
    }
    public void moveForwardUntilRed(){
        motorLeftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorLeftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        while(tapeIsRed()== false){
            motorLeftBack.setPower(0.25);
            motorRightBack.setPower(0.25);
            motorLeftFront.setPower(0.25);
            motorRightFront.setPower(0.25);
        }
    }

    /******** Lifting Motor **********/
    public void liftAutonomous(double liftheight){
        double ticksToMove = counts_per_inch_tetrix_lift * liftheight;
        double newmotorLift = motorLift.getCurrentPosition() + ticksToMove;
        motorLift.setTargetPosition((int)newmotorLift); //TODO : Check for rounding
        motorLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorLift.setPower(0.5);
        while (op.opModeIsActive() && motorLift.isBusy())
        {
            op.telemetry.addData("lifting ", motorLift.getCurrentPosition() + " busy=" + motorLift.isBusy());
            op.telemetry.update();
            op.idle();
        }
        //brake
        motorLift.setPower(0);
        motorLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

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