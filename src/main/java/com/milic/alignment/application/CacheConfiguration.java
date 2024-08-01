package com.milic.alignment.application;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration
public class CacheConfiguration {

  public static final String LOAN_CACHE = "loan";
  public static final String LOAN_SUMMARIES_CACHE = "loan-summaries";
}
