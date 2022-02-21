package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp

public class Controller extends LinearOpMode {
    //motor objects
    private DcMotor leftMotor;
    private DcMotor rightMotor;
    private DcMotor armMotor;
	private DcMotor tableMotor;
	private Servo armServo

    private float leftMotorPower;
    private float rightMotorPower;

    private float MovementXaxis;
    private float MovementYaxis;
    private float ArmYaxis;
	
	private boolean armServoActive;
	private boolean tableMotorActive;
	
	


    @Override
    public void runOpMode() {
    
   	 leftMotor = hardwareMap.get(DcMotor.class, "LeftDrive");
   	 rightMotor = hardwareMap.get(DcMotor.class, "RightDrive");
   	 rmMotor = hardwareMap.get(DcMotor.class, "ArmDrive");
	 tableMotor = hardwareMap.get(DcMotor.class, "Spinner");
	 armServo = hardwareMap.get(Servo.class, "Grabber");
	
   	 waitForStart();

   	 while (opModeIsActive()) {

    // grabs current value of controller inputs

    MovementYaxis = this.gamepad1.left_stick_y;
   	MovementXaxis = this.gamepad1.left_stick_x;
    ArmYaxis = this.gamepad1.right_stick_y;
	armServoActive = this.gamepad1.a;
	tableMotorActive = this.gamepad1.y;
   	 
    // converts Y,X input of left_stick into independent motor control
   	 leftMotorPower = MovementYaxis - MovementXaxis;
   	 rightMotorPower = MovementYaxis + MovementXaxis;

  	 //sets motor power
   	 leftMotor.setPower(leftMotorPower);
   	 rightMotor.setPower(rightMotorPower);
  	 armMotor.setPower(ArmYaxis);
	 
	 if(tableMotorActive){
		tableMotor.setPower(1);
	 }else{
		 tableMotor.setPower(0);	 
	 }
	 
	 if(armServoActive){
		 armServo.setPosition(1);
	 }else{
		 armServo.setPosition(0);
		 
	 }
	 
    
    //displays current power onto tablet
        	telemetry.addData("Power: ", "Left Motor:" + leftMotorPower*100 + "% Right Motor:" + rightMotorPower*100 + "%" );  
   		 telemetry.update();
   		 }
    }
    
}
