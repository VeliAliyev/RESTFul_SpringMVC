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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

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

    @Test
    void createVendor(){
        //given
        VendorDto newVendorDto = VendorDto.builder().id(1L).name("Veli").build();
        Vendor newVendor = Vendor.builder().id(1L).name("Veli").build();
        when(vendorRepository.save(any(Vendor.class))).thenReturn(newVendor);

        //when
        VendorDto savedVendor = vendorService.createVendor(newVendorDto);

        //then
        assertEquals(newVendorDto.getName(), savedVendor.getName());
        assertEquals(newVendorDto.getId(), savedVendor.getId());

    }

    @Test
    void updateVendor(){
        //given
        VendorDto newVendorDto = VendorDto.builder().id(1L).name("Ayşe").build();
        Vendor oldVendor = Vendor.builder().id(1L).name("Veli").build();
        Vendor newVendor = Vendor.builder().id(1L).name("Ayşe").build();
        when(vendorRepository.findById(anyLong())).thenReturn(Optional.ofNullable(oldVendor));
        when(vendorRepository.save(any(Vendor.class))).thenReturn(newVendor);

        //when
        VendorDto savedVendor = vendorService.updateVendor(anyLong(), newVendorDto);

        //then
        assertNotNull(savedVendor);
        assertEquals("Ayşe", savedVendor.getName());
        assertNotEquals("Veli", savedVendor.getName());
    }


    @Test
    void getVendorById(){
        //given
        Vendor vendor = Vendor.builder().id(1L).name("Veli").build();
        when(vendorRepository.findById(anyLong())).thenReturn(Optional.ofNullable(vendor));

        //when
        VendorDto vendorDto = vendorService.findVendorById(anyLong());

        //then
        assertEquals(1L, vendorDto.getId());
        assertEquals("Veli", vendorDto.getName());
    }
}