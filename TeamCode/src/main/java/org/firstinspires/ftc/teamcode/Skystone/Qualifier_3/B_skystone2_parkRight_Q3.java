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
        robot.moveForwardIMU(22,1.0);
        robot.moveRightIMU(8,0.5);
        robot.moveForwardIMU(3,0.4);
        robot.AbsoluteTurnIMU(0,1.0);
        sleep(500);

        //move left until skystone is detected
        for (int i=0; i<2; i++) {
            if(robot.blockIsSky()==true){
                break;
            }
            a++;
        }
        robot.moveForward(5,0.75);
        robot.clawClamp(false);
        robot.moveBackwardIMU(20,0.5);
        robot.moveLeftIMU(70-(a*8), 0.5);
        //drop the stone
        robot.clawClamp(true);
        robot.moveRightIMU(86-(a*8),0.5);
        robot.moveForwardIMU(15,1.0);
        if(a==0){
            robot.moveForwardIMU(10,0.5);
        }
        robot.moveForwardIMU(5,0.5);
        robot.clawClamp(false);
        if(a==0){
            robot.moveBackwardIMU(10,0.5);
        }
        robot.moveBackwardIMU(20,1.0);
        robot.moveLeftIMU(86-(a*8),0.5);
        robot.clawClamp(true);
        robot.moveRightUntilBlue();
        robot.clawClamp(false);
        sleep(5000);
        sleep(1000);

        stop();

    }

}