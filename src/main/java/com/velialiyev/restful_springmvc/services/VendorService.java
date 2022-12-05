package com.velialiyev.restful_springmvc.services;

import com.velialiyev.restful_springmvc.api.v1.model.VendorDto;

import java.util.List;

public interface VendorService {
    List<VendorDto> getAllVendors();
    VendorDto createVendor(VendorDto vendorDto);
    VendorDto findVendorById(Long id);
    void deleteVendorById(Long id);
}
