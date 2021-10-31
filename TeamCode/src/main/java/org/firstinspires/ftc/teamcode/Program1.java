package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "AutonomousBlue", group = "Linear Opmode")

public class Program1 extends LinearOpMode {
    hwMapMantises hwMap = new hwMapMantises();
    mantisesClass mantisClass = new mantisesClass();
    @Override
    public void runOpMode(){
        hwMap.initialize(hardwareMap);
        waitForStart();

        mantisClass.resetChassisEncoders(hwMap.left_wheel, hwMap.right_wheel);
        mantisClass.setCraneClawPos(hwMap.crane_claw, 0.4);
        mantisClass.runCraneArm(hwMap.crane_arm, 200, 0.1);
        mantisClass.checkIfCraneArmBusy(hwMap.crane_arm);
        mantisClass.runToPositionForward(hwMap.left_wheel, hwMap.right_wheel, 8, 0.5);
        mantisClass.checkIfChassisBusy(hwMap.left_wheel, hwMap.right_wheel);
        mantisClass.turnRight(hwMap.left_wheel, hwMap.right_wheel,90, 0.5);
        mantisClass.checkIfChassisBusy(hwMap.left_wheel, hwMap.right_wheel);
        mantisClass.runToPositionForward(hwMap.left_wheel, hwMap.right_wheel, 12, 0.5);
        mantisClass.checkIfChassisBusy(hwMap.left_wheel, hwMap.right_wheel);


    }
}
