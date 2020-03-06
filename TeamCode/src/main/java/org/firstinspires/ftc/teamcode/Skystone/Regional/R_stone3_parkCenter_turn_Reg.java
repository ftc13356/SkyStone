package org.firstinspires.ftc.teamcode.Skystone.Regional;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;


/**
 * <h1> SkyStone autonomous program</h1>
 * <p>
 * Giving proper comments in your program makes it more
 * user friendly and it is assumed as a high quality code.
 *
 * @author Aamod
 * @version 1.4
 * @since 2019-Dec-2
 */

@Autonomous(name = "R_stone3_parkCenter_turn_Reg")
public class R_stone3_parkCenter_turn_Reg extends LinearOpMode {
    Robot_Reg robot = new Robot_Reg();
    private ElapsedTime runtime = new ElapsedTime();
    private Servo stone_claw_servo;


    public R_stone3_parkCenter_turn_Reg() {

    }

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Ready to go");
        telemetry.update();
        robot.initChassis(this);
        telemetry.addData("Status", "InitComplete, Ready to Start");
        telemetry.update();

        //Wait for Start
        while (!opModeIsActive() && !isStopRequested()) {
            telemetry.addData("status", "waiting for start command...");
            telemetry.update();
        }

        // Main Program (Start Pressed and we are out of above While loop)
        robot.moveForwardIMU(19, 0.9); // robot approaches stones
        robot.AbsoluteTurnIMU(0,2.5); // it still works with power 2.5

        //Grab Stone 1
        robot.moveForward(6,0.35);

        /*
        // move forward until stone
        double distance = robot.sensor.block_distance_sensor.getDistance(DistanceUnit.CM);
        robot.drivetrain.motorLeftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.drivetrain.motorRightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.drivetrain.motorLeftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.drivetrain.motorRightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        float power= (float) 0.5;
        robot.drivetrain.motorLeftBack.setPower(power);
        robot.drivetrain.motorRightBack.setPower(power);
        robot.drivetrain.motorLeftFront.setPower(power);
        robot.drivetrain.motorRightFront.setPower(power);
        while(distance>4){
            distance = robot.sensor.block_distance_sensor.getDistance(DistanceUnit.CM);
            //idle()
        }
        robot.stopAllMotors();
        */

        robot.clawClampPosition(0); // robot grabs stone
        sleep(300); // just in case
        robot.liftPositionNoWait(1);
        sleep(100);
        robot.moveBackward(9, 1); // robot moves away from stones
        robot.AbsoluteTurnIMU(-80,0.4);
        robot.AbsoluteTurnIMU(-90,0.6);
        robot.moveForward(31, 1);
        robot.clawClampPosition(1);
        robot.moveBackward(39,1);
        robot.AbsoluteTurnIMU(3,0.45);
        robot.AbsoluteTurnIMU(3,1);
        robot.moveForward(7,0.45);
        robot.liftPositionNoWait(0);

        //Grab Stone 2
        //robot.moveForward(8, 0.35);

        // move forward until stone
        double distance2 = robot.sensor.block_distance_sensor.getDistance(DistanceUnit.CM);
        robot.drivetrain.motorLeftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.drivetrain.motorRightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.drivetrain.motorLeftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.drivetrain.motorRightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        float power2=(float)0.3;
        robot.drivetrain.motorLeftBack.setPower(power2);
        robot.drivetrain.motorRightBack.setPower(power2);
        robot.drivetrain.motorLeftFront.setPower(power2);
        robot.drivetrain.motorRightFront.setPower(power2);
        while(distance2>3.8){
            distance2 = robot.sensor.block_distance_sensor.getDistance(DistanceUnit.CM);
            //idle()
        }
        robot.stopAllMotors();
        robot.moveForward(2,0.2);

        robot.clawClampPosition(0); // robot grabs 2nd stone
        sleep(300);
        robot.liftPositionNoWait(1.2);
        sleep(100);
        robot.moveBackward(10, 1);
        robot.AbsoluteTurnIMU(-80,0.4);
        robot.AbsoluteTurnIMU(-90,0.6);
        robot.moveForward(37, 1);
        robot.clawClampPosition(1);
        robot.moveBackward(55, 1);
        robot.AbsoluteTurnIMU(6,0.45);
        robot.AbsoluteTurnIMU(6,1);
        robot.moveForward(7,0.45);
        robot.liftPositionNoWait(0);

        //Grab Stone 3
        //robot.moveForward(11, 0.35);

        double distance3 = robot.sensor.block_distance_sensor.getDistance(DistanceUnit.CM);
        robot.drivetrain.motorLeftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.drivetrain.motorRightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.drivetrain.motorLeftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.drivetrain.motorRightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        float power3=(float)0.3;
        robot.drivetrain.motorLeftBack.setPower(power3);
        robot.drivetrain.motorRightBack.setPower(power3);
        robot.drivetrain.motorLeftFront.setPower(power3);
        robot.drivetrain.motorRightFront.setPower(power3);
        while(distance3>3.8){
            distance3 = robot.sensor.block_distance_sensor.getDistance(DistanceUnit.CM);
            //idle()
        }
        robot.stopAllMotors();
        robot.moveForward(2,0.2);
        robot.clawClampPosition(0); // robot grabs 2nd stone
        sleep(300);
        robot.liftPositionNoWait(1.2);
        robot.moveBackward(12, 1);
        robot.AbsoluteTurnIMU(-80,0.4);
        robot.AbsoluteTurnIMU(-90,0.6);
        robot.moveForward(52, 1);
        robot.clawClampPosition(1);
        robot.moveBackward(13,1);
        robot.clawClampPosition(0);
        sleep(500);

    }
}
