package com.milic.alignment.loan.sync;

import com.milic.alignment.loan.mockserver.LoanWiremockStartedEvent;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
class LoanWiremockStartedListener implements ApplicationListener<LoanWiremockStartedEvent> {

  private final LoanSyncer service;

  @Async
  @Override
  public void onApplicationEvent(@NonNull LoanWiremockStartedEvent event) {
    log.info("Triggering [ sync loans ] on app startup");
    service.syncLoans();
  }
}
