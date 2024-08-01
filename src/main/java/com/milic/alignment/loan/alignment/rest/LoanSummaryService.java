package com.milic.alignment.loan.alignment.rest;

import com.milic.alignment.loan.alignment.Loan;
import com.milic.alignment.loan.alignment.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class LoanSummaryService {

  private final LoanRepository repository;

  public Page<Loan> getLoanSummaries(Pageable pageable) {
    return repository.findAll(pageable);
  }
}
