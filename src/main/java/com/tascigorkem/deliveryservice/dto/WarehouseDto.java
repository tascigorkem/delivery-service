package com.tascigorkem.deliveryservice.dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WarehouseDto {
    private int warehouseId;
    private int cutOffTime;
}



