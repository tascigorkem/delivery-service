package com.tascigorkem.deliveryservice.service;

import com.tascigorkem.deliveryservice.dto.WarehouseDto;
import org.springframework.stereotype.Service;

@Service
public class WarehouseServiceImpl implements WarehouseService {
    @Override
    public WarehouseDto findWarehouseById(int warehouseId) {
        return dummyWarehouse();
    }

    private WarehouseDto dummyWarehouse() {
        return WarehouseDto.builder()
                .warehouseId(1)
                .cutOffTime(12)
                .build();
    }
}
