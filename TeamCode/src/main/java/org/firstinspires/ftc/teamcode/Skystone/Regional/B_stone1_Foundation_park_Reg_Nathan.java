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
 * @author Nathan
 * @version 1.0
 * @since 2020 Jan-7
 */


@Autonomous(name = "B_stone1_Foundation_park_Reg_Nathan")
public class B_stone1_Foundation_park_Reg_Nathan extends LinearOpMode {
    Robot_Reg robot = new Robot_Reg();
    private ElapsedTime runtime = new ElapsedTime();
    private Servo stone_claw_servo;


    public B_stone1_Foundation_park_Reg_Nathan() {

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
//        for (int i = 0; i <= 100; i++) {
//            double gain = 0.005;
//            robot.setIMUgain(gain);
//
//            robot.moveRightIMU(30,0.5);
//            robot.moveLeftIMU(30,0.5);
//            telemetry.addData("gain = ", gain );
//            telemetry.update();
//        }
        robot.moveRightIMU(15, 0.2);
        robot.moveForwardIMU(15, 0.5);
        robot.moveRightIMU(15, 0.2);

    }
}

