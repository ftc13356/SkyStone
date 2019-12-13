package org.firstinspires.ftc.teamcode.Skystone.Unused;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Skystone.Qualifier_1.hexChassis;

@Disabled
@Autonomous(name = "RLf_BLf")

public class RLf_BLf_15 extends LinearOpMode {
    hexChassis robot = new hexChassis();
    private ElapsedTime runtime = new ElapsedTime();
    //public Servo stone_claw_servo;
    public RLf_BLf_15 () {

    }
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Ready to go");
        telemetry.update();

        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        robot.initChassis(this);
        waitForStart();
        //blue
        /*robot.moveForward(35);
        robot.moveLeft(72);
        robot.clawClamp(false);
        robot.moveBackward(20);
        robot.clawClamp(true);*/
        //red
        /*robot.moveForward(5);
        robot.moveRight(54);
        robot.clawClamp(false);
        robot.moveBackward(5);
        robot.clawClamp(true);
        */

        stop();

    }

}