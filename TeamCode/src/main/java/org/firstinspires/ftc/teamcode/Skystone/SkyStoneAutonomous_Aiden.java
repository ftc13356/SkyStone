package org.firstinspires.ftc.teamcode.Skystone;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Skystone Demo")
public class SkyStoneAutonomous_Aiden extends LinearOpMode {



    @Override
    public void runOpMode() {

        basicChassis myChassis = new basicChassis();

        waitForStart();

        myChassis.moveForward(22);


        myChassis.moveForward(5);
        stop();

    }
}
