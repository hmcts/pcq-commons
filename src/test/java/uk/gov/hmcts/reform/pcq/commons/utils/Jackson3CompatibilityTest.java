package uk.gov.hmcts.reform.pcq.commons.utils;

import org.junit.jupiter.api.Test;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;
import uk.gov.hmcts.reform.pcq.commons.model.PcqAnswerRequest;

import static org.assertj.core.api.Assertions.assertThat;

class Jackson3CompatibilityTest {

    private final ObjectMapper objectMapper = JsonMapper.builder().build();

    @Test
    void preservesPublicRequestJsonPropertyNamesWithJackson3() throws Exception {
        PcqAnswerRequest request = new PcqAnswerRequest("pcq-id");
        request.setCaseId("case-id");

        String json = objectMapper.writeValueAsString(request);

        assertThat(json).contains("\"pcqId\":\"pcq-id\"");
        assertThat(json).contains("\"ccdCaseId\":\"case-id\"");
        assertThat(json).doesNotContain("\"caseId\"");
    }
}
