package com.companyName.invoiceMicroservices.rest.invoice;

import com.companyName.coreMicroservices.model.ExtendedResponse;
import com.companyName.coreMicroservices.repository.entity.Invoice;
import com.companyName.coreMicroservices.repository.entity.Payment;
import com.companyName.coreMicroservices.model.BasicResponse;
import com.companyName.invoiceMicroservices.rest.invoice.delegate.InvoiceDetailDelegate;
import com.companyName.invoiceMicroservices.rest.invoice.exceptions.InvoiceDetailException;
import com.companyName.invoiceMicroservices.rest.invoice.model.request.InvoiceDetailRequest;
import com.companyName.invoiceMicroservices.rest.invoice.model.response.InvoiceDetailResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidParameterException;
import java.util.List;

@Slf4j
@Controller
public class InvoiceDetailController {

    @Autowired
    InvoiceDetailDelegate delegate;

    @RequestMapping(value = "/invoiceDetailBasicResponseByInvoiceNumber",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<ExtendedResponse<List<InvoiceDetailResponse>>> invoiceDetailBasicResponseByInvoiceNumber(@RequestBody InvoiceDetailRequest invoice) throws InvalidParameterException, InvoiceDetailException {

        log.info("Entering in invoiceDetail service - PathVariable: [{}]", invoice.getInvoiceNumber());

        List<InvoiceDetailResponse> delegateResult =  null;
        ExtendedResponse<List<InvoiceDetailResponse>> response = new ExtendedResponse<>();
        try {
            delegateResult= delegate.getInvoiceByInvoiceNumber(invoice.getInvoiceNumber());
            if (!delegateResult.isEmpty() && delegateResult!=null){
                response.setData(delegateResult);
                //response.setTimestamp(fmt.format(new Date()));
            } else {
                //metti log "nessun dato trovato"
                throw new InvoiceDetailException("No data found for request param: "+invoice.getInvoiceNumber());
            }
            log.debug("result delegate.getInvoiceDetail(invoice) [{}]", response);
        } catch (InvalidParameterException  e){
            log.error("ERROR {} ", e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error("ERROR {} ", e.getMessage(), e);

        }

        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @RequestMapping(value = "/invoiceDetailBasicResponseByInvoiceNumberPathParam",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<BasicResponse<List<InvoiceDetailResponse>>> invoiceDetailBasicResponseByInvoiceNumberPathParam(@RequestParam Long invoiceNumber) throws InvalidParameterException {

        log.info("Entering in invoiceDetailBasicResponseByInvoiceNumberPathParam - PathVariable: [{}]", invoiceNumber);

        List<InvoiceDetailResponse> delegateResult =  null;
        BasicResponse<List<InvoiceDetailResponse>> response = new BasicResponse<>();
        try {
            delegateResult= delegate.getInvoiceByInvoiceNumber(invoiceNumber);
            if (!delegateResult.isEmpty() && delegateResult!=null){
                response.setData(delegateResult);
                //response.setTimestamp(fmt.format(new Date()));
            } else {
                //metti log "nessun dato trovato"
            }
            log.debug("result delegate.getInvoiceDetail(invoice) [{}]", response);
        } catch (InvalidParameterException  e){
            log.error("ERROR {} ", e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error("ERROR {} ", e.getMessage(), e);

        }

        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @RequestMapping(value = "/invoiceDetailBasicResponseGetAll",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<BasicResponse<List<InvoiceDetailResponse>>> invoiceDetailBasicResponseGetAll() throws InvalidParameterException {

        log.info("Entering in invoiceDetail service(param)(JPA)(all)");

        List<InvoiceDetailResponse> delegateResult =  null;
        BasicResponse<List<InvoiceDetailResponse>> response = new BasicResponse<>();
        try {
            delegateResult= delegate.getAllInvoiceList();
            if (!delegateResult.isEmpty() && delegateResult!=null){
                response.setData(delegateResult);
                //response.setTimestamp(fmt.format(new Date()));
            } else {
                //metti log "nessun dato trovato"
            }
            log.debug("result delegate.getInvoiceDetail(invoice) [{}]", response);
        } catch (InvalidParameterException  e){
            log.error("ERROR {} ", e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error("ERROR {} ", e.getMessage(), e);

        }

        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }


    @RequestMapping(value = "/updateInvoice",
    method = RequestMethod.PUT,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<BasicResponse<List<InvoiceDetailResponse>>> updateInvoice(@RequestParam Long invoiceNumber, Long invoiceId) throws InvalidParameterException {

        log.info("Entering in invoice update of [{}]",invoiceNumber);

        List<InvoiceDetailResponse> delegateResult = null;
        BasicResponse<List<InvoiceDetailResponse>> response = new BasicResponse<>();
        try {
            delegateResult = delegate.updateInvoiceDetail(invoiceNumber,invoiceId);
            if (!delegateResult.isEmpty() && delegateResult != null) {
                response.setData(delegateResult);
                //response.setTimestamp(fmt.format(new Date()));
            } else {
                //metti log "nessun dato trovato"
            }
            log.debug("result delegate.getInvoiceDetail(invoice) [{}]", response);
        } catch (InvalidParameterException e) {
            log.error("ERROR {}", e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error("ERROR {}",e.getMessage(), e);
        }

        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @RequestMapping(value = "/addPayment",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<BasicResponse<List<InvoiceDetailResponse>>> addPayment(@RequestBody Payment payment,@RequestParam Long invoiceId) throws InvalidParameterException {

        log.info("Entering in AddPayment [{}]",invoiceId);

        BasicResponse<List<InvoiceDetailResponse>> response = new BasicResponse<>();
        try {
            delegate.addPaymentToInvoice(invoiceId,payment);

            log.debug("response [{}]", response);
        } catch (InvalidParameterException e) {
            log.error("ERROR {}", e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error("ERROR {}",e.getMessage(), e);
        }

        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @RequestMapping(value = "/deleteInvoice",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<BasicResponse<List<InvoiceDetailResponse>>> deleteInvoice(@RequestBody Invoice invoice) throws InvalidParameterException {

        log.info("Entering in invoice delete of [{}]",invoice.getInvoiceNumber());

        boolean deleted=false;

        List<InvoiceDetailResponse> delegateResult = null;
        BasicResponse<List<InvoiceDetailResponse>> response = new BasicResponse<>();
        try {
            delegateResult= delegate.getInvoiceByInvoiceNumber(invoice.getInvoiceNumber());
            deleted=delegate.deleteInvoiceDetail(invoice);
            if (!delegateResult.isEmpty() && delegateResult != null) {
                response.setData(delegateResult);
                //response.setTimestamp(fmt.format(new Date()));
            } else {
                //metti log "nessun dato trovato"
            }
            log.debug("result delegate.getInvoiceDetail(invoice) [{}]", response);
        } catch (InvalidParameterException e) {
            log.error("ERROR {}", e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error("ERROR {}",e.getMessage(), e);
        }
        log.info(deleted ? "eseguita delete di {} - {}" : "errore nella delete di {}", invoice.getInvoiceNumber(),invoice.getId());

        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @RequestMapping(value = "/deleteInvoiceByInvoiceNumber",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<BasicResponse<List<InvoiceDetailResponse>>> deleteInvoiceByInvoiceNumber(@RequestBody Invoice invoice) throws InvalidParameterException {

        log.info("Entering in invoice delete of [{}]",invoice.getInvoiceNumber());

        boolean deleted=false;

        List<InvoiceDetailResponse> delegateResult = null;
        BasicResponse<List<InvoiceDetailResponse>> response = new BasicResponse<>();
        try {
            delegateResult= delegate.getInvoiceByInvoiceNumber(invoice.getInvoiceNumber());
            deleted=delegate.deleteInvoiceDetailByInvoiceNumber(invoice);
            if (!delegateResult.isEmpty() && delegateResult != null) {
                response.setData(delegateResult);
                //response.setTimestamp(fmt.format(new Date()));
            } else {
                //metti log "nessun dato trovato"
            }
            log.debug("result delegate.getInvoiceDetail(invoice) [{}]", response);
        } catch (InvalidParameterException e) {
            log.error("ERROR {}", e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error("ERROR {}",e.getMessage(), e);
        }
        log.info(deleted ? "eseguita delete di {} - {}" : "errore nella delete di {}", invoice.getInvoiceNumber(),invoice.getId());

        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
}