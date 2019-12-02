package org.firstinspires.ftc.teamcode.Skystone.Qualifier_1;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class RobotB{
    hexChassis drivetrain = new hexChassis();
    Accesories accesories = new Accesories();
    private ElapsedTime runtime = new ElapsedTime();
    private LinearOpMode op              = null;
    private HardwareMap hardwareMap     = null;


    public RobotB() {
    }
    public void initChassis (LinearOpMode opMode){
        op = opMode;
        hardwareMap = op.hardwareMap;
        drivetrain.initChassis(opMode);
        accesories.initChassis(opMode);
    }
    public void moveForwardUntilBlue(){
        while(accesories.tapeIsBlue()==false){
            drivetrain.motorLeftBack.setPower(1.0);
            drivetrain.motorRightBack.setPower(1.0);
            drivetrain.motorLeftFront.setPower(1.0);
            drivetrain.motorRightFront.setPower(1.0);
        }

    }
    public void moveForwardUntilRed(){
        while(accesories.tapeIsRed()==false){
            drivetrain.motorLeftBack.setPower(1.0);
            drivetrain.motorRightBack.setPower(1.0);
            drivetrain.motorLeftFront.setPower(1.0);
            drivetrain.motorRightFront.setPower(1.0);
        }

    }
    public void moveRightUntilBlue(){
        while(accesories.tapeIsBlue()==false){
            drivetrain.motorLeftBack.setPower(1.0);
            drivetrain.motorRightBack.setPower(-1.0);
            drivetrain.motorLeftFront.setPower(-1.0);
            drivetrain.motorRightFront.setPower(1.0);
        }

    }
    public void moveRightUntilRed(){
        while(accesories.tapeIsRed()==false){
            drivetrain.motorLeftBack.setPower(1.0);
            drivetrain.motorRightBack.setPower(-1.0);
            drivetrain.motorLeftFront.setPower(-1.0);
            drivetrain.motorRightFront.setPower(1.0);
        }

    }
    public void moveLeftUntilBlue(){
        while(accesories.tapeIsBlue()==false){
            drivetrain.motorLeftBack.setPower(-1.0); //TODO Is this correct? Only one motor is neagative
            drivetrain.motorRightBack.setPower(1.0);
            drivetrain.motorLeftFront.setPower(1.0);
            drivetrain.motorRightFront.setPower(1.0);
        }

    }
    public void moveLeftUntilRed(){
        while(accesories.tapeIsRed()==false){
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
        drivetrain.moveForwardTeleop(power,distance);
    }

    public void moveBackwardTeleop(double power, double distance) {
        drivetrain.moveBackwardTeleop(power,distance);
    }

    public void moveRightTeleop(double power, double distance) {
        drivetrain.moveRightTeleop(power,distance);
    }

    public void moveLeftTeleop(double power, double distance) {
        drivetrain.moveLeftTeleop(power,distance);
    }



    //@direction: true = left, false = right
    public void inPlaceTurnTeleop(double degrees, boolean direction, double power) {
        drivetrain.inPlaceTurnTeleop(degrees,direction,power);
    }

    public void moveForward(double distance, double power) {
        drivetrain.moveForward(distance,power);
    }

    public void moveBackward(double distance, double power) {
        drivetrain.moveBackward(distance,power);
    }

    public void moveRight(double distance, double power) {
        drivetrain.moveRight(distance,power);
    }

    public void moveLeft(double distance, double power) {
        drivetrain.moveLeft(distance,power);
    }

    //@direction: true = left, false = right
    public void inPlaceTurn(double degrees, boolean direction, double power) {
        drivetrain.inPlaceTurn(degrees,direction,power);
    }

    /******** Left Front Motor **********/
    public void moveMotorLeftFront(double distance){ drivetrain.moveMotorLeftFront(distance); }
    /******** Right Front Motor **********/
    public void moveMotorRightFront(double distance){
        drivetrain.moveMotorRightFront(distance);
    }
    /******** Left Back Motor **********/
    public void moveMotorLeftBack(double distance){
        drivetrain.moveMotorLeftBack(distance);
    }
    /******** Right Back Motor **********/
    public void moveMotorRightBack(double distance){
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

    public void liftTeleop_NotWorking(boolean up) {
        accesories.liftTeleop_NotWorking(up);
    }
}