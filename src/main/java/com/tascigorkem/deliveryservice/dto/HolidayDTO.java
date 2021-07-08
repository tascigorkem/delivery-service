package com.tascigorkem.deliveryservice.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HolidayDTO {
    private String name;
    private LocalDate date;
}
