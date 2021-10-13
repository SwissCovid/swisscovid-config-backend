package org.dpppt.switzerland.backend.sdk.config.ws;

import org.junit.Before;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest(
        properties = {
            "management.endpoints.enabled-by-default=true",
            "management.endpoints.web.exposure.include=*"
        })
public class GaenConfigControllerWithoutActuatorSecurityTest extends BaseControllerTest {

    @Before
    public void setup() throws Exception {
        super.setup();
        this.mockMvc =
                MockMvcBuilders.webAppContextSetup(webApplicationContext)
                        .addFilter(filter, "/*")
                        .build();
    }
}
