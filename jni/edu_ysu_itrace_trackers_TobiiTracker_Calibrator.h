/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class edu_ysu_itrace_TobiiTracker_Calibrator */

#ifndef _Included_edu_ysu_itrace_TobiiTracker_Calibrator
#define _Included_edu_ysu_itrace_TobiiTracker_Calibrator
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     edu_ysu_itrace_TobiiTracker_Calibrator
 * Method:    jniAddPoint
 * Signature: (DD)V
 */
JNIEXPORT void JNICALL Java_edu_ysu_itrace_trackers_TobiiTracker_00024Calibrator_jniAddPoint
  (JNIEnv *, jobject, jdouble, jdouble);

/*
 * Class:     edu_ysu_itrace_TobiiTracker_Calibrator
 * Method:    jniStartCalibration
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_edu_ysu_itrace_trackers_TobiiTracker_00024Calibrator_jniStartCalibration
  (JNIEnv *, jobject);

/*
 * Class:     edu_ysu_itrace_TobiiTracker_Calibrator
 * Method:    jniStopCalibration
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_edu_ysu_itrace_trackers_TobiiTracker_00024Calibrator_jniStopCalibration
  (JNIEnv *, jobject);

/*
 * Class:     edu_ysu_itrace_TobiiTracker_Calibrator
 * Method:    jniGetCalibration
 * Signature: ()[I
 */
JNIEXPORT jintArray JNICALL Java_edu_ysu_itrace_trackers_TobiiTracker_00024Calibrator_jniGetCalibration
  (JNIEnv *, jobject);

#ifdef __cplusplus
}
#endif
#endif
