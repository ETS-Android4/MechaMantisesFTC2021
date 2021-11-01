package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class mantisesClass{
    LinearOpMode op = null;
    public DcMotor left_wheel = null;
    public DcMotor right_wheel = null;
    public DcMotor carousel = null;
    public DcMotor crane_arm = null;
    public Servo crane_claw = null;
    //    public BNO055IMU imu;
    public double startPosition = 0.0;
    public final static double speed = 0.001;
    public final static double max_claw = 0.4;
    public final static double min_claw = 0.001;
    public final static double min_arm = 1;
    public final static double max_arm = 320;

    private static final DcMotor.RunMode encoder_true = DcMotor.RunMode.RUN_USING_ENCODER;
    private static final DcMotor.RunMode encoder_false = DcMotor.RunMode.RUN_WITHOUT_ENCODER;
    private static final DcMotor.RunMode reset_encoder = DcMotor.RunMode.STOP_AND_RESET_ENCODER;
    private static final DcMotor.Direction direction_forward = DcMotor.Direction.FORWARD;
    private static final DcMotor.Direction direction_reverse = DcMotor.Direction.REVERSE;
    public mantisesClass(LinearOpMode opMode){
        op = opMode;
        left_wheel = opMode.hardwareMap.dcMotor.get("left_motor");
        right_wheel = opMode.hardwareMap.dcMotor.get("right_motor");
        carousel = opMode.hardwareMap.dcMotor.get("carousel_arm");
        crane_arm = opMode.hardwareMap.dcMotor.get("crane_arm");
        crane_claw = opMode.hardwareMap.servo.get("claw_arm");
//        imu = hardwareMap.get(BNO055IMU.class, "imu");

        left_wheel.setDirection(direction_reverse);
        right_wheel.setDirection(direction_forward);
        carousel.setDirection(direction_forward);
        crane_arm.setDirection(direction_reverse);

        left_wheel.setMode(reset_encoder);
        right_wheel.setMode(reset_encoder);
        carousel.setMode(reset_encoder);
        crane_arm.setMode(reset_encoder);
        left_wheel.setMode(encoder_true);
        right_wheel.setMode(encoder_true);
        carousel.setMode(encoder_true);
        crane_arm.setMode(encoder_true);

        crane_claw.setPosition(startPosition);

        left_wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right_wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        carousel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        crane_arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        left_wheel.setPower(0);
        right_wheel.setPower(0);
        carousel.setPower(0);
        crane_arm.setPower(0);

//        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
//        parameters.accelerationIntegrationAlgorithm = null;
//        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
//        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
//        parameters.calibrationData = null;
//        parameters.calibrationDataFile = "";
//        parameters.loggingEnabled = false;
//        parameters.loggingTag = "Who cares.";



        }
        public void resetChassisEncoders(){
        left_wheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right_wheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


    }

    public void runToPosition(int inches, String direction){
        if(direction == "backward"){
            inches = inches*-1;
        }else{
            inches = inches*1;
        }
        resetChassisEncoders();
        left_wheel.setTargetPosition(inches*107);
        right_wheel.setTargetPosition(inches*107);
        left_wheel.setPower(0.5);
        right_wheel.setPower(0.5);
        left_wheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        right_wheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        checkIfChassisBusy();



    }
    public void checkIfChassisBusy(){
        while (left_wheel.isBusy() || right_wheel.isBusy()){

        }


    }
    public void checkIfCraneArmBusy(){
        while(crane_arm.isBusy()){

        }
    }
    public void turnLeft(int degrees){
        resetChassisEncoders();
        left_wheel.setTargetPosition(-(degrees*14));
        right_wheel.setTargetPosition(degrees*14);
        left_wheel.setPower(0.5);
        right_wheel.setPower(0.5);
        left_wheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        right_wheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        checkIfChassisBusy();


    }
    public void turnRight(int degrees){
        resetChassisEncoders();
        left_wheel.setTargetPosition(degrees*13);
        right_wheel.setTargetPosition(-(degrees*13));
        left_wheel.setPower(0.5);
        right_wheel.setPower(0.5);
        left_wheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        right_wheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        checkIfChassisBusy();

    }
    public void runCraneArm(int targetPosition, double power){
        crane_arm.setTargetPosition(targetPosition);
        crane_arm.setPower(power);
        crane_arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        checkIfCraneArmBusy();
    }

    public int getPos(DcMotor dcMotor){
        return dcMotor.getCurrentPosition();


    }public double getServoPos(Servo servo){
        return servo.getPosition();
    }

    public void setCraneClawPos(double targetPos) {

        crane_claw.setPosition(targetPos);



    }
    public void runCarousel(){
        carousel.setTargetPosition(1440*5);
        carousel.setPower(1);
        carousel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void checkIfCarouselIsBusy(){
        while (carousel.isBusy()){

        }
    }


}
