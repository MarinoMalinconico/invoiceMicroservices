package com.companyName.invoiceMicroservices.rest.invoice.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDetailRequest {

    @Getter
    @Setter
    private String cf;

}
