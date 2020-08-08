package org.firstinspires.ftc.teamcode.SkystoneOffSeason.Chassis2;

import static java.lang.Math.abs;


public class RobotChassis2 {
    Chassis2DeleteLater drivetrain = new Chassis2DeleteLater();

    public void stopAllMotors() {
        drivetrain.stopAllMotors();
    }


    public void moveForwardTeleop(double power) {
        drivetrain.moveForwardTeleop(power);
    }

    public void moveBackwardTeleop(double power) {
        drivetrain.moveBackwardTeleop(power);
    }

    public void moveRightTeleop(double power) {
        drivetrain.moveRightTeleop(power);
    }

    public void moveLeftTeleop(double power) {
        drivetrain.moveLeftTeleop(power);
    }

    public void moveDiagonalRightUpTeleop(double angle, double power) {
        drivetrain.moveDiagonalRightUpTeleop(angle, power);
    }

    public void moveDiagonalRightDownTeleop(double angle, double power) {
        drivetrain.moveDiagonalRightDownTeleop(angle, power);
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


}
