package org.firstinspires.ftc.teamcode.Skystone.Qualifier_3;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class myColorSensors {
    private LinearOpMode op = null;
    private HardwareMap hardwareMap = null;
    private ElapsedTime period = new ElapsedTime();

    ColorSensor tape_color_sensor;
    ColorSensor block_color_sensor;
    DistanceSensor block_distance_sensor;

    public myColorSensors() {


    }

    public void init(LinearOpMode opMode) {
        op = opMode;
        hardwareMap = op.hardwareMap;

        // Color Sensors
        block_color_sensor = hardwareMap.get(ColorSensor.class, "block_color_sensor");
        block_distance_sensor = hardwareMap.get(DistanceSensor.class, "block_color_sensor");
        tape_color_sensor = hardwareMap.get(ColorSensor.class, "tape_color_sensor");
    }

    //detects if red or if blue returns true and false
    public boolean tapeIsRed() {
        boolean redded = false;
        float hsvValues[] = {0F, 0F, 0F};
        final double SCALE_FACTOR = 255;
        // Color.RGBToHSV((tape_color_sensor.red()), (tape_color_sensor.green()), (tape_color_sensor.blue()), hsvValues);

        Color.RGBToHSV((int) (tape_color_sensor.red() * SCALE_FACTOR),
                (int) (tape_color_sensor.green() * SCALE_FACTOR),
                (int) (tape_color_sensor.blue() * SCALE_FACTOR),
                hsvValues);
        Color.RGBToHSV((int) (tape_color_sensor.red() * SCALE_FACTOR),
                (int) (tape_color_sensor.green() * SCALE_FACTOR),
                (int) (tape_color_sensor.blue() * SCALE_FACTOR),
                hsvValues);
        if (hsvValues[0] >= 10 || hsvValues[0] <= 30) {
            redded = true;
            op.telemetry.addData("ColorSensorStatus", "Red");

        } else {
            op.telemetry.addData("ColorSensorStatus", "Unknown");
            redded = false;
        }
        op.telemetry.addLine()
                .addData("H", "%.3f", hsvValues[0])
                .addData("S", "%.3f", hsvValues[1])
                .addData("V", "%.3f", hsvValues[2]);
        op.telemetry.update();
        return redded;
    }

    public boolean tapeIsBlue() {
        boolean blued = false;
        float hsvValues[] = {0F, 0F, 0F};
        final double SCALE_FACTOR = 255;
        Color.RGBToHSV((int) (tape_color_sensor.red() * SCALE_FACTOR),
                (int) (tape_color_sensor.green() * SCALE_FACTOR),
                (int) (tape_color_sensor.blue() * SCALE_FACTOR),
                hsvValues);
        Color.RGBToHSV((int) (tape_color_sensor.red() * SCALE_FACTOR),
                (int) (tape_color_sensor.green() * SCALE_FACTOR),
                (int) (tape_color_sensor.blue() * SCALE_FACTOR),
                hsvValues);
        op.telemetry.addLine()
                .addData("H", "%.3f", hsvValues[0])
                .addData("S", "%.3f", hsvValues[1])
                .addData("V", "%.3f", hsvValues[2]);
        op.telemetry.update();
        op.sleep(200);
        if (hsvValues[0] >= 160 && hsvValues[0] <= 205) {
            op.telemetry.addData("ColorSensorStatus", "Blue");
            blued = true;
        } else {
            op.telemetry.addData("ColorSensorStatus", "Unknown");
            blued = false;
        }
        op.telemetry.addLine()
                .addData("H", "%.3f", hsvValues[0])
                .addData("S", "%.3f", hsvValues[1])
                .addData("V", "%.3f", hsvValues[2]);
        op.telemetry.update();
        return blued;
    }

}