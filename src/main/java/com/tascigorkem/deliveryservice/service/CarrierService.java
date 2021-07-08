package com.tascigorkem.deliveryservice.service;

import com.tascigorkem.deliveryservice.dto.CarrierDto;

public interface CarrierService {
    CarrierDto findByCarrierById(int carrierId);
}
