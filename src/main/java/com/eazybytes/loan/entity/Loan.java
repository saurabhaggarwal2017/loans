package com.eazybytes.loan.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.processing.Generated;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Loan extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long loanId;
    private String customerName;
    private String mobileNumber;
    private String loanType;
    private int loanAmount;
    private int loanAmountPaid;
    private int outstandingLoanAmount;
}
