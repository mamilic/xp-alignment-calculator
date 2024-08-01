package com.milic.alignment.loan.mockserver;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
class ApplicationStartedListener implements ApplicationListener<ApplicationStartedEvent> {

  private final ApplicationEventPublisher publisher;

  @Async
  @Override
  public void onApplicationEvent(@NonNull ApplicationStartedEvent event) {
    LoanWiremockStub.getInstance().start();

    publisher.publishEvent(new LoanWiremockStartedEvent(this));
  }
}
