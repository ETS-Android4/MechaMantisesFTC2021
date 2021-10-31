package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class mantisesClass {


    hwMapMantises hwMap = new hwMapMantises();
    public void resetChassisEncoders(DcMotor left, DcMotor right){
        left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


    }

    public void runToPositionForward(DcMotor left, DcMotor right, int inches){
        resetChassisEncoders(left, right);
        left.setTargetPosition(inches*107);
        right.setTargetPosition(inches*107);
        left.setPower(0.5);
        right.setPower(0.5);
        left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        right.setMode(DcMotor.RunMode.RUN_TO_POSITION);



    }
    public void checkIfChassisBusy(DcMotor left, DcMotor right){
        while (left.isBusy() || right.isBusy()){

        }


    }
    public void checkIfCraneArmBusy(DcMotor craneArm){
        while(craneArm.isBusy()){

        }
    }
    public void turnLeft(DcMotor left, DcMotor right, int degrees){
        resetChassisEncoders(left, right);
        left.setTargetPosition(-(degrees*14));
        right.setTargetPosition(degrees*14);
        left.setPower(0.5);
        right.setPower(0.5);
        left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        right.setMode(DcMotor.RunMode.RUN_TO_POSITION);


    }
    public void turnRight(DcMotor left, DcMotor right, int degrees){
        resetChassisEncoders(left, right);
        left.setTargetPosition(degrees*13);
        right.setTargetPosition(-(degrees*13));
        left.setPower(0.5);
        right.setPower(0.5);
        left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        right.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }
    public void runCraneArm(DcMotor craneArm, int targetPosition, double power){
        craneArm.setTargetPosition(targetPosition);
        craneArm.setPower(power);
        craneArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public int getPos(DcMotor dcMotor){
        return dcMotor.getCurrentPosition();


    }public double getServoPos(Servo servo){
        return servo.getPosition();
    }

    public void setCraneClawPos(Servo craneClaw, double targetPos) {

        craneClaw.setPosition(targetPos);



    }


}
