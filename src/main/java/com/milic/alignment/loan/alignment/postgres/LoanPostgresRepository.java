package com.milic.alignment.loan.alignment.postgres;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface LoanPostgresRepository extends JpaRepository<LoanEntity, Long> {

  Optional<LoanEntity> findByLoanId(Long id);
}
