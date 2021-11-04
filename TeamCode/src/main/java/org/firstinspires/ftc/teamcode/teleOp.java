package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
@TeleOp(name = "TeleOp!")
public class teleOp extends LinearOpMode {
    public void runOpMode(){
        int rotations = 0;
        boolean claw_close;
        boolean claw_open;
        double left;
        double right;
        boolean arm_up;
        boolean arm_down;
        boolean carousel_forward;
        boolean carousel_stop;
        boolean carousel_reverse;
        boolean forward;
        boolean backward;
        boolean left_turn;
        boolean right_turn;
        mantisesClass mantisClass = new mantisesClass(this); //this does the init

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
            arm_up = gamepad1.y&&rotations<365;
            arm_down = gamepad1.a&&rotations>0;
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
            else{}
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
//                if(rotations <365){
//                    rotations+=1;
//                }
                mantisClass.crane_arm.setTargetPosition(rotations+=1);
                mantisClass.crane_arm.setPower(0.1);
                mantisClass.crane_arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }else if (arm_down){
//                if (rotations > 0) {
//                    rotations -= 1;
//                }
                mantisClass.crane_arm.setTargetPosition(rotations-=1);
                mantisClass.crane_arm.setPower(0.1);
                mantisClass.crane_arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }else {

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