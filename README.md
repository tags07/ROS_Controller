# ROS_Controller
Controlling a robot, using ROS, with an Android phone <br>

## Overview
This app implements [ROS indigo](http://wiki.ros.org/android).

This app provides a connection between a robot using ROS and an Android phone. The app establishes a connection over WIFI.

## Getting Started
* Create custom message [jars](http://wiki.ros.org/rosjava/Tutorials/hydro/Unofficial%20Messages)
* Put jars to [folder](https://github.com/erkihindo/ROS_Controller/tree/master/src/android_ROS/controller/libs)
* Change topic names in  [strings.xml](https://github.com/erkihindo/ROS_Controller/blob/master/src/android_ROS/controller/src/main/res/values/strings.xml)
* Change data types for subscriber located in defineImageViews() in [Controller.java](https://github.com/erkihindo/ROS_Controller/blob/master/src/android_ROS/controller/src/main/java/com/github/ros_java/android_ROS/controller/Controller.java) 
* Change publisher message creation in [Publisherr.java](https://github.com/erkihindo/ROS_Controller/blob/master/src/android_ROS/controller/src/main/java/com/github/ros_java/android_ROS/controller/Publisherr.java) and [BridgeToPublisher.java](https://github.com/erkihindo/ROS_Controller/blob/master/src/android_ROS/controller/src/main/java/com/github/ros_java/android_ROS/controller/BridgeToPublisher.java)

