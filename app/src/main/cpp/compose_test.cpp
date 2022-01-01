//
// Created by wig on 2021-10-05.
//

#define LOG_TAG    "gpiod-lib_JNI"
#include <cstring>
#include <jni.h>
#include <cinttypes>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_compose_1test_MainActivity_getGpioInfo(JNIEnv *env, jobject thiz, jint gpiobank, jint gpioline) {

    std::ignore = thiz;

    char bank[32] = {0}, ret[4] = {0};
    sprintf(bank, "gpiochip%d", gpiobank);
    //ALOGI("GPIO gpiobank is %d", gpiobank);
    //ALOGI("GPIO gpioline is %d", gpioline);

    //jint value = gpiod_ctxless_get_value((char *)bank, gpioline, 0, "gpioget");

    //ALOGI("Value = %d", value);

    sprintf(ret, "%d", 1);

    return env->NewStringUTF(ret);
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_compose_1test_MainActivity_setGpioInfo(JNIEnv *env, jobject thiz, jint gpiobank, jint gpioline, jint gpio_value) {

    std::ignore = thiz;

    char bank[32] = {0};
    sprintf(bank, "/dev/gpiochip%d", gpiobank);
    //ALOGI("GPIO gpiobank is %d", gpiobank);
    //ALOGI("GPIO gpioline is %d", gpioline);

    //int ret = gpiod_ctxless_set_value(bank, gpioline, gpio_value, 0, "gpioset", NULL, NULL);

    //ALOGI("set gpio ret = %d", ret);

    return env->NewStringUTF("Set finish!");
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_compose_1test_MainActivity_stringFromJNI(JNIEnv *env, jobject thiz) {
    // TODO: implement stringFromJNI()
    return env->NewStringUTF("Test");
}