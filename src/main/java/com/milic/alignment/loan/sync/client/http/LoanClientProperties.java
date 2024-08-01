package com.milic.alignment.loan.sync.client.http;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "external.api.loan")
class LoanClientProperties {
  private String baseUrl;
}
