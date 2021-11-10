
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import java.util.List;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;


@Autonomous(name = "AutonomousBlue3", group = "Concept")
//@Disabled
public class AutonomousBlue3 extends LinearOpMode {

    private static final String TFOD_MODEL_ASSET = "FreightFrenzy_DM.tflite";
    private static final String[] LABELS = {
            "Duck",
            "Marker"
    };


    private static final String VUFORIA_KEY =
            "AckJVyv/////AAABmVTYhXy+2kKblacQ/Kj23axJUG1tC4BGaCJHvXW9RO9dIdeQxmVRyLL70kcUjvC2eNt3nxcokoC4d+E0H4N+ah4PAaqkxk1q20takUJ3ILjj19Md6iMYrSToAoRXP0mF1GbB7zSECEXduXe2bs08F9qekY4M0QoTHXeSiaCHo2X8TfA0NsvqSE9nBGgVJi3hUGe/h+/ug5MUAmZsbWKQMQNCpG3E/Lu44sbeet4rs2AimQITW33KXR3t99OEmYYfkjKa+jAl3yvbq1zuNVGauURIX9wlvueS5mRuk4tkN3Ax5O+67wuL+UoGNmoXS3Fb1X/ur4ZdX+FaWd03aBjKN+MVTUYDZqnVfgZpXenUV9sg";

    /**
     * {@link #vuforia} is the variable we will use to store our instance of the Vuforia
     * localization engine.
     */
    private VuforiaLocalizer vuforia;

    /**
     * {@link #tfod} is the variable we will use to store our instance of the TensorFlow Object
     * Detection engine.
     */
    private TFObjectDetector tfod;
    //private int craneArmPos = 0;

    @Override
    public void runOpMode() {
        MantisesClass mantis = new MantisesClass(this);
        telemetry.addData("Initializing", "DO NOT START OPMODE!");
        telemetry.update();
        // The TFObjectDetector uses the camera frames from the VuforiaLocalizer, so we create that
        // first.
        initVuforia();
        initTfod();

        /**
         * Activate TensorFlow Object Detection before we wait for the start command.
         * Do it here so that the Camera Stream window will have the TensorFlow annotations visible.
         **/
        if (tfod != null) {
            tfod.activate();

            // The TensorFlow software will scale the input images from the camera to a lower resolution.
            // This can result in lower detection accuracy at longer distances (> 55cm or 22").
            // If your target is at distance greater than 50 cm (20") you can adjust the magnification value
            // to artificially zoom in to the center of image.  For best results, the "aspectRatio" argument
            // should be set to the value of the images used to create the TensorFlow Object Detection model
            // (typically 16/9).
            tfod.setZoom(1, 16.0/9.0);
        }

        /** Wait for the game to begin */

        telemetry.addData("Ready To Start OpMode", "Press The Start Button To Start!");
        telemetry.update();

        waitForStart();
        // mantis.runDistance(1,"forward", 0.2);
        sleep(500);


        if (tfod != null) {
            // getUpdatedRecognitions() will return null if no new information is available since
            // the last time that call was made.
            List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
            if (updatedRecognitions != null) {
                telemetry.addData("# Object Detected", updatedRecognitions.size());
                sleep(1000);
                // step through the list of recognitions and display boundary info.
                //int i = 0;
                int location = 0;
                for (Recognition recognition : updatedRecognitions) {
                    if(recognition.getLabel().equals("Duck")){
                        if(recognition.getLeft()>350&&recognition.getLeft()<750){
                            location = 1;
                        }else if(recognition.getLeft()>750&&recognition.getLeft()<1100){
                            location = 2;
                        }
                        else{
                            location = 0;
                        }
                    }
                }
                telemetry.addData("Location", location);
                telemetry.update();
                //sleep(1000);
                if(location == 0){
                    mantis.setCraneClawPos(0.4);
                    mantis.runCraneArm(400, 0.1);
                    // sleep(10000);
                    mantis.runDistance(7, "forward", 0.5);
                    mantis.turnLeft(40, 0.3);
                    mantis.runDistance(15, "forward", 0.5);
                    mantis.runDistance(14, "forward", 0.2);
                    mantis.setCraneClawPos(0);
                    mantis.runDistance(22, "backward", 0.5);
                    mantis.turnLeft(55, 0.3);
                    mantis.runDistance(26, "backward", 0.5);
                    mantis.turnRight(90, 0.3);
                    mantis.runDistance(13, "backward", 0.5);
                    mantis.runDistance(5, "backward", 0.2);
                    mantis.runCarousel();
                    mantis.runDistance(21, "forward", 0.5);
                    mantis.runCraneArm(0, 0.3);

                }else if(location == 1){
                    AutonomousRun(mantis, 275, 0);

//                            mantis.setCraneClawPos(0.4);
//                            mantis.runCraneArm(250, 0.1);
//                           // sleep(10000);
//                            mantis.runDistance(8, "forward", 0.5);
//                            mantis.turnLeft(30, 0.3);
//                            mantis.runDistance(15, "forward", 0.5);
//                            mantis.runDistance(10, "forward", 0.2);
//                            mantis.setCraneClawPos(0);
//                            mantis.runDistance(18, "backward", 0.5);
//                            mantis.turnLeft(40, 0.3);
//                            mantis.runDistance(25, "backward", 0.5);
//                            mantis.turnRight(75, 0.3);
//                            mantis.runDistance(13, "backward", 0.5);
//                            mantis.runCarousel();
//                            mantis.runDistance(21, "forward", 0.5);
//                            mantis.runCraneArm(0, 0.1);
                }else if(location == 2){
                    AutonomousRun(mantis, 100, 0);
//                            mantis.setCraneClawPos(0.4);
//                            mantis.runCraneArm(100, 0.1);
//                           // sleep(10000);
//                            mantis.runDistance(8, "forward", 0.5);
//                            mantis.turnLeft(30, 0.3);
//                            mantis.runDistance(15, "forward", 0.5);
//                            mantis.runDistance(10, "forward", 0.2);
//                            mantis.setCraneClawPos(0);
//                            mantis.runDistance(18, "backward", 0.5);
//                            mantis.turnLeft(40, 0.3);
//                            mantis.runDistance(25, "backward", 0.5);
//                            mantis.turnRight(75, 0.3);
//                            mantis.runDistance(13, "backward", 0.5);
//                            mantis.runCarousel();
//                            mantis.runDistance(21, "forward", 0.5);
//                            mantis.runCraneArm(0, 0.1);
                }

            }
        }
        stop();


    }

