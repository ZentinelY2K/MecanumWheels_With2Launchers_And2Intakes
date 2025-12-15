package org.firstinspires.ftc.teamcode.ZentinelY2K;




import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp(name = "MainTeleOp", group = "TeleOp")
public class MainTeleOp extends LinearOpMode {




   DcMotor leftBack, rightBack, leftFront, rightFront;


   DcMotor leftLauncher, rightLauncher;


   DcMotor intake;


   CRServo pushWheel;




   public void runOpMode() throws InterruptedException {


       leftBack = hardwareMap.get(DcMotor.class, "leftBack");
       leftFront = hardwareMap.get(DcMotor.class, "leftFront");
       rightBack = hardwareMap.get(DcMotor.class, "rightBack");
       rightFront = hardwareMap.get(DcMotor.class, "rightFront");


       leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
       leftBack.setDirection(DcMotorSimple.Direction.REVERSE);


       leftLauncher = hardwareMap.get(DcMotor.class, "leftLauncher");
       rightLauncher = hardwareMap.get(DcMotor.class, "rightLauncher");


       leftLauncher.setDirection(DcMotorSimple.Direction.REVERSE);


       intake = hardwareMap.get(DcMotor.class, "intake");


       pushWheel = hardwareMap.get(CRServo.class, "pushWheel");


       waitForStart();
       while (opModeIsActive()) {
           driveNormalHS();
           shooterHS();
           }


   }




       public void driveNormalHS () {
           double y = gamepad1.left_stick_y;
           double x = -gamepad1.left_stick_x;
           double rx = gamepad1.right_stick_x;


           double leftBackPW = y - x + rx;//apply mecanum formula for left back
           double leftFrontPW = y + x + rx;//apply mecanum formula for left front
           double rightBackPW = y + x - rx;//apply mecanum formula for right back
           double rightFrontPW = y - x - rx;//apply mecanum formula for right front
           //safety protocol before wheels get the power MAX library setup
           double max_rpm = Math.max(Math.abs(leftBackPW), Math.max(Math.abs(leftFrontPW), Math.max(Math.abs(rightBackPW), Math.max(Math.abs(rightFrontPW), 1.0))));


           if (max_rpm > 1.0) { //We set the limit of power
               leftBackPW /= max_rpm; //divide its current power by its maximum value resulting in: 1
               leftFrontPW /= max_rpm;//same
               rightBackPW /= max_rpm;//same
               rightFrontPW /= max_rpm;//same
           }




           leftBack.setPower(leftBackPW);
           leftFront.setPower(leftFrontPW);
           rightBack.setPower(rightBackPW);
           rightFront.setPower(rightFrontPW);










       }


       public void shooterHS () {
           if (gamepad2.right_bumper) {
               intake.setPower(1);
           }
           if (gamepad2.left_bumper) {
               intake.setPower(0);
           }




           if (gamepad2.right_trigger > 0) {
               pushWheel.setPower(1);
           }


           if (gamepad2.left_trigger > 0) {
               pushWheel.setPower(-0.13);
           }
           if (gamepad2.a) {
               leftLauncher.setPower(0.62);
               rightLauncher.setPower(0.62);
           }
           if (gamepad2.b) {
               leftLauncher.setPower(0.76);
               rightLauncher.setPower(0.76);
           }


           if (gamepad2.y) {
               leftLauncher.setPower(0.82);
               rightLauncher.setPower(0.82);
           }
           if (gamepad2.x) {
               leftLauncher.setPower(0.92);
               rightLauncher.setPower(0.92);
           }




















           }
















       }










