/* This file has been autogenerated by Ivory
 * Compiler version  0.1.0.0
 */
#ifndef __TOWER_TASK_USERCODE_USERPPMINPUT_42_H__
#define __TOWER_TASK_USERCODE_USERPPMINPUT_42_H__
#ifdef __cplusplus
extern "C" {
#endif
#include <apwrapper/userinput_capture.h>
#include "controloutput_type.h"
#include "data_rate.h"
#include "flightmode_type.h"
#include "gcsstream_timing.h"
#include "gps_type.h"
#include "ivory.h"
#include "mavlink_hil_state_msg.h"
#include "motors_type.h"
#include "optflow_type.h"
#include "radio_info_type.h"
#include "radio_stat_type.h"
#include "sensors_type.h"
#include "tower_primitives.h"
#include "tower_task_loop_userPPMInput_42.h"
#include "userinput_decode.h"
#include "userinput_type.h"
extern uint16_t channels_49[8U];
extern struct userinput_result userinput_50;
extern struct flightmode flightmode_51;
void eventhandler_userPPMInput_42_per50_55(const uint32_t* n_var0);

#ifdef __cplusplus
}
#endif
#endif /* __TOWER_TASK_USERCODE_USERPPMINPUT_42_H__ */