package com.tascigorkem.deliveryservice.service;

import com.tascigorkem.deliveryservice.dto.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeliveryServiceTest {

    private final CarrierService carrierService = Mockito.mock(CarrierService.class);
    private final WarehouseService warehouseService = Mockito.mock(WarehouseService.class);
    private final HolidayExternalClient holidayExternalClient = Mockito.mock(HolidayExternalClient.class);
    private final DeliveryService deliveryService = new DeliveryServiceImpl(carrierService, warehouseService, holidayExternalClient);

    @Test
    void getDeliveryDate_RetrieveDeliveryDate_ShouldReturnDeliveryDate() {
        // GIVEN:
        DeliveryRequestDto delivery1 = DeliveryRequestDto.builder()
                .orderId(1)
                .carrierId(1)
                .warehouseId(1)
                .orderDate(LocalDateTime
                        .of(2020, 3, 30, 10, 21, 38))
                .build();

        DeliveryRequestDto delivery2 = DeliveryRequestDto.builder()
                .orderId(2)
                .carrierId(1)
                .warehouseId(1)
                .orderDate(LocalDateTime
                        .of(2020, 3, 30, 11, 21, 38))
                .build();

        DeliveryRequestDto delivery3 = DeliveryRequestDto.builder()
                .orderId(3)
                .carrierId(1)
                .warehouseId(1)
                .orderDate(LocalDateTime
                        .of(2020, 4, 1, 11, 21, 38))
                .build();

        DeliveryRequestDto delivery4 = DeliveryRequestDto.builder()
                .orderId(4)
                .carrierId(1)
                .warehouseId(1)
                .orderDate(LocalDateTime
                        .of(2020, 1, 1, 11, 21, 38))
                .build();

        CarrierDto dummyCarrier = dummyCarrier();
        Mockito.when(carrierService.findByCarrierById(1)).thenReturn(dummyCarrier);

        WarehouseDto dummyWarehouse = dummyWarehouse();
        Mockito.when(warehouseService.findWarehouseById(1)).thenReturn(dummyWarehouse);

        List<HolidayDTO> holidays = new ArrayList<>();
        holidays.add(new HolidayDTO("New Year", LocalDate.of(2020, 1, 1)));
        Mockito.when(holidayExternalClient.getHolidays("2020")).thenReturn(holidays);

        // WHEN:
        DeliveryResponseDto response1 = deliveryService.getDeliveryDate(delivery1);
        DeliveryResponseDto response2 = deliveryService.getDeliveryDate(delivery2);
        DeliveryResponseDto response3 = deliveryService.getDeliveryDate(delivery3);
        DeliveryResponseDto response4 = deliveryService.getDeliveryDate(delivery4);

        // THEN:
        assertEquals(LocalDate.of(2020, 4, 1).toString(),
                response1.getDeliveryDate().toString());
        assertEquals(LocalDate.of(2020, 4, 2).toString(),
                response2.getDeliveryDate().toString());
        assertEquals(LocalDate.of(2020, 4, 4).toString(),
                response3.getDeliveryDate().toString());
        assertEquals(LocalDate.of(2020, 1, 4).toString(),
                response4.getDeliveryDate().toString());

        Mockito.verify(carrierService, Mockito.times(4)).findByCarrierById(1);
        Mockito.verify(warehouseService, Mockito.times(4)).findWarehouseById(1);
        Mockito.verify(holidayExternalClient, Mockito.times(4)).getHolidays("2020");
    }

    private CarrierDto dummyCarrier() {
        return CarrierDto.builder()
                .carrierId(1)
                .carrierName("DHL")
                .deliveryDays(2)
                .build();
    }

    private WarehouseDto dummyWarehouse() {
        return WarehouseDto.builder()
                .warehouseId(1)
                .cutOffTime(12)
                .build();
    }
}
