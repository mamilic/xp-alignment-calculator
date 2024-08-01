package com.milic.alignment.loan.alignment.postgres;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@Table(name = "loan")
class LoanEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "loan_id", nullable = false, unique = true)
  private Long loanId;

  @Column(name = "partnerName", nullable = false)
  private String partnerName;

  @ManyToOne
  @JoinColumn(name = "exposure_id", referencedColumnName = "id", nullable = false)
  private CurrencyValueEntity exposure;

  @ManyToOne
  @JoinColumn(name = "total_aligned_id", referencedColumnName = "id", nullable = false)
  private CurrencyValueEntity totalAligned;

  @ManyToOne
  @JoinColumn(name = "total_eligable", referencedColumnName = "id", nullable = false)
  private CurrencyValueEntity totalEligable;

  @ManyToOne
  @JoinColumn(name = "ccm_eligable_id", referencedColumnName = "id", nullable = false)
  private CurrencyValueEntity ccmEligable;

  @ManyToOne
  @JoinColumn(name = "ccm_aligned_id", referencedColumnName = "id", nullable = false)
  private CurrencyValueEntity ccmAligned;

  @ManyToOne
  @JoinColumn(name = "cca_eligable_id", referencedColumnName = "id", nullable = false)
  private CurrencyValueEntity ccaEligable;

  @ManyToOne
  @JoinColumn(name = "cca_aligned_id", referencedColumnName = "id", nullable = false)
  private CurrencyValueEntity ccaAligned;
}
