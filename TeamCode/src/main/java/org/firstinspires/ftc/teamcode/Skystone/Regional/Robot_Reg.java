package org.firstinspires.ftc.teamcode.Skystone.Regional;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.Locale;

import static java.lang.Math.abs;

public class Robot_Reg {
    torqueChassisReg drivetrain = new torqueChassisReg();
    myColorSensors_Reg sensor = new myColorSensors_Reg();
    LiftandClaw_Reg liftandClaw = new LiftandClaw_Reg();
    FoundationPuller_Reg puller = new FoundationPuller_Reg();
    CapstoneStick_Reg stick = new CapstoneStick_Reg();
    private ElapsedTime runtime = new ElapsedTime();
    private LinearOpMode op = null;
    private HardwareMap hardwareMap = null;


    public Robot_Reg() {
    }

    public void initChassis(LinearOpMode opMode) {
        op = opMode;
        hardwareMap = op.hardwareMap;
        
        drivetrain.init(opMode);
        puller.init(opMode);
        sensor.init(opMode);
        liftandClaw.init(opMode);
        stick.init(opMode);
    }

    public double getAngle() {
        return drivetrain.getAngle();
    }

    public void moveForwardUntilBlue() {
        double x =0;
        x = op.getRuntime();
        while (sensor.tapeIsBlue() == false) {
            drivetrain.moveForwardTeleop(0.75);
            if(op.getRuntime()-x>=3){
                break;
            }
        }
        drivetrain.stopAllMotors();

    }

    public void moveForwardUntilRed() {
        double x =0;
        x = op.getRuntime();
        while (sensor.tapeIsRed() == false) {
            drivetrain.moveForwardTeleop( 0.75);
            if(op.getRuntime()-x>=3){
                break;
            }
        }
        drivetrain.stopAllMotors();


    }
    public void moveBackwardUntilBlue() {
        double x =0;
        x = op.getRuntime();
        while (sensor.tapeIsBlue() == false) {
            drivetrain.moveBackwardTeleop(0.75 , 0.5);
            if(op.getRuntime()-x>=3){
                break;
            }
        }
        drivetrain.stopAllMotors();

    }

    public void moveBackwardUntilRed() {
        double x =0;
        x = op.getRuntime();
        while (sensor.tapeIsRed() == false) {
            drivetrain.moveBackwardTeleop(0.75, 0.5);
            if(op.getRuntime()-x>=3){
                break;
            }
        }
        drivetrain.stopAllMotors();


    }

    public void moveRightUntilBlue() {
        double x =0;
        x = op.getRuntime();
        while (sensor.tapeIsBlue() == false) {
            drivetrain.moveRightTeleop(0.75, 0.5);
            if(op.getRuntime()-x>=3){
                break;
            }
        }
        drivetrain.stopAllMotors();

    }

    public void moveRightUntilRed() {
        double x =0;
        x = op.getRuntime();
        while (sensor.tapeIsRed() == false) {
            drivetrain.moveRightTeleop(0.75, 0.5);
            if(op.getRuntime()-x>=3){
                break;
            }
        }
        drivetrain.stopAllMotors();


    }

    public void moveLeftUntilBlue() {
        double x =0;
        x = op.getRuntime();
        while (sensor.tapeIsBlue() == false) {
            drivetrain.moveLeftTeleop(0.75, 0.5);
            if(op.getRuntime()-x>=3){
                break;
            }
        }
        drivetrain.stopAllMotors();


    }

    public void moveLeftUntilRed() {
        while (sensor.tapeIsRed() == false) {
            double x =0;
            x = op.getRuntime();
            drivetrain.moveLeftTeleop(0.75, 0.5);
            if(op.getRuntime()-x>=3){
                break;
            }
        }
        drivetrain.stopAllMotors();


    }

    public void stopAllMotors() {
        drivetrain.stopAllMotors();
    }


    public void moveForwardTeleop(double power, double distance) {
        drivetrain.moveForwardTeleop(power, distance);
    }

    public void moveBackwardTeleop(double power, double distance) {
        drivetrain.moveBackwardTeleop(power, distance);
    }

    public void moveRightTeleop(double power, double distance) {
        drivetrain.moveRightTeleop(power, distance);
    }

    public void moveLeftTeleop(double power, double distance) {
        drivetrain.moveLeftTeleop(power, distance);
    }


