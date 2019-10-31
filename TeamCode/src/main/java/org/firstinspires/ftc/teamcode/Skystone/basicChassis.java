package org.firstinspires.ftc.teamcode.Skystone;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;



public class basicChassis {

    /* local OpMode members. */
    private LinearOpMode op = null;
    private HardwareMap hardwareMap = null;
    private ElapsedTime period = new ElapsedTime();

    public DcMotor left;
    public DcMotor right;
    public Servo stone_claw_servo;
    public ColorSensor tape_color_sensor;
    private float speed = 37.5f;

    public basicChassis() {

    }

    public void initChassis(LinearOpMode opMode) {
        op = opMode;
        hardwareMap = op.hardwareMap;

        left = hardwareMap.dcMotor.get("LeftMotor");
        right = hardwareMap.dcMotor.get("RightMotor");
        stone_claw_servo = hardwareMap.servo.get("stone_claw_servo");
        tape_color_sensor = hardwareMap.colorSensor.get("C1");

    }

    public void moveForward(double distance) {
        double sleepTime = (distance / speed * 1000);
        left.setPower(.5);
        right.setPower(-.5);
        op.sleep((long) sleepTime);
        left.setPower(0);
        right.setPower(0);
    }

    public void moveBackward(double distance) {
        double sleepTime = (distance / speed * 1000);
        left.setPower(-.5);
        right.setPower(.5);
        op.sleep((long) sleepTime);
        left.setPower(0);
        right.setPower(0);
    }

    //@direction: true = left, false = right
    public void inPlaceTurn(double degrees, boolean direction) {

        double timeInMilliSec = degrees / 45 * 333;

        if (direction == true) {
            left.setPower(-1);
            right.setPower(-1);
            op.sleep((long) timeInMilliSec);
        } else {
            left.setPower(1);
            right.setPower(1);
            op.sleep((long) timeInMilliSec);
        }
        left.setPower(0);
        right.setPower(0);
    }

    //@direction: true = left, false = right
    //degrees means the angle of turning
    public void normalTurn(double degrees, boolean direction) {

        double timeInMilliSec = degrees / 45 * 333;

        if (direction == true) {
            left.setPower(1);
            right.setPower(0);
            op.sleep((long) timeInMilliSec);
        } else { //TODO: Both statements are the same
            left.setPower(1);
            right.setPower(0);
            op.sleep((long) timeInMilliSec);
        }
        //stop
        left.setPower(0);
        right.setPower(0);
    }

    /**
     * <h1>A function for raising and lowering the stone claw.</h1>
     * <p>
     * Giving proper comments in your program makes it more
     * user friendly and it is assumed as a high quality code.
     *
     * @author Warren Zhou
     * @version 1.0
     * @since 2019-10-20
     */
    //true = unclamp, false = clamp
    public void clawClamp(boolean direction) {
        if (direction == true) {
            int x = 1;
            stone_claw_servo.setPosition(1.0);
        } else {
            stone_claw_servo.setPosition(-1.0);
        }

    }
    //detects if red or if blue returns true and false
    public boolean tapeIsRed() {
        boolean redded= false;
        float hsvValues[] = {0F, 0F, 0F};
        final double SCALE_FACTOR = 255;
        // Color.RGBToHSV((tape_color_sensor.red()), (tape_color_sensor.green()), (tape_color_sensor.blue()), hsvValues);

        Color.RGBToHSV((int) (tape_color_sensor.red() * SCALE_FACTOR),
                (int) (tape_color_sensor.green() * SCALE_FACTOR),
                (int) (tape_color_sensor.blue() * SCALE_FACTOR),
                hsvValues);
        if (hsvValues[0] >= 340 || hsvValues[0] <= 20) {
            redded = true;
            op.telemetry.addData("ColorSensorStatus", "Red");

        } else {
            op.telemetry.addData("ColorSensorStatus", "Unknown");
            redded = false;
        }
        op.telemetry.addLine()
                .addData("H", "%.3f", hsvValues[0])
                .addData("S", "%.3f", hsvValues[1])
                .addData("V", "%.3f", hsvValues[2]);
        op.telemetry.update();
        return redded;
    }

    public boolean tapeIsBlue() {
        boolean blued;
        float hsvValues[] = {0F, 0F, 0F};
        final double SCALE_FACTOR = 255;
        Color.RGBToHSV((int) (tape_color_sensor.red() * SCALE_FACTOR),
                (int) (tape_color_sensor.green() * SCALE_FACTOR),
                (int) (tape_color_sensor.blue() * SCALE_FACTOR),
                hsvValues);

        if (hsvValues[0] >= 200 && hsvValues[0] <= 275) {
            op.telemetry.addData("ColorSensorStatus", "Blue");
            blued = true;
        } else {
            op.telemetry.addData("ColorSensorStatus", "Unknown");
            blued = false;
        }
        op.telemetry.addLine()
                .addData("H", "%.3f", hsvValues[0])
                .addData("S", "%.3f", hsvValues[1])
                .addData("V", "%.3f", hsvValues[2]);
        op.telemetry.update();
        return blued;
    }
    //will move until it detects blue/red, momentum causse bug
    public void moveForwardUntilBlue(){
        while(tapeIsBlue()== false){
            left.setPower(0.25);
            right.setPower(-0.25);
        }
    }
    public void moveForwardUntilRed(){
        while(tapeIsRed()== false){
            left.setPower(0.25);
            right.setPower(-0.25);
        }
    }
}
