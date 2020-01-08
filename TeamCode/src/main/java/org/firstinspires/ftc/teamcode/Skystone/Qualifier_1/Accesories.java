package org.firstinspires.ftc.teamcode.Skystone.Qualifier_1;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

//public class Accesories extends hexChassis_Teleop{
public class Accesories {
    private LinearOpMode op = null;
    private HardwareMap hardwareMap = null;
    private ElapsedTime period = new ElapsedTime();
    DcMotorEx motorLift;
    Servo stone_claw_servo;
    ColorSensor tape_color_sensor;
    ColorSensor block_color_sensor;
    DistanceSensor block_distance_sensor;
    //variables for lifting mechanism
    double counts_per_motor_tetrix = 0;
    double counts_per_inch_lift = 0;

    public Accesories() {

        /******* Lift motor ********/
        counts_per_motor_tetrix = 1440; //TODO
        //counts_per_inch
        counts_per_inch_lift = 101; //550 for Tetrix and 100 for Hex
    }

    public void initChassis(LinearOpMode opMode) {
        op = opMode;
        hardwareMap = op.hardwareMap;
        // Lifting motors
        motorLift = (DcMotorEx) hardwareMap.dcMotor.get("motorLift");
        // Claw Servo
        stone_claw_servo = hardwareMap.servo.get("stone_claw_servo");
        // Color Sensors
        block_color_sensor = hardwareMap.get(ColorSensor.class, "C1");
        block_distance_sensor = hardwareMap.get(DistanceSensor.class,"C1");
        // Lifting Motors
        motorLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        motorLift.setDirection(DcMotor.Direction.FORWARD);
        motorLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        stone_claw_servo.setPosition(1.0);
    }

    public void stopLift() {
        motorLift.setPower(0);
    }

    //true = unclamp, false = clamp
    public void clawClamp(boolean direction) {
        if (direction == true) {
            stone_claw_servo.setPosition(1.0);
        } else {
            stone_claw_servo.setPosition(0.0);
        }
        op.sleep(200);
    }

    //0.0 is clamped, 1.0 is unclamped
    public void clawClampPosition(double claw_position) {
        op.telemetry.addData("claw position :", claw_position);
        op.telemetry.update();
        stone_claw_servo.setPosition(claw_position);
        op.sleep(50);
    }

