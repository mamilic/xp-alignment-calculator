package com.milic.alignment.loan.alignment;

import com.milic.alignment.application.CacheConfiguration;
import com.milic.alignment.loan.alignment.Loan.LoanId;
import java.util.Optional;
import lombok.NonNull;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LoanRepository {

  Optional<Loan> findByLoanId(LoanId loanId);

  @CacheEvict(value = CacheConfiguration.LOAN_SUMMARIES_CACHE, allEntries = true)
  Loan save(@NonNull Loan loan);

  @Cacheable(CacheConfiguration.LOAN_SUMMARIES_CACHE)
  Page<Loan> findAll(Pageable pageable);
}
