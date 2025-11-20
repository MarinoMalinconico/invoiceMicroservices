package com.companyName.invoiceMicroservices.rest.invoice.model.response;

import com.companyName.coreMicroservices.repository.entity.Payment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDetailResponse {

    @Getter
    @Setter
    private Long id;

    @Getter @Setter
    private Long invoiceNumber;

    @Getter @Setter
    private List<Payment> payments = new ArrayList<>();

}
