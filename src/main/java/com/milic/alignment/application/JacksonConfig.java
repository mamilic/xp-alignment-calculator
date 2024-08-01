package com.milic.alignment.application;

import com.fasterxml.jackson.databind.Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.jackson.datatype.money.MoneyModule;

@Configuration
class JacksonConfig {

  @Bean
  public Module objectMapper() {
    return new MoneyModule().withQuotedDecimalNumbers().withDefaultFormatting();
  }
}
