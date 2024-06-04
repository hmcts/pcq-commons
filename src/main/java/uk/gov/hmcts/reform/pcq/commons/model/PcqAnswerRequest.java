package uk.gov.hmcts.reform.pcq.commons.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * This is the object representing the REST request for the SubmitAnswers API.
 */
@Getter
@Setter
@NoArgsConstructor
public class PcqAnswerRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 4328743L;

    private String pcqId;

    private String dcnNumber;

    private String formId;

    @JsonProperty("ccdCaseId")
    private String caseId;

    private String partyId;

    private int channel;

    private String completedDate;

    private String serviceId;

    private String actor;

    private int versionNo;

    private String optOut;

    private PcqAnswers pcqAnswers;

    public PcqAnswerRequest(String pcqId) {
        this.pcqId = pcqId;
    }

}
