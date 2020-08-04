package org.firstinspires.ftc.teamcode.SkystoneOffSeason.Chassis2;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.Locale;

import static java.lang.Math.abs;


public class RobotChassis2 {
    Chassis2 drivetrain = new Chassis2();

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

    public void moveDiagonalRightUpTeleop(double angle, double power, double distance) {
        drivetrain.moveDiagonalRightUpTeleop(angle, power, distance);
    }

    public void moveDiagonalRightDownTeleop(double angle, double power, double distance) {
        drivetrain.moveDiagonalRightDownTeleop(angle, power, distance);
    }

    public void moveDiagonalLeftUpTeleop(double angle, double power, double distance) {
        drivetrain.moveDiagonalLeftUpTeleop(angle, power, distance);
    }

    public void moveDiagonalLeftDownTeleop(double angle, double power, double distance) {
        drivetrain.moveDiagonalLeftDownTeleop(angle, power, distance);

    }

    public void inPlaceTurnTeleop(double degrees, boolean direction, double power) {
        drivetrain.inPlaceTurnTeleop(degrees, direction, power);

    }


}
