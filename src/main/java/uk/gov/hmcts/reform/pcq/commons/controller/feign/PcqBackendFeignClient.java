package uk.gov.hmcts.reform.pcq.commons.controller.feign;

import feign.Headers;
import feign.RequestLine;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import uk.gov.hmcts.reform.pcq.commons.config.FeignInterceptorConfiguration;
import uk.gov.hmcts.reform.pcq.commons.model.PcqAnswerRequest;

@FeignClient(name = "PcqBackendFeignClient", url = "${pcqBackendUrl}", configuration =
        FeignInterceptorConfiguration.class)
public interface PcqBackendFeignClient {

    @GetMapping("/pcq/backend/consolidation/pcqRecordWithoutCase")
    @RequestLine("GET /pcq/backend/consolidation/pcqRecordWithoutCase")
    Response getPcqWithoutCase(@RequestHeader("X-Correlation-Id") String token,
                               @RequestHeader("ServiceAuthorization") String serviceAuthorization);

    @PutMapping("/pcq/backend/consolidation/addCaseForPCQ/{pcqId}")
    Response addCaseForPcq(@RequestHeader("X-Correlation-Id") String token,
                           @RequestHeader("ServiceAuthorization") String serviceAuthorization,
                           @PathVariable("pcqId") String pcqId,
                           @RequestParam("caseId") String caseId);

    @PostMapping("/pcq/backend/submitAnswers")
    @Headers("Content-Type: application/json")
    Response submitAnswers(@RequestHeader("X-Correlation-Id") String token,
                           @RequestHeader("Authorization") String bearerToken,
                           PcqAnswerRequest answerRequest);

}
