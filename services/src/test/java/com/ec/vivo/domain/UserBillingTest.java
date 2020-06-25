package com.ec.vivo.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.ec.vivo.web.rest.TestUtil;

public class UserBillingTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserBilling.class);
        UserBilling userBilling1 = new UserBilling();
        userBilling1.setId("id1");
        UserBilling userBilling2 = new UserBilling();
        userBilling2.setId(userBilling1.getId());
        assertThat(userBilling1).isEqualTo(userBilling2);
        userBilling2.setId("id2");
        assertThat(userBilling1).isNotEqualTo(userBilling2);
        userBilling1.setId(null);
        assertThat(userBilling1).isNotEqualTo(userBilling2);
    }
}
