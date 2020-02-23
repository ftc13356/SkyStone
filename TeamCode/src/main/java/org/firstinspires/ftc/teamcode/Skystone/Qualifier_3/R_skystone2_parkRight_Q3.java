package org.firstinspires.ftc.teamcode.Skystone.Qualifier_3;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name = "R_skystone2_parkRight_Q3")
public class R_skystone2_parkRight_Q3 extends LinearOpMode {
    RobotA robot = new RobotA();
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
        robot.moveForwardIMU(18,1.0);
        robot.moveLeft(3, 1.0);
        robot.AbsoluteTurnIMU(0,1.0);
        a = vuforia.RedSkyDetect();
        robot.moveForward(7, 0.75);
        robot.moveRightIMU(3+a*8,1.0);
        robot.moveForwardIMU(5,0.75);
        robot.clawClamp(false);
        robot.moveBackwardIMU(39,1.0);
        robot.liftPosition(1.2);
        robot.moveForwardIMU(1,0.2);
        robot.fasterMoveRightIMU(60-(a*8), 1.0);
        //drop the stone
        robot.clawClamp(true);
        robot.liftPosition(0.0);
        robot.fasterMoveLeftIMU(90-(a*8),1.0);
        robot.AbsoluteTurnIMU(0,1.0);
        robot.moveForwardIMU(25,0.7);
        if(a==0){
            robot.moveForwardIMU(10,0.5);
        }
        robot.moveForwardIMU(5,0.5);
        robot.clawClamp(false);
        if(a==0){
            robot.moveBackwardIMU(10,0.5);
        }
        robot.moveBackwardIMU(45,1.0);
        robot.liftPosition(1.2);
        robot.moveForwardIMU(1,0.2);
        robot.fasterMoveRightIMU(95-(a*8),1.0);
        robot.clawClamp(true);
        robot.liftPosition(0.0);
        robot.moveLeftUntilRed();
        robot.clawClamp(false);
        sleep(300);
        /*for(int i=0;i<15;i++){
        robot.moveLeftUntilRed();
        robot.moveLeftIMU(10,1.0);
        robot.moveRightUntilRed();
        robot.moveRightIMU(10,1.0);}*/
        stop();

    }

}