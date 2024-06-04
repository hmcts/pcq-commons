package uk.gov.hmcts.reform.pcq.commons.exception;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

class ExternalApiExceptionTest {

    @Test
    void externalApiExceptionTest() {
        ExternalApiException externalApiException = new ExternalApiException(BAD_REQUEST, "BAD REQUEST");

        assertEquals("400 BAD_REQUEST", externalApiException.getHttpStatus().toString(),"Not expected status");
        assertEquals("BAD REQUEST", externalApiException.getErrorMessage(),"Not expected message");
    }
}
