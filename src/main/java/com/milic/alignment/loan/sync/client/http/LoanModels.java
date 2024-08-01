package com.milic.alignment.loan.sync.client.http;

import java.util.List;
import javax.money.MonetaryAmount;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;

@UtilityClass
class LoanModels {

  @Getter
  @RequiredArgsConstructor
  static class LoanResponse {
    private final Metadata metadata;
    private final List<ProductWithAlignment> productListWithAlignment;
  }

  @Getter
  @RequiredArgsConstructor
  static class Metadata {
    private final List<String> productIds;
    private final MonetaryAmount grossCarryingAmountTotal;
    private final MonetaryAmount eligabilityTotal;
    private final MonetaryAmount alignmentTotal;
  }

  @Getter
  @RequiredArgsConstructor
  static class ProductWithAlignment {
    private final Product product;
    private final List<AlignmentKPI> alignmentKPIList;
  }

  @Getter
  @RequiredArgsConstructor
  static class Product {
    private final Long contractId;
    private final Borrower borrower;
    private final List<String> useOfProceeds;
    private final MonetaryAmount grossCarryingAmount;
  }

  @Getter
  @RequiredArgsConstructor
  static class Borrower {
    private final Long id;
    private final String name;
    private final String lei;
  }

  @Getter
  @RequiredArgsConstructor
  static class AlignmentKPI {
    private final String useOfProceeds;
    private final List<DataPoint> dataPoints;
    private final MonetaryAmount eligibilityValue;
    private final MonetaryAmount alignmentValue;
  }

  @Getter
  @RequiredArgsConstructor
  static class DataPoint {
    private final String environmentalObjective;
    private final String fulfillmentType;
    private final Boolean isSpecializedLending;
    private final MonetaryAmount eligableValue;
    private final MonetaryAmount alignedValue;
  }
}
