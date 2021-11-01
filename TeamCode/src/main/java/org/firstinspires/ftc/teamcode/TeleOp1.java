//package org.firstinspires.ftc.teamcode;
//
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.DcMotor;
//
//@TeleOp(name = "TeleOp", group = "Linear Opmode")
//public class TeleOp1 extends OpMode {
//    int rotations;
//    hwMapMantises hwMap = new hwMapMantises();
//    mantisesClass mantisClass = new mantisesClass();
//    boolean claw_close;
//    boolean claw_open;
//    double left;
//    double right;
//    boolean arm_up;
//    boolean arm_down;
//    boolean carousel_forward;
//    boolean carousel_stop;
//    boolean carousel_reverse;
//    boolean forward;
//    boolean backward;
//    boolean left_turn;
//    boolean right_turn;
//    @Override
//    public void init() {
//        hwMap.initialize(hardwareMap);
//    }
//    @Override
//    public void init_loop() {
//    }
//    @Override
//    public void start() {
//
//    }
//    @Override
//    public void loop(){
//        left = -gamepad1.left_stick_y;
//        right = -gamepad1.right_stick_y;
//        claw_close = gamepad2.y&&mantisClass.getServoPos(hwMap.crane_claw)>0;
//        claw_open = gamepad2.a&&mantisClass.getServoPos(hwMap.crane_claw)<0.4;
//        carousel_forward = gamepad1.b;
//        carousel_stop = gamepad1.x;
//        carousel_reverse = gamepad1.y;
//        arm_up = gamepad2.dpad_up&&mantisClass.getPos(hwMap.crane_arm)<370;
//        arm_down = gamepad2.dpad_down&&mantisClass.getPos(hwMap.crane_arm)>0;
//        forward = gamepad1.dpad_up;
//        backward = gamepad1.dpad_down;
//        left_turn = gamepad1.dpad_left;
//        right_turn = gamepad1.dpad_right;
//        if(claw_open){
//            hwMap.crane_claw.setPosition(hwMap.startPosition+= hwMap.speed);
//        }else if (claw_close) {
//            hwMap.crane_claw.setPosition(hwMap.startPosition -= hwMap.speed);
//        }
//        else{
//
//        }
//        if (left==0&&right==0){
//            if(forward){
//                hwMap.left_wheel.setPower(0.3);
//                hwMap.right_wheel.setPower(0.3);
//            }else if(backward){
//                hwMap.left_wheel.setPower(-0.3);
//                hwMap.right_wheel.setPower(-0.3);
//            }else if(left_turn){
//                hwMap.left_wheel.setPower(-0.3);
//                hwMap.right_wheel.setPower(0.3);
//            }else if(right_turn){
//                hwMap.left_wheel.setPower(0.3);
//                hwMap.right_wheel.setPower(-0.3);
//            }
//            else{
//                hwMap.left_wheel.setPower(0);
//                hwMap.right_wheel.setPower(0);
//            }
//        }else{
//            hwMap.left_wheel.setPower(left);
//            hwMap.right_wheel.setPower(right);
//        }
//
//
//        if (arm_up){
//            hwMap.crane_arm.setTargetPosition(rotations+=1);
//            hwMap.crane_arm.setPower(0.1);
//            hwMap.crane_arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            telemetry.addData("Motor Ticks", hwMap.crane_arm.getCurrentPosition());
//            telemetry.update();
//        }else if (arm_down){
//            hwMap.crane_arm.setTargetPosition(rotations-=1);
//            hwMap.crane_arm.setPower(0.1);
//            hwMap.crane_arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        }else {
//
//        }
//        if (carousel_forward){
//            hwMap.carousel.setPower(1);
//        }else  if(carousel_stop){
//            hwMap.carousel.setPower(0);
//        }else if(carousel_reverse){
//            hwMap.carousel.setPower(-1);
//        }else {
//
//        }
//    }
//}