package com.parkee.library_management.controller;

import com.parkee.library_management.model.entity.Borrowing;
import com.parkee.library_management.model.request.NewBorrowingRq;
import com.parkee.library_management.service.BorrowingService;
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
public class BorrowingController {
    private final BorrowingService borrowingService;

    public BorrowingController(@Autowired BorrowingService borrowingService) {
        this.borrowingService = borrowingService;
    }

    @GetMapping("/borrowings")
    public ResponseEntity<List<Borrowing>> getAllBorrowings(@RequestParam(required = false) Long borrowerId,
                                                            @RequestParam(required = false) Long bookId) {
        List<Borrowing> borrowings = borrowingService.getBorrowings(borrowerId, bookId);
        return ResponseEntity.ok(borrowings);
    }

    @GetMapping("/borrowings/{borrowingId}")
    public ResponseEntity<Borrowing> getBorrowingByBorrowingId(@PathVariable Long borrowingId) {
        Borrowing borrowing = borrowingService.getBorrowingByBorrowingId(borrowingId);
        return ResponseEntity.ok(borrowing);
    }

    @PostMapping("/borrowings")
    public ResponseEntity<Borrowing> addBorrowing(@RequestBody NewBorrowingRq newBorrowingRq) {
        return ResponseEntity.ok(borrowingService.addBorrowing(newBorrowingRq));
    }

    @PutMapping("/borrowings/finish/{borrowingId}")
    public ResponseEntity<Borrowing> updateBorrowing(@PathVariable Long borrowingId) {
        return ResponseEntity.ok(borrowingService.updateBorrowing(borrowingId));
    }
}