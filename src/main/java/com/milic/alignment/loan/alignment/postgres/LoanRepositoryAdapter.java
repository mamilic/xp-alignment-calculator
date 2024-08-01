package com.milic.alignment.loan.alignment.postgres;

import com.milic.alignment.loan.alignment.Loan;
import com.milic.alignment.loan.alignment.Loan.LoanId;
import com.milic.alignment.loan.alignment.LoanRepository;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class LoanRepositoryAdapter implements LoanRepository {

  private final LoanPostgresMapper mapper;
  private final LoanPostgresRepository loanRepository;
  private final CurrencyValuePostgresRepository currencyRepository;

  @Override
  public Optional<Loan> findByLoanId(LoanId loanId) {
    return loanRepository.findByLoanId(loanId.value()).map(mapper::toDomain);
  }

  @Override
  public Loan save(@NonNull Loan loan) {
    LoanEntity entity = mapper.toEntity(loan);

    if (Objects.nonNull(entity.getTotalEligable())) {
      CurrencyValueEntity savedEligableTotal = currencyRepository.save(entity.getTotalEligable());
      entity.setTotalEligable(savedEligableTotal);
    }

    if (Objects.nonNull(entity.getTotalAligned())) {
      CurrencyValueEntity alignedTotal = currencyRepository.save(entity.getTotalAligned());
      entity.setTotalAligned(alignedTotal);
    }

    if (Objects.nonNull(entity.getExposure())) {
      CurrencyValueEntity exposure = currencyRepository.save(entity.getExposure());
      entity.setExposure(exposure);
    }

    if (Objects.nonNull(entity.getCcaAligned())) {
      CurrencyValueEntity ccaAligned = currencyRepository.save(entity.getCcaAligned());
      entity.setCcaAligned(ccaAligned);
    }

    if (Objects.nonNull(entity.getCcaEligable())) {
      CurrencyValueEntity ccaEligable = currencyRepository.save(entity.getCcaEligable());
      entity.setCcaEligable(ccaEligable);
    }

    if (Objects.nonNull(entity.getCcmAligned())) {
      CurrencyValueEntity ccmAligned = currencyRepository.save(entity.getCcmAligned());
      entity.setCcmAligned(ccmAligned);
    }

    if (Objects.nonNull(entity.getCcmAligned())) {
      CurrencyValueEntity ccmEligable = currencyRepository.save(entity.getCcmEligable());
      entity.setCcmEligable(ccmEligable);
    }

    loanRepository.save(entity);

    return mapper.toDomain(entity);
  }

  public Page<Loan> findAll(Pageable pageable) {
    Page<LoanEntity> entityPage = loanRepository.findAll(pageable);
    List<Loan> domainList = entityPage.getContent().stream().map(mapper::toDomain).toList();
    return new PageImpl<>(domainList, pageable, entityPage.getTotalElements());
  }
}