    //@direction: true = left, false = right
    public void inPlaceTurnTeleop(double degrees, boolean direction, double power) {
        drivetrain.inPlaceTurnTeleop(degrees, direction, power);
    }

    public void inPlaceTurnIMU(double degrees, double power) {
        drivetrain.inPlaceTurnIMU(degrees, power);
    }

    public void AbsoluteTurnIMU(double degrees, double power) {
        drivetrain.AbsoluteTurnIMU(degrees, power);
    }

    //not tested yet
    public void normalTurnTeleop(double degrees, boolean direction, double power) {
        drivetrain.normalTurnTeleop(degrees, direction, power);
    }

    public void fasterMoveRightIMU(double distance, double power) { drivetrain.fastermoveRightIMU(distance, power); }

    public void fasterMoveLeftIMU(double distance, double power) { drivetrain.fastermoveLeftIMU(distance, power); }

    public void moveForwardIMU(double distance, double power) { drivetrain.moveForwardIMU(distance, power); }

    public void moveForward(double distance, double power) { drivetrain.moveForward(distance, power); }

    public void moveBackwardIMU(double distance, double power) { drivetrain.moveBackwardIMU(distance, power); }

    public void moveBackward(double distance, double power) { drivetrain.moveBackward(distance, power); }

    public void moveLeftIMU(double distance, double power) { drivetrain.moveLeftIMU(distance, power); }

    public void moveRight(double distance, double power) {
        drivetrain.moveRight(distance, power);
    }

    public void moveRightIMU(double distance, double power) { drivetrain.moveRightIMU(distance, power); }

    public void moveLeft(double distance, double power) {
        drivetrain.moveLeft(distance, power);
    }

    //@direction: true = left, false = right
    public void inPlaceTurn(double degrees, boolean direction, double power) {
        drivetrain.inPlaceTurn(degrees, direction, power);
    }

    /******** Left Front Motor **********/
    public void moveMotorLeftFront(double distance) {
        drivetrain.moveMotorLeftFront(distance);
    }

    /******** Right Front Motor **********/
    public void moveMotorRightFront(double distance) {
        drivetrain.moveMotorRightFront(distance);
    }

    /******** Left Back Motor **********/
    public void moveMotorLeftBack(double distance) {
        drivetrain.moveMotorLeftBack(distance);
    }

    /******** Right Back Motor **********/
    public void moveMotorRightBack(double distance) {
        drivetrain.moveMotorRightBack(distance);
    }

    public void testIMU() {drivetrain.testIMU();}

    public void stopLift() {
        liftandClaw.stopLift();
    }

    //true = unclamp, false = clamp
    public void clawClamp(boolean direction) {
        liftandClaw.clawClamp(direction);
    }

    //0.0 is clamped, 1.0 is unclamped
    public void clawClampPosition(double claw_position) {
        liftandClaw.clawClampPosition(claw_position);
    }
    //moves foundation sticks down to move the foundation to horizontal position
    public void moveFoundationRightdown(boolean direction) {
        puller.moveFoundationRightdown(direction);
    }
    //moves foundation sticks down to move the foundation to horizontal position
    public void moveFoundationLefttdown(boolean direction) {
        puller.moveFoundationLeftdown(direction);
    }
    public void moveCapstoneStickdownToStone(boolean direction) {
        stick.moveCapstoneStickdownToStone(direction);
    }
    public void moveCapstoneStickToStonePosition(double capstone_stick_position) {
        stick.moveCapstoneStickToStonePosition(capstone_stick_position);
    }
    public void moveCapstoneStickdownToFoundtion(boolean direction) {
        stick.moveCapstoneStickdownToFoundation(direction);
    }
    public void moveCapstoneStickToFoundationPosition(double capstone_stick_position) {
        stick.moveCapstoneStickToFoundationPosition(capstone_stick_position);
    }

    //detects if red or if blue returns true and false
    public boolean tapeIsRed() {
        return sensor.tapeIsRed();
    }

