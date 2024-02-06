package com.riosr.ecommerce.dto;

import lombok.Data;

@Data
public class PurchaseResponseDto {

    // Lombok @Data will generate constructor for final fields only
    private final String orderTrackingNumber;
}
