package com.milic.alignment.loan.alignment;

import com.milic.alignment.loan.CurrencyCodes;
import com.milic.alignment.loan.alignment.Loan.Amount;
import com.milic.alignment.loan.alignment.Loan.AmountType;
import com.milic.alignment.loan.sync.LoanModels.AlignmentKPI;
import java.util.List;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TotalCalculatorTest {

  private final TotalCalculator calculator = new TotalCalculator();

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
  void should_calculateTotal_when_ListIsEmpty() {
    // Given
    List<AlignmentKPI> alignmentKPIs = List.of();

    // When
    Amount result = calculator.calculate(alignmentKPIs);

    // Then
    Assertions.assertEquals(result.getAligned(), Money.of(0, CurrencyCodes.EURO));
    Assertions.assertEquals(result.getEligable(), Money.of(0, CurrencyCodes.EURO));
    Assertions.assertEquals(AmountType.TOTAL, result.getType());
  }

  @Test
  void should_calculateTotal_when_AlignmentKPIListHasOneElement() {
    // Given
    Money alignmentValue = Money.of(100, CurrencyCodes.EURO);
    Money eligibilityValue = Money.of(50, CurrencyCodes.EURO);
    AlignmentKPI alignmentKPI = Products.of(alignmentValue, eligibilityValue);

    // When
    Amount result = calculator.calculate(List.of(alignmentKPI));

    // Then
    Assertions.assertEquals(alignmentValue, result.getAligned());
    Assertions.assertEquals(eligibilityValue, result.getEligable());
    Assertions.assertEquals(AmountType.TOTAL, result.getType());
  }

  @Test
  void should_calculateTotal_when_AlignmentKPIListHasMultipleElements() {
    // Given
    Money alignmentValue = Money.of(100, CurrencyCodes.EURO);
    Money eligibilityValue = Money.of(50, CurrencyCodes.EURO);
    AlignmentKPI alignmentKPI = Products.of(alignmentValue, eligibilityValue);

    Money alignmentValueSecond = Money.of(100, CurrencyCodes.EURO);
    Money eligibilityValueSecond = Money.of(50, CurrencyCodes.EURO);
    AlignmentKPI alignmentKPISecond = Products.of(alignmentValueSecond, eligibilityValueSecond);

    // When
    Amount result = calculator.calculate(List.of(alignmentKPI, alignmentKPISecond));

    // Then
    Assertions.assertEquals(Money.of(200, CurrencyCodes.EURO), result.getAligned());
    Assertions.assertEquals(Money.of(100, CurrencyCodes.EURO), result.getEligable());
    Assertions.assertEquals(AmountType.TOTAL, result.getType());
  }
}
