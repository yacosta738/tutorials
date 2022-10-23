package org.acosta.cacheexample.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Entity(name = "INVOICEHEADER")
@Data
public class InvoiceHeader implements Serializable{
	
	private static final long serialVersionUID = 1L;

	
	@Id
	int id;

	@Column(name="active")
	String activo;	

	@Column(name="fiscalyear")
	int yearFiscal;
	@Column(name="numberinvoice")
	int numberInvoice;

	@Column(name="customerid",insertable=false, updatable=false)
	int customerId;



}
