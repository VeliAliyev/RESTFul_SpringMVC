package com.velialiyev.restful_springmvc.api.v1.controllers;

import com.velialiyev.restful_springmvc.api.v1.model.VendorDto;
import com.velialiyev.restful_springmvc.api.v1.model.VendorListDto;
import com.velialiyev.restful_springmvc.services.VendorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class VendorControllerTest {

    @Mock
    VendorService vendorService;

    @InjectMocks
    VendorController vendorController;
    MockMvc mockMvc;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(vendorController).build();
    }

    @Test
    void getAllVendors() throws Exception {

        List<VendorDto> vendorDtos = Arrays.asList(
                VendorDto.builder().id(1L).build(),
                VendorDto.builder().id(2L).build()
        );

        when(vendorService.getAllVendors()).thenReturn(vendorDtos);

        mockMvc.perform(get("/api/v1/vendors")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendors", hasSize(2)));
    }
}