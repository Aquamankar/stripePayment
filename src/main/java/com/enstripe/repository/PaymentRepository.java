package com.enstripe.repository;

import com.enstripe.entiy.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

// PaymentRepository.java
public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
