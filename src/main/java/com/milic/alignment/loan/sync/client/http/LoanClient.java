package com.milic.alignment.loan.sync.client.http;

import com.milic.alignment.loan.sync.LoanModels.Product;
import com.milic.alignment.loan.sync.LoanProvider;
import com.milic.alignment.loan.sync.client.http.LoanModels.LoanResponse;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class LoanClient implements LoanProvider {

  private final LoanMapper mapper;
  private final LoanHTTPClient client;

  @Override
  public Set<Product> getLoans() {
    LoanResponse loans = client.getLoans();
    return loans.getProductListWithAlignment().stream()
        .map(mapper::toDomain)
        .collect(Collectors.toSet());
  }
}
