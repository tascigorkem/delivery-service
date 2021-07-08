package com.tascigorkem.deliveryservice.service;

import com.tascigorkem.deliveryservice.dto.WarehouseDto;

public interface WarehouseService {
    WarehouseDto findWarehouseById(int warehouseId);
}
