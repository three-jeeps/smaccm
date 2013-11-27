/* This file has been autogenerated by Ivory
 * Compiler version  0.1.0.0
 */
#include "mavlink_hil_state_msg.h"

void mavlink_hil_state_msg_send(const struct hil_state_msg* n_var0,
                                uint8_t* n_var1, uint8_t n_var2[80U])
{
    uint8_t n_local0[56U] = {};
    uint8_t* n_ref1 = n_local0;
    uint64_t n_deref2 = n_var0->time_usec;
    
    mavlink_pack_uint64_t((uint8_t*) n_ref1, 0U, n_deref2);
    
    float n_deref3 = n_var0->roll;
    
    mavlink_pack_float((uint8_t*) n_ref1, 8U, n_deref3);
    
    float n_deref4 = n_var0->pitch;
    
    mavlink_pack_float((uint8_t*) n_ref1, 12U, n_deref4);
    
    float n_deref5 = n_var0->yaw;
    
    mavlink_pack_float((uint8_t*) n_ref1, 16U, n_deref5);
    
    float n_deref6 = n_var0->rollspeed;
    
    mavlink_pack_float((uint8_t*) n_ref1, 20U, n_deref6);
    
    float n_deref7 = n_var0->pitchspeed;
    
    mavlink_pack_float((uint8_t*) n_ref1, 24U, n_deref7);
    
    float n_deref8 = n_var0->yawspeed;
    
    mavlink_pack_float((uint8_t*) n_ref1, 28U, n_deref8);
    
    int32_t n_deref9 = n_var0->lat;
    
    mavlink_pack_int32_t((uint8_t*) n_ref1, 32U, n_deref9);
    
    int32_t n_deref10 = n_var0->lon;
    
    mavlink_pack_int32_t((uint8_t*) n_ref1, 36U, n_deref10);
    
    int32_t n_deref11 = n_var0->alt;
    
    mavlink_pack_int32_t((uint8_t*) n_ref1, 40U, n_deref11);
    
    int16_t n_deref12 = n_var0->vx;
    
    mavlink_pack_int16_t((uint8_t*) n_ref1, 44U, n_deref12);
    
    int16_t n_deref13 = n_var0->vy;
    
    mavlink_pack_int16_t((uint8_t*) n_ref1, 46U, n_deref13);
    
    int16_t n_deref14 = n_var0->vz;
    
    mavlink_pack_int16_t((uint8_t*) n_ref1, 48U, n_deref14);
    
    int16_t n_deref15 = n_var0->xacc;
    
    mavlink_pack_int16_t((uint8_t*) n_ref1, 50U, n_deref15);
    
    int16_t n_deref16 = n_var0->yacc;
    
    mavlink_pack_int16_t((uint8_t*) n_ref1, 52U, n_deref16);
    
    int16_t n_deref17 = n_var0->zacc;
    
    mavlink_pack_int16_t((uint8_t*) n_ref1, 54U, n_deref17);
    for (int32_t n_ix18 = 0; n_ix18 <= 55; n_ix18++) {
        ASSERTS(n_ix18 > 0 && 2147483647 - n_ix18 >= 6 || n_ix18 <= 0);
        if (n_ix18 + 6 >= 80) { } else {
            uint8_t n_deref19 = n_ref1[n_ix18];
            
            ASSERTS(n_ix18 > 0 && 2147483641 >= n_ix18 || n_ix18 <= 0);
            ASSERTS(0 <= 6 + n_ix18 && 6 + n_ix18 < 80);
            *&n_var2[(6 + n_ix18) % 80] = n_deref19;
        }
    }
    mavlinkSendWithWriter(90U, 183U, 56U, n_var1, n_var2);
    for (int32_t n_ix20 = 0; n_ix20 <= 15; n_ix20++) {
        ASSERTS(n_ix20 > 0 && 2147483647 - n_ix20 >= 64 || n_ix20 <= 0);
        ASSERTS(0 <= n_ix20 + 64 && n_ix20 + 64 < 80);
        *&n_var2[(n_ix20 + 64) % 80] = 0U;
    }
    return;
}

void mavlink_hil_state_unpack(struct hil_state_msg* n_var0, const
                              uint8_t* n_var1)
{
    uint64_t n_r0 = mavlink_unpack_uint64_t(n_var1, 0U);
    
    *&n_var0->time_usec = n_r0;
    
    float n_r1 = mavlink_unpack_float(n_var1, 8U);
    
    *&n_var0->roll = n_r1;
    
    float n_r2 = mavlink_unpack_float(n_var1, 12U);
    
    *&n_var0->pitch = n_r2;
    
    float n_r3 = mavlink_unpack_float(n_var1, 16U);
    
    *&n_var0->yaw = n_r3;
    
    float n_r4 = mavlink_unpack_float(n_var1, 20U);
    
    *&n_var0->rollspeed = n_r4;
    
    float n_r5 = mavlink_unpack_float(n_var1, 24U);
    
    *&n_var0->pitchspeed = n_r5;
    
    float n_r6 = mavlink_unpack_float(n_var1, 28U);
    
    *&n_var0->yawspeed = n_r6;
    
    int32_t n_r7 = mavlink_unpack_int32_t(n_var1, 32U);
    
    *&n_var0->lat = n_r7;
    
    int32_t n_r8 = mavlink_unpack_int32_t(n_var1, 36U);
    
    *&n_var0->lon = n_r8;
    
    int32_t n_r9 = mavlink_unpack_int32_t(n_var1, 40U);
    
    *&n_var0->alt = n_r9;
    
    int16_t n_r10 = mavlink_unpack_int16_t(n_var1, 44U);
    
    *&n_var0->vx = n_r10;
    
    int16_t n_r11 = mavlink_unpack_int16_t(n_var1, 46U);
    
    *&n_var0->vy = n_r11;
    
    int16_t n_r12 = mavlink_unpack_int16_t(n_var1, 48U);
    
    *&n_var0->vz = n_r12;
    
    int16_t n_r13 = mavlink_unpack_int16_t(n_var1, 50U);
    
    *&n_var0->xacc = n_r13;
    
    int16_t n_r14 = mavlink_unpack_int16_t(n_var1, 52U);
    
    *&n_var0->yacc = n_r14;
    
    int16_t n_r15 = mavlink_unpack_int16_t(n_var1, 54U);
    
    *&n_var0->zacc = n_r15;
}