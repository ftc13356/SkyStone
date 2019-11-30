package org.firstinspires.ftc.teamcode.Skystone.Unused;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Skystone.Qualifier_1.RobotB;


@Autonomous(name = "BLPwS2_17or33")

@Disabled
public class BLPwS2_17or33 extends LinearOpMode {
    /**
     * <h1>first SkyStone autonomous program</h1>
     * for testing basicChassis that Nathan and Andrew wrote
     * <p>
     * Giving proper comments in your program makes it more
     * user friendly and it is assumed as a high quality code.
     *
     * @author  Nathan
     * @version 1.0
     * @since   2019-Nov-13
     */
    private RobotB robot = new RobotB();
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {

        telemetry.addData("Status", "Ready to go");
        telemetry.update();
        sleep((long) 100);

        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        robot.initChassis(this);
        telemetry.addData("Status", "Ready to go 1");
        telemetry.update();
        sleep((long) 100);
        waitForStart();
        robot.moveForward(18, 1);
        robot.clawClamp(true);
        sleep(1000);
        robot.liftAutonomous(-1.3);
        robot.moveBackward(5, 1);
        robot.moveRight(54, .75);
        robot.moveForward(5,1);
        robot.clawClamp(false);
        sleep(1000);
        robot.liftAutonomous(1.3);
        robot.moveBackward(5,1);
        robot.moveLeft(60,.75);
        robot.moveForward(5,1);
        robot.liftAutonomous(-1.3);
        robot.clawClamp(false);
        robot.moveBackward(5,1);
        robot.moveRight(60,.75);
        robot.moveForward(5,1);
        robot.clawClamp(false);
        sleep(1000);
        robot.liftAutonomous(1.3);
        robot.moveLeft(40,.75);

        stop();
    }
}


