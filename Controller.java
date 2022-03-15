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
   boolean GrabberActivate;
   boolean SpinnerActivate;

    @Override
    public void runOpMode() {
    
    //drive motors
        leftMotor = hardwareMap.get(DcMotor.class, "LeftDrive");
        rightMotor = hardwareMap.get(DcMotor.class, "RightDrive");
    //arm motors
        armMotor = hardwareMap.get(DcMotor.class, "ArmDrive");
    //servo motors for arm
        
        
        servo1 = hardwareMap.get(Servo.class, "servo1");
        servo2 = hardwareMap.get(Servo.class, "servo2");
        
        waitForStart();

        while (opModeIsActive()) {
        // Analog Inputs   
        MovementYaxis = this.gamepad1.left_stick_y;
        MovementXaxis = this.gamepad1.left_stick_x;
        ArmYaxis = this.gamepad1.right_stick_y;
        // Digital Inputs
        GrabberActivate = gamepad1.a;

        //arm code 
        armMotor.setPower(ArmYaxis);
        //movment code
        leftMotorPower = -MovementYaxis + -MovementXaxis;
        rightMotorPower = -MovementYaxis + MovementXaxis;
        
        leftMotor.setPower(leftMotorPower);
        rightMotor.setPower(rightMotorPower);
        
        if(GrabberActivate){
                servo1.setPosition(1);
                servo2.setPosition(1);
            
        } else{
            
            servo1.setPosition(0.5);
            servo2.setPosition(0.5);

        }
    }}
    
}
