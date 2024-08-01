package com.milic.alignment.loan.alignment;

import com.milic.alignment.loan.CurrencyCodes;
import com.milic.alignment.loan.alignment.Loan.Amount;
import com.milic.alignment.loan.alignment.Loan.AmountType;
import com.milic.alignment.loan.sync.LoanModels.AlignmentKPI;
import com.milic.alignment.loan.sync.LoanModels.DataPoint;
import com.milic.alignment.loan.sync.LoanModels.EnvironmentalObjective;
import java.util.List;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CcmCalculatorTest {

  private final ClimateChangeMitigationCalculator calculator =
      new ClimateChangeMitigationCalculator();

  @Test
  void should_notAcceptNullList() {
    // When
    NullPointerException exception =
        Assertions.assertThrows(NullPointerException.class, () -> calculator.calculate(null));

    // Then
    Assertions.assertEquals(
        "alignmentKPIEntityList is marked non-null but is null", exception.getMessage());
  }

  @Test
  void should_calculateTotal_when_listIsEmpty() {
    // Given
    List<AlignmentKPI> alignmentKPIs = List.of();

    // When
    Amount result = calculator.calculate(alignmentKPIs);

    // Then
    Assertions.assertEquals(Money.of(0, CurrencyCodes.EURO), result.getAligned());
    Assertions.assertEquals(Money.of(0, CurrencyCodes.EURO), result.getEligable());
    Assertions.assertEquals(AmountType.CCM, result.getType());
  }

  @Test
  void should_calculateTotal_when_alignmentKPIListHasOneElementAndNoDataPoints() {
    // Given
    AlignmentKPI kpi = AlignmentKPI.builder().dataPoints(List.of()).build();

    // When
    Amount result = calculator.calculate(List.of(kpi));

    // Then
    Assertions.assertEquals(Money.of(0, CurrencyCodes.EURO), result.getAligned());
    Assertions.assertEquals(Money.of(0, CurrencyCodes.EURO), result.getEligable());
    Assertions.assertEquals(AmountType.CCM, result.getType());
  }

  @Test
  void should_calculateTotal_when_alignmentKPIHasOneAndMultipleDataPoints() {
    // Given
    DataPoint ccaDataPoint =
        DataPoint.builder()
            .environmentalObjective(EnvironmentalObjective.CCA)
            .alignedValue(Money.of(100, CurrencyCodes.EURO))
            .eligableValue(Money.of(50, CurrencyCodes.EURO))
            .build();
    DataPoint nonCcaDataPoint =
        DataPoint.builder()
            .environmentalObjective(EnvironmentalObjective.CCM)
            .alignedValue(Money.of(200, CurrencyCodes.EURO))
            .eligableValue(Money.of(100, CurrencyCodes.EURO))
            .build();
    AlignmentKPI kpi =
        AlignmentKPI.builder().dataPoints(List.of(ccaDataPoint, nonCcaDataPoint)).build();

    // When
    Amount result = calculator.calculate(List.of(kpi));

    // Then
    Assertions.assertEquals(Money.of(200, CurrencyCodes.EURO), result.getAligned());
    Assertions.assertEquals(Money.of(100, CurrencyCodes.EURO), result.getEligable());
    Assertions.assertEquals(AmountType.CCM, result.getType());
  }

  @Test
  void should_calculate_when_alignmentKPUHasMultipleKPIsAndDataPoints() {
    // Given
    DataPoint ccaDataPoint1 =
        DataPoint.builder()
            .environmentalObjective(EnvironmentalObjective.CCM)
            .alignedValue(Money.of(100, CurrencyCodes.EURO))
            .eligableValue(Money.of(50, CurrencyCodes.EURO))
            .build();
    DataPoint ccaDataPoint2 =
        DataPoint.builder()
            .environmentalObjective(EnvironmentalObjective.CCM)
            .alignedValue(Money.of(200, CurrencyCodes.EURO))
            .eligableValue(Money.of(100, CurrencyCodes.EURO))
            .build();
    AlignmentKPI kpi1 = AlignmentKPI.builder().dataPoints(List.of(ccaDataPoint1)).build();
    AlignmentKPI kpi2 = AlignmentKPI.builder().dataPoints(List.of(ccaDataPoint2)).build();
    List<AlignmentKPI> alignmentKPIs = List.of(kpi1, kpi2);

    // When
    Amount result = calculator.calculate(alignmentKPIs);

    // Then
    Assertions.assertEquals(Money.of(300, CurrencyCodes.EURO), result.getAligned());
    Assertions.assertEquals(Money.of(150, CurrencyCodes.EURO), result.getEligable());
    Assertions.assertEquals(AmountType.CCM, result.getType());
  }
}
