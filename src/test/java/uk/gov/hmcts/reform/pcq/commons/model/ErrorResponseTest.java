package uk.gov.hmcts.reform.pcq.commons.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ErrorResponseTest {

    private static final String ERR_MSG = "Test Message";

    @Test
    void testErrorResponse() {
        ErrorResponse errorResponse = new ErrorResponse(ERR_MSG);

        assertEquals(ERR_MSG, errorResponse.message, "Error message is invalid");
    }
}
