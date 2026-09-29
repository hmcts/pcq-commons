package uk.gov.hmcts.reform.pcq.commons.controller.feign;

import feign.MethodMetadata;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.openfeign.support.SpringMvcContract;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PcqBackendFeignClientTest {

    @Test
    void springMvcContractParsesAllClientMethods() {
        SpringMvcContract contract = new SpringMvcContract();

        List<MethodMetadata> metadata = contract.parseAndValidateMetadata(PcqBackendFeignClient.class);

        assertThat(metadata).hasSize(3);
        assertThat(metadata).extracting(metadataEntry -> metadataEntry.template().path())
                .containsExactlyInAnyOrder(
                        "/pcq/backend/consolidation/pcqRecordWithoutCase",
                        "/pcq/backend/consolidation/addCaseForPCQ/{pcqId}",
                        "/pcq/backend/submitAnswers");
    }

    @Test
    void submitAnswersKeepsRequestBodyAndHeaderParameters() {
        SpringMvcContract contract = new SpringMvcContract();
        MethodMetadata metadata = contract.parseAndValidateMetadata(PcqBackendFeignClient.class).stream()
                .filter(metadataEntry -> metadataEntry.method().getName().equals("submitAnswers"))
                .findFirst()
                .orElseThrow();

        assertThat(metadata.bodyIndex()).isEqualTo(2);
        assertThat(metadata.indexToName().get(0)).contains("X-Correlation-Id");
        assertThat(metadata.indexToName().get(1)).contains("Authorization");
    }
}
