package com.companyName.invoiceMicroservices.rest.invoice.delegate.impl;

import com.companyName.coreMicroservices.repository.InvoiceRepository;
import com.companyName.coreMicroservices.repository.entity.Invoice;
import com.companyName.coreMicroservices.repository.entity.Payment;
import com.companyName.invoiceMicroservices.rest.invoice.delegate.InvoiceDetailDelegate;
import com.companyName.invoiceMicroservices.rest.invoice.model.response.InvoiceDetailResponse;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class InvoiceDetailDelegateImpl implements InvoiceDetailDelegate {

    @Autowired
    InvoiceRepository repository;

    @Override
    public List<InvoiceDetailResponse> getInvoiceDetail(Long invoiceNumber) {
        log.debug("Into getInvoiceDetail delegate with PathParameter [{}]", invoiceNumber);

        List<Invoice> dbResult = repository.getAllInvoicePerUser(invoiceNumber);
        List<InvoiceDetailResponse> response = dbResultToDto(dbResult);

        return response;
    }

    private List<InvoiceDetailResponse> dbResultToDto(List<Invoice> dtos) {
        List<InvoiceDetailResponse> formattedDTOs = new ArrayList<>();
        for (Invoice dto : dtos) {
            InvoiceDetailResponse fileDto = new InvoiceDetailResponse();
            fileDto.setId(dto.getId());
            fileDto.setInvoiceNumber(dto.getInvoiceNumber());
            fileDto.setPayments(dto.getPayments());
            formattedDTOs.add(fileDto);
        }
        return formattedDTOs;
    }

    @Override
    public List<InvoiceDetailResponse> getInvoiceDetailJPA(Long invoiceNumber) {
        log.debug("Into getInvoiceDetail delegate with PathParameter [{}]", invoiceNumber);

        List<Invoice> dbResult = repository.findByinvoiceNumber(invoiceNumber);
        List<InvoiceDetailResponse> response = dbResultToDto(dbResult);

        return response;
    }

    @Override
    public List<InvoiceDetailResponse> getAllJPA() {
        log.debug("Into getInvoiceDetail delegate with all");

        List<Invoice> dbResult = repository.findAll();
        List<InvoiceDetailResponse> response = dbResultToDto(dbResult);

        return response;
    }


    public void addPaymentToInvoice(Long invoiceId, Payment payment){
        Optional<Invoice> invoice = repository.findById(invoiceId);
        if (invoice.isEmpty()) {
            throw new IllegalArgumentException("Invoice not found " + invoiceId);
        }
        invoice.get().getPayments().add(payment);
        repository.save(invoice.get());
    }

    @Override
    @Transactional
    public List<InvoiceDetailResponse> updateInvoiceDetail(Long invoiceNumber, Long invoiceId) {
        log.debug("Into updateInvoiceDetail");

        Optional<Invoice> currentInvoice = repository.findById(invoiceId);
        currentInvoice.ifPresent(invoice -> invoice.setInvoiceNumber(invoiceNumber));

        List<Invoice> dbResult = repository.findByinvoiceNumber(invoiceNumber);
        List<InvoiceDetailResponse> response = dbResultToDto(dbResult);

        return response;
    }

    @Override
    public boolean deleteInvoiceDetail(Invoice invoice) {
        log.debug("Into deleteInvoiceDetail for [{} - {}]",invoice.getInvoiceNumber(),invoice.getId());

        repository.delete(new Invoice(invoice));

        return true;
    }

    @Override
    public boolean deleteInvoiceDetailByInvoiceNumber(Invoice invoice) {
        log.debug("Into deleteInvoiceDetail for [{} - {}]",invoice.getInvoiceNumber(),invoice.getId());

        List<Invoice> dbResult = repository.findByinvoiceNumber(invoice.getInvoiceNumber());

        try {
            for(Invoice invToDelete:dbResult){
                repository.deleteById(invToDelete.getId());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return true;
    }
}
