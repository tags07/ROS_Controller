# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 2.8

#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:

# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list

# Suppress display of executed commands.
$(VERBOSE).SILENT:

# A target that is always out of date.
cmake_force:
.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /usr/bin/cmake

# The command to remove a file.
RM = /usr/bin/cmake -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /home/viki/ROS_Controller/src

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /home/viki/ROS_Controller/build

# Utility rule file for gradle-clean-android_ROS.

# Include the progress variables for this target.
include android_ROS/CMakeFiles/gradle-clean-android_ROS.dir/progress.make

android_ROS/CMakeFiles/gradle-clean-android_ROS:
	cd /home/viki/ROS_Controller/src/android_ROS && /home/viki/ROS_Controller/build/catkin_generated/env_cached.sh /home/viki/ROS_Controller/src/android_ROS/gradlew clean

gradle-clean-android_ROS: android_ROS/CMakeFiles/gradle-clean-android_ROS
gradle-clean-android_ROS: android_ROS/CMakeFiles/gradle-clean-android_ROS.dir/build.make
.PHONY : gradle-clean-android_ROS

# Rule to build all files generated by this target.
android_ROS/CMakeFiles/gradle-clean-android_ROS.dir/build: gradle-clean-android_ROS
.PHONY : android_ROS/CMakeFiles/gradle-clean-android_ROS.dir/build

android_ROS/CMakeFiles/gradle-clean-android_ROS.dir/clean:
	cd /home/viki/ROS_Controller/build/android_ROS && $(CMAKE_COMMAND) -P CMakeFiles/gradle-clean-android_ROS.dir/cmake_clean.cmake
.PHONY : android_ROS/CMakeFiles/gradle-clean-android_ROS.dir/clean

android_ROS/CMakeFiles/gradle-clean-android_ROS.dir/depend:
	cd /home/viki/ROS_Controller/build && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /home/viki/ROS_Controller/src /home/viki/ROS_Controller/src/android_ROS /home/viki/ROS_Controller/build /home/viki/ROS_Controller/build/android_ROS /home/viki/ROS_Controller/build/android_ROS/CMakeFiles/gradle-clean-android_ROS.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : android_ROS/CMakeFiles/gradle-clean-android_ROS.dir/depend

