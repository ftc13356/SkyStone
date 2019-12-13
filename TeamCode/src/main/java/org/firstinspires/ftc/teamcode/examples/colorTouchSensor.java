package org.firstinspires.ftc.teamcode.examples;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
@Disabled
@TeleOp(name = "Color Touch Sensor")
public class colorTouchSensor extends OpMode{

    private DigitalChannel touchSensor;
    private ColorSensor colorSensor;

    private float[] hsvValues = {0F, 0F, 0F};

    @Override
    public void init() {
        telemetry.addData("Arm", "Initializing");

        touchSensor = hardwareMap.digitalChannel.get("touchSensor");
        colorSensor = hardwareMap.colorSensor.get("colorSensor");
        touchSensor.setMode(DigitalChannel.Mode.INPUT);

        telemetry.addData("Arm", "Initialized");
    }

    @Override
    public void loop() {

        if (!touchSensor.getState()) {
            telemetry.addData("Touch Sensor", "Is Pressed");
        } else {
            telemetry.addData("Touch Sensor", "Is Not Pressed");
        }

        // Scans for red or blue color
        Color.RGBToHSV((colorSensor.red()), (colorSensor.green()), (colorSensor.blue()), hsvValues);
        telemetry.addData("Hue", hsvValues[0]);
        if (hsvValues[0] >= 340 || hsvValues[0] <= 20) {
            telemetry.addData("Color Sensor Status", "Red");
        }
        if (hsvValues[0] >= 200 && hsvValues[0] <= 275) {
            telemetry.addData("Color Sensor Status", "Blue");
        }
        else {
            telemetry.addData("Color Sensor Status", "Unknown");
        }
    }
}
