package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "AutonomousBlue", group = "Linear Opmode")

public class Program1 extends LinearOpMode {

    mantisesClass mantisClass = new mantisesClass(this);

    @Override
    public void runOpMode(){
        waitForStart();
        mantisClass.resetChassisEncoders();
        mantisClass.setCraneClawPos(0.4);
        mantisClass.runCraneArm(350, 0.1);
        mantisClass.checkIfCraneArmBusy();
        mantisClass.runToPosition(8, "forward");
        mantisClass.checkIfChassisBusy();
        mantisClass.turnLeft(35);
        mantisClass.checkIfChassisBusy();
        mantisClass.runToPosition(30, "forward");
        mantisClass.checkIfChassisBusy();
        mantisClass.setCraneClawPos(0);
        mantisClass.runToPosition(18, "backward");
        mantisClass.checkIfChassisBusy();
        mantisClass.turnLeft(45);
        mantisClass.checkIfChassisBusy();
        mantisClass.runToPosition(30, "backward");
        mantisClass.checkIfChassisBusy();
        mantisClass.turnRight(75);
        mantisClass.checkIfChassisBusy();
        mantisClass.runToPosition(20, "backward");
        mantisClass.checkIfChassisBusy();
        mantisClass.runCarousel();
        mantisClass.checkIfCarouselIsBusy();
        mantisClass.runToPosition(20, "forward");
        mantisClass.checkIfChassisBusy();
        mantisClass.runCraneArm(0, 0.1);
        mantisClass.checkIfCraneArmBusy();
        stop();
    }
}
