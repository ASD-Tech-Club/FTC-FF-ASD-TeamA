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
    
    private float opensize =  0.1f;
    private float speed = 1f; 

    
    private DcMotor leftMotor;
    private DcMotor rightMotor;
    private DcMotor armMotor;
    
    private float leftMotorPower;
    private float rightMotorPower;
    
    private float driveXaxis;
    private float driveYaxis;
    
    private float armYaxis;
    
    private Servo servo1;
    private Servo servo2;
    
    private DcMotor SpinnerMotor;
    
   boolean grabberEngaged;
   boolean grabberDisengaged;

   boolean spinnerActivate;
   boolean apressed;
   
   boolean turboActivate;


   //modes 


    @Override
    public void runOpMode() {
        
        //define motor objects and servo objects
        leftMotor = hardwareMap.get(DcMotor.class, "LeftDrive");
        rightMotor = hardwareMap.get(DcMotor.class, "RightDrive");
        armMotor = hardwareMap.get(DcMotor.class, "ArmDrive");
        servo1 = hardwareMap.get(Servo.class, "servo1");
        servo2 = hardwareMap.get(Servo.class, "servo2");
        
        waitForStart();
        
        apressed = false;

        while (opModeIsActive()) {
        // drive input from left stick   
        driveYaxis = this.gamepad1.left_stick_y;
        driveXaxis = this.gamepad1.left_stick_x;
        // arm input from right stick
        armYaxis = -this.gamepad1.right_stick_y;
        
        
        // Digital Inputs
        
        grabberEngaged = gamepad1.a;
        grabberDisengaged = gamepad1.x;
        
        

        //arm code 
        //motor drive code
        
        armMotor.setPower(armYaxis);
        
        
        
        CalculateMotorVelocity(driveYaxis,driveXaxis,speed);
        
        leftMotor.setPower(leftMotorPower);
        rightMotor.setPower(rightMotorPower);
        
        
        
        if (apressed == false) {
            if(grabberEngaged){
                apressed = true;
                servo1.setPosition(1);
                servo2.setPosition(-1);
            }
        } 
        if(apressed == true) {
            if(grabberDisengaged) {
                servo1.setPosition(0.5 - opensize);
                servo2.setPosition(0.5 + opensize);
                apressed = false;
            }
        }

}
}

public void CalculateMotorVelocity(float driveYaxis, float driveXaxis,float speed){
    //setting class vaiables
    this.driveXaxis = driveXaxis;
    this.driveYaxis = driveYaxis;
    this.speed = speed;
    //calculating motor velocity
     leftMotorPower = driveYaxis  - driveXaxis;
        rightMotorPower= driveYaxis + driveXaxis;
        leftMotorPower *= speed;
        rightMotorPower *= speed;
    
}

}
