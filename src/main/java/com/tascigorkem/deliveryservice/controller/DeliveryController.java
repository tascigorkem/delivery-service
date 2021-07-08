package com.tascigorkem.deliveryservice.controller;

import com.tascigorkem.deliveryservice.dto.DeliveryRequestDto;
import com.tascigorkem.deliveryservice.dto.DeliveryResponseDto;
import com.tascigorkem.deliveryservice.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    private final DeliveryService deliveryService;

    @PostMapping("/date")
    public ResponseEntity<DeliveryResponseDto> getDeliveryDate(
            @Valid @RequestBody DeliveryRequestDto deliveryRequestDto) {

        return ResponseEntity.ok(
                deliveryService.getDeliveryDate(deliveryRequestDto)
        );
    }

    @PostMapping("/date-list")
    public ResponseEntity<List<DeliveryResponseDto>> getDeliveryDateList(
            @Valid @RequestBody List<DeliveryRequestDto> deliveryRequestDto) {
        List<DeliveryResponseDto> deliveryResponseDtoList = deliveryRequestDto.stream()
                .map(deliveryService::getDeliveryDate).collect(Collectors.toList());
        return ResponseEntity.ok(
                deliveryResponseDtoList
        );
    }
}
