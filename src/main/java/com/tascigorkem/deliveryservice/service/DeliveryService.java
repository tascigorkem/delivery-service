package com.tascigorkem.deliveryservice.service;

import com.tascigorkem.deliveryservice.dto.DeliveryRequestDto;
import com.tascigorkem.deliveryservice.dto.DeliveryResponseDto;

public interface DeliveryService {
    DeliveryResponseDto getDeliveryDate(DeliveryRequestDto deliveryRequestDto);
}
