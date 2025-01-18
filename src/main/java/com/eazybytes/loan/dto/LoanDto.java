package com.eazybytes.loan.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class LoanDto {
    @Size(min = 3, max = 14, message = "Name length should be in between 3 to 14.")
    private String customerName;
    @Max(value = 100000, message = "Maximum loan amount should be 1,00,000 Rs.")
    @Min(value = 50000, message = "Minimum loan amount should be 50,000 Rs.")
    private int loanAmount;
    @NotEmpty(message = "Loan type must not be empty.")
    private String loanType;
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 digits")
    private String mobileNumber;
}
