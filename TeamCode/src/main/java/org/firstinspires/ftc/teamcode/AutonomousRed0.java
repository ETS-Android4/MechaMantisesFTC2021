package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import java.util.List;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
@Autonomous(name = "AutonomousRed0", group = "LinearOpMode")
public class AutonomousRed0 extends LinearOpMode {
    @Override
    public void runOpMode() {
        telemetry.addData("Initializing", "DO NOT START OPMODE!");
        telemetry.update();
        MantisesClass mantis = new MantisesClass(this);
        telemetry.addData("Ready To Start OpMode", "Press The Start Button To Start!");
        telemetry.update();
        waitForStart();

        mantis.runCraneArm(mantis.middle_arm, 0.1);
        mantis.runDistance(7, "forward", 0.6);
        mantis.turnRight(40, 0.3);
        mantis.runDistance(15-4, "forward", 0.5);
        mantis.runDistance(14, "forward", 0.2);
        mantis.setCraneClawPos(0);
        mantis.runDistance(22-4, "backward", 0.5);
        mantis.turnRight(55, 0.3);
        mantis.runDistance(26, "backward", 0.5);
        mantis.turnLeft(69, 0.3);
        mantis.runDistance(13, "backward", 0.5);
        mantis.runDistance(5, "backward", 0.2);
        mantis.runCarouselRev();
        mantis.runDistance(21, "forward", 1);
        mantis.runCraneArm(MantisesClass.reset_arm, 0.3);
        stop();
    }

}