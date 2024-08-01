package com.milic.alignment.loan.alignment.postgres;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "currency_value")
public class CurrencyValueEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "amount", nullable = false)
  private BigDecimal amount;

  @Column(name = "currency_code", nullable = false)
  private String currencyCode;

  @Builder
  public CurrencyValueEntity(Long id, BigDecimal amount, String currencyCode) {
    this.id = id;
    this.amount = amount;
    this.currencyCode = currencyCode;
  }
}
