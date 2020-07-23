package org.firstinspires.ftc.teamcode.SkystoneOffSeason.Chassis1;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class Chassis1 {
    private LinearOpMode op = null;
    private HardwareMap hardwareMap = null;
    public DcMotor left;
    public DcMotor right;
    private float speed = 37.5f;
    private double distance;


    public Chassis1() {

    }

    public void initChassis(LinearOpMode opMode) {//initialization
        op = opMode;
        hardwareMap = op.hardwareMap;

        left = hardwareMap.dcMotor.get("LeftMotor");//gets the name LeftMotor from hardware map and assigns it to left motor
        right = hardwareMap.dcMotor.get("RightMotor");//gets the name RightMotor from hardware map and assigns it to right motor
    }


    public void twoMotor(double distance) {
        this.distance = distance;//creates distance parameter
        double sleepTime = (distance / speed * 1000);
        left.setPower(1);//sets power
        right.setPower(-1);//sets power
        op.sleep((long) sleepTime);
        left.setPower(0);
        right.setPower(0);
    }
}
