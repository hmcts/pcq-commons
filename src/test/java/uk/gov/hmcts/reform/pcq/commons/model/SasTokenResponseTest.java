package uk.gov.hmcts.reform.pcq.commons.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SasTokenResponseTest {

    private static final String SAS_TOKEN = "Test Sas Token";

    @Test
    void testSasTokenResponse() {
        SasTokenResponse sasTokenResponse = new SasTokenResponse(SAS_TOKEN);

        assertEquals(SAS_TOKEN, sasTokenResponse.getSasToken(), "SAS Token is Invalid");
    }

}
