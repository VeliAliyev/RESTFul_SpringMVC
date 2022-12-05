package com.velialiyev.restful_springmvc.services;

import com.velialiyev.restful_springmvc.api.v1.mapper.VendorMapper;
import com.velialiyev.restful_springmvc.api.v1.model.VendorDto;
import com.velialiyev.restful_springmvc.domain.Vendor;
import com.velialiyev.restful_springmvc.repository.VendorRepository;
import com.velialiyev.restful_springmvc.services.impl.VendorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class VendorServiceTest {

    @Mock
    VendorRepository vendorRepository;

    VendorService vendorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        vendorService = new VendorServiceImpl(vendorRepository, VendorMapper.INSTANCE);
    }

    @Test
    void getAllVendors() {

        //given
        List<Vendor> vendors = Arrays.asList(Vendor.builder().id(1L).build(), Vendor.builder().id(2L).build());
        when(vendorRepository.findAll()).thenReturn(vendors);

        //when
        List<VendorDto> vendorDtos = vendorService.getAllVendors();

        //then
        assertEquals(1L, vendorDtos.get(0).getId());
        assertEquals(2L, vendorDtos.get(1).getId());
    }
}