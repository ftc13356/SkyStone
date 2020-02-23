package org.firstinspires.ftc.teamcode.Skystone.Regional;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Tape_Reg {
    private LinearOpMode op = null;
    private HardwareMap hardwareMap = null;
    private ElapsedTime period = new ElapsedTime();
    DcMotorEx motorTape;
    double counts_per_motor_tetrix = 0;
    double counts_per_inch_tape = 0;
    double tapelength = 0;

    public Tape_Reg() {

        /******* Tape motor ********/
        //counts_per_inch
        counts_per_inch_tape = 46; //550 for Tetrix and 100 for Hex
    }

    public void init(LinearOpMode opMode) {
        op = opMode;
        hardwareMap = op.hardwareMap;
        // Lifting motors
        motorTape = (DcMotorEx) hardwareMap.dcMotor.get("motorTape");
        // Lifting Motors
        motorTape.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        motorTape.setDirection(DcMotor.Direction.FORWARD);
        motorTape.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void stopTape() {
        motorTape.setPower(0);
    }

    public void tapePosition(double tapelength, Gamepad gp) {
        int ticksPosition = (int) (counts_per_inch_tape * tapelength + 0.5); //adds .5 for rounding
        motorTape.setTargetPosition(ticksPosition);
        motorTape.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorTape.setPower(1.0);
        op.sleep(100);
        while (op.opModeIsActive() && motorTape.isBusy() && motorTape.getVelocity() !=0 && gp.left_trigger <=0.1 && gp.right_trigger <=0.1) {
            //while (op.opModeIsActive() && motorLift.isBusy() && motorLift.getVelocity() !=0 ) {
            op.telemetry.addData("Extending ", motorTape.getCurrentPosition() + " velocity=" + motorTape.getVelocity() + " busy=" + motorTape.isBusy());
            op.telemetry.update();
            op.idle();
        }
        //brake
        motorTape.setPower(0);
        motorTape.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void tapePosition(double tapelength) {
        int ticksPosition = (int) (counts_per_inch_tape * tapelength + 0.5); //adds .5 for rounding
        motorTape.setTargetPosition(ticksPosition);
        motorTape.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorTape.setPower(1.0);
        op.sleep(100);
        //while (op.opModeIsActive() && motorLift.isBusy() && motorLift.getVelocity() !=0 && gp.left_trigger <=0.1 && gp.right_trigger <=0.1) {
        while (op.opModeIsActive() && motorTape.isBusy() && motorTape.getVelocity() !=0 ) {
            op.telemetry.addData("extending ", motorTape.getCurrentPosition() + " velocity=" + motorTape.getVelocity() + " busy=" + motorTape.isBusy());
            op.telemetry.update();
            op.idle();
        }
        op.sleep(4000);
        //brake
        motorTape.setPower(0);
        motorTape.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void tapeTeleopPower(float tapepower) {
        motorTape.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorTape.setPower(tapepower);
    }


}
