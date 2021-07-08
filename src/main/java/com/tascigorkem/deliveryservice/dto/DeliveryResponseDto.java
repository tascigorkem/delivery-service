package com.tascigorkem.deliveryservice.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryResponseDto {
    private int orderId;
    private LocalDate deliveryDate;
}
