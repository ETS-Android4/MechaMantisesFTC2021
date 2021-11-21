package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
@TeleOp(name = "TeleOp!")
public class TeleOpRun extends LinearOpMode {
    public void runOpMode(){
        //int rotations = 0;
        boolean claw_close;
        boolean claw_open;
        double left;
        double right;
        boolean arm_up;
        boolean arm_down;
        boolean arm_middle;
        boolean arm_off;
        boolean carousel_forward;
        boolean carousel_stop;
        boolean carousel_reverse;
        boolean forward;
        boolean backward;
        boolean left_turn;
        boolean right_turn;
        MantisesClass mantisClass = new MantisesClass(this); //this does the init

        while (!opModeIsActive() && !isStopRequested()) {//you can just use waitForStart() here, but this prints stuff out, you guys don't have this so it would be running while you have not hit the start button which is illegal
            telemetry.addData("status", "waiting for start command...");
            telemetry.update();
        }
        while(!isStopRequested()){
            left = -gamepad1.left_stick_y;
            right = -gamepad1.right_stick_y;
            claw_close = gamepad1.x&&mantisClass.getServoPos(mantisClass.crane_claw)>0;
            claw_open = gamepad1.b&&mantisClass.getServoPos(mantisClass.crane_claw)<0.4;
            carousel_forward = gamepad1.left_bumper;
            carousel_stop = gamepad1.right_bumper;
            carousel_reverse = gamepad1.start;
            arm_up = gamepad2.y/*&&rotations<mantisClass.max_arm*/;
            arm_down = gamepad2.a/*&&rotations>mantisClass.min_arm*/;
            arm_middle = gamepad2.x;
            arm_off = gamepad2.b;
            forward = gamepad1.dpad_up;
            backward = gamepad1.dpad_down;
            left_turn = gamepad1.dpad_left;
            right_turn = gamepad1.dpad_right;
            if(claw_open){
                if(mantisClass.startPosition<0.4){
                    mantisClass.startPosition+=mantisClass.speed;
                }
                mantisClass.crane_claw.setPosition(mantisClass.startPosition+= mantisClass.speed);
            }else if (claw_close) {
                mantisClass.crane_claw.setPosition(mantisClass.startPosition -= mantisClass.speed);
            }
            if (left==0&&right==0){
                if(forward){
                    mantisClass.left_wheel.setPower(0.3);
                    mantisClass.right_wheel.setPower(0.3);
                }else if(backward){
                    mantisClass.left_wheel.setPower(-0.3);
                    mantisClass.right_wheel.setPower(-0.3);
                }else if(left_turn){
                    mantisClass.left_wheel.setPower(-0.3);
                    mantisClass.right_wheel.setPower(0.3);
                }else if(right_turn){
                    mantisClass.left_wheel.setPower(0.3);
                    mantisClass.right_wheel.setPower(-0.3);
                }
                else{
                    mantisClass.left_wheel.setPower(0);
                    mantisClass.right_wheel.setPower(0);
                }
            }else{
                mantisClass.left_wheel.setPower(left);
                mantisClass.right_wheel.setPower(right);
            }
            if (arm_up){
                mantisClass.crane_arm.setTargetPosition(mantisClass.up_arm);
                mantisClass.crane_arm.setPower(0.1);
                mantisClass.crane_arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                if(rotations <365){
//                    rotations+=1;
//                }
//                mantisClass.crane_arm.setTargetPosition(rotations+=1);
//                mantisClass.crane_arm.setPower(0.1);
//                mantisClass.crane_arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
               // mantisClass.runCraneArm(400, 0.1);

            }else if (arm_down){
                mantisClass.crane_arm.setTargetPosition(mantisClass.down_arm);
                mantisClass.crane_arm.setPower(0.1);
                mantisClass.crane_arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                if (rotations > 0) {
//                    rotations -= 1;
//                }
//                mantisClass.crane_arm.setTargetPosition(rotations-=1);
//                mantisClass.crane_arm.setPower(0.1);
//                mantisClass.crane_arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
               // mantisClass.runCraneArm(100, 0.1);
            }else if(arm_middle) {
                mantisClass.crane_arm.setTargetPosition(mantisClass.middle_arm);
                mantisClass.crane_arm.setPower(0.1);
                mantisClass.crane_arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                //mantisClass.runCraneArm(275, 0.1);
            }
            else if(arm_off){
                mantisClass.crane_arm.setTargetPosition(mantisClass.reset_arm);
                mantisClass.crane_arm.setPower(0.1);
                mantisClass.crane_arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

//                mantisClass.runCraneArm(0, 0.1);
//                mantisClass.crane_arm.setPower(0);
            }
            if (carousel_forward){
                mantisClass.carousel.setPower(1);
            }else  if(carousel_stop){
                mantisClass.carousel.setPower(0);
            }else if(carousel_reverse){
                mantisClass.carousel.setPower(-1);
            }else {}
        }
        stop();//stops the program
    }
}