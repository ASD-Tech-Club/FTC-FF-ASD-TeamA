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
	
    private DcMotor Motor1;
    private DcMotor Motor2;
    private DcMotor Motor3;
	
   
    private float MovementXaxis;
    private float MovementYaxis;
	
    private float ArmYaxis;
    private Servo Grabber;
    private DcMotor SpinnerMotor;
   boolean GrabberActivate;
   boolean SpinnerActivate;
   
   private triMotor MotorTranslator;
   
   private double[] triMotorVelocity;

    @Override
    public void runOpMode() {
    
        Motor1 = hardwareMap.get(DcMotor.class, "M1");
        Motor2 = hardwareMap.get(DcMotor.class, "M2");
		Motor3 = hardwareMap.get(DcMotor.class, "M3");
		
		MotorTranslator = new triMotor();
        
        waitForStart();
		
        while (opModeIsActive()) {
        // Analog Inputs
        MovementYaxis = this.gamepad1.left_stick_y;
        MovementXaxis = this.gamepad1.left_stick_x;

		triMotorVelocity = MotorTranslator.motorvelocity((double)MovementXaxis,(double)MovementYaxis,0);

        Motor1.setPower((float)triMotorVelocity[0]);
        Motor2.setPower((float)triMotorVelocity[1]);
		Motor3.setPower((float)triMotorVelocity[2]);
		
    }}
     
    }
}
