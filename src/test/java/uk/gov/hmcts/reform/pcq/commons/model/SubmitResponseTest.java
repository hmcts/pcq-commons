package uk.gov.hmcts.reform.pcq.commons.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SubmitResponseTest {

    private static final String PCQ_ID = "PCQ_ID1";
    private static final String STATUS = "Success";
    private static final String STATUS_CODE = "200";

    @Test
    void testSubmitResponse() {
        SubmitResponse submitResponse = new SubmitResponse();
        submitResponse.setPcqId(PCQ_ID);
        submitResponse.setResponseStatus(STATUS);
        submitResponse.setResponseStatusCode(STATUS_CODE);

        assertEquals(PCQ_ID, submitResponse.getPcqId(),"PCQ Ids don't match");
        assertEquals(STATUS, submitResponse.getResponseStatus(),"Response status doesn't match");
        assertEquals(STATUS_CODE, submitResponse.getResponseStatusCode(),"Response status code doesn't match");
    }
}
