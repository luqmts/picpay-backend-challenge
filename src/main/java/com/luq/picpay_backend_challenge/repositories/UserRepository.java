package com.luq.picpay_backend_challenge.repositories;

import com.luq.picpay_backend_challenge.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByDocumentAndIdNot(String document, Integer id);
    boolean existsByMailAndIdNot(String mail, Integer id);
    boolean existsByDocument(String document);
    boolean existsByMail(String mail);
}
