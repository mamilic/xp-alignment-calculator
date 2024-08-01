package com.milic.alignment.loan.sync.client.http;

import com.milic.alignment.loan.sync.client.http.LoanModels.LoanResponse;
import org.springframework.web.service.annotation.GetExchange;

interface LoanHTTPClient {

  @GetExchange("/loans")
  LoanResponse getLoans();
}
