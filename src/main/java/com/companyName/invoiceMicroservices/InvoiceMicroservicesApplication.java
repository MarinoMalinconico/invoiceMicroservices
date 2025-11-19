package com.companyName.invoiceMicroservices;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Slf4j
@AutoConfiguration
@EntityScan(basePackages = "com.companyName.coreMicroservices.repository.entity")
@EnableJpaRepositories(basePackages = "com.companyName.coreMicroservices.repository")
public class InvoiceMicroservicesApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(InvoiceMicroservicesApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        log.info("Metodo run");
        //YYYY-mm-dd 2025-09-30
        //invoiceRepository.save(new Invoice(null, LocalDate.parse("2024-09-22"),"pippo",   "RGNRDV87H13D761R", new BigDecimal("3000.01"), "EUR"));
        //invoiceRepository.save(new Invoice(null, LocalDate.parse("2024-09-22"),"pluto",   "RGNLSN87H13D761R", new BigDecimal("3000.02"), "JPY"));
        //invoiceRepository.save(new Invoice(null, LocalDate.parse("2024-09-22"),"paperino","FRNFBA85M08D761M", new BigDecimal("3000.03"), "USD"));
        //invoiceRepository.save(new Invoice(null, LocalDate.parse("2024-09-22"),"topolino","DSTLCU89R52D761R", new BigDecimal("3000.04"), "GBP"));
        //invoiceRepository.save(new Invoice(null, LocalDate.parse("2024-09-22"),"sora",    "DSTLCU89R52D761R", new BigDecimal("3000.05"), "RUB"));
    }
}
