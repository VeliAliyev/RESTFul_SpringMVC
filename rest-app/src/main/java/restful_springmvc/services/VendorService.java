package restful_springmvc.services;

import restful_springmvc.api.v1.model.VendorDto;

import java.util.List;

public interface VendorService {
    List<VendorDto> getAllVendors();
    VendorDto createVendor(VendorDto vendorDto);
    VendorDto updateVendor(Long id,VendorDto vendorDto);
    VendorDto patchVendor(Long id,VendorDto vendorDto);
    VendorDto findVendorById(Long id);
    void deleteVendorById(Long id);
}
