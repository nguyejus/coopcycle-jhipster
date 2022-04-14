package com.nguyejus.coopcycle.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.nguyejus.coopcycle.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DeliveryManTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DeliveryMan.class);
        DeliveryMan deliveryMan1 = new DeliveryMan();
        deliveryMan1.setId(1L);
        DeliveryMan deliveryMan2 = new DeliveryMan();
        deliveryMan2.setId(deliveryMan1.getId());
        assertThat(deliveryMan1).isEqualTo(deliveryMan2);
        deliveryMan2.setId(2L);
        assertThat(deliveryMan1).isNotEqualTo(deliveryMan2);
        deliveryMan1.setId(null);
        assertThat(deliveryMan1).isNotEqualTo(deliveryMan2);
    }
}
