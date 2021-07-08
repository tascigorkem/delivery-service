package com.tascigorkem.deliveryservice.service;

import com.tascigorkem.deliveryservice.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DeliveryServiceImpl implements DeliveryService {

    private final CarrierService carrierService;
    private final WarehouseService warehouseService;
    private final HolidayExternalClient holidayExternalClient;

    @Override
    public DeliveryResponseDto getDeliveryDate(DeliveryRequestDto deliveryRequestDto) {

        CarrierDto carrier = carrierService.findByCarrierById(deliveryRequestDto.getCarrierId());
        WarehouseDto warehouse = warehouseService.findWarehouseById(deliveryRequestDto.getWarehouseId());

        // UTC to CET
        ZoneId zoneId = ZoneId.of("CET"); // ZoneId.of("Europe/Berlin")
        ZonedDateTime zonedOrderDate = deliveryRequestDto.getOrderDate().atZone(zoneId);
        LocalDateTime orderLocalDateTime = zonedOrderDate.toLocalDateTime()
                .plusSeconds(zonedOrderDate.getOffset().getTotalSeconds());

        // if order date is after the cutoff time, skip the next day
        if (orderLocalDateTime.getHour() > warehouse.getCutOffTime()) {
            orderLocalDateTime = orderLocalDateTime.plusDays(1);
        }

        LocalDate orderLocalDate = orderLocalDateTime.toLocalDate();

        // if order date is a day of weekend, skip the monday
        if (DayOfWeek.SATURDAY.equals(orderLocalDate.getDayOfWeek())) {
            orderLocalDate = orderLocalDate.plusDays(2);
        } else if (DayOfWeek.SUNDAY.equals(orderLocalDate.getDayOfWeek())) {
            orderLocalDate = orderLocalDate.plusDays(1);
        }

        List<HolidayDTO> holidays = holidayExternalClient.getHolidays(String.valueOf(orderLocalDateTime.getYear()));
        // if order date is holiday, skip the next working day
        List<LocalDate> holidayDates = holidays.stream().map(HolidayDTO::getDate).collect(Collectors.toList());
        while (holidayDates.contains(orderLocalDate)) {
            orderLocalDate = orderLocalDate.plusDays(1);
        }

        LocalDate deliveryLocalDate = orderLocalDate.plusDays(carrier.getDeliveryDays());

        return DeliveryResponseDto.builder()
                .orderId(deliveryRequestDto.getOrderId())
                .deliveryDate(deliveryLocalDate)
                .build();
    }
}
