# ROS_Controller
Controlling a robot, using ROS, with an Android phone <br>

## Overview
This app implements [ROS indigo](http://wiki.ros.org/android).

This app provides a connection between a robot using ROS and an Android phone. The app establishes a connection over WIFI.

## Getting Started
If you have custom messages like me then:
* Create custom message [jars](http://wiki.ros.org/rosjava/Tutorials/hydro/Unofficial%20Messages)
* Put jars to [folder](https://github.com/erkihindo/ROS_Controller/tree/master/src/android_ROS/controller/libs)
* Change topic names in  [strings.xml](https://github.com/erkihindo/ROS_Controller/blob/master/src/android_ROS/controller/src/main/res/values/strings.xml)
* Change data types for subscriber located in defineImageViews() in [Controller.java](https://github.com/erkihindo/ROS_Controller/blob/master/src/android_ROS/controller/src/main/java/com/github/ros_java/android_ROS/controller/Controller.java) 
* Change publisher message creation in [Publisherr.java](https://github.com/erkihindo/ROS_Controller/blob/master/src/android_ROS/controller/src/main/java/com/github/ros_java/android_ROS/controller/Publisherr.java) and [BridgeToPublisher.java](https://github.com/erkihindo/ROS_Controller/blob/master/src/android_ROS/controller/src/main/java/com/github/ros_java/android_ROS/controller/BridgeToPublisher.java)
* If needed, change [layout](https://github.com/erkihindo/ROS_Controller/blob/master/src/android_ROS/controller/src/main/res/layout/main.xml). 

## Current message types

[Message examples](https://github.com/erkihindo/ROS_Controller/tree/master/media/msg)<br>
The image subscriber uses a default type: CompressedImage. <br>
Note: RosImageView can`t handle anything more than a CompressedImage.

## Example

This is an example using a CompressedImage and ImageData subscriber with a /com_cmd publisher. The slider is for controlling speed.

![Ros Controller](/media/screenshots/Screenshot1.png?raw=true "Ros controller")