    public boolean tapeIsBlue() {
        return sensor.tapeIsBlue();
    }
    public boolean BlueBlockIsSky (){
        float hsvValues[] = {0F, 0F, 0F};
        float hsvValues2[] = {0F, 0F, 0F};
        boolean altitude = true;
        final double SCALE_FACTOR = 255;
        double distance=0;
        double a =20;
        double b = 0;
        Color.RGBToHSV((int) (sensor.block_color_sensor.red() * SCALE_FACTOR),
                (int) (sensor.block_color_sensor.green() * SCALE_FACTOR),
                (int) (sensor.block_color_sensor.blue() * SCALE_FACTOR),
                hsvValues2);
        Color.RGBToHSV((int) (sensor.block_color_sensor.red() * SCALE_FACTOR),
                (int) (sensor.block_color_sensor.green() * SCALE_FACTOR),
                (int) (sensor.block_color_sensor.blue() * SCALE_FACTOR),
                hsvValues2);
        Color.RGBToHSV((int) (sensor.block_color_sensor.red() * SCALE_FACTOR),
                (int) (sensor.block_color_sensor.green() * SCALE_FACTOR),
                (int) (sensor.block_color_sensor.blue() * SCALE_FACTOR),
                hsvValues2);
        while(a>5) {
            hsvValues = hsvValues2;
            Color.RGBToHSV((int) (sensor.block_color_sensor.red() * SCALE_FACTOR),
                    (int) (sensor.block_color_sensor.green() * SCALE_FACTOR),
                    (int) (sensor.block_color_sensor.blue() * SCALE_FACTOR),
                    hsvValues2);
            a = abs(hsvValues[0] - hsvValues2[0]);
            op.telemetry.addData("diffrence", a);
            op.telemetry.addData("Hue1", hsvValues[0]);
            op.telemetry.addData("Hue2", hsvValues2[0]);
            op.telemetry.update();
        }
        op.telemetry.addData("Alpha", sensor.block_color_sensor.alpha());
        op.telemetry.addData("Red  ", sensor.block_color_sensor.red());
        op.telemetry.addData("Green", sensor.block_color_sensor.green());
        op.telemetry.addData("Blue ", sensor.block_color_sensor.blue());
        op.telemetry.addLine()
                .addData("H", "%.3f", hsvValues2[0])
                .addData("S", "%.3f", hsvValues2[1])
                .addData("V", "%.3f", hsvValues2[2])
                .addData ("Distance (cm)",
                        String.format(Locale.US, "%.02f", sensor.block_distance_sensor.getDistance(DistanceUnit.CM)));
        op.telemetry.update();
        distance = sensor.block_distance_sensor.getDistance(DistanceUnit.CM);
        /*while(distance>4.0);{
            distance =sensor.block_distance_sensor.getDistance(DistanceUnit.CM);
            drivetrain.moveForwardTeleop(0.25);
        }
        drivetrain.stopAllMotors();*/
        if(hsvValues2[0]>70&&hsvValues2[0]<=100){
            altitude=false;
            drivetrain.moveLeftIMU(7.5,0.5);
            drivetrain.AbsoluteTurnIMU(0,1.0);
        }
        return altitude;
    }
    public boolean RedBlockIsSky (){
        float hsvValues[] = {0F, 0F, 0F};
        float hsvValues2[] = {0F, 0F, 0F};
        boolean altitude = true;
        final double SCALE_FACTOR = 255;
        double distance=0;
        double a =20;
        double b = 0;
        Color.RGBToHSV((int) (sensor.block_color_sensor.red() * SCALE_FACTOR),
                (int) (sensor.block_color_sensor.green() * SCALE_FACTOR),
                (int) (sensor.block_color_sensor.blue() * SCALE_FACTOR),
                hsvValues2);
        Color.RGBToHSV((int) (sensor.block_color_sensor.red() * SCALE_FACTOR),
                (int) (sensor.block_color_sensor.green() * SCALE_FACTOR),
                (int) (sensor.block_color_sensor.blue() * SCALE_FACTOR),
                hsvValues2);
        Color.RGBToHSV((int) (sensor.block_color_sensor.red() * SCALE_FACTOR),
                (int) (sensor.block_color_sensor.green() * SCALE_FACTOR),
                (int) (sensor.block_color_sensor.blue() * SCALE_FACTOR),
                hsvValues2);
        while(a>5) {
            hsvValues = hsvValues2;
            Color.RGBToHSV((int) (sensor.block_color_sensor.red() * SCALE_FACTOR),
                    (int) (sensor.block_color_sensor.green() * SCALE_FACTOR),
                    (int) (sensor.block_color_sensor.blue() * SCALE_FACTOR),
                    hsvValues2);
            a = abs(hsvValues[0] - hsvValues2[0]);
            op.telemetry.addData("diffrence", a);
            op.telemetry.addData("Hue1", hsvValues[0]);
            op.telemetry.addData("Hue2", hsvValues2[0]);
            op.telemetry.update();
        }
        op.telemetry.addData("Alpha", sensor.block_color_sensor.alpha());
        op.telemetry.addData("Red  ", sensor.block_color_sensor.red());
        op.telemetry.addData("Green", sensor.block_color_sensor.green());
        op.telemetry.addData("Blue ", sensor.block_color_sensor.blue());
        op.telemetry.addLine()
                .addData("H", "%.3f", hsvValues2[0])
                .addData("S", "%.3f", hsvValues2[1])
                .addData("V", "%.3f", hsvValues2[2])
                .addData ("Distance (cm)",
                        String.format(Locale.US, "%.02f", sensor.block_distance_sensor.getDistance(DistanceUnit.CM)));
        op.telemetry.update();
        distance = sensor.block_distance_sensor.getDistance(DistanceUnit.CM);
        /*while(distance>4.0);{
            distance =sensor.block_distance_sensor.getDistance(DistanceUnit.CM);
            drivetrain.moveForwardTeleop(0.25);
        }
        drivetrain.stopAllMotors();*/
        if(hsvValues2[0]>70&&hsvValues2[0]<=105){
            altitude=false;
            drivetrain.moveRightIMU(7.5,0.5);
            drivetrain.AbsoluteTurnIMU(0,1.0);
        }
        return altitude;
    }
    public void ColorTestTape() {
        sensor.ColorTestTape();
    }
    public void ColorTestBlock() {
        sensor.ColorTestBlock();
    }


