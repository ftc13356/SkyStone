package org.firstinspires.ftc.teamcode.Skystone.Qualifier_3;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class RobotA {
    torqueChassis drivetrain = new torqueChassis();
    Accesories accesories = new Accesories();
    private ElapsedTime runtime = new ElapsedTime();
    private LinearOpMode op = null;
    private HardwareMap hardwareMap = null;


    public RobotA() {
    }

    public void initChassis(LinearOpMode opMode) {
        op = opMode;
        hardwareMap = op.hardwareMap;
        drivetrain.initChassis(opMode);
        accesories.initChassis(opMode);
    }

    public void moveForwardUntilBlue() {
        while (accesories.tapeIsBlue() == false) {
            drivetrain.motorLeftBack.setPower(1.0);
            drivetrain.motorRightBack.setPower(1.0);
            drivetrain.motorLeftFront.setPower(1.0);
            drivetrain.motorRightFront.setPower(1.0);
        }

    }

    public void moveForwardUntilRed() {
        while (accesories.tapeIsRed() == false) {
            drivetrain.motorLeftBack.setPower(1.0);
            drivetrain.motorRightBack.setPower(1.0);
            drivetrain.motorLeftFront.setPower(1.0);
            drivetrain.motorRightFront.setPower(1.0);
        }

    }

    public void moveRightUntilBlue() {
        while (accesories.tapeIsBlue() == false) {
            drivetrain.motorLeftBack.setPower(1.0);
            drivetrain.motorRightBack.setPower(-1.0);
            drivetrain.motorLeftFront.setPower(-1.0);
            drivetrain.motorRightFront.setPower(1.0);
        }

    }

    public void moveRightUntilRed() {
        while (accesories.tapeIsRed() == false) {
            drivetrain.motorLeftBack.setPower(1.0);
            drivetrain.motorRightBack.setPower(-1.0);
            drivetrain.motorLeftFront.setPower(-1.0);
            drivetrain.motorRightFront.setPower(1.0);
        }

    }

    public void moveLeftUntilBlue() {
        while (accesories.tapeIsBlue() == false) {
            drivetrain.motorLeftBack.setPower(-1.0); //TODO Is this correct? Only one motor is neagative
            drivetrain.motorRightBack.setPower(1.0);
            drivetrain.motorLeftFront.setPower(1.0);
            drivetrain.motorRightFront.setPower(1.0);
        }

    }

    public void moveLeftUntilRed() {
        while (accesories.tapeIsRed() == false) {
            drivetrain.motorLeftBack.setPower(-1.0); //TODO Is this correct? Only one motor is neagative
            drivetrain.motorRightBack.setPower(1.0);
            drivetrain.motorLeftFront.setPower(1.0);
            drivetrain.motorRightFront.setPower(1.0);
        }

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

    public void moveForward(double distance, double power) {
        drivetrain.moveForward(distance, power);
    }

    public void moveBackward(double distance, double power) {
        drivetrain.moveBackward(distance, power);
    }

    public void moveRight(double distance, double power) {
        drivetrain.moveRight(distance, power);
    }

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

    public void stopLift() {
        accesories.stopLift();
    }

    //true = unclamp, false = clamp
    public void clawClamp(boolean direction) {
        accesories.clawClamp(direction);
    }

    //0.0 is clamped, 1.0 is unclamped
    public void clawClampPosition(double claw_position) {
        accesories.clawClampPosition(claw_position);
    }

    //detects if red or if blue returns true and false
    public boolean tapeIsRed() {
        return accesories.tapeIsRed();
    }

    public boolean tapeIsBlue() {
        return accesories.tapeIsBlue();
    }

    public boolean blockIsYellow() {
        return accesories.blockIsYellow();
    }

    public boolean blockIsSky() {
        return accesories.blockIsSky();
    }

    /******** Lifting Motor **********/
    public void liftAutonomous(double liftheight) {
        accesories.liftAutonomous(liftheight);
    }

    public void liftPosition(double liftposition) {
        accesories.liftPosition(liftposition);
    }

    public void liftTeleop(boolean up) {
        accesories.liftTeleop(up);
    }

    public void liftTeleopPower(float power) {
        accesories.liftTeleopPower(power);
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
        }
    }
}