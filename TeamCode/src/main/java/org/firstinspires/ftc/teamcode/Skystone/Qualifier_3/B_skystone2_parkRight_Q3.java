package org.firstinspires.ftc.teamcode.Skystone.Qualifier_3;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name = "B_skystone2_parkRight_Q3")
public class B_skystone2_parkRight_Q3 extends LinearOpMode {
    Vuforia_Q3 vuforia = new Vuforia_Q3();
    RobotA robot = new RobotA();
    private ElapsedTime runtime = new ElapsedTime();
    @Override
    public void runOpMode() {
        vuforia.initVuforia(this);
        telemetry.addData("Status", "InitComplete, Ready to Start");
        telemetry.update();

        //Wait for Start
        while (!opModeIsActive() && !isStopRequested()) {
            telemetry.addData("status", "waiting for start command...");
            telemetry.update();
        }
        int a=0;
        robot.moveForwardIMU(11,1.0);
        robot.AbsoluteTurnIMU(0,1.0);
        a=vuforia.BlueSkyDetect();
        robot.moveForwardIMU(14,0.75);
        if(a == 0){
            robot.moveRightIMU(7,0.75);
        }
        else if (a==2){
            robot.moveLeftIMU(8, 0.75);
        }
        robot.AbsoluteTurnIMU(0,1.0);
        robot.moveForwardIMU(5,0.75);
        robot.clawClamp(false);
        robot.moveBackwardIMU(39,1.0);
        robot.liftPosition(1.2);
        robot.moveForwardIMU(1,0.2);
        robot.fasterMoveLeftIMU(60-(a*8), 1.0);
        //drop the stone
        robot.clawClamp(true);
        robot.liftPosition(0.0);
        robot.fasterMoveRightIMU(83-(a*8),1.0);
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
        robot.fasterMoveLeftIMU(92-(a*8),1.0);
        robot.clawClamp(true);
        robot.liftPosition(0.0);
        robot.moveRightUntilBlue();
        robot.clawClamp(false);

        stop();

    }

}