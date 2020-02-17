package org.firstinspires.ftc.teamcode.Skystone.Regional;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class LiftandClaw_Reg {
    private LinearOpMode op = null;
    private HardwareMap hardwareMap = null;
    private ElapsedTime period = new ElapsedTime();
    DcMotorEx motorLift;
    DcMotorEx motorTape;
    Servo stone_claw_servo;
    double counts_per_motor_tetrix = 0;
    double counts_per_inch_lift = 0;
    double liftheight = 0;

    public LiftandClaw_Reg() {

        /******* Lift motor ********/
        //counts_per_inch
        counts_per_inch_lift = 46; //550 for Tetrix and 100 for Hex
    }
    public void init(LinearOpMode opMode) {
        op = opMode;
        hardwareMap = op.hardwareMap;
        // Lifting motors
        motorLift = (DcMotorEx) hardwareMap.dcMotor.get("motorLift");
        // Claw Servo
        stone_claw_servo = hardwareMap.servo.get("stone_claw_servo");
        // Lifting Motors
        motorLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        motorLift.setDirection(DcMotor.Direction.FORWARD);
        motorLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        stone_claw_servo.setPosition(1.0);
    }
    public void stopLift() {
        motorLift.setPower(0);
    }

    //true = unclamp, false = clamp
    public void clawClamp(boolean direction) {
        if (direction == true) {
            stone_claw_servo.setPosition(1.0);
        } else {
            stone_claw_servo.setPosition(0.0);
        }
        op.sleep(200);
    }

    //0.0 is clamped, 1.0 is unclamped
    public void clawClampPosition(double claw_position) {
        op.telemetry.addData("claw position :", claw_position);
        op.telemetry.update();
        stone_claw_servo.setPosition(claw_position);
        op.sleep(50);
    }

    /******** Lifting Motor **********/
    public void liftAutonomous(double liftheight) {
        double ticksToMove = counts_per_inch_lift * liftheight;
        int newmotorLift = (int) (motorLift.getCurrentPosition() + ticksToMove + 0.5); //adds .5 for rounding
        //TODO: Check limits for safety
        motorLift.setTargetPosition(newmotorLift);
        motorLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorLift.setPower(1.0);
        op.sleep(100);
        while (op.opModeIsActive() && motorLift.isBusy() && motorLift.getVelocity() !=0 ) {
            op.telemetry.addData("Lifting ", motorLift.getCurrentPosition() + " velocity=" + motorLift.getVelocity() + " busy=" + motorLift.isBusy());
            op.telemetry.update();
            op.idle();
        }
        //brake
        motorLift.setPower(0);
        motorLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void liftPosition(double liftposition, Gamepad gp) {
        int ticksPosition = (int) (counts_per_inch_lift * liftposition + 0.5); //adds .5 for rounding
        motorLift.setTargetPosition(ticksPosition);
        motorLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorLift.setPower(1.0);
        op.sleep(100);
        while (op.opModeIsActive() && motorLift.isBusy() && motorLift.getVelocity() !=0 && gp.left_trigger <=0.1 && gp.right_trigger <=0.1) {
            //while (op.opModeIsActive() && motorLift.isBusy() && motorLift.getVelocity() !=0 ) {
            op.telemetry.addData("Lifting ", motorLift.getCurrentPosition() + " velocity=" + motorLift.getVelocity() + " busy=" + motorLift.isBusy());
            op.telemetry.update();
            op.idle();
        }
        //brake
        motorLift.setPower(0);
        motorLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void liftPosition(double liftposition) {
        int ticksPosition = (int) (counts_per_inch_lift * liftposition + 0.5); //adds .5 for rounding
        motorLift.setTargetPosition(ticksPosition);
        motorLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorLift.setPower(1.0);
        op.sleep(100);
        //while (op.opModeIsActive() && motorLift.isBusy() && motorLift.getVelocity() !=0 && gp.left_trigger <=0.1 && gp.right_trigger <=0.1) {
        while (op.opModeIsActive() && motorLift.isBusy() && motorLift.getVelocity() !=0 ) {
            op.telemetry.addData("Lifting ", motorLift.getCurrentPosition() + " velocity=" + motorLift.getVelocity() + " busy=" + motorLift.isBusy());
            op.telemetry.update();
            op.idle();
        }
        //brake
        motorLift.setPower(0);
        motorLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }






}
