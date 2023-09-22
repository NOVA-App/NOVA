package com.sehbeomschool.nova.domain.realty.dao;

import com.sehbeomschool.nova.domain.realty.domain.Realty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RealtyRepository extends JpaRepository<Realty, Long> {

}
