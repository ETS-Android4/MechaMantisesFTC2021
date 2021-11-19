package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
@Autonomous(name = "AutonomousRed2", group = "LinearOpMode")
@Disabled
public class AutonomousRed2 extends LinearOpMode {
    @Override
    public void runOpMode() {

        telemetry.addData("Initializing", "DO NOT START OPMODE!");
        telemetry.update();
        MantisesClass mantis = new MantisesClass(this);
        telemetry.addData("Ready To Start OpMode", "Press The Start Button To Start!");
        telemetry.update();
        waitForStart();

        mantis.runCraneArm(mantis.down_arm, 0.1);
        mantis.runDistance(7, "forward", 0.5);
        mantis.turnLeft(30, 0.3);
        mantis.runDistance(16-7.75, "forward", 0.5);
        mantis.runDistance(7, "forward", 0.2);
        mantis.setCraneClawPos(0);
        mantis.runDistance(10-7.75, "backward", 0.5);
        mantis.turnRight(120, 0.3);
        mantis.runDistance(40, "forward", 1);
        mantis.runCraneArm(mantis.reset_arm, 0.3);
        stop();
    }
}