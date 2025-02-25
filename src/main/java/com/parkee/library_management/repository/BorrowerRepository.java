package com.parkee.library_management.repository;

import com.parkee.library_management.model.entity.Book;
import com.parkee.library_management.model.entity.Borrower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Afif Rana M
 * on 23/02/2025
 */
@Repository
public interface BorrowerRepository extends JpaRepository<Borrower, Long> {
    List<Borrower> findByNameContaining(String name);
    Optional<Borrower> findByKtpNumber(String ktpNumber);
}

