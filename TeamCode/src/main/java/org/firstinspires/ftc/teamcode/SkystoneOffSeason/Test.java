//package org.firstinspires.ftc.teamcode.SkystoneOffSeason;
//
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.util.ElapsedTime;
//
//@Autonomous(name = "Test")
//public class Test extends LinearOpMode {
//
//    StraferChassis robot = new StraferChassis();
//    private ElapsedTime runtime = new ElapsedTime();
//
//    @Override
//    public void runOpMode() {
//        telemetry.addData("Status", "Ready for Init");
//        telemetry.update();
//        robot.init(this);
//
//        waitForStart();
//
//        while (opModeIsActive()) {
//
//            /*****INDIVIDUAL MOTORS****/
//
//            robot.motorLeftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//            robot.motorLeftBack.setPower(0.5);
//            sleep(3000);
//            robot.motorLeftBack.setPower(0);
//
//            robot.motorLeftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//            robot.motorLeftFront.setPower(0.5);
//            sleep(3000);
//            robot.motorLeftFront.setPower(0);
//
//            robot.motorRightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//            robot.motorRightBack.setPower(0.5);
//            sleep(3000);
//            robot.motorRightBack.setPower(0);
//
//            robot.motorRightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//            robot.motorRightFront.setPower(0.5);
//            sleep(3000);
//            robot.motorRightFront.setPower(0);
//
//
////            robot.moveMotorLeftBack(10);
////            telemetry.addData("LB", 10);
////            telemetry.update();
////            sleep(2000);
////            robot.moveMotorLeftFront(10);
////            telemetry.addData("LF", 10);
////            telemetry.update();
////            sleep(2000);
////            robot.moveMotorRightBack(10);
////            telemetry.addData("RB", 10);
////            telemetry.update();
////            sleep(2000);
////            robot.moveMotorRightFront(10);
////            telemetry.addData("RF", 10);
////            telemetry.update();
////            sleep(2000);
////
////            /***Directional Movement***/
////            telemetry.addData("Direction: ", "Forward");
////            telemetry.addData("IMU", "No");
////            telemetry.update();
////            robot.moveForward(20, .85);
////            sleep(1000);
////
////            telemetry.addData("Direction: ", "Backward");
////            telemetry.addData("IMU", "No");
////            telemetry.update();
////            robot.moveBackward(20, .85);
////            sleep(1000);
////
////            telemetry.addData("Direction: ", "Forward");
////            telemetry.addData("IMU", "Yes");
////            telemetry.update();
////            robot.moveForwardIMU(20, .85);
////            sleep(1000);
////
////            telemetry.addData("Direction: ", "Backward");
////            telemetry.addData("IMU", "Yes");
////            telemetry.update();
////            robot.moveBackwardIMU(20, .85);
////            sleep(1000);
////
////
////            telemetry.addData("Direction: ", "Left");
////            telemetry.addData("IMU", "No");
////            telemetry.update();
////            robot.moveLeft(20, .85);
////            sleep(1000);
////
////            telemetry.addData("Direction: ", "Right");
////            telemetry.addData("IMU", "No");
////            telemetry.update();
////            robot.moveRight(20, .85);
////            sleep(1000);
////
////            telemetry.addData("Direction: ", "Right");
////            telemetry.addData("IMU", "No");
////            telemetry.update();
////            robot.moveLeftIMU(20, .85, 0, 0.06, 0.15);
////            sleep(1000);
////
////            telemetry.addData("Direction: ", "Right");
////            telemetry.addData("IMU", "Yes");
////            telemetry.update();
////            robot.moveRightIMU(20, .85, 0, 0.06, 0.15);
////            sleep(1000);
//
//            stop();
//        }
//    }
//}