package com.milic.alignment.loan.alignment;

import com.milic.alignment.loan.alignment.Loan.Amount;
import com.milic.alignment.loan.alignment.Loan.LoanId;
import com.milic.alignment.loan.sync.LoanModels.Product;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
class LoanAlignmentOverviewCalculator {

  private final TotalCalculator totalCalculator;
  private final ClimateChangeAdaptationCalculator ccaCalculator;
  private final ClimateChangeMitigationCalculator ccmCalculator;

  public Loan calculate(@NonNull Product product) {
    log.trace("calculating loan alignment overview for product [ {} ]", product);

    Amount total = totalCalculator.calculate(product.getAlignmentKPIEntityList());
    Amount climateChangeAdaptation = ccaCalculator.calculate(product.getAlignmentKPIEntityList());
    Amount climateChangeMitigation = ccmCalculator.calculate(product.getAlignmentKPIEntityList());

    Loan loan =
        Loan.builder()
            .loanId(LoanId.of(product.getContractId().value()))
            .partnerName(product.getBorrower().getName())
            .exposure(product.getGrossCarryingAmount())
            .total(total)
            .cca(climateChangeAdaptation)
            .ccm(climateChangeMitigation)
            .build();

    log.debug("calculated loan [ {} ]", loan);

    return loan;
  }
}
