package com.milic.alignment.loan.sync;

import com.milic.alignment.loan.sync.LoanModels.Product;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
class LoanSyncService implements LoanSyncer {

  private final LoanProvider provider;
  private final ApplicationEventPublisher publisher;

  @Override
  public void syncLoans() {
    log.info("Syncing loans");

    Set<Product> products = provider.getLoans();

    products.forEach(
        product -> publisher.publishEvent(new LoanDataItemPublishedEvent(this, product)));
  }
}
