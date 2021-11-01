//package org.firstinspires.ftc.teamcode;
//import com.qualcomm.hardware.bosch.BNO055IMU;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DcMotorSimple;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//import com.qualcomm.robotcore.hardware.Servo;
//import com.qualcomm.robotcore.util.ElapsedTime;
//
//public class hwMapMantises {
//    public DcMotor left_wheel;
//    public DcMotor right_wheel;
//    public DcMotor carousel;
//    public DcMotor crane_arm;
//    public Servo crane_claw;
////    public BNO055IMU imu;
//    public double startPosition = 0.0;
//    public final static double speed = 0.001;
//    public final static double max_claw = 0.4;
//    public final static double min_claw = 0.001;
//    public final static double min_arm = 1;
//    public final static double max_arm = 320;
//
//    DcMotor.RunMode encoder_true = DcMotor.RunMode.RUN_USING_ENCODER;
//    DcMotor.RunMode encoder_false = DcMotor.RunMode.RUN_WITHOUT_ENCODER;
//    public DcMotor.RunMode reset_encoder = DcMotor.RunMode.STOP_AND_RESET_ENCODER;
//    DcMotor.Direction direction_forward = DcMotor.Direction.FORWARD;
//    DcMotor.Direction direction_reverse = DcMotor.Direction.REVERSE;
//
//
//
//
//    HardwareMap hardwareMap= null;
//    public ElapsedTime runtime = new ElapsedTime();
//
//    public hwMapMantises(){}
//
//    public void initialize(HardwareMap hwMap){
//        hardwareMap = hwMap;
//
//        left_wheel = hardwareMap.get(DcMotor.class, "left_motor");
//        right_wheel = hardwareMap.get(DcMotor.class, "right_motor");
//        carousel = hardwareMap.get(DcMotor.class, "carousel_arm");
//        crane_arm = hardwareMap.get(DcMotor.class, "crane_arm");
//        crane_claw = hardwareMap.get(Servo.class, "claw_arm");
////        imu = hardwareMap.get(BNO055IMU.class, "imu");
//
//        left_wheel.setDirection(direction_reverse);
//        right_wheel.setDirection(direction_forward);
//        carousel.setDirection(direction_forward);
//        crane_arm.setDirection(direction_reverse);
//
//        left_wheel.setMode(reset_encoder);
//        right_wheel.setMode(reset_encoder);
//        carousel.setMode(reset_encoder);
//        crane_arm.setMode(reset_encoder);
//        left_wheel.setMode(encoder_true);
//        right_wheel.setMode(encoder_true);
//        carousel.setMode(encoder_true);
//        crane_arm.setMode(encoder_true);
//
//        crane_claw.setPosition(startPosition);
//
//        left_wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        right_wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        carousel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        crane_arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//
//        left_wheel.setPower(0);
//        right_wheel.setPower(0);
//        carousel.setPower(0);
//        crane_arm.setPower(0);
//
////        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
////        parameters.accelerationIntegrationAlgorithm = null;
////        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
////        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
////        parameters.calibrationData = null;
////        parameters.calibrationDataFile = "";
////        parameters.loggingEnabled = false;
////        parameters.loggingTag = "Who cares.";
//
//
//
//    }
//
//
//}
