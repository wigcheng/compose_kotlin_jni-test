LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := gpiod-jni
LOCAL_SRC_FILES := gpiod_lib.cpp
LOCAL_LDLIBS    := -llog

include $(BUILD_SHARED_LIBRARY)