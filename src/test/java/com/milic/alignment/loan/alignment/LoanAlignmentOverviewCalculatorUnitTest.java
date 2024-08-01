package com.milic.alignment.loan.alignment;

import com.milic.alignment.loan.alignment.Loan.Amount;
import com.milic.alignment.loan.alignment.Loan.AmountType;
import com.milic.alignment.loan.sync.LoanModels.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LoanAlignmentOverviewCalculatorUnitTest {

  @Mock private TotalCalculator totalCalculator;
  @Mock private ClimateChangeAdaptationCalculator ccaCalculator;
  @Mock private ClimateChangeMitigationCalculator ccmCalculator;

  @InjectMocks private LoanAlignmentOverviewCalculator calculator;

  @Test
  void should_notAcceptNullProduct() {
    // When
    NullPointerException exception =
        Assertions.assertThrows(NullPointerException.class, () -> calculator.calculate(null));

    // Then
    Assertions.assertEquals("product is marked non-null but is null", exception.getMessage());
  }

  @Test
  void should_returnLoan_when_calculatesTheLoanAlignmentOverview() {
    // Given
    Product product = Products.of(1_500);
    Amount total = Amounts.ofEuro(2_000, 2_000, AmountType.TOTAL);
    Amount cca = Amounts.ofEuro(2_000, 2_000, AmountType.CCA);
    Amount ccm = Amounts.ofEuro(2_000, 2_000, AmountType.CCM);

    Mockito.when(totalCalculator.calculate(ArgumentMatchers.anyList())).thenReturn(total);
    Mockito.when(ccaCalculator.calculate(ArgumentMatchers.anyList())).thenReturn(cca);
    Mockito.when(ccmCalculator.calculate(ArgumentMatchers.anyList())).thenReturn(ccm);

    // When
    Loan loan = calculator.calculate(product);

    // Then
    Assertions.assertEquals(product.getBorrower().getName(), loan.getPartnerName());
    Assertions.assertEquals(product.getGrossCarryingAmount(), loan.getExposure());
    Assertions.assertEquals(total, loan.getTotal());
    Assertions.assertEquals(cca, loan.getCca());
    Assertions.assertEquals(ccm, loan.getCcm());
  }
}
