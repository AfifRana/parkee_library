package com.parkee.library_management.service;

import com.parkee.library_management.model.entity.Book;
import com.parkee.library_management.model.entity.Borrower;
import com.parkee.library_management.model.request.NewBorrowerRq;
import com.parkee.library_management.repository.BorrowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Afif Rana M
 * on 23/02/2025
 */
@Service
public class BorrowerService {
    private final BorrowerRepository borrowerRepository;

    public BorrowerService(@Autowired BorrowerRepository borrowerRepository) {
        this.borrowerRepository = borrowerRepository;
    }

    public List<Borrower> getAllBorrower() {
        return borrowerRepository.findAll();
    }

    public Borrower getBorrowerById(Long id) {
        return borrowerRepository.findById(id).orElse(null);
    }

    public Borrower getBorrowerByName(String name) {
        return borrowerRepository.findByName(name).orElse(null);
    }

    public Borrower addBorrower(NewBorrowerRq newBorrowerRq) {
        Borrower borrower = new Borrower();
        borrower.setName(newBorrowerRq.getName());
        borrower.setKtpNumber(newBorrowerRq.getKtpNumber());
        borrower.setEmail(newBorrowerRq.getEmail());
        return borrowerRepository.save(borrower);
    }
}
