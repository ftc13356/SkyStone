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
    StraferChassis robot = new StraferChassis();
    AamodVuforia vuforia = new AamodVuforia(op, VuforiaLocalizer.CameraDirection.BACK);
    AamodVuforiaWebcam vuforiaWebcam = new AamodVuforiaWebcam(op, VuforiaLocalizer.CameraDirection.BACK);

    public Robot() {
    }

    public void initChassis(LinearOpMode opMode) {
        op = opMode;
        hardwareMap = op.hardwareMap;

        robot.init(opMode);
        op.telemetry.addData("After Robot Init", "before vuforia");
        op.telemetry.update();
        //vuforia.init();
        op.telemetry.addData("After Vuforia Init", "before vuforiawebcam");
        op.telemetry.update();
        vuforiaWebcam.init(opMode);
        op.telemetry.addData("After Vuforia webcam Init", "before exiting");
        op.telemetry.update();
    }

    public void moveVuforiaWebcam(double x, double y, double angle, double turn) {
        double xdifference = x - vuforiaWebcam.getVuforiaX();
        double ydifference = y - vuforiaWebcam.getVuforiaY();
        double angledifference = angle - vuforiaWebcam.getVuforiaAngle();

        double magnitude = Math.sqrt(xdifference * xdifference + ydifference * ydifference);

        robot.moveAngle2(magnitude, angledifference, turn);
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

    public void moveBackward(double distance, double power) {
        robot.moveBackward(distance, power);
    }

    public void moveBackwardIMU(double distance, double power) {
        robot.moveBackwardIMU(distance, power);
    }

    public void moveRight(double distance, double power) {
        robot.moveRight(distance, power);
    }

    public void moveRightIMU(double distance, double power, double startingAngle, double gain, double maxCorrection) {
        robot.moveRightIMU(distance, power, startingAngle, gain, maxCorrection);
    }

    public void moveLeft(double distance, double power) {
        robot.moveLeft(distance, power);
    }

    public void moveLeftIMU(double distance, double power, double startingAngle, double gain, double maxCorrection) {
        robot.moveLeftIMU(distance, power, startingAngle, gain, maxCorrection);
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

