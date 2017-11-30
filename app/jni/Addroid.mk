LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)
LOCAL_MODULE := demo //要生成的so库的名称，但实际为libdemo.so
LOCAL_SRC_FILES := demo.c //要使用的文件，刚才编写的demo.c文件
include $(BUILD_SHARED_LIBRARY)