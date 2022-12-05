package com.velialiyev.restful_springmvc.api.v1.mapper;

import com.velialiyev.restful_springmvc.api.v1.model.VendorDto;
import com.velialiyev.restful_springmvc.domain.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VendorMapper {
    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);
    VendorDto vendorToVendorDto(Vendor vendor);
    Vendor vendorDtoToVendor(VendorDto vendorDto);
}
