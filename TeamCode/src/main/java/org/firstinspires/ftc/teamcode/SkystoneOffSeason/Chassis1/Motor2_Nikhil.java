package org.firstinspires.ftc.teamcode.SkystoneOffSeason.Chassis1;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Skystone.Outreach.basicChassis;
import org.firstinspires.ftc.teamcode.Skystone.Qualifier_1.hexChassis;

/**
 * <h1> SkyStone autonomous program</h1>
 * <p>
 * Giving proper comments in your program makes it more
 * user friendly and it is assumed as a high quality code.
 *
 * @author  Nikhil
 * @version 1.0
 * @since   2020-July-14
 *
 */
@Autonomous(name = "2 Motors")

public class Motor2_Nikhil extends LinearOpMode {

    Chassis1 robot=new Chassis1();

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Ready to go");
        telemetry.update();
        robot.initChassis(this);
        telemetry.addData("Status", "InitComplete, Ready to Start");
        telemetry.update();
        waitForStart();
        //^ initialization
        robot.twoMotor(20000);
        //turns the two wheels for a distance of 20000

    }
}
