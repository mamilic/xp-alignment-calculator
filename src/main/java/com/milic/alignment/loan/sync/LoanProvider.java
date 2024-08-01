package com.milic.alignment.loan.sync;

import com.milic.alignment.loan.sync.LoanModels.Product;
import java.util.Set;

public interface LoanProvider {

  Set<Product> getLoans();
}
