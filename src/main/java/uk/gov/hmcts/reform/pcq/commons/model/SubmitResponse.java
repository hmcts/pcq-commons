package uk.gov.hmcts.reform.pcq.commons.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
public class SubmitResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 5328745L;

    private String pcqId;

    private String responseStatus;

    private String responseStatusCode;

}
