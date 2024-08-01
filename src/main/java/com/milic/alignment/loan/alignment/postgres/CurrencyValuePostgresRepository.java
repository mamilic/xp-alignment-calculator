package com.milic.alignment.loan.alignment.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface CurrencyValuePostgresRepository extends JpaRepository<CurrencyValueEntity, Long> {}
