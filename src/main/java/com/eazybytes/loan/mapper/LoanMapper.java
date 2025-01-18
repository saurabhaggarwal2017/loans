package com.eazybytes.loan.mapper;

import com.eazybytes.loan.dto.LoanDto;
import com.eazybytes.loan.dto.LoanResponseDto;
import com.eazybytes.loan.entity.Loan;

public class LoanMapper {
    public static LoanResponseDto mapToLoanResponseDto(Loan loan, LoanResponseDto loanResponseDto) {
        loanResponseDto.setLoanId(loan.getLoanId());
        loanResponseDto.setCustomerName(loan.getCustomerName());
        loanResponseDto.setMobileNumber(loan.getMobileNumber());
        loanResponseDto.setLoanType(loan.getLoanType());
        loanResponseDto.setLoanAmount(loan.getLoanAmount());
        loanResponseDto.setLoanAmountPaid(loan.getLoanAmountPaid());
        loanResponseDto.setOutstandingLoanAmount(loan.getOutstandingLoanAmount());
        return loanResponseDto;
    }
}
