package com.eazybytes.loan.service.impl;

import com.eazybytes.loan.dto.LoanDto;
import com.eazybytes.loan.dto.LoanResponseDto;
import com.eazybytes.loan.entity.Loan;
import com.eazybytes.loan.exception.ResourceNotFoundException;
import com.eazybytes.loan.mapper.LoanMapper;
import com.eazybytes.loan.repository.LoanRepository;
import com.eazybytes.loan.service.ILoanService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class LoanServiceImpl implements ILoanService {
    private final LoanRepository loanRepository;

    @Override
    public void createLoan(LoanDto loanDto) {
        Loan loan = new Loan();
        long loanId = 100000000000000L + new Random().nextLong(100000000000000L);
        loan.setLoanId(loanId);
        loan.setCustomerName(loanDto.getCustomerName());
        loan.setMobileNumber(loanDto.getMobileNumber());
        loan.setLoanType(loanDto.getLoanType());
        loan.setLoanAmount(loanDto.getLoanAmount());
        loan.setOutstandingLoanAmount(loan.getLoanAmount());
        loanRepository.save(loan);
    }

    @Override
    public LoanResponseDto getLoanDetails(String mobileNumber, long loanId) {
        Loan loan = loanRepository.findByMobileNumberAndLoanId(mobileNumber, loanId)
                .orElseThrow(() -> new ResourceNotFoundException("Loan", "mobileNumber, loanId", String.format("%s, %s", mobileNumber, loanId)));
        return LoanMapper.mapToLoanResponseDto(loan, new LoanResponseDto());
    }

    @Override
    public List<LoanResponseDto> getAllLoanDetails(String mobileNumber) {
        List<Loan> loans = loanRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber));
        return loans.stream()
                .map(loan -> LoanMapper.mapToLoanResponseDto(loan, new LoanResponseDto()))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteLoan(String mobileNumber, long loanId) {
        Loan loan = loanRepository.findByMobileNumberAndLoanId(mobileNumber, loanId)
                .orElseThrow(() -> new ResourceNotFoundException("Loan", "mobileNumber, loanId", String.format("%s, %s", mobileNumber, loanId)));
        loanRepository.delete(loan);
    }

}
