package com.milic.alignment.loan.sync;

import java.util.List;
import javax.money.MonetaryAmount;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.UtilityClass;

@UtilityClass
public class LoanModels {

  @Getter
  @Builder
  @ToString
  @RequiredArgsConstructor
  @EqualsAndHashCode(onlyExplicitlyIncluded = true)
  public static class Product {
    @EqualsAndHashCode.Include private final ContractId contractId;
    private final Borrower borrower;
    private final List<String> useOfProceeds;
    private final MonetaryAmount grossCarryingAmount;
    private final List<AlignmentKPI> alignmentKPIEntityList;

    public record ContractId(Long value) {}
  }

  @Getter
  @Builder
  @ToString
  @RequiredArgsConstructor
  @EqualsAndHashCode(onlyExplicitlyIncluded = true)
  public static class Borrower {
    @EqualsAndHashCode.Include private final BorrowerId id;
    private final String name;
    private final String lei;

    public record BorrowerId(Long value) {}
  }

  @Getter
  @Builder
  @ToString
  @EqualsAndHashCode
  @RequiredArgsConstructor
  public static class AlignmentKPI {
    private final String useOfProceeds;
    private final List<DataPoint> dataPoints;
    private final MonetaryAmount eligibilityValue;
    private final MonetaryAmount alignmentValue;
  }

  @Getter
  @Builder
  @ToString
  @EqualsAndHashCode
  @RequiredArgsConstructor
  public static class DataPoint {
    private final EnvironmentalObjective environmentalObjective;
    private final String fulfillmentType;
    private final Boolean isSpecializedLending;
    private final MonetaryAmount eligableValue;
    private final MonetaryAmount alignedValue;
  }

  public enum EnvironmentalObjective {
    CCM,
    CCA
  }
}
