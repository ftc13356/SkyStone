package org.firstinspires.ftc.teamcode.Skystone.Unused;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Skystone.Qualifier_3.RobotA;


@Autonomous(name = "Warren Test")
public class Warren_Test extends LinearOpMode {
    RobotA robot = new RobotA();
    private ElapsedTime runtime = new ElapsedTime();
    public Warren_Test () {

    }
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Ready to go");
        telemetry.update();
        robot.initChassis(this);
        int a=0;

        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        waitForStart();
        //move to the blocks
      robot.moveForward(24,1.0);
        robot.moveForward(5,0.4);
        //move left until skystone is detected
        for (int i=0; i<3; i++) {
            if(robot.blockIsSky()==true){
                break;
            }
            a++;
        }
        robot.moveBackward(10,0.5);
        robot.AbsoluteTurnIMU(90, 1.0);
        robot.moveForward(40-(a*8), 1.0);
        //drop the stone
        robot.clawClamp(true);
        robot.AbsoluteTurnIMU(90, 1.0);
        robot.moveBackward(72-(a*8),1.0);
        robot.AbsoluteTurnIMU(0, 1.0);
        robot.moveForward(10,1.0);
        robot.moveForward(5,0.5);
        robot.clawClamp(false);
        //move backward depending on amount of times it took to detect skystone
        robot.moveBackward(10,1.0);
        robot.inPlaceTurnIMU(90,1.0);
        robot.moveForward(76-(a*8),1.0);
        robot.clawClamp(true);
        robot.moveBackwardUntilBlue();
        robot.clawClamp(false);
       sleep(5000);
      sleep(1000);

       stop();

    }

}