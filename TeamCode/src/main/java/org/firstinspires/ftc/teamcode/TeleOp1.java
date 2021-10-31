package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "TeleOp", group = "Linear Opmode")
public class TeleOp1 extends OpMode {
    int rotations;
    hwMapMantises hwMap = new hwMapMantises();
    mantisesClass mantisClass = new mantisesClass();


    boolean claw_close;
    boolean claw_open;
    double left;
    double right;
    boolean arm_up;
    boolean arm_down;
    boolean caresoul;
    boolean caresoul_stop;


    @Override
    public void init() {
        hwMap.initialize(hardwareMap);
    }

    @Override
    public void init_loop() {
    }


    @Override
    public void start() {

    }

    @Override
    public void loop(){
        left = -gamepad1.left_stick_y;
        right = -gamepad1.right_stick_y;
        claw_close = gamepad1.y&&mantisClass.getServoPos(hwMap.crane_claw)>0;
        claw_open = gamepad1.a&&mantisClass.getServoPos(hwMap.crane_claw)<0.4;
        hwMap.left_wheel.setPower(left);
        hwMap.right_wheel.setPower(right);
        caresoul = gamepad1.right_bumper;
        caresoul_stop = gamepad1.left_bumper;
        arm_up = gamepad1.dpad_up&&mantisClass.getPos(hwMap.crane_arm)<320;
        arm_down = gamepad1.dpad_down&&mantisClass.getPos(hwMap.crane_arm)>0;



        if(claw_open){
            hwMap.crane_claw.setPosition(hwMap.startPosition+= hwMap.speed);
        }else if (claw_close) {
            hwMap.crane_claw.setPosition(hwMap.startPosition -= hwMap.speed);
        }
        else{

        }
        if (arm_up){
            hwMap.crane_arm.setTargetPosition(rotations+=1);
            hwMap.crane_arm.setPower(0.1);
            hwMap.crane_arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }else if (arm_down){
            hwMap.crane_arm.setTargetPosition(rotations-=1);
            hwMap.crane_arm.setPower(0.1);
            hwMap.crane_arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }else {

        }

        if (caresoul){
            hwMap.carousel.setPower(1);
        }else  if(caresoul_stop){
            hwMap.carousel.setPower(0);
        }



    }
}