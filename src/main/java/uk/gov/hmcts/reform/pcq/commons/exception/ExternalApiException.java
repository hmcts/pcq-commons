package uk.gov.hmcts.reform.pcq.commons.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Getter
public class ExternalApiException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 43287452;

    private final HttpStatus httpStatus;

    private final String errorMessage;

    public ExternalApiException(HttpStatus httpStatus, String errorMessage) {
        super(errorMessage);
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }

}
