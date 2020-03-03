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
    Robot_Reg robot; { robot = new Robot_Reg(); }
    Vuforia_Reg vuforia = new Vuforia_Reg();
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

        robot.moveForwardIMU(14.375,0.4);
        robot.AbsoluteTurnIMU(0,1.0);
        a = vuforia.RedSkyDetect();
        robot.moveForwardIMU(7.0, 0.22);
        if(a==0){
            robot.fasterMoveLeftIMU(4.8,0.75);
        }
        else if(a==2){robot.fasterMoveRightIMU((a-1)*5.75,0.75);}
        robot.moveForwardIMU(5,0.4);
        robot.clawClamp(false);
        double b = 0.0;
        if (a==0) {
            b = 12;
        } else if (a==1) {
            b =  13.5;
        } else {
            b = 11;
        }
        robot.moveBackwardIMU(b,0.55);
        robot.liftPosition(1.55);
        robot.AbsoluteTurnIMU(0,1.0);
        //robot.AbsoluteTurnIMU(-84,0.5);
        //robot.moveForwardIMU(74-(a*7-3), 0.9);
        robot.fasterMoveRightIMU(90-(a*7-3), 1.0);// crossing field
        //robot.moveForwardIMU(34,0.5);
        //robot.AbsoluteTurnIMU(0,1.0);
        //robot.fasterMoveRightIMU(38,1.0);
        robot.liftPosition(3.58);

        double d = 0.0;
        if (a==0) {
            d = 9;
        } else if (a==1) {
            d =  8;
        } else {
            d =8;
        }
        robot.moveForwardIMU(d,0.35);
        robot.moveForwardIMU(3,0.2);
        robot.moveFoundationRightdown(true);// grabs foundation
        robot.moveFoundationLefttdown(true);
        robot.clawClamp(true);// places stone
        robot.moveBackward(37, 1.0);
        robot.moveLeftIMU(28, 1.0);
        robot.moveRightIMU(0.25, 1.0);
        robot.moveForward(56, 1.0);
        robot.moveFoundationLefttdown(false);
        robot.moveFoundationRightdown(false);
        robot.moveRight(11,1.0);
        //robot.moveBackward(5,1.0);
        robot.liftPosition(1.8);
        robot.moveBackward(40,1.0);
        //robot.clawClampPosition(0.0);


    }
}

