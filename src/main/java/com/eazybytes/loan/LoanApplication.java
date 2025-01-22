package com.eazybytes.loan;

import com.eazybytes.loan.dto.LoansContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(LoansContactInfoDto.class)
@OpenAPIDefinition(
        info = @Info(
                title = "Loan service",
                description = "Loan service for providing loans to customers.",
                contact = @Contact(
                        name = "Saurabh Kumar",
                        url = "https://github.com/saurabhaggarwal2017",
                        email = "saurabhaggarwal2017@gmail.com"
                ),
                version = "V1"
        ),
        externalDocs = @ExternalDocumentation(
                description = "for more information check out tutor github.",
                url = "https://github.com/eazybytes/microservices/tree/3.3.2/section2/loans"
        )
)
public class LoanApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoanApplication.class, args);
    }

}
