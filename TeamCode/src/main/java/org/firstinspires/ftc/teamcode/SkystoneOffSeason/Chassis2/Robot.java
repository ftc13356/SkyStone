package org.firstinspires.ftc.teamcode.SkystoneOffSeason.Chassis2;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

public class Robot {
    private LinearOpMode op = null;
    private HardwareMap hardwareMap = null;
    private ElapsedTime runtime = new ElapsedTime();
    Chassis2 drivetrain = new Chassis2();

    private AamodVuforiaWebcam vuforiaWebcam = null;
    private double vuforiaX = 0;
    private double vuforiaY = 0;
    private double vuforiaAngle = 0;
    private double robotAngle = 0;

    public Robot() {
    }

    public void initChassis(LinearOpMode opMode) {
        op = opMode;
        hardwareMap = op.hardwareMap;

        vuforiaWebcam = new AamodVuforiaWebcam(op, VuforiaLocalizer.CameraDirection.BACK);

        drivetrain.init(opMode);
        vuforiaWebcam.init(opMode);

        vuforiaWebcam.start();

        getVuforiaPosition();
        op.telemetry.addData("Position","%.2f %.2f %.2f %.2f", vuforiaX, vuforiaY, vuforiaAngle, robotAngle);
        op.telemetry.update();
        op.sleep(1000);
    }

    public void moveVuforiaWebcam(double x, double y, double endAngle) {
        getVuforiaPosition();

        double xdifference = x - vuforiaX;
        double ydifference = y - vuforiaY;

        double turn = endAngle - robotAngle;

        double magnitude = Math.sqrt((xdifference * xdifference) + (ydifference * ydifference));

        double mAngle = robotAngle - Math.toDegrees(Math.acos(ydifference/magnitude)); //move Angle

        op.telemetry.addData("VuforiaX","%.2f %.2f %.2f %.3f %.3f %.3f", vuforiaX, x, xdifference, robotAngle, vuforiaAngle, endAngle );
        op.telemetry.addData("VuforiaY","%.2f %.2f %.2f %.2f %.3f %.3f", vuforiaY, y, ydifference, magnitude, turn, mAngle);
        op.telemetry.update();
        op.sleep(5000);
        drivetrain.moveAngle2(magnitude, mAngle, turn);

        getVuforiaPosition();

        op.telemetry.addData("VuforiaX","%.2f %.2f %.2f %.3f %.3f %.3f", vuforiaX, x, xdifference, robotAngle, vuforiaAngle, endAngle );
        op.telemetry.addData("VuforiaY","%.2f %.2f %.2f %.2f %.3f %.3f", vuforiaY, y, ydifference, magnitude, turn, mAngle);
        op.telemetry.update();
        op.sleep(5000);
    }

    public void stopAllMotors() {
        drivetrain.stopAllMotors();
    }

    public void stopAllMotorsSideways() {
        drivetrain.stopAllMotorsSideways();
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


    public double getAngle() {
        return drivetrain.getAngle();
    }


    /**
     * Directional Movement
     **/
    public void moveForward(double distance, double power) {
        drivetrain.moveForward(distance, power);
    }

    public void moveForwardIMU(double distance, double power) {
        drivetrain.moveForwardIMU(distance, power);
    }

    public void moveForwardTeleop(double power) {
        drivetrain.moveForwardTeleop(power);
    }

    public void moveBackward(double distance, double power) {
        drivetrain.moveBackward(distance, power);
    }

    public void moveBackwardIMU(double distance, double power) {
        drivetrain.moveBackwardIMU(distance, power);
    }

    public void moveBackwardTeleop(double power) {
        drivetrain.moveBackwardTeleop(power);
    }
    
    public void moveRight(double distance, double power) {
        drivetrain.moveRight(distance, power);
    }

    public void moveRightIMU(double distance, double power, double startingAngle, double gain, double maxCorrection) {
        drivetrain.moveRightIMU(distance, power, startingAngle, gain, maxCorrection);
    }

    public void moveRightTeleop(double power) {
        drivetrain.moveRightTeleop(power);
    }

    public void moveDiagonalRightUpTeleop(double angle, double power) {
        drivetrain.moveDiagonalRightUpTeleop(angle, power);
    }

    public void moveDiagonalRightDownTeleop(double angle, double power) {
        drivetrain.moveDiagonalRightDownTeleop(angle, power);
    }

    public void moveLeft(double distance, double power) {
        drivetrain.moveLeft(distance, power);
    }

    public void moveLeftIMU(double distance, double power, double startingAngle, double gain, double maxCorrection) {
        drivetrain.moveLeftIMU(distance, power, startingAngle, gain, maxCorrection);
    }

    public void moveLeftTeleop(double power) {
        drivetrain.moveLeftTeleop(power);
    }

    public void moveDiagonalLeftUpTeleop(double angle, double power) {
        drivetrain.moveDiagonalLeftUpTeleop(angle, power);
    }

    public void moveDiagonalLeftDownTeleop(double angle, double power) {
        drivetrain.moveDiagonalLeftDownTeleop(angle, power);

    }

    public void inPlaceTurnTeleop(double degrees, boolean direction, double power) {
        drivetrain.inPlaceTurnTeleop(degrees, direction, power);
    }


    public void move(double fwd, double rsd, double turn, double fwdpr, double rsdpwr, double turnpwr) {
        drivetrain.move(fwd, rsd, turn, fwdpr, rsdpwr, turnpwr);
    }

    public void move(double fwd, double rsd, double turn, double pwr) {
        drivetrain.move(fwd, rsd, turn, pwr);
    }

    public void moveAngle(double distance, double angle) {
        drivetrain.moveAngle(distance, angle);
    }

    public void moveAngle2(double distance, double angle, double turn) {
        drivetrain.moveAngle2(distance, angle, turn);
    }


    /**Vuforia**/

    public double getVuforiaAngle() {
        return vuforiaWebcam.getVuforiaAngle();
    }

    public void getVuforiaPosition() {
        vuforiaX = vuforiaWebcam.getVuforiaX();
        vuforiaY = vuforiaWebcam.getVuforiaY();
        vuforiaAngle = vuforiaWebcam.getVuforiaAngle();
        robotAngle = vuforiaAngle + 90;
        robotAngle = (robotAngle>180?robotAngle-360:robotAngle);
    }
    public void stopVuforia() {
        vuforiaWebcam.interrupt();
    }
}

