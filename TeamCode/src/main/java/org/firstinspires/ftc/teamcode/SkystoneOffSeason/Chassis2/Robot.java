package org.firstinspires.ftc.teamcode.SkystoneOffSeason.Chassis2;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

public class Robot {
    private ElapsedTime runtime = new ElapsedTime();
    private LinearOpMode op = null;
    private HardwareMap hardwareMap = null;
    Chassis2_straferChassis robot = new Chassis2_straferChassis();
    AamodVuforia vuforia = new AamodVuforia(op, VuforiaLocalizer.CameraDirection.BACK);
    AamodVuforiaWebcam vuforiaWebcam = new AamodVuforiaWebcam(op, VuforiaLocalizer.CameraDirection.BACK);

    public Robot() {
    }

    public void initChassis(LinearOpMode opMode) {
        op = opMode;
        hardwareMap = op.hardwareMap;

        robot.init(opMode);
        //vuforia.init();
        vuforiaWebcam.init(opMode);
    }

    public void moveVuforiaWebcam(double x, double y, double turn) {
        double xdifference = x - vuforiaWebcam.getVuforiaX();
        double ydifference = y - vuforiaWebcam.getVuforiaY();
        double magnitude = Math.sqrt(xdifference * xdifference + ydifference * ydifference);

        double angle = Math.acos(Math.toRadians(ydifference/magnitude));
        op.telemetry.addData("VuforiaX", vuforiaWebcam.getVuforiaX());
        op.telemetry.addData("VuforiaY", vuforiaWebcam.getVuforiaY());
        op.telemetry.update();
        op.idle();
        robot.moveAngle2(magnitude, angle, turn);
        op.telemetry.addData("VuforiaX", vuforiaWebcam.getVuforiaX());
        op.telemetry.addData("VuforiaY", vuforiaWebcam.getVuforiaY());
        op.telemetry.update();
        op.idle();
    }

    public void stopAllMotors() {
        robot.stopAllMotors();
    }

    public void stopAllMotorsSideways() {
        robot.stopAllMotorsSideways();
    }


    /******** Left Front Motor **********/
    public void moveMotorLeftFront(double distance) {
        robot.moveMotorLeftFront(distance);
    }

    /******** Right Front Motor **********/
    public void moveMotorRightFront(double distance) {
        robot.moveMotorRightFront(distance);
    }

    /******** Left Back Motor **********/
    public void moveMotorLeftBack(double distance) {
        robot.moveMotorLeftBack(distance);
    }

    /******** Right Back Motor **********/
    public void moveMotorRightBack(double distance) {
        robot.moveMotorRightBack(distance);
    }


    public double getAngle() {
        return robot.getAngle();
    }


    /**
     * Directional Movement
     **/
    public void moveForward(double distance, double power) {
        robot.moveForward(distance, power);
    }

    public void moveForwardIMU(double distance, double power) {
        robot.moveForwardIMU(distance, power);
    }

    public void moveForwardTeleop(double power) {
        robot.moveForwardTeleop(power);
    }

    public void moveBackward(double distance, double power) {
        robot.moveBackward(distance, power);
    }

    public void moveBackwardIMU(double distance, double power) {
        robot.moveBackwardIMU(distance, power);
    }

    public void moveBackwardTeleop(double power) {
        robot.moveBackwardTeleop(power);
    }
    
    public void moveRight(double distance, double power) {
        robot.moveRight(distance, power);
    }

    public void moveRightIMU(double distance, double power, double startingAngle, double gain, double maxCorrection) {
        robot.moveRightIMU(distance, power, startingAngle, gain, maxCorrection);
    }

    public void moveRightTeleop(double power) {
        robot.moveRightTeleop(power);
    }

    public void moveDiagonalRightUpTeleop(double angle, double power) {
        robot.moveDiagonalRightUpTeleop(angle, power);
    }

    public void moveDiagonalRightDownTeleop(double angle, double power) {
        robot.moveDiagonalRightDownTeleop(angle, power);
    }

    public void moveLeft(double distance, double power) {
        robot.moveLeft(distance, power);
    }

    public void moveLeftIMU(double distance, double power, double startingAngle, double gain, double maxCorrection) {
        robot.moveLeftIMU(distance, power, startingAngle, gain, maxCorrection);
    }

    public void moveLeftTeleop(double power) {
        robot.moveLeftTeleop(power);
    }

    public void moveDiagonalLeftUpTeleop(double angle, double power) {
        robot.moveDiagonalLeftUpTeleop(angle, power);
    }

    public void moveDiagonalLeftDownTeleop(double angle, double power) {
        robot.moveDiagonalLeftDownTeleop(angle, power);

    }

    public void inPlaceTurnTeleop(double degrees, boolean direction, double power) {
        robot.inPlaceTurnTeleop(degrees, direction, power);
    }

    /**
     * move
     **/
    public void move(double fwd, double rsd, double turn, double fwdpr, double rsdpwr, double turnpwr) {
        robot.move(fwd, rsd, turn, fwdpr, rsdpwr, turnpwr);
    }

    public void move(double fwd, double rsd, double turn, double pwr) {
        robot.move(fwd, rsd, turn, pwr);
    }

    public void moveAngle(double distance, double angle) {
        robot.moveAngle(distance, angle);
    }

    public void moveAngle2(double distance, double angle, double turn) {
        robot.moveAngle2(distance, angle, turn);
    }


    /**Vuforia**/
    public double getVuforiaX() {
        return vuforiaWebcam.getVuforiaX();
    }

    public double getVuforiaY() {
        return vuforiaWebcam.getVuforiaY();
    }

    public double getVuforiaAngle() {
        return vuforiaWebcam.getVuforiaAngle();
    }

    public String getVuforiaTrackable() {
        return vuforiaWebcam.getVuforiaTrackable();
    }

    public void run(){
        vuforiaWebcam.run();
    }
}

