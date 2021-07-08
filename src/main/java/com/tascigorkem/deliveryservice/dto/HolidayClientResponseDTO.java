package com.tascigorkem.deliveryservice.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HolidayClientResponseDTO {
    private List<HolidayDTO> holidays;
}
