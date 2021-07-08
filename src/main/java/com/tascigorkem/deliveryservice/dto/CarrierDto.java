package com.tascigorkem.deliveryservice.dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarrierDto {
    private int carrierId;
    private String carrierName;
    private int deliveryDays;
}
