package com.parkee.library_management.controller;

import com.parkee.library_management.model.entity.Borrower;
import com.parkee.library_management.model.request.NewBorrowerRq;
import com.parkee.library_management.model.response.GeneralRs;
import com.parkee.library_management.service.BorrowerService;
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
public class BorrowerController {
    private final BorrowerService borrowerService;

    public BorrowerController(@Autowired BorrowerService borrowerService) {
        this.borrowerService = borrowerService;
    }

    @GetMapping("/borrowers")
    public ResponseEntity<GeneralRs> getBorrowers(@RequestParam(required = false) String borrowerName) {
        GeneralRs generalRs = new GeneralRs();
        try {
            List<Borrower> borrowers = borrowerService.getBorrowers(borrowerName);

            generalRs.setStatusCode(HttpStatus.OK.value());
            generalRs.setMessage("Successfully get the borrowers");
            generalRs.setData(borrowers);

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

    @GetMapping("/borrowers/{borrowerId}")
    public ResponseEntity<GeneralRs> getBorrowerById(@PathVariable Long borrowerId) {
        GeneralRs generalRs = new GeneralRs();
        try {
            Borrower borrower = borrowerService.getBorrowerById(borrowerId);

            generalRs.setStatusCode(HttpStatus.OK.value());
            generalRs.setMessage("Successfully get the borrowers by ID : " + borrower.getId());
            generalRs.setData(borrower);

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

    @GetMapping("/borrowers/ktp-number/{ktpNumber}")
    public ResponseEntity<GeneralRs> getBorrowerByKtpNumber(@PathVariable String ktpNumber) {
        GeneralRs generalRs = new GeneralRs();
        try {
            Borrower borrower = borrowerService.getBorrowerByKtpNumber(ktpNumber);

            generalRs.setStatusCode(HttpStatus.OK.value());
            generalRs.setMessage("Successfully get the borrowers by KTP No : " + borrower.getKtpNumber());
            generalRs.setData(borrower);

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

    @PostMapping("/borrowers")
    public ResponseEntity<GeneralRs> addBorrower(@RequestBody NewBorrowerRq newBorrowerRq) {
        GeneralRs generalRs = new GeneralRs();
        try {
            Borrower borrower = borrowerService.addBorrower(newBorrowerRq);

            generalRs.setStatusCode(HttpStatus.CREATED.value());
            generalRs.setMessage("Borrower with ID " + borrower + " has been added");
            generalRs.setData(borrower);

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

    @PutMapping("/borrowers")
    public ResponseEntity<GeneralRs> updateBorrower(@RequestBody Borrower borrowerRq) {
        GeneralRs generalRs = new GeneralRs();
        try {
            Borrower borrower = borrowerService.updateBorrower(borrowerRq);

            generalRs.setStatusCode(HttpStatus.CREATED.value());
            generalRs.setMessage("Borrower with ID " + borrower + " has been added");
            generalRs.setData(borrower);

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
