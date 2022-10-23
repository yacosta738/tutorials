package org.acosta.cacheexample.repository;

import org.springframework.data.repository.CrudRepository;

import org.acosta.cacheexample.entities.InvoiceHeader;

public interface InvoiceHeaderRepository extends CrudRepository<InvoiceHeader, Integer>{

}
