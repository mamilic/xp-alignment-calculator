package com.milic.alignment.loan.mockserver;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import lombok.extern.log4j.Log4j2;

@Log4j2
class LoanWiremockStub {

  public static final LoanWiremockStub INSTANCE = new LoanWiremockStub();
  private static final int PORT_NUMBER = 9001;

  private final WireMockServer wireMockServer;

  public LoanWiremockStub() {
    this.wireMockServer = new WireMockServer(WireMockConfiguration.options().port(PORT_NUMBER));
  }

  public static LoanWiremockStub getInstance() {
    return INSTANCE;
  }

  public void start() {
    log.info("Starting WireMock server for loan service stubs.");
    stubLoans();
    wireMockServer.start();
  }

  private void stubLoans() {
    wireMockServer.stubFor(
        get(urlPathMatching("/loans"))
            .willReturn(
                aResponse()
                    .withStatus(200)
                    .withHeader("Content-Type", "application/json")
                    .withBodyFile("loans.json")));
  }
}
