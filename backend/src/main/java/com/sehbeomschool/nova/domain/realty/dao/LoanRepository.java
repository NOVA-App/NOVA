package com.sehbeomschool.nova.domain.realty.dao;

import com.sehbeomschool.nova.domain.realty.domain.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {

}
