package com.parkee.library_management.controller;

import com.parkee.library_management.exception.BusinessException;
import com.parkee.library_management.model.entity.Borrowing;
import com.parkee.library_management.model.request.NewBorrowingRq;
import com.parkee.library_management.model.response.GeneralRs;
import com.parkee.library_management.service.BorrowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<GeneralRs> getBorrowings(@RequestParam(required = false) Long borrowerId, @RequestParam(required = false) Long bookId) {
        GeneralRs generalRs = new GeneralRs();
        try {
            List<Borrowing> borrowings = borrowingService.getBorrowings(borrowerId, bookId);

            generalRs.setStatusCode(HttpStatus.OK.value());
            generalRs.setMessage("Successfully get the borrowings");
            generalRs.setData(borrowings);

            return ResponseEntity.
                    status(generalRs.getStatusCode()).
                    body(generalRs);
        } catch (Exception e) {
            generalRs.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            generalRs.setMessage(e.getMessage());
            generalRs.setData(null);

            return ResponseEntity.
                    status(generalRs.getStatusCode()).
                    body(generalRs);
        }
    }

    @GetMapping("/borrowings/{borrowingId}")
    public ResponseEntity<GeneralRs> getBorrowingByBorrowingId(@PathVariable Long borrowingId) {
        GeneralRs generalRs = new GeneralRs();
        try {
            Borrowing borrowing = borrowingService.getBorrowingByBorrowingId(borrowingId);

            generalRs.setStatusCode(HttpStatus.OK.value());
            generalRs.setMessage("Successfully get the borrowings ID : " + borrowing.getId());
            generalRs.setData(borrowing);

            return ResponseEntity.
                    status(generalRs.getStatusCode()).
                    body(generalRs);
        } catch (Exception e) {
            generalRs.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            generalRs.setMessage(e.getMessage());
            generalRs.setData(null);

            return ResponseEntity.
                    status(generalRs.getStatusCode()).
                    body(generalRs);
        }
    }

    @PostMapping("/borrowings")
    public ResponseEntity<GeneralRs> addBorrowing(@RequestBody NewBorrowingRq newBorrowingRq) {
        GeneralRs generalRs = new GeneralRs();
        try {
            Borrowing borrowing = borrowingService.addBorrowing(newBorrowingRq);

            generalRs.setStatusCode(HttpStatus.CREATED.value());
            generalRs.setMessage("Borrowing with ID " + borrowing.getId() + " has been created");
            generalRs.setData(borrowing);

            return ResponseEntity.
                    status(generalRs.getStatusCode()).
                    body(generalRs);
        } catch (BusinessException e) {
            generalRs.setStatusCode(HttpStatus.OK.value());
            generalRs.setMessage(e.getMessage());
            generalRs.setData(null);

            return ResponseEntity.
                    status(generalRs.getStatusCode()).
                    body(generalRs);
        } catch (Exception e) {
            generalRs.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            generalRs.setMessage(e.getMessage());
            generalRs.setData(null);

            return ResponseEntity.
                    status(generalRs.getStatusCode()).
                    body(generalRs);
        }
    }

    @PutMapping("/borrowings/finish/{borrowingId}")
    public ResponseEntity<GeneralRs> finishBorrowing(@PathVariable Long borrowingId) {
        GeneralRs generalRs = new GeneralRs();
        try {
            Borrowing borrowing = borrowingService.finishBorrowing(borrowingId);

            generalRs.setStatusCode(HttpStatus.OK.value());
            generalRs.setMessage("Borrowing with ID " + borrowingId + " has been finished");
            generalRs.setData(borrowing);

            return ResponseEntity.
                    status(generalRs.getStatusCode()).
                    body(generalRs);
        } catch (Exception e) {
            generalRs.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            generalRs.setMessage(e.getMessage());
            generalRs.setData(null);

            return ResponseEntity.
                    status(generalRs.getStatusCode()).
                    body(generalRs);
        }
    }
}