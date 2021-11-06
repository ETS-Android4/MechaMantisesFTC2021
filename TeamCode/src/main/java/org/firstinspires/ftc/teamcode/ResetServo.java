package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Servo")
@Disabled
public class ResetServo extends LinearOpMode {
    public void runOpMode(){
        MantisesClass mantis = new MantisesClass(this);
        waitForStart();
        while(opModeIsActive()){

            telemetry.addData("this:", mantis.camera.getPosition());
            telemetry.update();
        }
    }


}
