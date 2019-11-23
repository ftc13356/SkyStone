package org.firstinspires.ftc.teamcode.Skystone.Unused;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Skystone.Qualifier_1.hexChassis;


@Autonomous(name = "Warren Test")
public class Warren_Test extends LinearOpMode {
    hexChassis robot = new hexChassis();
    private ElapsedTime runtime = new ElapsedTime();
    public Warren_Test () {

    }
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Ready to go");
        telemetry.update();
        int a=0;

        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        robot.initChassis(this);
        waitForStart();
        //move to the blocks
        robot.moveForward(30,1.0);
        robot.moveForward(6,0.4);
        //account for the curve
        robot.inPlaceTurn(15,true, 1.0);
        //move left until skystone is detected
        for (int i=0; i<3; i++) {
            if(robot.blockIsYellow()==false){
                break;
            }
            //move left one block
            robot.moveLeft(13, 1.0);
            sleep(300);
            //account for the curve
            robot.inPlaceTurn(25, true,1.0);
            sleep(300);
            a++;
        }
        robot.moveForward(4,0.4);
        //pick up the stone
        robot.clawClamp(false);
        sleep(200);
        robot.moveBackward(20,0.5);
        sleep(300);
        robot.moveForward(2,0.4);
        robot.liftAutonomous(0.25);
        robot.inPlaceTurn(170,true,1.0);
        robot.moveForward(60-(a*5),1.0);
        //drop the stone
        robot.clawClamp(true);
        sleep(200);
        robot.moveRight(15,1.0);
        robot.inPlaceTurn(30,false,1.0);
        //move backward depending on amount of times it took to detect skystone
        robot.moveBackward(20,1.0);
        robot.clawClamp(false);

        stop();

    }

}