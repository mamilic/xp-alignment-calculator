package com.milic.alignment.loan.sync.client.http;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Log4j2
@Configuration
class LoanClientConfiguration {

  @Bean
  LoanHTTPClient loanHTTPClient(RestClient.Builder builder, LoanClientProperties properties) {
    log.info("Creating LoanHTTPClient with properties: [ {} ]", properties);

    RestClient client = builder.baseUrl(properties.getBaseUrl()).build();
    RestClientAdapter adapter = RestClientAdapter.create(client);
    HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();

    return factory.createClient(LoanHTTPClient.class);
  }
}
