package com.parkee.library_management.repository;

import com.parkee.library_management.model.entity.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Afif Rana M
 * on 23/02/2025
 */
@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {
    List<Borrowing> findByBorrowerId(Long borrowerId);
    List<Borrowing> findByBookId(Long bookId);
    List<Borrowing> findByBorrowerIdAndBookId(Long borrowerId, Long bookId);
    List<Borrowing> findByBorrowerName(String name);
    List<Borrowing> findByBorrowerIdAndStatus(Long id, Borrowing.Status status);
}

