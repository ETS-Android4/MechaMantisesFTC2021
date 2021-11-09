package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
@Autonomous(name = "TestTurn")
public class TestTurn extends LinearOpMode {
    public void runOpMode(){
        MantisesClass mantis = new MantisesClass(this);
        waitForStart();
        mantis.turnLeft(8.2, 0.3);
        stop();
    }
}
