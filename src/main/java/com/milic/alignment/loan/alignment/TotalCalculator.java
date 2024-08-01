package com.milic.alignment.loan.alignment;

import com.milic.alignment.loan.CurrencyCodes;
import com.milic.alignment.loan.alignment.Loan.Amount;
import com.milic.alignment.loan.alignment.Loan.AmountType;
import com.milic.alignment.loan.sync.LoanModels.AlignmentKPI;
import java.util.List;
import java.util.function.Function;
import javax.money.MonetaryAmount;
import lombok.NonNull;
import org.javamoney.moneta.Money;
import org.springframework.stereotype.Component;

@Component
class TotalCalculator {

  public Amount calculate(@NonNull List<AlignmentKPI> alignmentKPIEntityList) {
    MonetaryAmount alignedTotal =
        calculateTotal(alignmentKPIEntityList, AlignmentKPI::getAlignmentValue);
    MonetaryAmount eligableTotal =
        calculateTotal(alignmentKPIEntityList, AlignmentKPI::getEligibilityValue);

    return Amount.builder()
        .aligned(alignedTotal)
        .eligable(eligableTotal)
        .type(AmountType.TOTAL)
        .build();
  }

  private MonetaryAmount calculateTotal(
      @NonNull List<AlignmentKPI> alignmentKPIList,
      Function<AlignmentKPI, MonetaryAmount> getAlignmentValue) {
    return alignmentKPIList.stream()
        .map(getAlignmentValue)
        .reduce(Money.of(0, CurrencyCodes.EURO), MonetaryAmount::add);
  }
}
