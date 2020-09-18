package org.firstinspires.ftc.teamcode.SkystoneOffSeason.Chassis2;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

@Autonomous(name = "AamodVuforiaAuto")
public class AamodVuforiaAuto extends LinearOpMode {

    @Override
    public void runOpMode() {
        //AamodVuforia vuforiaB = new AamodVuforia(this, VuforiaLocalizer.CameraDirection.BACK);
        //AamodVuforia vuforiaF = new AamodVuforia(this, VuforiaLocalizer.CameraDirection.FRONT);
        AamodVuforiaWebcam vuforiaWebcam = new AamodVuforiaWebcam(this, VuforiaLocalizer.CameraDirection.BACK);

        vuforiaWebcam.init();

        waitForStart();
//        telemetry.addData("Before Vuforia", "");
//        telemetry.update();
//        sleep(5000);
        vuforiaWebcam.start();
//        telemetry.addData("After Vuforia", "");
//        telemetry.update();
//        sleep(5000);

        while (opModeIsActive()) {

//            telemetry.addData("Before Display Position", "");
//            telemetry.update();
//            sleep(5000);

            //Webcam
            telemetry.addData("Back X", vuforiaWebcam.getVuforiaX());
            telemetry.addData("Back Y", vuforiaWebcam.getVuforiaY());
            telemetry.addData("Back Angle", vuforiaWebcam.getVuforiaAngle());
            telemetry.addData("Back Target", vuforiaWebcam.getVuforiaTrackable());
            telemetry.update();

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