package org.firstinspires.ftc.teamcode.Skystone.Unused;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Skystone.Qualifier_1.hexChassis;


@Autonomous(name = "RLs2ft_BLs2ft")

@Disabled
public class RLs2ft_BLs2ft_19or35 extends LinearOpMode {
    hexChassis robot = new hexChassis();
    private ElapsedTime runtime = new ElapsedTime();
    //public Servo stone_claw_servo;
    public RLs2ft_BLs2ft_19or35 () {

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
        /*robot.moveForward(40);
        robot.clawClamp(false);
        robot.moveBackward(5);
        robot.moveLeft(72);
        robot.moveForward(5);
        robot.clawClamp(true);
        robot.moveBackward(5);
        robot.moveRight(66);
        robot.moveForward(5);
        robot.clawClamp(false);
        robot.moveBackward(5);
        robot.moveLeft(66);
        robot.moveForward(5);
        robot.clawClamp(true);
        robot.moveBackward(5);
        robot.clawClamp(false);
        robot.moveBackward(20);
        robot.clawClamp(true);
        robot.moveRight(36);*/
        //red
        /*robot.moveForward(25);
        robot.clawClamp(false);
        robot.moveBackward(5);
        robot.moveRight(72);
        robot.moveForward(5);
        robot.clawClamp(true);
        robot.moveBackward(5);
        robot.moveLeft(66);
        robot.moveForward(5);
        robot.clawClamp(false);
        robot.moveBackward(5);
        robot.moveRight(66);
        robot.moveForward(5);
        robot.clawClamp(true);
        robot.moveBackward(5);
        robot.clawClamp(false);
        robot.moveBackward(5);
        robot.clawClamp(true);
        robot.moveLeft(36);
            */

        stop();

    }

}