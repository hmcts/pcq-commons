package uk.gov.hmcts.reform.pcq.commons.exception;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class ExternalApiExceptionTest {

    @Test
    public void externalApiExceptionTest() {
        ExternalApiException externalApiException = new ExternalApiException(BAD_REQUEST, "BAD REQUEST");

        assertEquals("`Not expected status", "400 BAD_REQUEST", externalApiException.getHttpStatus().toString());
        assertEquals("Not expected message", "BAD REQUEST", externalApiException.getErrorMessage());
    }
}