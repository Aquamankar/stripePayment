package com.enstripe.service;

import com.enstripe.entiy.Payment;
import com.enstripe.exceptin.StripeException;
import com.enstripe.payload.PaymentRequest;
import com.enstripe.repository.PaymentRepository;
import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:application.properties")
public class PaymentService {

    @Value("${stripe.api.key}")
    private String stripeApiKey;
    private PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    //private String stripeApiKey;
    public Payment createPayment(PaymentRequest paymentRequest) throws StripeException, com.stripe.exception.StripeException {

        Stripe.apiKey = stripeApiKey;
        // Create a payment intent using the Stripe API
        PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
                .setAmount(paymentRequest.getAmount())
                .setCurrency(paymentRequest.getCurrency())
                .setDescription(paymentRequest.getDescription())
                .build();

        PaymentIntent paymentIntent = PaymentIntent.create(createParams);

        // Save payment details to the database
        Payment payment = new Payment();
        payment.setAmount(paymentRequest.getAmount());
        payment.setCurrency(paymentRequest.getCurrency());
        payment.setDescription(paymentRequest.getDescription());
        // Set other payment details as needed

        payment = paymentRepository.save(payment);

        return payment;
    }


}