    /**
     * Initialize the Vuforia localization engine.
     */
    private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = CameraDirection.BACK;

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the TensorFlow Object Detection engine.
    }

    /**
     * Initialize the TensorFlow Object Detection engine.
     */
    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minResultConfidence = 0.8f;
        tfodParameters.isModelTensorFlow2 = true;
        tfodParameters.inputSize = 320;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABELS);
    }
    private void AutonomousRun(MantisesClass mantis, int craneArmPos, int offset){
//        mantis.setCraneClawPos(0.4);
//        mantis.runCraneArm(craneArmPos, 0.1);
//        mantis.runDistance(13, "forward", 0.75);
//        mantis.turnLeft(90, 0.3);
//        mantis.runDistance(25, "forward", 0.75);
//        mantis.turnRight(97, 0.3);
//        mantis.runDistance(9+offset,"forward", .75);
//        mantis.runDistance(3,"forward" ,0.2);
//        mantis.setCraneClawPos(0.0);
//        mantis.runDistance(12+offset, "backward", 0.75);
//        mantis.turnLeft(95, 0.3);
//        mantis.runDistance(35, "backward", 1);
//        sleep(100);
//        mantis.runDistance(10, "backward", 0.2);
//        mantis.turnRight(90, 0.3);



        mantis.setCraneClawPos(0.4);
        mantis.runCraneArm(craneArmPos, 0.1);
        // sleep(10000);
        mantis.runDistance(7, "forward", 0.5);
        mantis.turnRight(35, 0.3);
        mantis.runDistance(15, "forward", 0.5);
        mantis.runDistance(10+offset, "forward", 0.2);
        mantis.setCraneClawPos(0);
        mantis.runDistance(10+offset, "backward", 0.5);
        mantis.turnLeft(125, 0.3);
        mantis.runDistance(46, "forward", 1);
//        mantis.turnRight(90, 0.3);
//        mantis.runDistance(13, "backward", 0.5);
//        mantis.runDistance(5, "backward", 0.2);
//        mantis.runCarousel();
//        mantis.runDistance(21, "forward", 0.5);
//        mantis.runCraneArm(0, 0.3);
    }
}
