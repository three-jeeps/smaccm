/* This file has been autogenerated by Ivory
 * Compiler version  0.1.0.0
 */
#ifndef __TOWER_TASK_LOOP_PX4IOAR_275_H__
#define __TOWER_TASK_LOOP_PX4IOAR_275_H__
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
#include "tower_task_usercode_px4ioar_275.h"
#include "userinput_type.h"
bool emitFromTask_px4ioar_275_chan268_277(const uint8_t* n_var0);
bool receiveFromTask_px4ioar_275_chan34_279(struct motors* n_var0);
bool emitFromTask_px4ioar_275_chan289_291(const uint32_t* n_var0);
bool receiveFromTask_px4ioar_275_chan289_293(uint32_t* n_var0);

#ifdef __cplusplus
}
#endif
#endif /* __TOWER_TASK_LOOP_PX4IOAR_275_H__ */