package com.parkee.library_management.controller;

import com.parkee.library_management.model.entity.Book;
import com.parkee.library_management.model.entity.Borrower;
import com.parkee.library_management.model.request.NewBorrowerRq;
import com.parkee.library_management.service.BookService;
import com.parkee.library_management.service.BorrowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Afif Rana M
 * on 23/02/2025
 */
@RestController
@RequestMapping("/api/v1")
public class BorrowerController {
    private final BorrowerService borrowerService;

    public BorrowerController(@Autowired BorrowerService borrowerService) {
        this.borrowerService = borrowerService;
    }

    @GetMapping("/borrowers")
    public ResponseEntity<List<Borrower>> getAllBorrowers() {
        return ResponseEntity.ok(borrowerService.getAllBorrower());
    }

    @PostMapping("/borrowers")
    public ResponseEntity<Borrower> addBorrower(@RequestBody NewBorrowerRq newBorrowerRq) {
        return ResponseEntity.ok(borrowerService.addBorrower(newBorrowerRq));
    }
}
