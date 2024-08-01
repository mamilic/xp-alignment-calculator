package com.milic.alignment.loan.alignment;

import com.milic.alignment.loan.CurrencyCodes;
import com.milic.alignment.loan.sync.LoanModels.AlignmentKPI;
import com.milic.alignment.loan.sync.LoanModels.Borrower;
import com.milic.alignment.loan.sync.LoanModels.Borrower.BorrowerId;
import com.milic.alignment.loan.sync.LoanModels.DataPoint;
import com.milic.alignment.loan.sync.LoanModels.EnvironmentalObjective;
import com.milic.alignment.loan.sync.LoanModels.Product;
import com.milic.alignment.loan.sync.LoanModels.Product.ContractId;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import lombok.experimental.UtilityClass;
import org.javamoney.moneta.Money;

@UtilityClass
class Products {

  public static Product of(Number grossCarryingAmount) {
    DataPoint dataPoint =
        DataPoint.builder()
            .environmentalObjective(EnvironmentalObjective.CCM)
            .fulfillmentType("Type A")
            .isSpecializedLending(true)
            .eligableValue(Money.of(5_000, CurrencyCodes.EURO))
            .alignedValue(Money.of(3_000, CurrencyCodes.EURO))
            .build();

    AlignmentKPI alignmentKPI =
        AlignmentKPI.builder()
            .useOfProceeds("Purpose 1")
            .dataPoints(List.of(dataPoint))
            .eligibilityValue(Money.of(2_000, CurrencyCodes.EURO))
            .alignmentValue(Money.of(1_500, CurrencyCodes.EURO))
            .build();

    List<AlignmentKPI> alignmentKPIEntityList = List.of(alignmentKPI);

    return Product.builder()
        .contractId(new ContractId(ThreadLocalRandom.current().nextLong()))
        .borrower(borrower())
        .grossCarryingAmount(Money.of(grossCarryingAmount, CurrencyCodes.EURO))
        .alignmentKPIEntityList(alignmentKPIEntityList)
        .build();
  }

  private static Borrower borrower() {
    return Borrower.builder()
        .id(new BorrowerId(ThreadLocalRandom.current().nextLong()))
        .name(UUID.randomUUID().toString())
        .lei(UUID.randomUUID().toString())
        .build();
  }

  public static AlignmentKPI of(Money alignmentValue, Money eligibilityValue) {
    return AlignmentKPI.builder()
        .alignmentValue(alignmentValue)
        .eligibilityValue(eligibilityValue)
        .build();
  }
}
