package com.eazybytes.loan.repository;

import com.eazybytes.loan.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    Optional<Loan> findByMobileNumberAndLoanId(String mobileNumber, long loanId);

    Optional<List<Loan>> findByMobileNumber(String mobileNumber);
}
