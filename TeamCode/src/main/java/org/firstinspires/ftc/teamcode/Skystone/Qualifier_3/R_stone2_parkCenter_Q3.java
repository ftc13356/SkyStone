package org.firstinspires.ftc.teamcode.Skystone.Qualifier_3;

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
 * @version 1.0
 * @since 2020-Jan-7
 */

//@Disabled
@Autonomous(name = "R_stone2_parkCenter_Q3")
public class R_stone2_parkCenter_Q3 extends LinearOpMode {
    RobotA robot = new RobotA();
    private ElapsedTime runtime = new ElapsedTime();
    private Servo stone_claw_servo;


    public R_stone2_parkCenter_Q3() {

    }

    @Override
    public void runOpMode() {
        robot.initChassis(this);
        telemetry.addData("Status", "InitComplete, Ready to Start");
        telemetry.update();

        //Wait for Start
        while (!opModeIsActive() && !isStopRequested()) {
            telemetry.addData("status", "waiting for start command...");
            telemetry.update();
        }
        robot.moveForward(28, 1); // robot approaches stones
        //stop();
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
        // move forward until stone

        while(distance>4){
            distance = robot.sensor.block_distance_sensor.getDistance(DistanceUnit.CM);
            //idle()
        }
        robot.stopAllMotors();

        //robot.moveForward(30, 1); // robot approaches stones
        //robot.moveForward(5, 0.5); // slows down
        robot.clawClampPosition(0); // robot grabs stone
        sleep(500); // just in case
        robot.liftPosition(1.2); // lifts stone slightly
        robot.moveBackward(13, 1); // backs away from stones
        //stop();
        /*robot.AbsoluteTurnIMU(90, 1.0); //positive is left // turns. Robot should be facing tape
        robot.moveForward(36, 1); //crosses tape*/
        robot.fasterMoveRightIMU(42,1);
        robot.clawClampPosition(1); // unclamps stone
        /*robot.moveBackward(43, 1); // moves to get second stone
        robot.AbsoluteTurnIMU(0, 1); // gets ready to get next stone*/
        robot.fasterMoveLeftIMU(52,1);
        robot.liftPosition(0); // lowers lift to ground

        robot.moveForward(7, 1);
        double distance2 = robot.sensor.block_distance_sensor.getDistance(DistanceUnit.CM);
        robot.drivetrain.motorLeftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.drivetrain.motorRightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.drivetrain.motorLeftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.drivetrain.motorRightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        float power2=(float)0.5;
        robot.drivetrain.motorLeftBack.setPower(power2);
        robot.drivetrain.motorRightBack.setPower(power2);
        robot.drivetrain.motorLeftFront.setPower(power2);
        robot.drivetrain.motorRightFront.setPower(power2);

        while(distance2>4){
            distance2 = robot.sensor.block_distance_sensor.getDistance(DistanceUnit.CM);
            //idle()
        }
        robot.stopAllMotors();
        //robot.moveForward(8, 1); // approaches stones
        //robot.moveForward(6, 0.5); // slows down
        robot.clawClampPosition(0); // robot grabs 2nd stone
        sleep(500); // just in case
        robot.liftPosition(1.2); // lifts stone slightly
        robot.moveBackward(13, 1); // backs away from stones
        robot.fasterMoveRightIMU(52,1);
        /*robot.inPlaceTurnIMU(90, 1); // turns. Robot should be facing tape
        robot.moveForward(43, 1); // crosses tape*/
        robot.clawClampPosition(1); // unclamps stone
        robot.AbsoluteTurnIMU(0,1.0);
        sleep(400);
        robot.moveBackward(3,1);
        robot.moveLeftUntilRed(); // parks on tape
        robot.clawClampPosition(0); // just in case
    }
}