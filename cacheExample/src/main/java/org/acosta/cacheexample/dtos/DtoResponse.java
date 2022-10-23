package org.acosta.cacheexample.dtos;

import org.springframework.http.HttpStatus;

import org.acosta.cacheexample.entities.InvoiceHeader;

import lombok.Data;

@Data
public class DtoResponse {
	long interval;
	HttpStatus httpStatus;
	InvoiceHeader invoiceHeader;
}
