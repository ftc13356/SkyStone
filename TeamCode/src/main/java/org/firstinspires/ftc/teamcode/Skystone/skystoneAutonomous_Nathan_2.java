package org.firstinspires.ftc.teamcode.Skystone;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

public class skystoneAutonomous_Nathan_2 {
    @Autonomous(name = "skystoneAutonomous_Nathan_2")
    public class skystoneAutonomous_Nathan extends LinearOpMode {


        hexChassis robot = new hexChassis();
        private ElapsedTime runtime = new ElapsedTime();

        public void skystoneAutonomous_Nathan() {

        }

        @Override
        public void runOpMode() {

            telemetry.addData("Status", "Ready to go");
            telemetry.update();

            robot.initChassis(this);

            //place robot on tile 2
            waitForStart();
            robot.moveForward(16);
            sleep(50);
            robot.inPlaceTurn(90, false);
            sleep(50);
            robot.moveForward(16);
            sleep(50);
            robot.inPlaceTurn(90, true);
            sleep(50);
            //to avoid hitting the block
            robot.moveForward(18);
            sleep(50);
            robot.inPlaceTurn(90, false);
            sleep(50);
            robot.moveForward(16);
            sleep(50);
            robot.moveForward(16);
            sleep(50);
            robot.inPlaceTurn(90, false);
            sleep(50);
            //robot puts claw down and picks up block
            //pull claw back up
            robot.moveForward(18);
            sleep(50);
            robot.inPlaceTurn(90, false);
            sleep(50);
            robot.moveForward(16);
            sleep(50);
            robot.moveForward(16);
            sleep(50);
            robot.moveForward(16);
            sleep(50);
            robot.inPlaceTurn(90, false);
            sleep(50);
            //robot puts claw down
            //release claw
            //clamp claw around the base of foundation
            robot.moveBackward(16);

            stop();
        }
    }
}
