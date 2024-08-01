package com.milic.alignment.loan.alignment;

import com.milic.alignment.loan.CurrencyCodes;
import com.milic.alignment.loan.alignment.Loan.Amount;
import com.milic.alignment.loan.alignment.Loan.AmountType;
import com.milic.alignment.loan.sync.LoanModels.AlignmentKPI;
import com.milic.alignment.loan.sync.LoanModels.DataPoint;
import com.milic.alignment.loan.sync.LoanModels.EnvironmentalObjective;
import java.util.List;
import java.util.function.Function;
import javax.money.MonetaryAmount;
import lombok.NonNull;
import org.javamoney.moneta.Money;
import org.springframework.stereotype.Component;

@Component
class ClimateChangeMitigationCalculator {

  public Amount calculate(@NonNull List<AlignmentKPI> alignmentKPIEntityList) {
    MonetaryAmount ccmAligned =
        calculateDataPoints(alignmentKPIEntityList, DataPoint::getAlignedValue);
    MonetaryAmount ccmEligable =
        calculateDataPoints(alignmentKPIEntityList, DataPoint::getEligableValue);

    return Amount.builder().aligned(ccmAligned).eligable(ccmEligable).type(AmountType.CCM).build();
  }

  private MonetaryAmount calculateDataPoints(
      List<AlignmentKPI> alignmentKPIEntityList,
      Function<DataPoint, MonetaryAmount> getAlignedValue) {
    return alignmentKPIEntityList.stream()
        .flatMap(kpi -> kpi.getDataPoints().stream())
        .filter(
            dataPoint -> EnvironmentalObjective.CCM.equals(dataPoint.getEnvironmentalObjective()))
        .map(getAlignedValue)
        .reduce(Money.of(0, CurrencyCodes.EURO), MonetaryAmount::add);
  }
}
