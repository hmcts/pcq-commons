package uk.gov.hmcts.reform.pcq.commons.utils;

import org.junit.jupiter.api.Test;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;
import uk.gov.hmcts.reform.pcq.commons.model.PcqAnswerRequest;
import uk.gov.hmcts.reform.pcq.commons.model.PcqAnswerResponse;
import uk.gov.hmcts.reform.pcq.commons.model.PcqAnswers;

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

    @Test
    void roundTripsPcqAnswerRequestWithJackson3() throws Exception {
        PcqAnswerRequest request = new PcqAnswerRequest("pcq-id");
        request.setDcnNumber("dcn-number");
        request.setFormId("form-id");
        request.setCaseId("case-id");
        request.setPartyId("party-id");
        request.setChannel(1);
        request.setCompletedDate("2026-09-29T12:00:00.000Z");
        request.setServiceId("service-id");
        request.setActor("actor");
        request.setVersionNo(2);
        request.setOptOut("N");

        PcqAnswers answers = new PcqAnswers();
        answers.setDobProvided(1);
        answers.setLanguageMain(2);
        answers.setDisabilityConditionOther("details");
        answers.setOptOut(true);
        request.setPcqAnswers(answers);

        String json = objectMapper.writeValueAsString(request);
        PcqAnswerRequest roundTrip = objectMapper.readValue(json, PcqAnswerRequest.class);

        assertThat(roundTrip.getPcqId()).isEqualTo("pcq-id");
        assertThat(roundTrip.getCaseId()).isEqualTo("case-id");
        assertThat(roundTrip.getPcqAnswers().getLanguageMain()).isEqualTo(2);
        assertThat(roundTrip.getPcqAnswers().getDisabilityConditionOther()).isEqualTo("details");
        assertThat(roundTrip.getPcqAnswers().getOptOut()).isTrue();
        assertThat(json).contains("\"ccdCaseId\"");
        assertThat(json).contains("\"language_main\"");
        assertThat(json).contains("\"disability_other_details\"");
    }

    @Test
    void roundTripsPcqAnswerResponseWithJackson3() throws Exception {
        PcqAnswerResponse response = new PcqAnswerResponse();
        response.setPcqId("pcq-id");
        response.setDcnNumber("dcn-number");
        response.setFormId("form-id");
        response.setCaseId("case-id");
        response.setPartyId("party-id");
        response.setChannel(1);
        response.setCompletedDate("2026-09-29T12:00:00.000Z");
        response.setServiceId("service-id");
        response.setActor("actor");
        response.setVersionNo(2);

        String json = objectMapper.writeValueAsString(response);
        PcqAnswerResponse roundTrip = objectMapper.readValue(json, PcqAnswerResponse.class);

        assertThat(roundTrip.getPcqId()).isEqualTo("pcq-id");
        assertThat(roundTrip.getCaseId()).isEqualTo("case-id");
        assertThat(roundTrip.getVersionNo()).isEqualTo(2);
        assertThat(json).contains("\"ccdCaseId\"");
        assertThat(json).doesNotContain("\"caseId\"");
    }
}
