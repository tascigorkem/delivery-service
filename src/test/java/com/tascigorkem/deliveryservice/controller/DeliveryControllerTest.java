package com.tascigorkem.deliveryservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tascigorkem.deliveryservice.dto.DeliveryRequestDto;
import com.tascigorkem.deliveryservice.dto.DeliveryResponseDto;
import com.tascigorkem.deliveryservice.service.DeliveryService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DeliveryController.class)
class DeliveryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DeliveryService deliveryService;

    @Test
    void getDeliveryDate_RetrieveDeliveryDate_ShouldReturnDeliveryDate() throws Exception {
        // GIVEN:
        DeliveryRequestDto deliveryRequestDto = DeliveryRequestDto.builder()
                .orderId(1)
                .carrierId(1)
                .warehouseId(1)
                .orderDate(LocalDateTime
                        .of(2020, 3, 30, 10, 21, 38))
                .build();

        DeliveryResponseDto expectedResponse1 = DeliveryResponseDto.builder()
                .orderId(deliveryRequestDto.getOrderId())
                .deliveryDate(LocalDate.of(2020, 4, 1))
                .build();

        Mockito.when(deliveryService.getDeliveryDate(deliveryRequestDto))
                .thenReturn(expectedResponse1);

        // WHEN:
        this.mockMvc.perform(MockMvcRequestBuilders.post("/delivery/date")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(deliveryRequestDto)))
                .andDo(print())

                // THEN:
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(mvcResult -> {
                    assertNotNull(mvcResult.getResponse().getContentAsString());

                    DeliveryResponseDto responseDeliveryResponseDto = objectMapper
                            .readValue(mvcResult.getResponse().getContentAsString(), DeliveryResponseDto.class);

                    assertEquals(expectedResponse1, responseDeliveryResponseDto);
                });

        Mockito.verify(deliveryService).getDeliveryDate(deliveryRequestDto);
    }
}
