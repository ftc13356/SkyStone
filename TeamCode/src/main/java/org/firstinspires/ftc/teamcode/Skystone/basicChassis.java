package org.firstinspires.ftc.teamcode.Skystone;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
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
    private float speed = 37.5f;

    public basicChassis() {

    }

    public void initChassis(LinearOpMode opMode) {
        op = opMode;
        hardwareMap = op.hardwareMap;

        left = hardwareMap.dcMotor.get("LeftMotor");
        right = hardwareMap.dcMotor.get("RightMotor");
        stone_claw_servo = hardwareMap.servo.get("stone_claw_servo");

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
            stone_claw_servo.setPosition(1.0);
        } else {
            stone_claw_servo.setPosition(-1.0);
        }

    }
}
