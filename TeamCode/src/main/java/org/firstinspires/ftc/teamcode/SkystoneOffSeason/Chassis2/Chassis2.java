package org.firstinspires.ftc.teamcode.SkystoneOffSeason.Chassis2;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;


public class Chassis2 {
    DcMotorEx motorLeftFront;
    DcMotorEx motorRightFront;
    DcMotorEx motorLeftBack;
    DcMotorEx motorRightBack;

    public void stopAllMotors() {
        motorLeftBack.setPower(0);
        motorRightBack.setPower(0);
        motorLeftFront.setPower(0);
        motorRightFront.setPower(0);
    }

    public void stopAllMotorsSideways() {
        motorLeftBack.setPower(0);
        motorLeftFront.setPower(0);
        motorRightFront.setPower(0);
        motorRightBack.setPower(0);
    }

    public void moveForwardTeleop(double power, double distance) { //TODO Remove this function and move all calls to function without distance parameter
        // Changes motor mode back to default
        motorLeftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorLeftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //was 0.70
        motorLeftBack.setPower(power);
        motorRightBack.setPower(power);
        motorLeftFront.setPower(power);
        motorRightFront.setPower(power);
    }

    public void moveForwardTeleop(double power) {
        // Changes motor mode back to default
        motorLeftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorLeftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //was 0.70
        motorLeftBack.setPower(power);
        motorRightBack.setPower(power);
        motorLeftFront.setPower(power);
        motorRightFront.setPower(power);

    }

    public void moveBackwardTeleop(double power, double distance) {
        // Changes motor mode back to default
        motorLeftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorLeftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //was -0.80
        motorLeftBack.setPower(-power);
        motorRightBack.setPower(-power);
        motorLeftFront.setPower(-power);
        motorRightFront.setPower(-power);

    }

    public void moveRightTeleop(double power, double distance) {
        // Changes motor mode back to default
        motorLeftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorLeftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //this was 1.0 and -1.0
        motorLeftBack.setPower(power*0.95);
        motorRightBack.setPower(-power);
        motorLeftFront.setPower(-power*0.85);
        motorRightFront.setPower(power);

    }

    public void moveLeftTeleop(double power, double distance) {
        // Changes motor mode back to default
        motorLeftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorLeftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //was -1.0 and 1.0
        motorLeftBack.setPower(-power*0.95); //TODO: change back & use encoders for sideways
        motorRightBack.setPower(power);
        motorLeftFront.setPower(power*0.85);
        motorRightFront.setPower(-power);

    }

    public void moveDiagonalRightUpTeleop(double angle, double power, double distance) {
        // Changes motor mode back to default
        motorLeftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorLeftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //was -1.0 and 1.0
        motorLeftBack.setPower(0); //TODO: change back & use encoders for sideways
        motorRightBack.setPower(power);
        motorLeftFront.setPower(power);
        motorRightFront.setPower(0);

    }

    public void moveDiagonalRightDownTeleop(double angle, double power, double distance) {
        // Changes motor mode back to default
        motorLeftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorLeftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //was -1.0 and 1.0
        motorLeftBack.setPower(0); //TODO: change back & use encoders for sideways
        motorRightBack.setPower(-power);
        motorLeftFront.setPower(-power);
        motorRightFront.setPower(0);

    }

    public void moveDiagonalLeftUpTeleop(double angle, double power, double distance) {
        // Changes motor mode back to default
        motorLeftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorLeftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //was -1.0 and 1.0
        motorLeftBack.setPower(power); //TODO: change back & use encoders for sideways
        motorRightBack.setPower(0);
        motorLeftFront.setPower(0);
        motorRightFront.setPower(power);

    }

    public void moveDiagonalLeftDownTeleop(double angle, double power, double distance) {
        // Changes motor mode back to default
        motorLeftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorLeftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //was -1.0 and 1.0
        motorLeftBack.setPower(-power); //TODO: change back & use encoders for sideways
        motorRightBack.setPower(0);
        motorLeftFront.setPower(0);
        motorRightFront.setPower(-power);

    }


    //@direction: true = left, false = right
    public void inPlaceTurnTeleop(double degrees, boolean direction, double power) {
        // Changes motor mode back to default
        motorLeftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorLeftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorRightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        if (direction == true) {
            motorLeftBack.setPower(-power);
            motorLeftFront.setPower(-power);
            motorRightBack.setPower(power);
            motorRightFront.setPower(power);
        } else {
            motorLeftBack.setPower(power);
            motorLeftFront.setPower(power);
            motorRightBack.setPower(-power);
            motorRightFront.setPower(-power);
        }
    }
}
