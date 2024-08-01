package com.milic.alignment.loan.alignment.rest;

import javax.money.MonetaryAmount;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
class LoanSummary {

  private final Long loanId;
  private final String partnerName;
  private final MonetaryAmount exposure;
  private final Amount total;
  private final Amount ccm;
  private final Amount cca;

  @Getter
  @RequiredArgsConstructor
  public static class Amount {
    private final MonetaryAmount aligned;
    private final MonetaryAmount eligable;
  }
}
