package com.github.ros_java.android_ROS.controller;

/**
 * Created by erki on 2/11/16.
 */
public class BridgeToPublisher {

    public static float speed = 0;
    public static float angle = 0;
    public static float turn = 0;
    public static boolean kick = false;
    public static String text = "";
    public static int progressBar = 0;

    //From the left joystick
    public static void set_movement(int x, int y) {
        speed = (float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2))/25;
        /*
        Log.d("X Angle", "" + x);
        Log.d("Y Angle", "" + y);
        */
        if(progressBar != 0) {
            speed = speed * progressBar;
        }
        double angle1 = Math.atan2(10, 0);
        double angle2 = Math.atan2(y, x );
        float degToRad = (float) (-1*(angle1 - angle2));
        angle = degToRad;


    }

    //From the right joystick
    public static void set_turn_and_kick(int x, int y) {
        if(y >= 8) kick = true;
        else kick = false;
        double turnD = -1*x/10.0*Math.PI;

        turn = (float) turnD;
        if(x <=1 && x >= -1 && y >= 6) turn = 0;

    }



}
