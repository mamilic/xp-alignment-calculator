package com.milic.alignment.loan.sync;

import com.milic.alignment.loan.mockserver.LoanWiremockStartedEvent;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
class LoanFetchScheduler {

  private static final long FETCH_LOANS_RATE_MILLIS = 60_000;
  private static final Duration FETCH_LOANS_RATE = Duration.ofMillis(FETCH_LOANS_RATE_MILLIS);

  private final LoanSyncer service;
  private final TaskScheduler taskScheduler;

  @Async
  @EventListener(LoanWiremockStartedEvent.class)
  public void onLoanWiremockStartedEvent() {
    taskScheduler.scheduleAtFixedRate(this::syncLoans, FETCH_LOANS_RATE);
  }

  private void syncLoans() {
    log.info(
        "Triggering [ sync loans ] scheduled task, rate [ {} ] millis", FETCH_LOANS_RATE_MILLIS);
    service.syncLoans();
  }
}
