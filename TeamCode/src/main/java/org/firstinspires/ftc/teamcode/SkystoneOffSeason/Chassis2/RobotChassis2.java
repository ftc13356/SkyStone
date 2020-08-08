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
