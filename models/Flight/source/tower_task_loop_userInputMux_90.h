/* This file has been autogenerated by Ivory
 * Compiler version  0.1.0.0
 */
#ifndef __TOWER_TASK_LOOP_USERINPUTMUX_90_H__
#define __TOWER_TASK_LOOP_USERINPUTMUX_90_H__
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
#include "tower_task_usercode_userInputMux_90.h"
#include "userinput_type.h"
void read_userInputMux_90_dataport12_92(uint8_t* n_var0);
void read_userInputMux_90_dataport36_94(struct userinput_result* n_var0);
void read_userInputMux_90_dataport37_96(struct userinput_result* n_var0);
void read_userInputMux_90_dataport38_98(bool* n_var0);
void write_userInputMux_90_dataport41_100(const
                                          struct userinput_result* n_var0);

#ifdef __cplusplus
}
#endif
#endif /* __TOWER_TASK_LOOP_USERINPUTMUX_90_H__ */