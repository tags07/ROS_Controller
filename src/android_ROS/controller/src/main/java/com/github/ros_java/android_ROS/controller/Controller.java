package com.github.ros_java.android_ROS.controller;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.SeekBar;
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
import sensor_msgs.CompressedImage;

//Custom messages
import msgs.Ball;
import msgs.ImageData;

public class Controller extends RosActivity
{
    private RosTextView<ImageData> rosTextView;

    //displays the camera`s image
    private RosImageView2<CompressedImage> rosImageView;

    private Publisherr talker;
    private TextView txtX1, txtY1;
    private TextView txtX2, txtY2;
    private EditText enteredText;
    private DualJoystickView joystick;
    private String displayed_message;
    private double lastChanceToKick;
    private double shootingRange;
    private SeekBar bar;

    public Controller() {
        // The RosActivity constructor configures the notification title.

        super("ROS Controller", "ROS Controller");
    }

    /** Called when the activity is first created. */
    @SuppressWarnings("unchecked")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        hideTitleBar();

        setContentView(R.layout.main);

        defineTextFields();

        defineEditTextFields();

        defineSlider();

        defineJoySticks();

        defineTextViews();

        defineImageViews();

        //defines publisher class
        talker = new Publisherr(this);
    }

    public void defineSlider() {
        bar = (SeekBar)findViewById(R.id.seekBar);
        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                BridgeToPublisher.progressBar = seekBar.getProgress();


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {


            }
        });

    }

    public void hideTitleBar() {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void defineTextFields() {
        txtX1 = (TextView) findViewById(R.id.TextViewX1);
        txtY1 = (TextView) findViewById(R.id.TextViewY1);

        txtX2 = (TextView) findViewById(R.id.TextViewX2);
        txtY2 = (TextView) findViewById(R.id.TextViewY2);
    }


    public void defineEditTextFields() {
        enteredText = (EditText)findViewById(R.id.entered_text);
        enteredText.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                BridgeToPublisher.text = String.valueOf(s);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
            }
        });
    }

    public void defineJoySticks() {
        joystick = (DualJoystickView) findViewById(R.id.dualjoystickView);

        joystick.setOnJostickMovedListener(_listenerLeft, _listenerRight);
    }

    public void defineTextViews() {
        rosTextView = (RosTextView<ImageData>) findViewById(R.id.text);
        rosTextView.setTopicName(getResources().getString(R.string.sub_data_topic));

        rosTextView.setMessageType(ImageData._TYPE);
        displayed_message = "";
        lastChanceToKick = 5000;


        rosTextView.setMessageToStringCallable(new MessageCallable<String, ImageData>() {
            @Override
            public String call(ImageData message) {
                List<Ball> pallid = message.getBalls();
                float smallest_length = 500;

                for (int i = 0; i < pallid.size(); i++) {
                    if (pallid.get(i).getDistance() < smallest_length) {
                        smallest_length = pallid.get(i).getDistance();
                    }
                }

                if (smallest_length < Double.parseDouble(getResources().getString(R.string.kick_range))) {
                    displayed_message = "KICK";
                    lastChanceToKick = System.currentTimeMillis();
                } else {
                    hideMessageDelay();
                }
                /*
                if (pallid.size() > 0) {
                    joined = String.valueOf(pallid.get(0).getDistance());
                    Log.d("Balls", "aa" + joined.length());
                }*/


                return displayed_message;
            }
        });
    }

    public void hideMessageDelay() {
        if(System.currentTimeMillis() - lastChanceToKick > 1000) {
            Log.d("Time", "" + (System.currentTimeMillis() - lastChanceToKick));
            displayed_message = "";
        }
    }


    public void defineImageViews() {
        rosImageView = (RosImageView2<sensor_msgs.CompressedImage>)findViewById(R.id.image);
        rosImageView.setMessageType(sensor_msgs.CompressedImage._TYPE);
        rosImageView.setTopicName(getResources().getString(R.string.sub_image_topic));
        rosImageView.setMessageToBitmapCallable(new BitmapFromCompressedImage());
    }

    //Defines what joystick(left) movement does
    private JoystickMovedListener _listenerLeft = new JoystickMovedListener() {

        @Override
        public void OnMoved(int pan, int tilt) {
            txtX1.setText(Integer.toString(pan));
            txtY1.setText(Integer.toString(tilt));
            BridgeToPublisher.set_movement(pan, -tilt);
        }

        @Override
        public void OnReleased() {
            txtX1.setText("released");
            txtY1.setText("released");
            BridgeToPublisher.set_movement(0,0);
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
            BridgeToPublisher.set_turn(pan, -tilt);
        }

        @Override
        public void OnReleased() {
            txtX2.setText("released");
            txtY2.setText("released");
            BridgeToPublisher.set_turn(0,0);
        }

        public void OnReturnedToCenter() {
            txtX2.setText("stopped");
            txtY2.setText("stopped");
        };
    };

    @Override
    protected void init(NodeMainExecutor nodeMainExecutor) {
        // At this point, the user has already been prompted to either enter the URI
        // of a master to use or to start a master locally.

        NodeConfiguration nodeConfiguration = NodeConfiguration.newPublic(getRosHostname());
        nodeConfiguration.setMasterUri(getMasterUri());

        //starts subscribers and publishers
        nodeMainExecutor.execute(rosTextView, nodeConfiguration);
        nodeMainExecutor.execute(rosImageView, nodeConfiguration);
        nodeMainExecutor.execute(talker, nodeConfiguration);

    }
}
