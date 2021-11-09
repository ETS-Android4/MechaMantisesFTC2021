package org.firstinspires.ftc.teamcode;

import android.graphics.drawable.GradientDrawable;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class MantisesClass {
    LinearOpMode op = null;
    public DcMotor left_wheel;
    public DcMotor right_wheel = null;
    public DcMotor carousel = null;
    public DcMotor crane_arm = null;
    public Servo crane_claw = null;
    public Servo camera = null;
    public BNO055IMU imu = null;
    public Orientation orientation;
    public double startPosition = 0.0;
    public final static double speed = 0.001;
    public final static double max_claw = 0.4;
    public final static double min_claw = 0.001;
    public final static int min_arm = 1;
    public final static int max_arm = 390;

    private static final DcMotor.RunMode encoder_true = DcMotor.RunMode.RUN_USING_ENCODER;
    private static final DcMotor.RunMode encoder_false = DcMotor.RunMode.RUN_WITHOUT_ENCODER;
    private static final DcMotor.RunMode reset_encoder = DcMotor.RunMode.STOP_AND_RESET_ENCODER;
    private static final DcMotor.Direction direction_forward = DcMotor.Direction.FORWARD;
    private static final DcMotor.Direction direction_reverse = DcMotor.Direction.REVERSE;
    public MantisesClass(LinearOpMode opMode){
        op = opMode;
        left_wheel = opMode.hardwareMap.get(DcMotor.class, "left_motor");
        right_wheel = opMode.hardwareMap.get(DcMotor.class,"right_motor");
        carousel = opMode.hardwareMap.get(DcMotor.class, "carousel_arm");
        crane_arm = opMode.hardwareMap.get(DcMotor.class, "crane_arm");
        crane_claw = opMode.hardwareMap.get(Servo.class, "claw_arm");
        camera = opMode.hardwareMap.get(Servo.class, "camera");
        imu = opMode.hardwareMap.get(BNO055IMU.class, "imu");

        imu.initialize(new BNO055IMU.Parameters());

        orientation = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        orientation.firstAngle = 0;

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




        }

        public void resetChassisEncoders(){
            left_wheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            right_wheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            left_wheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            right_wheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


    }
//    public void turnLeftGyro(int degrees, double speed){
//        int x = 0;
//        orientation.firstAngle = 0;
//
//        while(x!=10){
//            orientation = imu.getAngularOrientation(AxesReference.INTRINSIC,AxesOrder.ZYX,AngleUnit.DEGREES);
//            if (orientation.firstAngle<degrees){
//                left_wheel.setPower(-speed);
//                right_wheel.setPower(speed);
//
//            }else{
//                left_wheel.setPower(0);
//                right_wheel.setPower(0);
//                x=10;
//            }
//        }
//
//    }
//    public void turnRightGyro(int degrees, double speed){
//        int x = 0;
//        orientation.firstAngle = 0;
//
//        while(x!=10){
//            orientation = imu.getAngularOrientation(AxesReference.INTRINSIC,AxesOrder.ZYX,AngleUnit.DEGREES);
//            if (orientation.firstAngle<-degrees){
//                left_wheel.setPower(speed);
//                right_wheel.setPower(-speed);
//
//            }else{
//                left_wheel.setPower(0);
//                right_wheel.setPower(0);
//                x=10;
//
//            }
//        }
//
//
//    }

    public void runDistance(int inches, String direction, double power){
        int MOTOR_TICK_COUNT = 1440;
        double circumfrence = 3.14*4.001;
        double rotationsNeeded = inches/circumfrence;
        int drivingTarget = (int) (rotationsNeeded*MOTOR_TICK_COUNT);
        if(direction.equals("backward")){
            drivingTarget = drivingTarget*-1;
        }else{
            drivingTarget = drivingTarget*1;
        }
        int leftDrivingTarget = left_wheel.getCurrentPosition()+drivingTarget;
        int rightDrivingTarget = right_wheel.getCurrentPosition()+drivingTarget;
        //resetChassisEncoders();
        left_wheel.setTargetPosition(leftDrivingTarget);
        right_wheel.setTargetPosition(rightDrivingTarget);
        left_wheel.setPower(power);
        right_wheel.setPower(power);
        left_wheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        right_wheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        waitIfChassisBusy();
        op.sleep(100);
        stopChassis();





    }
    public void stopMotor(){
        double left_power = left_wheel.getPower();
        double right_power = right_wheel.getPower();
        while(left_power>0.1||right_power>0.1){
            if(left_power>0.1) {
                left_power-=0.1;
            }
            if(right_power>0.1) {
                right_power-=0.1;
            }
            left_wheel.setPower(left_power);
            right_wheel.setPower(right_power);
        }
        stopChassis();
    }

    public void stopChassis(){
        left_wheel.setPower(0);
        right_wheel.setPower(0);
    }
    public void waitIfChassisBusy(){
        while (left_wheel.isBusy() || right_wheel.isBusy()){
            op.telemetry.addData("Driving", "Distance");
            op.telemetry.update();

        }


    }
    public void waitIfCraneArmBusy(){
        while(crane_arm.isBusy()){

        }
    }
    public void turnLeft(double degrees, double power){
        //resetChassisEncoders();
        degrees = degrees * 0.09111111111;
        int MOTOR_TICK_COUNT = 1440;
        double circumfrence = 3.14*4.001;
        double rotationsNeeded = degrees/circumfrence;
        int drivingTarget = (int) (rotationsNeeded*MOTOR_TICK_COUNT);
        int leftDrivingTarget = left_wheel.getCurrentPosition()-drivingTarget;
        int rightDrivingTarget = right_wheel.getCurrentPosition()+drivingTarget;
        left_wheel.setTargetPosition(leftDrivingTarget);
        right_wheel.setTargetPosition(rightDrivingTarget);
        left_wheel.setPower(power);
        right_wheel.setPower(power);
        left_wheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        right_wheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        waitIfChassisBusy();
        op.sleep(100);
        stopChassis();




    }
    public void turnRight(double degrees, double power){
        //resetChassisEncoders();
        degrees = degrees * 0.09111111111;
        int MOTOR_TICK_COUNT = 1440;
        double circumfrence = 3.14*4.001;
        double rotationsNeeded = degrees/circumfrence;
        int drivingTarget = (int) (rotationsNeeded*MOTOR_TICK_COUNT);
        int leftDrivingTarget = left_wheel.getCurrentPosition()+drivingTarget;
        int rightDrivingTarget = right_wheel.getCurrentPosition()-drivingTarget;
        left_wheel.setTargetPosition(leftDrivingTarget);
        right_wheel.setTargetPosition(rightDrivingTarget);
        left_wheel.setPower(power);
        right_wheel.setPower(power);
        left_wheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        right_wheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        waitIfChassisBusy();
        op.sleep(100);
        stopChassis();


    }
    public void runCraneArm(int targetPosition, double power){
        if(targetPosition> max_arm){
            targetPosition = max_arm;
        }
        if(targetPosition< min_arm){
            targetPosition = min_arm;
        }
        crane_arm.setTargetPosition(targetPosition);
        crane_arm.setPower(power);
        crane_arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        waitIfCraneArmBusy();

    }

    public int getPos(DcMotor dcMotor){
        return dcMotor.getCurrentPosition();


    }public double getServoPos(Servo servo){
        return servo.getPosition();
    }

    public void setCraneClawPos(double targetPos) {
        if(targetPos>max_claw){
            targetPos = max_claw;

        }
        if (targetPos<min_claw) {
            targetPos = min_claw;

        }

        crane_claw.setPosition(targetPos);



    }
    public void runCarousel(){
        carousel.setTargetPosition(1480*5);
        carousel.setPower(1);
        carousel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        waitIfCarouselIsBusy();
    }
    public void waitIfCarouselIsBusy(){
        while (carousel.isBusy()){

        }
    }


}