    /******** Lifting Motor **********/
    public void liftAutonomous(double liftheight) {
        liftandClaw.liftAutonomous(liftheight);
    }

    public void liftPosition(double liftposition, Gamepad gp) {
        liftandClaw.liftPosition(liftposition, gp);
    }

    public void liftPosition(double liftposition) {
        liftandClaw.liftPosition(liftposition);
    }



    /**
     * <h1> SkyStone autonomous program</h1>
     * <p>
     * Giving proper comments in your program makes it more
     * user friendly and it is assumed as a high quality code.
     *
     * @author Aamod
     * @version 1.0
     * @since 2019-Dec-2
     */
    /*
    @Disabled //TODO why is this here?
    @Autonomous(name = "BLPcS1_17or25")
    public static class BLFPcS1_17or25 extends LinearOpMode {
        RobotA robot = new RobotA();
        private ElapsedTime runtime = new ElapsedTime();
        private Servo stone_claw_servo;


        public BLFPcS1_17or25() {
        }

        @Override
        public void runOpMode() {
            telemetry.addData("Status", "Ready to go");
            telemetry.update();
            robot.initChassis(this);
            telemetry.addData("Status", "InitComplete, Ready to Start");
            telemetry.update();
            waitForStart();

            robot.moveForward(14,1); // goes to collect stone
            robot.clawClampPosition(0); // grabs block
            robot.moveBackward(8, 1); // backs up
            robot.liftAutonomous(1.2); // lifts up, so stone doesn't drag against ground
            robot.inPlaceTurn(90, true, 1); // left=true, right=false
            robot.moveForward(60,1); // robot heads towards foundation
            robot.liftAutonomous(5); // robot lifts stone
            robot.inPlaceTurn(90,false,1); // robot is in front of foundation
            robot.moveForward(7,1); // robot is inches away from foundation
            robot.moveForward(3, 0.35); // robot is touching foundation
            robot.liftAutonomous(4); // robot lowers stone
            robot.clawClampPosition(1); // robot releases stone
            robot.moveBackward(5,1); // backs up from foundation
            robot.moveLeft(5,1); // movse towards foundation's edge
            robot.moveForward(2,0.35); // robot touches foundation
            robot.clawClampPosition(0); // robot clamps onto foundation
            robot.moveBackward(8,0.75); // robot pulls foundation back
            robot.inPlaceTurn(100, true, 1); // turns foundation
            robot.clawClampPosition(1); // robot unclamps foundation
            robot.moveBackward(15,1); // robot goes towards parking
            robot.moveRight(4,1); // tape should be behind robots
            robot.moveBackward(5,1); // robot goes on top of tape
            robot.clawClampPosition(0); // just in case

            robot.stopAllMotors();
            robot.stopLift();
        }*/

    }
