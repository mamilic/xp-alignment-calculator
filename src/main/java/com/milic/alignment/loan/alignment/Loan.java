package com.milic.alignment.loan.alignment;

import javax.money.MonetaryAmount;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Loan {

  @EqualsAndHashCode.Include private final LoanId loanId;
  private final String partnerName;
  private final MonetaryAmount exposure;
  private final Amount total;
  private final Amount ccm;
  private final Amount cca;

  @Getter
  @ToString
  @Builder
  @RequiredArgsConstructor
  public static class Amount {
    private final MonetaryAmount aligned;
    private final MonetaryAmount eligable;
    private final AmountType type;
  }

  public enum AmountType {
    TOTAL,
    CCA,
    CCM
  }

  public record LoanId(Long value) {
    public static LoanId of(Long value) {
      return new LoanId(value);
    }
  }
}
