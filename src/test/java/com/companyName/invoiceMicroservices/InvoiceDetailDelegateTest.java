package com.companyName.invoiceMicroservices;

import com.companyName.coreMicroservices.repository.InvoiceRepository;
import com.companyName.coreMicroservices.repository.entity.Invoice;
import com.companyName.coreMicroservices.repository.entity.Payment;
import com.companyName.invoiceMicroservices.rest.invoice.delegate.impl.InvoiceDetailDelegateImpl;
import com.companyName.invoiceMicroservices.rest.invoice.model.response.InvoiceDetailResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InvoiceDetailDelegateTest {

    @Mock
    InvoiceRepository repository;

    @InjectMocks
    InvoiceDetailDelegateImpl delegate;


    @Test
    void dbResultToDto_shouldReturnListInvoiceDetailResponse(){

        List<Invoice> saved = new ArrayList<>();
        Invoice invoice = new Invoice();
        invoice.setId(1L);
        invoice.setInvoiceNumber(73L);

        List<Payment> savedListPayment = new ArrayList<>();
        Payment payment = new Payment();
        payment.setId(1L);
        payment.setTransaction_date(LocalDate.parse("2024-09-22"));
        payment.setTransaction_description("description test");
        payment.setFkUser("TSTCOD99T99T999T");
        payment.setAmount(new BigDecimal("3000.01"));
        payment.setCurrency("EUR");
        savedListPayment.add(payment);
        invoice.setPayments(savedListPayment);

        saved.add(invoice);

        when(repository.findAll()).thenReturn(saved);

        List<InvoiceDetailResponse> iDr = delegate.getAllInvoiceList();

        assertEquals(iDr.get(0).getId(), saved.get(0).getId(),"OK ID");
        assertEquals(iDr.get(0).getInvoiceNumber(), saved.get(0).getInvoiceNumber(),"OK INVOICE NUMBER");
        assertEquals(iDr.get(0).getPayments().get(0).getId(),saved.get(0).getPayments().get(0).getId(),"OK PAYMENT ID");
        assertEquals(iDr.get(0).getPayments().get(0).getTransaction_date(),saved.get(0).getPayments().get(0).getTransaction_date(),"OK TRANSACTION_DATE");
        assertEquals(iDr.get(0).getPayments().get(0).getTransaction_description(),saved.get(0).getPayments().get(0).getTransaction_description(),"OK TRANSACTION_DESCRIPTION");
        assertEquals(iDr.get(0).getPayments().get(0).getFkUser(),saved.get(0).getPayments().get(0).getFkUser(),"OK FKUSER");
        assertEquals(iDr.get(0).getPayments().get(0).getAmount(),saved.get(0).getPayments().get(0).getAmount(),"OK AMOUNT");
        assertEquals("EUR", iDr.get(0).getPayments().get(0).getCurrency(), "OK CURRENCY");

    }

}
