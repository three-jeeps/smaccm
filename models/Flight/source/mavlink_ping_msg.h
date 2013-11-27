/* This file has been autogenerated by Ivory
 * Compiler version  0.1.0.0
 */
#ifndef __MAVLINK_PING_MSG_H__
#define __MAVLINK_PING_MSG_H__
#ifdef __cplusplus
extern "C" {
#endif
#include "ivory.h"
#include "mavlinkSendModule.h"
#include "mavlink_pack_ivory.h"
struct ping_msg {
    uint64_t time_usec;
    uint32_t ping_seq;
    uint8_t target_system;
    uint8_t target_component;
} __attribute__((__packed__));
void mavlink_ping_msg_send(const struct ping_msg* n_var0, uint8_t* n_var1,
                           uint8_t n_var2[80U]);
void mavlink_ping_unpack(struct ping_msg* n_var0, const uint8_t* n_var1);

#ifdef __cplusplus
}
#endif
#endif /* __MAVLINK_PING_MSG_H__ */