package com.milic.alignment.shared;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class LockSyncManager {
  private final ReentrantLock lock = new ReentrantLock();
  private final AtomicBoolean isInProgress = new AtomicBoolean(false);

  public boolean acquire() {
    log.trace("Attempting to start sync.");
    if (isInProgress.get()) {
      log.trace("Sync already in progress. Exiting method.");
      return false;
    }

    if (lock.tryLock()) {
      try {
        if (isInProgress.get()) {
          log.trace("Sync already in progress after acquiring lock. Exiting method.");
          return false;
        }
        log.trace("Lock acquired. Setting isInProgress to true.");
        isInProgress.set(true);
        return true;
      } catch (Exception e) {
        lock.unlock();
        throw e;
      }
    } else {
      log.trace("Unable to acquire lock. Exiting method.");
      return false;
    }
  }

  public void release() {
    isInProgress.set(false);
    lock.unlock();
    log.trace("Lock released and isInProgress set to false.");
  }
}
