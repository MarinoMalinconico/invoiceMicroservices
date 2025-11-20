package com.companyName.invoiceMicroservices.rest.invoice.delegate.impl;

import com.companyName.coreMicroservices.repository.InvoiceRepository;
import com.companyName.coreMicroservices.repository.entity.Invoice;
import com.companyName.invoiceMicroservices.rest.invoice.delegate.InvoiceDetailDelegate;
import com.companyName.invoiceMicroservices.rest.invoice.model.response.InvoiceDetailResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<InvoiceDetailResponse> addInvoiceDetail(Invoice invoice) {
        log.debug("Into addInvoiceDetail");

        repository.save(new Invoice(invoice));

        List<Invoice> dbResult = repository.findByinvoiceNumber(invoice.getInvoiceNumber());
        List<InvoiceDetailResponse> response = dbResultToDto(dbResult); 

        return response;
    }

    @Override
    public List<InvoiceDetailResponse> updateInvoiceDetail(Invoice invoice) {
        log.debug("Into updateInvoiceDetail");

        repository.save(new Invoice(invoice));

        List<Invoice> dbResult = repository.findByinvoiceNumber(invoice.getInvoiceNumber());
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
    public boolean deleteInvoiceDetailByCf(Invoice invoice) {
        log.debug("Into deleteInvoiceDetail for [{} - {}]",invoice.getInvoiceNumber(),invoice.getId());

        repository.deleteinvoiceByinvoiceNumber(invoice.getInvoiceNumber());

        return true;
    }
}
