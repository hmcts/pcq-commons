package uk.gov.hmcts.reform.pcq.commons.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

@Getter
public class SasTokenResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 1328741L;

    @JsonProperty("sas_token")
    private String sasToken;

    public SasTokenResponse(String sasToken) {
        this.sasToken = sasToken;
    }
}
