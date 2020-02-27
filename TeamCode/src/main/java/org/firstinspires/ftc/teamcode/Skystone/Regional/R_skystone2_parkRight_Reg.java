package org.firstinspires.ftc.teamcode.Skystone.Regional;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "R_skystone2_parkRight_Reg")
public class R_skystone2_parkRight_Reg extends LinearOpMode {
    Robot_Reg robot; { robot = new Robot_Reg(); }
    Vuforia_Reg vuforia = new Vuforia_Reg();
    private ElapsedTime runtime = new ElapsedTime();
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
         * The init() method of the hardware class does all the work here
         */
        //move to the blocks
        robot.moveForwardIMU(15,0.4);
        a = vuforia.RedSkyDetect();
        robot.moveForward(7, 0.75);
        if(a==0){
            robot.fasterMoveLeftIMU(5,0.75);
        }
        else{robot.fasterMoveRightIMU((a-1)*5,0.75);}
        robot.moveForwardIMU(5,0.2);
        robot.clawClamp(false);
        robot.moveBackwardIMU(10,1.0);
        robot.liftPosition(1.4);
        robot.AbsoluteTurnIMU(0,1.0);
        robot.fasterMoveRightIMU(52-(a*7-3), 1.0);
        //drop the stone
        robot.clawClamp(true);
        robot.liftPosition(0.0);
        robot.AbsoluteTurnIMU(0,1.0);
        robot.fasterMoveLeftIMU(78-(a*7-3),1.0);
        robot.moveForwardIMU(5,0.5);
        if(a==0){
            robot.moveForwardIMU(10,0.4);
        }
        robot.moveForwardIMU(5,0.3);
        robot.clawClamp(false);
        if(a==0){
            robot.moveBackwardIMU(10,0.4);
        }
        robot.moveBackwardIMU(22,0.75);
        robot.liftPosition(1.2);
        robot.AbsoluteTurnIMU(0,1.0);
        robot.fasterMoveRightIMU(84-(a*8),1.0);
        robot.clawClamp(true);
        robot.liftPosition(0.0);
        robot.moveLeftUntilRed(0.5);
        /*robot.moveForwardIMU(15,1.0);
        robot.AbsoluteTurnIMU(0,1.0);
        a = vuforia.RedSkyDetect();
        robot.moveForward(9, 0.75);
        if(a == 0){
            robot.moveLeftIMU(3,0.5);
        }
        else{
        robot.moveRightIMU((a*7)-4,1.0);}
        robot.moveForwardIMU(5,0.75);
        robot.clawClamp(false);
        robot.moveBackwardIMU(14,1.0);
        robot.liftPosition(1.2);
        robot.fasterMoveRightIMU(56-(a*8), 1.0);
        //drop the stone
        robot.clawClamp(true);
        robot.liftPosition(0.0);
        robot.AbsoluteTurnIMU(0,1.0);
        robot.fasterMoveLeftIMU(86-(a*8),1.0);
        robot.AbsoluteTurnIMU(0,1.0);
        robot.moveForwardIMU(10,0.7);
        if(a==0){
            robot.moveForwardIMU(10,0.5);
        }
        robot.moveForwardIMU(5,0.5);
        robot.clawClamp(false);
        if(a==0){
            robot.moveBackwardIMU(10,0.5);
        }
        robot.moveBackwardIMU(14,1.0);
        robot.liftPosition(1.2);
        robot.fasterMoveRightIMU(91-(a*8),1.0);
        robot.clawClamp(true);
        robot.liftPosition(0.0);
        robot.moveLeftUntilRed();*/
        /*for(int i=0;i<15;i++){
        robot.moveLeftUntilRed();
        robot.moveLeftIMU(10,1.0);
        robot.moveRightUntilRed();
        robot.moveRightIMU(10,1.0);}*/
        //robot.ColorTestTape();
        //sleep(10000);
        stop();

    }

}
