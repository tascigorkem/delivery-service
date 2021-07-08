package com.tascigorkem.deliveryservice.service;

import com.tascigorkem.deliveryservice.dto.HolidayClientResponseDTO;
import com.tascigorkem.deliveryservice.dto.HolidayDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Service
public class HolidayExternalClient {

    @Value("${holiday-external-client-url}")
    private String holidayExternalClientUrl;

    @Autowired
    private RestTemplate restTemplate;

    public List<HolidayDTO> getHolidays(String year) {
        String url = holidayExternalClientUrl
                .replace("{countryCode}", "DE-BE")
                .replace("{year}", year);
        ResponseEntity<HolidayClientResponseDTO> responseEntity = restTemplate.getForEntity(
                url, HolidayClientResponseDTO.class);
        return Objects.requireNonNull(responseEntity.getBody()).getHolidays();
    }
}
