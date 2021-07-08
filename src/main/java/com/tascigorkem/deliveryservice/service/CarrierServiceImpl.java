package com.tascigorkem.deliveryservice.service;

import com.tascigorkem.deliveryservice.dto.CarrierDto;
import org.springframework.stereotype.Service;

@Service
public class CarrierServiceImpl implements CarrierService {
    @Override
    public CarrierDto findByCarrierById(int carrierId) {
        return dummyCarrier();
    }

    private CarrierDto dummyCarrier() {
        return CarrierDto.builder()
                .carrierId(1)
                .carrierName("DHL")
                .deliveryDays(2)
                .build();
    }
}
