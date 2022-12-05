package com.velialiyev.restful_springmvc.api.v1.controllers;

import com.velialiyev.restful_springmvc.api.v1.model.VendorDto;
import com.velialiyev.restful_springmvc.api.v1.model.VendorListDto;
import com.velialiyev.restful_springmvc.services.VendorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vendors")
@AllArgsConstructor
public class VendorController {

    private VendorService vendorService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public VendorListDto getAllVendors(){
        return new VendorListDto(vendorService.getAllVendors());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendorDto createVendor(@RequestBody VendorDto vendorDto){
        return vendorService.createVendor(vendorDto);
    }
}
