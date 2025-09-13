package com.luq.picpay_backend_challenge.repositories;

import com.luq.picpay_backend_challenge.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {}
