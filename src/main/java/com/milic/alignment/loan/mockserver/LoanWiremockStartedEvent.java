package com.milic.alignment.loan.mockserver;

import org.springframework.context.ApplicationEvent;

public class LoanWiremockStartedEvent extends ApplicationEvent {
  public LoanWiremockStartedEvent(Object source) {
    super(source);
  }
}
