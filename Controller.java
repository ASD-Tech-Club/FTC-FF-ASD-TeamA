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
    private Servo Grabber;
    private DcMotor SpinnerMotor;
   boolean GrabberActivate;
   boolean SpinnerActivate;

    @Override
    public void runOpMode() {
    
        leftMotor = hardwareMap.get(DcMotor.class, "LeftDrive");
        rightMotor = hardwareMap.get(DcMotor.class, "RightDrive");
        armMotor = hardwareMap.get(DcMotor.class, "ArmDrive");
        SpinnerMotor = hardwareMap.get(DcMotor.class, "SpinnerMotor");
        Grabber = hardwareMap.get(Servo.class, "Grabber");
        
        waitForStart();

        while (opModeIsActive()) {
        // Analog Inputs   
        MovementYaxis = this.gamepad1.left_stick_y;
        MovementXaxis = this.gamepad1.left_stick_x;
        ArmYaxis = this.gamepad1.right_stick_y;
		// Digital Inputs
		GrabberActivate = gamepad1.a;
		SpinnerActivate = gamepad1.y;

        //arm code 
        armMotor.setPower(ArmYaxis);
        
        int ArmPowerIndicator = Math.abs( (int)(ArmYaxis*10));
        telemetry.addData("Arm:", generateBar(ArmPowerIndicator));

        //movment code
        leftMotorPower = -MovementYaxis + -MovementXaxis;
        rightMotorPower = -MovementYaxis + MovementXaxis;
        
        leftMotor.setPower(leftMotorPower);
        rightMotor.setPower(rightMotorPower);
        
        if(GrabberActivate){
                Grabber.setPosition(0.5);
            
        } else{
            
            Grabber.setPosition(0);
        }
        
        if(SpinnerActivate){
            SpinnerMotor.setPower(1);
            
        }else{
            SpinnerMotor.setPower(0);

        }
        
        
        int LeftPowerIndicator = Math.abs( (int)(leftMotorPower*10));
        int RightPowerIndicator = Math.abs((int)(rightMotorPower*10));
        telemetry.addData("Movement Motor 1: ", generateBar(LeftPowerIndicator));
       
    }}
    
    public String generateBar(int power){ // power bar code
        
            String output = "";
            for(int i = 0; i < power ;i++){
            output += "█";
            }
        return output;
            
        
        
        
    }
}
