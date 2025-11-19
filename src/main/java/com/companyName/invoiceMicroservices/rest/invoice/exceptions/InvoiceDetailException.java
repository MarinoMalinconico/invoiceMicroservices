package com.companyName.invoiceMicroservices.rest.invoice.exceptions;

public class InvoiceDetailException extends Exception {
    public InvoiceDetailException(String message) {
        super(message);
    }
    public InvoiceDetailException(String message, Throwable cause) {
        super(message, cause);
    }
}
