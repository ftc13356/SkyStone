package org.firstinspires.ftc.teamcode.Skystone.Regional;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Skystone.Qualifier_3.RobotA;
import org.firstinspires.ftc.teamcode.Skystone.Qualifier_3.Vuforia_Q3;

/**
 * <h1> SkyStone autonomous program</h1>
 * <p>
 * Giving proper comments in your program makes it more
 * user friendly and it is assumed as a high quality code.
 *
 * @author Aamod
 * @version 1.0
 * @since 2020 Jan-7
 */


@Autonomous(name = "R_stone1_Foundation_park_Reg")
public class R_stone1_Foundation_park_Reg extends LinearOpMode {
    Robot_Reg robot = new Robot_Reg();
    Vuforia_Q3 vuforia = new Vuforia_Q3();
    private ElapsedTime runtime = new ElapsedTime();
    private Servo stone_claw_servo;


    public R_stone1_Foundation_park_Reg() {

    }

    @Override
    public void runOpMode() {
        robot.initChassis(this);
        vuforia.initVuforia(this);
        telemetry.addData("Status", "InitComplete, Ready to Start");
        telemetry.update();

        //Wait for Start
        while (!opModeIsActive() && !isStopRequested()) {
            telemetry.addData("status", "waiting for start command...");
            telemetry.update();
        }
        int a=0;

        /*
         * Initialize the drive system variables.
         * The init() method of the hardware clss does all the work here
         */
        //move to the blocks
        robot.moveForwardIMU(17,0.4);
        a = vuforia.RedSkyDetect();
        robot.moveForward(7, 0.5);
        robot.moveRightIMU((a*7-3),0.5);
        robot.moveForwardIMU(5,0.2);
        robot.clawClamp(false);
        robot.moveBackwardIMU(16,0.25);
        robot.liftPosition(1.4);
        robot.AbsoluteTurnIMU(0,1.0);
        robot.moveRightIMU(56-(a*7-3), 0.5);
        robot.liftPosition(2.5);
        robot.moveForward(30,0.6);
        robot.moveRightIMU(16,1.0);
        robot.moveForward(4,0.15);
        robot.moveFoundationLefttdown(true);
        robot.moveFoundationRightdown(true);
        robot.moveBackward(33,0.6);
        robot.moveLeftIMU(60, 0.6);
        robot.moveRightIMU(5,0.6);
        robot.moveForward(32,0.6);
        robot.moveFoundationLefttdown(false);
        robot.moveFoundationRightdown(false);
        robot.moveRight(8,1.0);
        robot.moveBackward(5,1.0);
        robot.liftPosition(0);
        robot.moveBackward(50,1.0);
        robot.AbsoluteTurnIMU(90,1.0);
        robot.AbsoluteTurnIMU(0,1.0);
        robot.moveBackwardIMU(6,1.0);





    }
}

