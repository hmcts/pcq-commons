package uk.gov.hmcts.reform.pcq.commons.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class PcqPayLoad implements Serializable {
    @Serial
    private static final long serialVersionUID = 65589453L;

    @JsonProperty("Metadata_file")
    private PcqPayloadContents[] metaDataContents;
}
