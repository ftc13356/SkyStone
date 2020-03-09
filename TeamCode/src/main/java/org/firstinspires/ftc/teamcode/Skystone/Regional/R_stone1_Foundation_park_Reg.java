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

        robot.moveForwardIMU(16.3,0.4);
        robot.AbsoluteTurnIMU(0,1.0);
        a = vuforia.RedSkyDetect();
        if(a==0){
            robot.fasterMoveLeftIMU(4.65,0.75);
        }
        if(a==2){
            robot.fasterMoveRightIMU((a-1)*6.3,1.0);
        }
        if (a==1){
            robot.fasterMoveRightIMU(0.7,0.3);
        }
        robot.moveForwardIMU(6.1, 0.22);// moves forward torwards stone
        robot.moveForwardIMU(2,0.3);
        robot.clawClamp(false);//gets stone
        sleep(500);
        double b = 0.0;
        if (a==0) {
            b = 10.4;
        } else if (a==1) {
            b =  8.3;
        } else {
            b = 6.3;
        }
        robot.moveBackwardIMU(b,0.55);
        //moves backward
        robot.liftPosition(1.55);
        robot.AbsoluteTurnIMU(0,1.0);//straightens robot
        robot.fasterMoveRightIMU(91-(a*7-3),1.0 );// crossing field
        robot.liftPosition(3.535);//lifts lift

        double d = 0.0;
        if (a==0) {
            d = 10;
        } else if (a==1) {
            d =  8;
        } else {
            d =7.7;
        }
        robot.moveForwardIMU(d,0.35);//apporahes foundatio
        robot.moveForwardIMU(3,0.2);
        robot.moveFoundationRightdown(true);// grabs foundation
        robot.moveFoundationLefttdown(true);
        robot.clawClamp(true);// places stone
        robot.moveBackward(38, 1.0);//moves founation
        robot.moveLeftIMU(28, 0.5);// turns foundation; endangers servo
        robot.moveRightIMU(0.25, 1.0);//gets ready to push foundatio
        robot.moveForward(50, 1.0);//pushes foudation against wall
        robot.moveFoundationLefttdown(false);//lets go
        robot.moveFoundationRightdown(false);//lets go
        robot.fasterMoveRightIMU(9,1.0); // gets ready to park
        robot.liftPosition(1.8);//lift down
        //sleep(5000);
        robot.moveBackward(42,0.9);//parks
        //robot.clawClampPosition(0.0);
//*/

    }
}

