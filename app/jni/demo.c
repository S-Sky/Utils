#include <jni.h>
jstring
Java_cn_handsomedragon_testndk_MainActivity_getStrFromJNI(JNIEnv *env,jobject thiz) {
    return (*env)->NewStringUTF(env, "I`m Str from jni libs!");
}