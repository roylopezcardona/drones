package com.drones.appdrones.controllers;

import com.drones.appdrones.controllers.exceptions.DronesExceptionHandler;
import com.drones.appdrones.domain.dtos.DroneDTO;
import com.drones.appdrones.services.DroneService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ExtendWith(MockitoExtension.class)
public class DroneControllerTest {

    private static final String BASE_URL = "/drones";
    private static MockMvc mockMvc;

    @Mock
    private DroneService droneService;

    @InjectMocks
    private DroneController droneController;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(droneController)
                .setControllerAdvice(new DronesExceptionHandler())
                .build();
    }

    @Test
    public void create_success() throws Exception {
        when(droneService.create(any())).thenReturn(getDrone());

        final var payload = "{\"serialNumber\": \"My test number\"}";

        final String expectedJsonResponse = "{\"serialNumber\":\"test_number\",\"id\":1}";

        mockMvc.perform(
                        MockMvcRequestBuilders.post(BASE_URL)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(payload))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json(expectedJsonResponse))
                .andReturn();
    }

    private DroneDTO getDrone() {
        return DroneDTO.builder().id(1L).serialNumber("test_number").build();
    }

}
