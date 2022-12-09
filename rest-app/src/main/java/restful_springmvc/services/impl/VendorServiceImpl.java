package restful_springmvc.services.impl;

import restful_springmvc.api.v1.mapper.VendorMapper;
import restful_springmvc.api.v1.model.VendorDto;
import restful_springmvc.domain.Vendor;
import restful_springmvc.repository.VendorRepository;
import restful_springmvc.services.VendorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VendorServiceImpl implements VendorService {

    private VendorRepository vendorRepository;
    private VendorMapper vendorMapper = VendorMapper.INSTANCE;

    @Override
    public List<VendorDto> getAllVendors() {
        return vendorRepository.findAll().stream().map(vendorMapper::vendorToVendorDto).collect(Collectors.toList());
    }

    @Override
    public VendorDto createVendor(VendorDto vendorDto) {
        Vendor vendor = vendorMapper.vendorDtoToVendor(vendorDto);
        return vendorMapper.vendorToVendorDto(vendorRepository.save(vendor));
    }

    @Override
    public VendorDto updateVendor(Long id, VendorDto vendorDto) {
        Vendor oldVendor = vendorRepository.findById(id).orElseThrow();
        oldVendor.setName(vendorDto.getName());
        return vendorMapper.vendorToVendorDto(vendorRepository.save(oldVendor));
    }

    @Override
    public VendorDto patchVendor(Long id, VendorDto vendorDto) {

        Vendor vendor = vendorRepository.findById(id).orElseThrow();

        if(vendorDto.getName() != null){
            vendor.setName(vendorDto.getName());
        }

        if(vendorDto.getVendor_url() != null){
            vendor.setVendor_url(vendorDto.getVendor_url());
        }

        return vendorMapper.vendorToVendorDto(vendorRepository.save(vendor));
    }

    @Override
    public VendorDto findVendorById(Long id) {
        return vendorMapper.vendorToVendorDto(vendorRepository.findById(id).orElseThrow());
    }

    @Override
    public void deleteVendorById(Long id) {
        vendorRepository.deleteById(id);
    }
}
