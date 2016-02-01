//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.github.ros_java.android_ROS.controller;

import android.util.Log;

import org.ros.concurrent.CancellableLoop;
import org.ros.namespace.GraphName;
import org.ros.node.AbstractNodeMain;
import org.ros.node.ConnectedNode;
import org.ros.node.topic.Publisher;

import msgs.Cmd;


public class Publisherr extends AbstractNodeMain {
    private String topic_name;
    private float speed = 0;
    private float angle = 0;
    private float turn = 0;
    private boolean kick = false;

    public Publisherr() {
        this.topic_name = "/com_cmd";
    }

    public Publisherr(String topic) {
        this.topic_name = topic;
    }

    public GraphName getDefaultNodeName() {
        return GraphName.of("rosjava_tutorial_pubsub/talker");
    }

    public void onStart(ConnectedNode connectedNode) {
        final Publisher publisher = connectedNode.newPublisher(this.topic_name, "msgs/Cmd");

        connectedNode.executeCancellableLoop(new CancellableLoop() {
            private int sequenceNumber;

            protected void setup() {
                this.sequenceNumber = 0;
            }

            protected void loop() throws InterruptedException {
                Cmd str = (Cmd)publisher.newMessage();
                str.setAbsSpeed(speed);
                str.setKick(kick);
                str.setRadAngle(angle);
                str.setTurnRate(turn);
                publisher.publish(str);
                ++this.sequenceNumber;
                Thread.sleep(100L);
            }
        });
    }


    public void set_movement(int x, int y) {
        this.speed = (float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2))/15;

        Log.d("X Angle", "" + x);
        Log.d("Y Angle", "" + y);
        double angle1 = Math.atan2(10, 0);
        double angle2 = Math.atan2(y, x );
        float degToRad = (float) (-1*(angle1 - angle2));
        this.angle = degToRad;
        Log.d("Angle", "" + angle);


    }
    public void set_turn(int x, int y) {
        if(x <=1 && x >= -1 && y >= 8) kick = true;
        else kick = false;
        double turnD = -1*x/10.0*Math.PI;

        this.turn = (float) turnD;
        if(x <=1 && x >= -1 && y >= 6)this.turn = 0;

        //this.turn = x;
    }
}
