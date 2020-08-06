package org.firstinspires.ftc.teamcode.SkystoneOffSeason.Chassis2;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

@Autonomous(name = "AamodVuforiaAuto")
public class AamodVuforiaAuto extends LinearOpMode {

    StraferChassis robot = new StraferChassis();
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        //AamodVuforia vuforiaB = new AamodVuforia(this, VuforiaLocalizer.CameraDirection.BACK);
        //AamodVuforia vuforiaF = new AamodVuforia(this, VuforiaLocalizer.CameraDirection.FRONT);
        AamodVuforiaWebcam vuforiaWebcam = new AamodVuforiaWebcam(this, VuforiaLocalizer.CameraDirection.BACK);

        vuforiaWebcam.init(this);

        waitForStart();
        vuforiaWebcam.start();

        while (opModeIsActive()) {

            //Webcam
            telemetry.addData("Back X", vuforiaWebcam.getVuforiaX());
            telemetry.addData("Back Y", vuforiaWebcam.getVuforiaY());
            telemetry.addData("Back Angle", vuforiaWebcam.getVuforiaAngle());
            telemetry.addData("Back Target", vuforiaWebcam.getVuforiaTrackable());
            telemetry.update();

            double x = vuforiaWebcam.getVuforiaX();
            double y = vuforiaWebcam.getVuforiaY();
            double angle = vuforiaWebcam.getVuforiaAngle();


//            //Back
//            telemetry.addData("Back X", vuforiaB.getVuforiaX());
//            telemetry.addData("Back Y", vuforiaB.getVuforiaY());
//            telemetry.addData("Back Angle", vuforiaB.getVuforiaAngle());
//            telemetry.addData("Back Target", vuforiaB.getVuforiaTrackable());
//            sleep(500);
//            //Front
//            telemetry.addData("Front X", vuforiaF.getVuforiaX());
//            telemetry.addData("Front Y", vuforiaF.getVuforiaY());
//            telemetry.addData("Front Angle", vuforiaF.getVuforiaAngle());
//            telemetry.addData("Front Target", vuforiaF.getVuforiaTrackable());
//            telemetry.update();
        }
        vuforiaWebcam.interrupt();
    }
}