package com.milic.alignment.loan.alignment;

import com.milic.alignment.loan.sync.LoanDataItemPublishedEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
class LoanDataItemPublishedEventListener
    implements ApplicationListener<LoanDataItemPublishedEvent> {

  private final ExecutorService executor = Executors.newSingleThreadExecutor();

  private final LoanAlignmentOverviewService service;

  @Override
  public void onApplicationEvent(@NonNull LoanDataItemPublishedEvent event) {
    executor.submit(() -> processEvent(event));
  }

  private void processEvent(LoanDataItemPublishedEvent event) {
    log.trace("item received [ {} ]", event.getProduct());

    service.processProductItem(event.getProduct());
  }
}
