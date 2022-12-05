package com.velialiyev.restful_springmvc.api.v1.mapper;

import com.velialiyev.restful_springmvc.api.v1.model.VendorDto;
import com.velialiyev.restful_springmvc.domain.Vendor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VendorMapperTest {


    @Test
    void vendorToVendorDto() {
        Vendor vendor = Vendor.builder().id(1L).name("Veli").vendor_url("vendor_url").build();

        VendorDto vendorDto = VendorMapper.INSTANCE.vendorToVendorDto(vendor);

        assertEquals(1L, vendorDto.getId());
        assertEquals("Veli", vendorDto.getName());
        assertEquals("vendor_url", vendorDto.getVendor_url());
    }

    @Test
    void vendorDtoToVendor() {
        VendorDto vendorDto = VendorDto.builder().id(1L).name("Veli").vendor_url("vendor_url").build();

        Vendor vendor = VendorMapper.INSTANCE.vendorDtoToVendor(vendorDto);

        assertEquals(1L, vendor.getId());
        assertEquals("Veli", vendor.getName());
        assertEquals("vendor_url", vendor.getVendor_url());
    }
}