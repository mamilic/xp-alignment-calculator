package com.milic.alignment.loan.sync.client.http;

import com.milic.alignment.loan.sync.LoanModels.Borrower.BorrowerId;
import com.milic.alignment.loan.sync.LoanModels.Product;
import com.milic.alignment.loan.sync.LoanModels.Product.ContractId;
import com.milic.alignment.loan.sync.client.http.LoanModels.ProductWithAlignment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface LoanMapper {

  @Mapping(target = "contractId", source = "source.product.contractId")
  @Mapping(target = "borrower", source = "source.product.borrower")
  @Mapping(target = "useOfProceeds", source = "source.product.useOfProceeds")
  @Mapping(target = "grossCarryingAmount", source = "source.product.grossCarryingAmount")
  @Mapping(target = "alignmentKPIEntityList", source = "source.alignmentKPIList")
  Product toDomain(ProductWithAlignment source);

  @Mapping(target = "value", source = "value")
  BorrowerId mapBorrowerId(Long value);

  @Mapping(target = "value", source = "value")
  ContractId mapProductId(Long value);
}
