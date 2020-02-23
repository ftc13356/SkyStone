package org.firstinspires.ftc.teamcode.Skystone.Qualifier_3;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Skystone.Regional.Robot_Reg;


@Autonomous(name = "R_skystone2_parkRight_Q3")
public class R_skystone2_parkRight_Q3 extends LinearOpMode {
    Robot_Reg robot = new Robot_Reg();
    Vuforia_Q3 vuforia = new Vuforia_Q3();
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
         * The init() method of the hardware clss does all the work here
         */
        //move to the blocks
        robot.moveForwardIMU(16,0.5);
        robot.AbsoluteTurnIMU(0.0,1.0);
        a = vuforia.RedSkyDetect();
        robot.moveForward(7, 0.5);
        robot.moveRightIMU((a*6-2),0.5);
        robot.moveForwardIMU(5,0.2);
        robot.clawClamp(false);
        robot.moveBackwardIMU(11,0.25);
        robot.liftPosition(1.2);
        robot.moveRightIMU(56-(a*8), 0.5);
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
        robot.moveLeftUntilRed();
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
        stop();

    }

}