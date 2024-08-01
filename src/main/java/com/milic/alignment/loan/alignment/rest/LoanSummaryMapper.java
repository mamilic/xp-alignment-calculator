package com.milic.alignment.loan.alignment.rest;

import com.milic.alignment.loan.alignment.Loan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface LoanSummaryMapper {

  @Mapping(target = "loanId", source = "loanId.value")
  LoanSummary toDto(Loan loan);
}
