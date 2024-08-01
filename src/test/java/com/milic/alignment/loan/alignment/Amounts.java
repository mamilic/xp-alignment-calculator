package com.milic.alignment.loan.alignment;

import com.milic.alignment.loan.CurrencyCodes;
import com.milic.alignment.loan.alignment.Loan.Amount;
import com.milic.alignment.loan.alignment.Loan.AmountType;
import org.javamoney.moneta.Money;

class Amounts {

  public static Amount ofEuro(Number aligned, Number eligable, AmountType type) {
    return Amount.builder()
        .aligned(Money.of(aligned, CurrencyCodes.EURO))
        .eligable(Money.of(eligable, CurrencyCodes.EURO))
        .build();
  }
}
