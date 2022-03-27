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
    
    private float opensize = 0.1f;
    private float speed = 1f; 

    
    private DcMotor leftMotor;
    private DcMotor rightMotor;
    private DcMotor armMotor;
    
    private float leftMotorPower;
    private float rightMotorPower;
    private float armMotorPower;
    
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
        
        
        telemetry.addData("Status", "Motors Initialized - Gip Size: " + opensize + " Set Speed: " + speed  );
        telemetry.update();
        
        waitForStart();
        
        apressed = false;

        while (opModeIsActive()) {
            telemetry.addData("Status", "Running" );
        // drive input from left stick   
        driveYaxis = this.gamepad1.left_stick_y;
        driveXaxis = this.gamepad1.left_stick_x;
        // arm input from right stick
        armYaxis = -this.gamepad1.right_stick_y;
        
        
        // Digital Inputs
        
        grabberEngaged = gamepad1.a;
        grabberDisengaged = gamepad1.x;
        turboActivate = gamepad1.left_bumper;
        

        // Motor Driver Code
        CalculateMotorVelocities(driveYaxis,driveXaxis,armYaxis, speed,turboActivate);
        
        telemetry.addData("Turbo Status"," Turbo Activated: " + turboActivate);
        
        
         telemetry.addData("Left Motor Status", " Motor Power: " + 
         Math.abs(leftMotorPower * 100) + "%");
          telemetry.addData("Right Motor Status", " Motor Power: " + Math.abs(rightMotorPower * 100) + "%");
          telemetry.addData("Arm Motor Status", " Motor Power: " + Math.abs(armMotorPower * 100) + "%");
            //telemetry.update();
        
        armMotor.setPower(armMotorPower);
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
                 apressed = false;
                servo1.setPosition(0.5 - opensize);
                servo2.setPosition(0.5 + opensize);
            }
        }
        
        
         telemetry.addData("Grabber Status: ", " Grabber Position: " + servo1.getPosition() + " Grabber Activated: " + apressed);
        telemetry.update();

    }
}

public void CalculateMotorVelocities(
    
    float driveYaxis, float driveXaxis, float armYaxis,
    float speed, boolean turboActivate){
        
    //setting class vaiables
    this.driveXaxis = driveXaxis;
    this.driveYaxis = driveYaxis;
    this.armYaxis = armYaxis;
    this.speed = speed;
    //calculating motor velocity

    
    if(!turboActivate){
        
        leftMotorPower = driveYaxis  - driveXaxis;
        rightMotorPower= driveYaxis + driveXaxis;
        armMotorPower = armYaxis;
        
        leftMotorPower *= speed;
        rightMotorPower *= speed;
        armMotorPower *= speed;
    } else{
        
        leftMotorPower = driveYaxis  - driveXaxis;
        rightMotorPower= driveYaxis + driveXaxis;
        armMotorPower = armYaxis;
        
        leftMotorPower *= speed/5;
        rightMotorPower *= speed/5;
        armMotorPower *= speed/5;  
    }
     
    
}

}
