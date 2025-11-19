package com.companyName.invoiceMicroservices.rest.invoice.delegate;


import com.companyName.coreMicroservices.repository.entity.Invoice;
import com.companyName.invoiceMicroservices.rest.invoice.model.response.InvoiceDetailResponse;

import java.security.InvalidParameterException;
import java.util.List;

public interface InvoiceDetailDelegate {

    List<InvoiceDetailResponse> getInvoiceDetail(String FkUser) throws InvalidParameterException ;
    List<InvoiceDetailResponse> getInvoiceDetailJPA(String FkUser) throws InvalidParameterException ;
    List<InvoiceDetailResponse> getAllJPA() throws InvalidParameterException ;
    List<InvoiceDetailResponse> addInvoiceDetail(Invoice invoice) throws InvalidParameterException ;
    List<InvoiceDetailResponse> updateInvoiceDetail(Invoice invoice) throws InvalidParameterException ;
    boolean deleteInvoiceDetail(Invoice invoice) throws InvalidParameterException ;
    boolean deleteInvoiceDetailByCf(Invoice invoice) throws InvalidParameterException ;
}
