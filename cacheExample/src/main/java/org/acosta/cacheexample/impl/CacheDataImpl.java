package org.acosta.cacheexample.impl;

import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import org.acosta.cacheexample.dtos.DtoRequest;
import org.acosta.cacheexample.dtos.DtoResponse;
import org.acosta.cacheexample.entities.InvoiceHeader;
import org.acosta.cacheexample.repository.InvoiceHeaderRepository;
import org.acosta.cacheexample.services.ICacheData;

@Component
@Slf4j
public class CacheDataImpl implements ICacheData {

  private final InvoiceHeaderRepository invoiceHeaderRepository;

  public CacheDataImpl(
      InvoiceHeaderRepository invoiceHeaderRepository) {
    this.invoiceHeaderRepository = invoiceHeaderRepository;
  }

  @Override
  @Cacheable(cacheNames = "headers", condition = "#id > 1")
  public DtoResponse getDataCache(int id) {
    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
      log.error("Error: ", e);
      e.printStackTrace();
      // Restore interrupted state...
      Thread.currentThread().interrupt();
    }
    DtoResponse requestResponse = new DtoResponse();
    Optional<InvoiceHeader> invoice = invoiceHeaderRepository.findById(id);

    if (invoice.isPresent()) {
      requestResponse.setInvoiceHeader(invoice.get());
      requestResponse.setHttpStatus(HttpStatus.OK);
      return requestResponse;
    }
    requestResponse.setHttpStatus(HttpStatus.NOT_FOUND);
    return requestResponse;
  }

  @Override
  @CacheEvict(cacheNames = "headers", allEntries = true)
  public void flushCache() {
    log.info("Cache flushed");
  }

  @CachePut(cacheNames = "headers", key = "#dtoRequest.id")
  public DtoResponse update(DtoRequest dtoRequest) {
    DtoResponse requestResponse = new DtoResponse();
    Optional<InvoiceHeader> invoiceOptional = invoiceHeaderRepository.findById(dtoRequest.getId());
    if (!invoiceOptional.isPresent()) {
      requestResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
      return requestResponse;
    }
    InvoiceHeader invoice = invoiceOptional.get();
    invoice.setActivo(dtoRequest.getActive());
    invoiceHeaderRepository.save(invoice);

    requestResponse.setInvoiceHeader(invoice);
    requestResponse.setHttpStatus(HttpStatus.OK);
    return requestResponse;
  }

}
