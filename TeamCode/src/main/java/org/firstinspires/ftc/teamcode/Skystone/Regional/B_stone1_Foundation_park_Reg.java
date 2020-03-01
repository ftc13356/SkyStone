package org.firstinspires.ftc.teamcode.Skystone.Regional;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Skystone.Qualifier_3.RobotA;

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


@Autonomous(name = "B_stone1_Foundation_park_Reg")
public class B_stone1_Foundation_park_Reg extends LinearOpMode {
    Robot_Reg robot = new Robot_Reg();
    Vuforia_Reg vuforia = new Vuforia_Reg();
    private ElapsedTime runtime = new ElapsedTime();
    private Servo stone_claw_servo;


    public B_stone1_Foundation_park_Reg() {

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
        robot.moveForwardIMU(16,0.4);
        robot.AbsoluteTurnIMU(0,0.85);
        a = vuforia.BlueSkyDetect();
        robot.moveForward(7, 0.5);
        if(a==0){
            robot.moveRightIMU(5,0.5);
        }
        else{robot.moveLeftIMU((a-1)*5.7,0.45);}
        robot.moveForwardIMU(5,0.2);
        robot.clawClamp(false);
        sleep(100);
        robot.moveBackwardIMU(6.3,0.55);
        robot.liftPosition(1.55);
        //robot.AbsoluteTurnIMU(0,1.0);
        robot.AbsoluteTurnIMU(84,0.5);
        robot.moveForwardIMU(74-(a*7-3), 0.9);
        robot.liftPosition(4.295);
        //robot.moveForwardIMU(34,0.5);
        robot.AbsoluteTurnIMU(0,0.5);
        robot.moveForwardIMU(13,0.35);
        robot.moveFoundationRightdown(true);
        robot.moveFoundationLefttdown(true);
        robot.clawClamp(true);
        robot.moveBackward(39, 0.5);
        robot.moveRightIMU(28, 0.5);
        robot.moveLeftIMU(7, 0.18);
        robot.moveForward(56, 0.65);
        robot.moveFoundationLefttdown(false);
        robot.moveFoundationRightdown(false);
        robot.moveRight(2,0.2);
        robot.moveBackward(5,1.0);
        robot.liftPosition(0);
        robot.moveBackwardUntilRed(0.35);
        robot.clawClampPosition(0.0);
    }
}

