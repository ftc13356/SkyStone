package org.firstinspires.ftc.teamcode.Skystone.Qualifier_3;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import java.lang.Math;

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

        if (hsvValues[0] >= 70 || hsvValues[0] <= 100) {
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
        if (hsvValues[0] >= 140 && hsvValues[0] <= 185) {
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
    public void ColorTest() {
        float hsvValues[] = {0F, 0F, 0F};
        double SCALE_FACTOR = 255;
        double h=0;
        double s=0;
        double v=0;
        double H_array[]=new double[100];
        double S_array[]=new double[100];
        double V_array[]=new double[100];
        double sd[] = {0,0,0};
        for(int i =0; i<100; i++) {
            Color.RGBToHSV((int) (tape_color_sensor.red() * SCALE_FACTOR),
                    (int) (tape_color_sensor.green() * SCALE_FACTOR),
                    (int) (tape_color_sensor.blue() * SCALE_FACTOR),
                    hsvValues);
            h += hsvValues[0] ;
            H_array[i] = hsvValues[0];
            s += hsvValues[1] ;
            S_array[i] = hsvValues[1];
            v += hsvValues[2];
            V_array[i] = hsvValues[2];
            if(i%10==0){
                op.telemetry.addLine()
                        .addData("times", i);
                op.telemetry.update();
            }
            op.sleep(100);
        }
        op.telemetry.addLine()
                .addData("times", 100);
        op.telemetry.update();
        h /= 100.0;
        s /= 100.0;
        v /= 100.0;
        for(int j = 0;j<100; j++){
            sd[0] +=((H_array[j] - h)*(H_array[j] - h));
            sd[1] +=((S_array[j] - s)*(S_array[j] - s));
            sd[2] +=((V_array[j] - v)*(V_array[j] - v));
        }
        sd[0]/=99 ;
        sd[0]=Math.sqrt(sd[0]);
        sd[1]/=99 ;
        sd[1]=Math.sqrt(sd[1]);
        sd[2]/=99 ;
        sd[2]=Math.sqrt(sd[2]);

        op.telemetry.addLine()
                .addData("H", h)
                .addData("S", s)
                .addData("V", v)
                .addData("H_", sd[0])
                .addData("S_", sd[1])
                .addData("V_", sd[2]);


        op.telemetry.update();
    }

}