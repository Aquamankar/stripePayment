package com.enstripe.controller;

import com.enstripe.entiy.Payment;
import com.enstripe.exceptin.StripeException;
import com.enstripe.payload.ApiResponse;
import com.enstripe.payload.PaymentRequest;
import com.enstripe.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPayment(@RequestBody PaymentRequest paymentRequest) {
        try {
            Payment payment = paymentService.createPayment(paymentRequest);
            return ResponseEntity.ok(new ApiResponse("Payment created successfully", payment.getId()));
        } catch (StripeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Failed to create payment", e.getMessage()));
        } catch (com.stripe.exception.StripeException e) {
            throw new RuntimeException(e);
        }
    }


}
