package com.github.ros_java.android_ROS.controller;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import org.ros.android.BitmapFromCompressedImage;
import org.ros.android.MessageCallable;
import org.ros.android.RosActivity;
import org.ros.android.view.RosTextView;
import org.ros.node.NodeConfiguration;
import org.ros.node.NodeMainExecutor;

import java.util.List;

import Joystick.DualJoystickView;
import Joystick.JoystickMovedListener;
import msgs.Ball;
import msgs.ImageData;
import sensor_msgs.CompressedImage;


public class Controller extends RosActivity
{
    private RosTextView<ImageData> rosTextView;
    private RosImageView2<CompressedImage> rosImageView;

    private Publisherr talker;
    TextView txtX1, txtY1;
    TextView txtX2, txtY2;
    DualJoystickView joystick;

    public Controller() {
        // The RosActivity constructor configures the notification title and ticker
        // messages.

        super("ROS Controller", "ROS Controller");
    }

    /** Called when the activity is first created. */
    @SuppressWarnings("unchecked")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.main);
        txtX1 = (TextView) findViewById(R.id.TextViewX1);
        txtY1 = (TextView) findViewById(R.id.TextViewY1);

        txtX2 = (TextView) findViewById(R.id.TextViewX2);
        txtY2 = (TextView) findViewById(R.id.TextViewY2);

        joystick = (DualJoystickView) findViewById(R.id.dualjoystickView);

        joystick.setOnJostickMovedListener(_listenerLeft, _listenerRight);


        rosTextView = (RosTextView<ImageData>) findViewById(R.id.text);
        rosTextView.setTopicName("/vision_data");

        rosTextView.setMessageType(ImageData._TYPE);
        rosTextView.setMessageToStringCallable(new MessageCallable<String, ImageData>() {
            @Override
            public String call(ImageData message) {
                List<Ball> pallid = message.getBalls();
                String joined = "";

                if (pallid.size() > 0) {
                    joined = String.valueOf(pallid.get(0).getDistance());
                    Log.d("Balls", "aa" + joined.length());
                }


                return joined;
            }
        });

        rosImageView = (RosImageView2<sensor_msgs.CompressedImage>)findViewById(R.id.image);
        rosImageView.setMessageType(sensor_msgs.CompressedImage._TYPE);
        rosImageView.setTopicName("/front_cam/image/compressed");
        rosImageView.setMessageToBitmapCallable(new BitmapFromCompressedImage());


    }

    private JoystickMovedListener _listenerLeft = new JoystickMovedListener() {

        @Override
        public void OnMoved(int pan, int tilt) {
            txtX1.setText(Integer.toString(pan));
            txtY1.setText(Integer.toString(tilt));
            talker.set_movement(pan, -tilt);
        }

        @Override
        public void OnReleased() {
            txtX1.setText("released");
            txtY1.setText("released");
            talker.set_movement(0,0);
        }

        public void OnReturnedToCenter() {
            txtX1.setText("stopped");
            txtY1.setText("stopped");
        };
    };

    private JoystickMovedListener _listenerRight = new JoystickMovedListener() {

        @Override
        public void OnMoved(int pan, int tilt) {
            txtX2.setText(Integer.toString(pan));
            txtY2.setText(Integer.toString(tilt));
            talker.set_turn(pan, -tilt);
        }

        @Override
        public void OnReleased() {
            txtX2.setText("released");
            txtY2.setText("released");
            talker.set_turn(0,0);
        }

        public void OnReturnedToCenter() {
            txtX2.setText("stopped");
            txtY2.setText("stopped");
        };
    };

    @Override
    protected void init(NodeMainExecutor nodeMainExecutor) {
        talker = new Publisherr();

        // At this point, the user has already been prompted to either enter the URI
        // of a master to use or to start a master locally.

        // The user can easily use the selected ROS Hostname in the master chooser
        // activity.
        NodeConfiguration nodeConfiguration = NodeConfiguration.newPublic(getRosHostname());
        nodeConfiguration.setMasterUri(getMasterUri());
        nodeMainExecutor.execute(rosTextView, nodeConfiguration);
        nodeMainExecutor.execute(rosImageView, nodeConfiguration);
        nodeMainExecutor.execute(talker, nodeConfiguration);
        // The RosTextView is also a NodeMain that must be executed in order to
        // start displaying incoming messages.

    }
}
