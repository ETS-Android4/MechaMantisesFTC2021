package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

@Autonomous(name = "AutonomousBlue", group = "Linear Opmode")

public class Program1 extends LinearOpMode {





    @Override
    public void runOpMode(){
        mantisesClass mantisClass = new mantisesClass(this);


        waitForStart();


//            mantisClass.turnLeftGyro(90, 0.1);

        mantisClass.setCraneClawPos(0.4);
        mantisClass.runCraneArm(345, 0.1);
        mantisClass.runDistance(8, "forward", 0.5);
        mantisClass.turnLeft(30, 0.3);
        mantisClass.runDistance(20, "forward", 0.5);
        mantisClass.runDistance(9, "forward", 0.2);
        mantisClass.setCraneClawPos(0);
        mantisClass.runDistance(18, "backward", 0.5);
        mantisClass.turnLeft(45, 0.3);
        mantisClass.runDistance(30, "backward", 0.5);
        mantisClass.turnRight(75, 0.3);
        mantisClass.runDistance(20, "backward", 0.5);
        mantisClass.runCarousel();
        mantisClass.runDistance(23, "forward", 0.5);
        mantisClass.runCraneArm(0, 0.1);
        stop();


    }
}
