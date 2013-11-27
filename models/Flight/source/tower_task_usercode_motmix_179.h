/* This file has been autogenerated by Ivory
 * Compiler version  0.1.0.0
 */
#ifndef __TOWER_TASK_USERCODE_MOTMIX_179_H__
#define __TOWER_TASK_USERCODE_MOTMIX_179_H__
#ifdef __cplusplus
extern "C" {
#endif
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
#include "tower_task_loop_motmix_179.h"
#include "userinput_type.h"
void nodeInit_motmix_179();
void eventhandler_motmix_179_chan35_190(const struct controloutput* n_var0);

#ifdef __cplusplus
}
#endif
#endif /* __TOWER_TASK_USERCODE_MOTMIX_179_H__ */