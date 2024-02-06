package com.riosr.ecommerce.service;

import com.riosr.ecommerce.dto.PurchaseDto;
import com.riosr.ecommerce.dto.PurchaseResponseDto;

public interface CheckoutService {

    PurchaseResponseDto placeOrder(PurchaseDto purchaseDto);
}
