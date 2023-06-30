package uk.gov.hmcts.reform.pcq.commons.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@Getter
@AllArgsConstructor
public class FeignHeaderConfig {
    private final List<String> headers;
}