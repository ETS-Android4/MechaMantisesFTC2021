package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
@Autonomous(name = "AutonomousBlue2")
public class AutonomousBlue2 extends LinearOpMode {
    public void runOpMode(){
        MantisesClass mantis = new MantisesClass(this);
        telemetry.addData("Ready To Start OpMode", "Press The Start Button To Start!");
        telemetry.update();
        waitForStart();
        mantis.runDistance(15, "forward", 0.5);
        mantis.turnLeft(90, 0.3);
        mantis.runCraneArm(400, 0.1);
        mantis.runDistance(35, "forward", 1);
        mantis.runCraneArm(0, 0.1);
        stop();
    }
}
