package com.companyName.invoiceMicroservices;

import com.companyName.coreMicroservices.repository.InvoiceRepository;
import com.companyName.coreMicroservices.repository.entity.Invoice;
import com.companyName.coreMicroservices.repository.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@Slf4j
@AutoConfiguration
@EntityScan(basePackages = "com.companyName.coreMicroservices.repository.entity")
@EnableJpaRepositories(basePackages = "com.companyName.coreMicroservices.repository")
public class InvoiceMicroservicesApplication implements CommandLineRunner {

    @Autowired
    InvoiceRepository invoiceRepository;

	public static void main(String[] args) {
		SpringApplication.run(InvoiceMicroservicesApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        log.info("Metodo run");

        //List<Payment> payments = new ArrayList<>();
        //List<Payment> payments1 = new ArrayList<>();
        //List<Payment> payments2 = new ArrayList<>();
        //List<Payment> payments3 = new ArrayList<>();
        //List<Payment> payments4 = new ArrayList<>();
        //                                                 //YYYY-mm-dd 2025-09-30
        //Payment payment  = new Payment(null, LocalDate.parse("2024-09-22"),"pippo",   "RGNRDV87H13D761R", new BigDecimal("3000.01"), "EUR");
        //Payment payment1 = new Payment(null, LocalDate.parse("2024-09-22"),"pluto",   "RGNLSN87H13D761R", new BigDecimal("3000.02"), "JPY");
        //Payment payment2 = new Payment(null, LocalDate.parse("2024-09-22"),"paperino","FRNFBA85M08D761M", new BigDecimal("3000.03"), "USD");
        //Payment payment3 = new Payment(null, LocalDate.parse("2024-09-22"),"topolino","DSTLCU89R52D761R", new BigDecimal("3000.04"), "GBP");
        //Payment payment4 = new Payment(null, LocalDate.parse("2024-09-22"),"sora",    "DSTLCU89R52D761R", new BigDecimal("3000.05"), "RUB");
        //
        //payments.add(payment);
        //payments1.add(payment1);
        //payments2.add(payment2);
        //payments3.add(payment3);
        //payments4.add(payment4);
        //                                                 //tutti hanno liste diverse di pagamenti, se si usa la stessa lista,dopo il primo inserimento va in errore
        //invoiceRepository.save(new Invoice(null,202409231L,payments));
        //invoiceRepository.save(new Invoice(null,202409232L,payments1));
        //invoiceRepository.save(new Invoice(null,202409233L,payments2));
        //invoiceRepository.save(new Invoice(null,202409234L,payments3));
        //invoiceRepository.save(new Invoice(null,202409235L,payments4));
        //
        //log.info("save eseguite");
    }
}