    //detects if red or if blue returns true and false
    public boolean tapeIsRed() {
        boolean redded = false;
        float hsvValues[] = {0F, 0F, 0F};
        final double SCALE_FACTOR = 255;
        // Color.RGBToHSV((tape_color_sensor.red()), (tape_color_sensor.green()), (tape_color_sensor.blue()), hsvValues);

        Color.RGBToHSV((int) (block_color_sensor.red() * SCALE_FACTOR),
                (int) (block_color_sensor.green() * SCALE_FACTOR),
                (int) (block_color_sensor.blue() * SCALE_FACTOR),
                hsvValues);
        Color.RGBToHSV((int) (block_color_sensor.red() * SCALE_FACTOR),
                (int) (block_color_sensor.green() * SCALE_FACTOR),
                (int) (block_color_sensor.blue() * SCALE_FACTOR),
                hsvValues);
        if (hsvValues[0] >= 10 || hsvValues[0] <= 30) {
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
        Color.RGBToHSV((int) (block_color_sensor.red() * SCALE_FACTOR),
                (int) (block_color_sensor.green() * SCALE_FACTOR),
                (int) (block_color_sensor.blue() * SCALE_FACTOR),
                hsvValues);
        Color.RGBToHSV((int) (block_color_sensor.red() * SCALE_FACTOR),
                (int) (block_color_sensor.green() * SCALE_FACTOR),
                (int) (block_color_sensor.blue() * SCALE_FACTOR),
                hsvValues);
        op.telemetry.addLine()
                .addData("H", "%.3f", hsvValues[0])
                .addData("S", "%.3f", hsvValues[1])
                .addData("V", "%.3f", hsvValues[2]);
        op.telemetry.update();
        op.sleep(200);
        if (hsvValues[0] >= 160 && hsvValues[0] <= 205) {
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

    public boolean blockIsYellow() {
        boolean chinese = false; //TODO CHINESE
                float hsvValues[] = {0F, 0F, 0F};
                final float values[] = hsvValues;
                final double SCALE_FACTOR = 255;

                    Color.RGBToHSV((int) (block_color_sensor.red() * SCALE_FACTOR),
                            (int) (block_color_sensor.green() * SCALE_FACTOR),
                            (int) (block_color_sensor.blue() * SCALE_FACTOR),
                            hsvValues);
                    op.telemetry.addData("Alpha", block_color_sensor.alpha());
                    op.telemetry.addData("Red  ", block_color_sensor.red());
                    op.telemetry.addData("Green", block_color_sensor.green());
                    op.telemetry.addData("Blue ", block_color_sensor.blue());
        op.telemetry.addLine()
                .addData("H", "%.3f", hsvValues[0])
                .addData("S", "%.3f", hsvValues[1])
                .addData("V", "%.3f", hsvValues[2]);
        op.telemetry.update();

        op.sleep(200);
        if (hsvValues[0] >= 20 && hsvValues[0] <= 70 && hsvValues[2] >= 25) {
            op.telemetry.addData("ColorSensorStatus", "Yellow");
            chinese = true;
        } else {
            op.telemetry.addData("ColorSensorStatus", "Unknown");
            chinese = false;
        }
        return chinese;

    }

    public boolean blockIsSky() {
        boolean black = false;
        float hsvValues[] = {0F, 0F, 0F};
        final double SCALE_FACTOR = 255;
        Color.RGBToHSV((int) (block_color_sensor.red() * SCALE_FACTOR),
                (int) (block_color_sensor.green() * SCALE_FACTOR),
                (int) (block_color_sensor.blue() * SCALE_FACTOR),
                hsvValues);
        op.telemetry.addLine()
                .addData("H", "%.3f", hsvValues[0])
                .addData("S", "%.3f", hsvValues[1])
                .addData("V", "%.3f", hsvValues[2]);
        op.telemetry.update();
        op.sleep(200);
        if (hsvValues[1] <= 0.5) {
            op.telemetry.addData("ColorSensorStatus", "Black");
            black = true;
        } else {
            op.telemetry.addData("ColorSensorStatus", "Unknown");
            black = false;
        }
        op.telemetry.addLine()
                .addData("H", "%.3f", hsvValues[0])
                .addData("S", "%.3f", hsvValues[1])
                .addData("V", "%.3f", hsvValues[2]);
        op.telemetry.update();
        return black;

    }

    /******** Lifting Motor **********/
    public void liftAutonomous(double liftheight) {
        double ticksToMove = counts_per_inch_lift * liftheight;
        int newmotorLift = (int) (motorLift.getCurrentPosition() + ticksToMove + 0.5); //adds .5 for rounding
        //TODO: Check limits for safety
        motorLift.setTargetPosition(newmotorLift);
        motorLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorLift.setPower(1.0);
        op.sleep(100);
        while (op.opModeIsActive() && motorLift.isBusy() && motorLift.getVelocity() !=0 ) {
            op.telemetry.addData("Lifting ", motorLift.getCurrentPosition() + " velocity=" + motorLift.getVelocity() + " busy=" + motorLift.isBusy());
            op.telemetry.update();
            op.idle();
        }
        //brake
        motorLift.setPower(0);
        motorLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void liftPosition(double liftposition) {
        int ticksPosition = (int) (counts_per_inch_lift * liftposition + 0.5); //adds .5 for rounding
        motorLift.setTargetPosition(ticksPosition);
        motorLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorLift.setPower(1.0);
        op.sleep(100);
        //while (op.opModeIsActive() && motorLift.isBusy() && motorLift.getVelocity() !=0 && gamepad2.left_trigger<0.1 || gamepad2.right_trigger<0.1) {
        while (op.opModeIsActive() && motorLift.isBusy() && motorLift.getVelocity() !=0 ) {
            op.telemetry.addData("Lifting ", motorLift.getCurrentPosition() + " velocity=" + motorLift.getVelocity() + " busy=" + motorLift.isBusy());
            op.telemetry.update();
            op.idle();
        }
        //brake
        motorLift.setPower(0);
        motorLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void liftTeleop(boolean up) { //true for up and false for down
        motorLift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        if (up) {
            motorLift.setPower(1.0);
        } else {
            motorLift.setPower(-1.0);
        }
    }

    public void liftTeleopPower(float liftpower) {
        motorLift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorLift.setPower(liftpower);
    }
}
