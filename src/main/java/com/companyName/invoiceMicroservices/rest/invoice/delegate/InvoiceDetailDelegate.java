package com.companyName.invoiceMicroservices.rest.invoice.delegate;


import com.companyName.coreMicroservices.repository.entity.Invoice;
import com.companyName.coreMicroservices.repository.entity.Payment;
import com.companyName.invoiceMicroservices.rest.invoice.model.response.InvoiceDetailResponse;

import java.security.InvalidParameterException;
import java.util.List;

public interface InvoiceDetailDelegate {

    /* DEPRECATO
    List<InvoiceDetailResponse> getInvoiceDetail(Long invoiceNumber) throws InvalidParameterException ;*/
    List<InvoiceDetailResponse> getInvoiceByInvoiceNumber(Long invoiceNumber) throws InvalidParameterException ;
    List<InvoiceDetailResponse> getAllInvoiceList() throws InvalidParameterException ;
    void addPaymentToInvoice(Long invoiceId, Payment payment);
    List<InvoiceDetailResponse> updateInvoiceDetail(Long invoiceNumber, Long invoiceId) throws InvalidParameterException ;
    boolean deleteInvoiceDetail(Invoice invoice) throws InvalidParameterException ;
    boolean deleteInvoiceDetailByInvoiceNumber(Invoice invoice) throws InvalidParameterException ;
}
