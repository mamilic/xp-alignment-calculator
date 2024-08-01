package com.milic.alignment.loan.alignment.postgres;

import com.milic.alignment.loan.alignment.Loan;
import java.math.BigDecimal;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import org.javamoney.moneta.Money;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface LoanPostgresMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "loanId", source = "loanId.value")
  @Mapping(target = "exposure", source = "exposure")
  @Mapping(target = "totalEligable", source = "total.eligable")
  @Mapping(target = "totalAligned", source = "total.aligned")
  @Mapping(target = "ccaAligned", source = "cca.aligned")
  @Mapping(target = "ccaEligable", source = "cca.eligable")
  @Mapping(target = "ccmAligned", source = "ccm.aligned")
  @Mapping(target = "ccmEligable", source = "ccm.eligable")
  LoanEntity toEntity(Loan source);

  @Mapping(target = "loanId.value", source = "loanId")
  @Mapping(target = "cca.aligned", source = "ccaAligned")
  @Mapping(target = "cca.eligable", source = "ccaEligable")
  @Mapping(target = "ccm.aligned", source = "ccmAligned")
  @Mapping(target = "ccm.eligable", source = "ccmEligable")
  @Mapping(target = "total.aligned", source = "totalAligned")
  @Mapping(target = "total.eligable", source = "totalEligable")
  Loan toDomain(LoanEntity source);

  default CurrencyValueEntity toEntity(MonetaryAmount source) {
    return CurrencyValueEntity.builder()
        .amount(source.getNumber().numberValue(BigDecimal.class))
        .currencyCode(source.getCurrency().getCurrencyCode())
        .build();
  }

  default MonetaryAmount toDomain(CurrencyValueEntity source) {
    return Money.of(source.getAmount(), Monetary.getCurrency(source.getCurrencyCode()));
  }
}
