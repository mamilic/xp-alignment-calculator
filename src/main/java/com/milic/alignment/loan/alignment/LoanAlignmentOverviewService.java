package com.milic.alignment.loan.alignment;

import com.milic.alignment.loan.alignment.Loan.LoanId;
import com.milic.alignment.loan.sync.LoanModels.Product;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
class LoanAlignmentOverviewService {

  private final LoanRepository repository;
  private final LoanAlignmentOverviewCalculator loanAlignmentOverviewCalculator;

  @Transactional
  public void processProductItem(Product product) {

    LoanId loanId = LoanId.of(product.getContractId().value());

    if (isItemAlreadyProcessed(loanId)) {
      log.debug("Loan with id [ {} ] already exists. Skipping processing.", loanId.value());
      return;
    }

    log.debug("Processing loan with id [ {} ]", loanId.value());

    Loan loan = loanAlignmentOverviewCalculator.calculate(product);
    repository.save(loan);
  }

  private boolean isItemAlreadyProcessed(LoanId loanId) {
    return repository.findByLoanId(loanId).isPresent();
  }
}
