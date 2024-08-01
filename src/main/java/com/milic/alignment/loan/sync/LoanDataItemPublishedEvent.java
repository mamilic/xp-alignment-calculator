package com.milic.alignment.loan.sync;

import com.milic.alignment.loan.sync.LoanModels.Product;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class LoanDataItemPublishedEvent extends ApplicationEvent {

  private final Product product;

  public LoanDataItemPublishedEvent(Object source, Product product) {
    super(source);
    this.product = product;
  }
}
