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
    RobotA robot = new RobotA();
    private ElapsedTime runtime = new ElapsedTime();
    private Servo stone_claw_servo;


    public B_stone1_Foundation_park_Reg() {

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
            if(robot.BlueBlockIsSky()==true){
                break;
            }
            a++;
            robot.moveBackwardIMU(0.2, 0.5);
        }
        robot.moveForwardIMU(5,0.75);// torwards stone
        robot.clawClamp(false);//gets stone
        robot.moveBackwardIMU(16,1.0);
        robot.AbsoluteTurnIMU(0,1.0); //auto-correct angle of robot
        robot.liftPosition(1.2);//lifts lift a little bit up
        robot.moveForwardIMU(0.4,0.2);
        robot.fasterMoveLeftIMU(64-(a*8), 1.0);// crosses field
        robot.liftPosition(4.4); // lifts lift to be able to place stone
        robot.AbsoluteTurnIMU(0,1.0);////auto-correct angle of robot
        robot.fasterMoveLeftIMU(48,1.0);//lines up with foundation
        //robot.moveLeftIMU(10,0.6);
        robot.moveForwardIMU(6.15,0.2 );
        robot.liftPosition(2.3);
        robot.clawClamp(true);
        robot.moveFoundationLefttdown(true);
        robot.moveFoundationRightdown(true);
        robot.moveBackwardIMU(33,1.0);
        robot.moveFoundationLefttdown(false);
        robot.moveFoundationRightdown(false);
        robot.moveBackwardIMU(2,0.2);
        robot.AbsoluteTurnIMU(0,1.0);
        //robot.AbsoluteTurnIMU(90,0.6);
        robot.fasterMoveRightIMU(38.5,1.0);
        robot.moveForward(20,1.0);
        robot.liftPosition(0);
        robot.AbsoluteTurnIMU(0,1.0);
        robot.fasterMoveRightIMU(30,1.0);
 //     robot.moveRightUntilBlue();


    }
}

