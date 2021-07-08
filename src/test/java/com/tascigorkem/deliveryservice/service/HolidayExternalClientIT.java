package com.tascigorkem.deliveryservice.service;

import com.tascigorkem.deliveryservice.dto.HolidayDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class HolidayExternalClientIT {

    private final HolidayExternalClient holidayExternalClient;

    @Autowired
    public HolidayExternalClientIT(HolidayExternalClient holidayExternalClient) {
        this.holidayExternalClient = holidayExternalClient;
    }

    @Test
    void getHolidays() {
        //GIVEN:
        LocalDateTime localDateTime = LocalDateTime
                .of(2020, 3, 30, 10, 21, 38);
        //WHEN:
        List<HolidayDTO> holidays = holidayExternalClient.getHolidays(String.valueOf(localDateTime.getYear()));
        //THEN:
        Assertions.assertEquals(23, holidays.size());
    }
}
