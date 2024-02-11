package com.riosr.ecommerce.service;

import com.riosr.ecommerce.dto.PaymentInfoDto;
import com.riosr.ecommerce.dto.PurchaseDto;
import com.riosr.ecommerce.dto.PurchaseResponseDto;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

public interface CheckoutService {

    PurchaseResponseDto placeOrder(PurchaseDto purchaseDto);

    PaymentIntent createPaymentIntent(PaymentInfoDto paymentInfo) throws StripeException;
}
