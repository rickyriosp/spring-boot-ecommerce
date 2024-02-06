package com.riosr.ecommerce.controller;

import com.riosr.ecommerce.dto.PurchaseDto;
import com.riosr.ecommerce.dto.PurchaseResponseDto;
import com.riosr.ecommerce.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/checkout")
public class CheckoutController {

    private CheckoutService checkoutService;

    @Autowired
    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping(path = "/purchase")
    public PurchaseResponseDto placeOrder(@RequestBody PurchaseDto purchase) {

        PurchaseResponseDto purchaseResponse = this.checkoutService.placeOrder(purchase);

        return purchaseResponse;
    }
}
