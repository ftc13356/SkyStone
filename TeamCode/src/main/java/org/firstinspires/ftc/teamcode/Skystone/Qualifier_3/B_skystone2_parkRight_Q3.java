package org.firstinspires.ftc.teamcode.Skystone.Qualifier_3;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name = "Warren Test")
public class B_skystone2_parkRight_Q3 extends LinearOpMode {
    RobotA robot = new RobotA();
    private ElapsedTime runtime = new ElapsedTime();
    @Override
    public void runOpMode() {
        robot.initChassis(this);
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
        robot.moveForwardIMU(21.5,1.0);
        robot.moveRightIMU(5,0.5);
        robot.AbsoluteTurnIMU(0,1.0);
        robot.moveForwardIMU(4,0.4);
        //move left until skystone is detected
        for (int i=0; i<2; i++) {
            if(robot.blockIsSky()==true){
                break;
            }
            a++;
            robot.moveBackwardIMU(1, 0.5);
        }
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
        robot.fasterMoveLeftIMU(88-(a*8),1.0);
        robot.clawClamp(true);
        robot.liftPosition(0.0);
        robot.moveRightUntilBlue();
        robot.clawClamp(false);
        sleep(300);
        stop();

    }

}