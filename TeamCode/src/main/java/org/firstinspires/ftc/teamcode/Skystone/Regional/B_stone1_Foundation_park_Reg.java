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
            robot.fasterMoveRightIMU(0.6,0.3);
        }
        robot.moveForwardIMU(6.1, 0.22);// moves forward torwards stone
        robot.moveForwardIMU(2,0.3);
        robot.clawClamp(false);//gets stone
        sleep(500);//so that claw can close before moving
        double b = 0.0;
        if (a==0) {
            b = 10.7;
        } else if (a==1) {
            b =  9;
        } else {
            b = 8.775;
        }
        robot.moveBackwardIMU(b,0.55);
        //moves backward
        robot.liftPosition(1.55);
        robot.AbsoluteTurnIMU(0,1.0);//straightens robot
        double s = 0.0;
        if (a==0) {
            s = 47;//was 50
        } else if (a==1) {
            s =  59;
        } else {
            s =73;//was 77
        }
        robot.fasterMoveLeftIMU(s,1.0 );// crossing field
        robot.liftPosition(3.535);//lifts lift
        robot.AbsoluteTurnIMU(0,1.0);
        robot.fasterMoveLeftIMU(38,1.0);
        double d = 0.0;
        if (a==0) {
            d = 4.5;
        } else if (a==1) {
            d =  2;
        } else {
            d =1;
        }
        robot.moveForwardIMU(d,0.35);//apporahes foundation
        robot.moveForwardIMU(2.5,0.2);
        robot.moveFoundationRightdown(true);// grabs foundation
        robot.moveFoundationLefttdown(true);
        robot.clawClamp(true);// places stone
        robot.moveBackward(40, 1.0);//moves founation
        robot.moveRightIMU(28, 0.65);// turns foundation; endangers servo
        robot.moveLeftIMU(0.25, 1.0);//gets ready to push foundatio
        robot.moveForward(50, 1.0);//pushes foudation against wall
        robot.moveFoundationLefttdown(false);//lets go
        robot.moveFoundationRightdown(false);//lets go
        robot.fasterMoveLeftIMU(5,1.0); // gets ready to park
        robot.liftPosition(1.8);//lift down
        //sleep(5000);
        robot.moveBackward(42,0.45);//parks
        //robot.clawClampPosition(0.0);
//*/

    }
}

