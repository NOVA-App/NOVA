package com.sehbeomschool.nova.domain.realty.domain;

import com.sehbeomschool.nova.global.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Loan extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "LOAN_ID")
    private Long id;

    private Long principal;

    @Builder
    public Loan(Long id, Long principal) {
        this.id = id;
        this.principal = principal;
    }

    public void repayment(Long principalAmount) {
        this.principal -= principalAmount;
    }
}
