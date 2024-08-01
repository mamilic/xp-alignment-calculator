package com.milic.alignment.loan.sync;

import com.milic.alignment.shared.LockSyncManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@Primary
@RequiredArgsConstructor
class LoanSyncLockServiceAdapter implements LoanSyncer {

  private final LoanSyncService service;
  private final LockSyncManager syncManager;

  @Override
  public void syncLoans() {
    if (syncManager.acquire()) {
      try {
        service.syncLoans();
      } finally {
        syncManager.release();
      }
    } else {
      log.debug("Sync is already in progress. Exiting method.");
    }
  }
}
