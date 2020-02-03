package org.firstinspires.ftc.teamcode.Skystone.Qualifier_3;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

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

@Disabled
@Autonomous(name = "B_stone1_Foundation_park_Q3")
public class B_stone1_Foundation_park_Q3 extends LinearOpMode {
    RobotA robot = new RobotA();
    private ElapsedTime runtime = new ElapsedTime();
    private Servo stone_claw_servo;


    public B_stone1_Foundation_park_Q3() {

    }

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
        robot.fasterMoveLeftIMU(110-(a*8), 1.0);
        robot.moveBackward(5,0.2);
        sleep(1500);
        robot.liftAutonomous(4.5);
        sleep(1500);
        robot.moveForwardIMU(20,1.0);
        //robot.moveLeftIMU(10,0.6);
        robot.moveForwardIMU(3,0.2 );
        robot.moveFoundationLefttdown(true);
        robot.moveFoundationRightdown(true);
        robot.moveBackwardIMU(30,1.0);
        robot.moveFoundationLefttdown(false);
        robot.moveFoundationRightdown(false);
        robot.moveBackwardIMU(7,0.2);
        //robot.AbsoluteTurnIMU(90,0.6);
        robot.liftPosition(0);
        robot.moveRightUntilBlue();
        robot.clawClampPosition(0.0);
        //robot.moveBackwardIMU(5,0.2);
        //robot.liftPosition(2.83.0);
        //robot.fasterMoveLeftIMU(58, 1.0);
        /*robot.AbsoluteTurnIMU(0,1.0);
        robot.moveForwardIMU(32,1);
        robot.moveLeftIMU(10,0.6);
        robot.moveForwardIMU(5,0.2 );
        robot.moveFoundationLefttdown(true);
        robot.moveFoundationRightdown(true);
        robot.moveBackwardIMU(35,1.0);
        robot.moveFoundationLefttdown(false);
        robot.moveFoundationRightdown(false);
        robot.moveBackwardIMU(7,0.2);
        robot.AbsoluteTurnIMU(90,0.6);
        robot.liftPosition(0);
        robot.moveBackwardUntilBlue();
        robot.clawClampPosition(0.0);*/

    }
}

