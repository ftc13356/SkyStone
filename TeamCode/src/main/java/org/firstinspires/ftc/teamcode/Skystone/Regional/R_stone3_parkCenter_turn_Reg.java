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
        robot.moveForward(6,0.35); // should be pushing Stone a little

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
        robot.liftPositionNoWait(1.2); // lifts so Stone doesn't drag
        sleep(100); // just in case
        robot.moveBackward(11, 1); // robot moves away from stones
        robot.AbsoluteTurnIMU(-80,0.4); // Done twice so it is more accurate
        robot.AbsoluteTurnIMU(-90,0.6); // Done twice so it is more accurate
        robot.moveForward(31, 1); // crosses tape
        robot.clawClampPosition(1); // releases 1st Stone
        robot.moveBackward(39,1); // goes back to get 2nd Stone
        robot.AbsoluteTurnIMU(3,0.45); // Done twice so it is more accurate
        robot.AbsoluteTurnIMU(3,1); // Done twice so it is more accurate
        robot.moveForward(7,0.45); //Gets closer to 2nd Stone
        robot.liftPositionNoWait(0); // lift should be at ground level

        //Grab Stone 2
        //robot.moveForward(8, 0.35);

        // move forward until stone                                                                   _______
        double distance2 = robot.sensor.block_distance_sensor.getDistance(DistanceUnit.CM);         //      |   ______________
        robot.drivetrain.motorLeftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);                 //      |                 |
        robot.drivetrain.motorRightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);                //      |                 |
        robot.drivetrain.motorLeftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);                  //      |                 |
        robot.drivetrain.motorRightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);                 //      |                 |
        float power2=(float)0.3;                                                                    //      |                 |
        robot.drivetrain.motorLeftBack.setPower(power2);                                            //      |   ______________|
        robot.drivetrain.motorRightBack.setPower(power2);                                           //      }  |
        robot.drivetrain.motorLeftFront.setPower(power2);                                           //      |  |
        robot.drivetrain.motorRightFront.setPower(power2);                                          //      |  |
        while(distance2>3.8){                                                                       //      |  |
            distance2 = robot.sensor.block_distance_sensor.getDistance(DistanceUnit.CM);            //      |  |_______________
            //idle()                                                                                //      |
        }                                                                                           //      |
        robot.stopAllMotors(); // just in case                                                      //______|
        robot.moveForward(2,0.2); // just in case

        robot.clawClampPosition(0); // robot grabs 2nd stone
        sleep(300); // just in case
        robot.liftPositionNoWait(1.2); // robot lifts Stone
        sleep(100); // just in case
        robot.moveBackward(12, 1); // robot backs up with Stone
        robot.AbsoluteTurnIMU(-80,0.4); // Done twice so it is more accurate
        robot.AbsoluteTurnIMU(-90,0.6); // Done twice so it is more accurate
        robot.moveForward(35, 1); // crosses tape
        robot.moveForward(3,0.3); // so next Stone doesn't fall over
        robot.clawClampPosition(1); // releases Stone
        robot.moveBackward(55, 1); // goes to get last Stone
        robot.AbsoluteTurnIMU(6,0.45); // Done twice so it is more accurate
        robot.AbsoluteTurnIMU(6,1); // Done twice so it is more accurate
        robot.moveForward(7,0.45); // robot approaches Stone
        robot.liftPositionNoWait(0); // lift goes to ground level

        //Grab Stone 3
        //robot.moveForward(11, 0.35);
                                                                                                    //______   __________
        double distance3 = robot.sensor.block_distance_sensor.getDistance(DistanceUnit.CM);         //      |            |
        robot.drivetrain.motorLeftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);                 //      |            |
        robot.drivetrain.motorRightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);                //      |            |
        robot.drivetrain.motorLeftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);                  //      |            |
        robot.drivetrain.motorRightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);                 //      |            |
        float power3=(float)0.3;                                                                    //      |    ________|
        robot.drivetrain.motorLeftBack.setPower(power3);                                            //      |            |
        robot.drivetrain.motorRightBack.setPower(power3);                                           //      }            |
        robot.drivetrain.motorLeftFront.setPower(power3);                                           //      |            |
        robot.drivetrain.motorRightFront.setPower(power3);                                          //      |            |
        while(distance3>3.8){                                                                       //      |            |
            distance3 = robot.sensor.block_distance_sensor.getDistance(DistanceUnit.CM);            //      |  __________|
            //idle()                                                                                //      |
        }                                                                                           //______|
        robot.stopAllMotors();
        robot.moveForward(2,0.2); // just in case
        robot.clawClampPosition(0); // robot grabs 2nd stone
        sleep(300); // just in case
        robot.liftPositionNoWait(1.2); // lifts Stone
        sleep(100); // just in case
        robot.moveBackward(13, 1); // robot backs up with Stone
        robot.AbsoluteTurnIMU(-80,0.4); // Done twice so it is more accurate
        robot.AbsoluteTurnIMU(-90,0.6); // Done twice so it is more accurate
        robot.moveForward(54, 1); // crosses tape
        robot.moveForward(3,0.3); // so previous Stone doesn't fall over
        robot.clawClampPosition(1); // releases Stone
        robot.moveBackward(13,1); // parks
        robot.clawClampPosition(0); // just in case
        sleep(500); // just in case

    }
}
