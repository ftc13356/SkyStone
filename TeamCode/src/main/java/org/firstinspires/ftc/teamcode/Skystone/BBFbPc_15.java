package org.firstinspires.ftc.teamcode.Skystone;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class BBFbPc_15 {
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
    @Autonomous(name = "BBFbPc_15")
    public class BBFPw_15 extends LinearOpMode {

        private hexChassis robot = new hexChassis();
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
            robot.moveForward(18);
            robot.clawClamp(false);
            sleep(1000);
            robot.liftAutonomous(1.3);
            robot.moveBackward(18);
            robot.clawClamp(true);
            sleep(1000);
            robot.liftAutonomous(1.3);
            robot.moveRight(18);
            robot.moveForward(18);
            robot.moveRight(9);
            stop();
        }
    }
}
