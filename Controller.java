package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Blinker;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp

public class Controller extends LinearOpMode {
    
    private float opensize = (float) 0.2;
    private DcMotor leftMotor;
    private DcMotor rightMotor;
    private DcMotor armMotor;
    private float leftMotorPower;
    private float rightMotorPower;
    private float MovementXaxis;
    private float MovementYaxis;
    private float ArmYaxis;
    
    
    private Servo servo1;
    private Servo servo2;
    private DcMotor SpinnerMotor;
    private float speed = 0; 



   boolean GrabberEngaged;
   boolean GrabberDisengaged;

   
   boolean SpinnerActivate;
   boolean apressed;


   //modes 


    @Override
    public void runOpMode() {

            speed = (float)1;

    
    //drive motors
        leftMotor = hardwareMap.get(DcMotor.class, "LeftDrive");
        rightMotor = hardwareMap.get(DcMotor.class, "RightDrive");
    //arm motors
        armMotor = hardwareMap.get(DcMotor.class, "ArmDrive");
    //servo motors for arm
        
        
        servo1 = hardwareMap.get(Servo.class, "servo1");
        servo2 = hardwareMap.get(Servo.class, "servo2");
        
        waitForStart();
        apressed = false;

        while (opModeIsActive()) {
        // Analog Inputs   
        MovementYaxis = this.gamepad1.left_stick_y;
        MovementXaxis = this.gamepad1.left_stick_x;
        ArmYaxis = -this.gamepad1.right_stick_y;
        // Digital Inputs
        GrabberEngaged = gamepad1.a;
        GrabberDisengaged = gamepad1.x;
        
        

        //arm code 
        armMotor.setPower(ArmYaxis*speed);
        //movment code
        leftMotorPower = MovementYaxis  - MovementXaxis;
        rightMotorPower= MovementYaxis + MovementXaxis;
        
        leftMotorPower *= speed;
        rightMotorPower *= speed;
        
        leftMotor.setPower(leftMotorPower);
        rightMotor.setPower(rightMotorPower);
        
        
        
        if (apressed == false) {
            if(GrabberEngaged){
                apressed = true;
                servo1.setPosition(1);
                servo2.setPosition(-1);
            }
        } 
        if(apressed == true) {
            if(GrabberDisengaged) {
                servo1.setPosition(0.5 + opensize);
                servo2.setPosition(0.5 - opensize);
                apressed = false;
            }
        }

}
}
}
