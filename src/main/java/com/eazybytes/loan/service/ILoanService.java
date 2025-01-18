package com.eazybytes.loan.service;

import com.eazybytes.loan.dto.LoanDto;
import com.eazybytes.loan.dto.LoanResponseDto;

import java.util.List;

public interface ILoanService {
    void createLoan(LoanDto loanDto);

    LoanResponseDto getLoanDetails(String mobileNumber, long loanId);

    List<LoanResponseDto> getAllLoanDetails(String mobileName);

    void deleteLoan(String mobileNumber, long loanId);
}
