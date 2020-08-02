package com.ec.vivo.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.ec.vivo.web.rest.TestUtil;

public class UserEc2VivoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserEc2Vivo.class);
        UserEc2Vivo userEc2Vivo1 = new UserEc2Vivo();
        userEc2Vivo1.setId("id1");
        UserEc2Vivo userEc2Vivo2 = new UserEc2Vivo();
        userEc2Vivo2.setId(userEc2Vivo1.getId());
        assertThat(userEc2Vivo1).isEqualTo(userEc2Vivo2);
        userEc2Vivo2.setId("id2");
        assertThat(userEc2Vivo1).isNotEqualTo(userEc2Vivo2);
        userEc2Vivo1.setId(null);
        assertThat(userEc2Vivo1).isNotEqualTo(userEc2Vivo2);
    }
}
