package uk.gov.hmcts.reform.pcq.commons.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;

/**
 * This is the object representing the REST response for the PCQWithoutACase API.
 */
@NoArgsConstructor
public class PcqWithoutCaseResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 432973322;

    private static final String[] EMPTY_PCQ_ID_RESPONSE = {};

    private String[] pcqId;

    @Getter
    @Setter
    private String responseStatus;

    @Getter
    @Setter
    private String responseStatusCode;

    public String[] getPcqId() {
        if (pcqId == null) {
            return EMPTY_PCQ_ID_RESPONSE;
        }
        return Arrays.copyOf(pcqId, pcqId.length);
    }

    @SuppressWarnings("PMD.UseVarargs")
    public void setPcqId(String[] pcqId) {
        if (pcqId == null) {
            this.pcqId = new String[0];
        } else {
            this.pcqId = Arrays.copyOf(pcqId, pcqId.length);
        }
    }
}
