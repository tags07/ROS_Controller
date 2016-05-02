
package com.github.ros_java.android_ROS.controller;

import android.content.Context;

import org.ros.concurrent.CancellableLoop;
import org.ros.namespace.GraphName;
import org.ros.node.AbstractNodeMain;
import org.ros.node.ConnectedNode;
import org.ros.node.topic.Publisher;

//Custom messages
import msgs.Cmd;


public class Publisherr extends AbstractNodeMain {
    private String topic_name;
    Context app;

    public Publisherr(Context c) {
        this.app = c;
        this.topic_name = app.getString(R.string.pub_topic);
    }

    public Publisherr(String topic) {
        this.topic_name = topic;
    }

    public GraphName getDefaultNodeName() {
        return GraphName.of(app.getString(R.string.pub_displayed_name));
    }

    public void onStart(ConnectedNode connectedNode) {
        final Publisher publisher = connectedNode.newPublisher(this.topic_name, app.getString(R.string.pub_message_type));

        connectedNode.executeCancellableLoop(new CancellableLoop() {
            protected void loop() throws InterruptedException {
                //compose and send off message
                publisher.publish(createCmdMessage(publisher));
                Thread.sleep(100L);
            }
        });
    }

    public Cmd createCmdMessage(Publisher publisher) {
        Cmd msg = (Cmd)publisher.newMessage();
        msg.setAbsSpeed(BridgeToPublisher.speed);
        msg.setKick(BridgeToPublisher.kick);
        msg.setRadAngle(BridgeToPublisher.angle);
        msg.setTurnRate(BridgeToPublisher.turn);
        msg.setText(BridgeToPublisher.text);
        return msg;
    }

}
