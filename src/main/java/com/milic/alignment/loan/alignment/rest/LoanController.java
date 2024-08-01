package com.milic.alignment.loan.alignment.rest;

import com.milic.alignment.loan.alignment.Loan;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
class LoanController {

  private final LoanSummaryMapper mapper;
  private final LoanSummaryService service;

  @GetMapping(path = "loanData-summary", produces = MediaType.APPLICATION_JSON_VALUE)
  public Page<LoanSummary> getLoans(Pageable pageable) {
    Page<Loan> loanSummaries = service.getLoanSummaries(pageable);
    List<LoanSummary> loans = loanSummaries.stream().map(mapper::toDto).toList();

    return new PageImpl<>(loans, pageable, loanSummaries.getTotalElements());
  }
}
