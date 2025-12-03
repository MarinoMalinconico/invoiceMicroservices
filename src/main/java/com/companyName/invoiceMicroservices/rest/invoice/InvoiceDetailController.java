package com.companyName.invoiceMicroservices.rest.invoice;

import com.companyName.coreMicroservices.repository.entity.Invoice;
import com.companyName.coreMicroservices.repository.entity.Payment;
import com.companyName.invoiceMicroservices.common.model.BasicResponse;
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

    @RequestMapping(value = "/invoiceDetailBasicResponse",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<BasicResponse<List<InvoiceDetailResponse>>> invoiceDetailBasicResponseByInvoiceNumber(@RequestBody InvoiceDetailRequest invoice) throws InvalidParameterException, InvoiceDetailException {

        log.info("Entering in invoiceDetail service - PathVariable: [{}]", invoice.getInvoiceNumber());

        List<InvoiceDetailResponse> delegateResult =  null;
        BasicResponse<List<InvoiceDetailResponse>> response = new BasicResponse<>();
        try {
            delegateResult= delegate.getInvoiceDetail(invoice.getInvoiceNumber());
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

    //con path parameter
    @RequestMapping(value = "/invoiceDetailBasicResponseParam",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<BasicResponse<List<InvoiceDetailResponse>>> invoiceDetailBasicResponseParam(@RequestParam Long invoiceNumber) throws InvalidParameterException {

        log.info("Entering in invoiceDetail service(param) - PathVariable: [{}]", invoiceNumber);

        List<InvoiceDetailResponse> delegateResult =  null;
        BasicResponse<List<InvoiceDetailResponse>> response = new BasicResponse<>();
        try {
            delegateResult= delegate.getInvoiceDetail(invoiceNumber);
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

    //get con read e jpa
    @RequestMapping(value = "/invoiceDetailBasicResponseParam",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<BasicResponse<List<InvoiceDetailResponse>>> invoiceDetailBasicResponseGET(@RequestParam Long invoiceNumber) throws InvalidParameterException {

        log.info("Entering in invoiceDetail service(param)(JPA) - PathVariable: [{}]", invoiceNumber);

        List<InvoiceDetailResponse> delegateResult =  null;
        BasicResponse<List<InvoiceDetailResponse>> response = new BasicResponse<>();
        try {
            delegateResult= delegate.getInvoiceDetailJPA(invoiceNumber);
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

    //get all jpa
    @RequestMapping(value = "/invoiceDetailBasicResponseParamAll",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<BasicResponse<List<InvoiceDetailResponse>>> invoiceDetailBasicResponseGetAll() throws InvalidParameterException {

        log.info("Entering in invoiceDetail service(param)(JPA)(all)");

        List<InvoiceDetailResponse> delegateResult =  null;
        BasicResponse<List<InvoiceDetailResponse>> response = new BasicResponse<>();
        try {
            delegateResult= delegate.getAllJPA();
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


    @RequestMapping(value = "/UpdateInvoice",
    method = RequestMethod.PUT,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<BasicResponse<List<InvoiceDetailResponse>>> updateInvoiceNumber(@RequestParam Long invoiceNumber, Long invoiceId) throws InvalidParameterException {

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

    @RequestMapping(value = "/AddPayment",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<BasicResponse<List<InvoiceDetailResponse>>> AddPayment(@RequestBody Payment payment,@RequestParam Long invoiceId) throws InvalidParameterException {

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

    @RequestMapping(value = "/DeleteInvoice",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<BasicResponse<List<InvoiceDetailResponse>>> deleteInvoice(@RequestBody Invoice invoice) throws InvalidParameterException {

        log.info("Entering in invoice delete of [{}]",invoice.getInvoiceNumber());

        boolean deleted=false;

        List<InvoiceDetailResponse> delegateResult = null;
        BasicResponse<List<InvoiceDetailResponse>> response = new BasicResponse<>();
        try {
            delegateResult= delegate.getInvoiceDetailJPA(invoice.getInvoiceNumber());
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

    @RequestMapping(value = "/DeleteInvoiceByInvoiceNumber",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<BasicResponse<List<InvoiceDetailResponse>>> DeleteInvoiceByInvoiceNumber(@RequestBody Invoice invoice) throws InvalidParameterException {

        log.info("Entering in invoice delete of [{}]",invoice.getInvoiceNumber());

        boolean deleted=false;

        List<InvoiceDetailResponse> delegateResult = null;
        BasicResponse<List<InvoiceDetailResponse>> response = new BasicResponse<>();
        try {
            delegateResult= delegate.getInvoiceDetailJPA(invoice.getInvoiceNumber());
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