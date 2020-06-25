package org.firstinspires.ftc.teamcode.SkystoneOffSeason.Chassis2;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "AamodVuforiaAuto")
public class AamodVuforiaAuto extends LinearOpMode {

    @Override
    public void runOpMode() {
        AamodVuforia vuforia = new AamodVuforia(this);

        waitForStart();
        vuforia.start();

        while (opModeIsActive()) {
            telemetry.addData("X", vuforia.getVuforiaX());
            telemetry.addData("Y", vuforia.getVuforiaY());
            telemetry.addData("Angle", vuforia.getVuforiaAngle());
            telemetry.addData("Target", vuforia.getVuforiaTrackable());
            telemetry.update();
        }
        vuforia.interrupt();
    }
}