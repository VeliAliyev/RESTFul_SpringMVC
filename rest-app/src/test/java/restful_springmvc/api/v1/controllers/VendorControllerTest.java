package restful_springmvc.api.v1.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import restful_springmvc.api.v1.model.VendorDto;
import restful_springmvc.services.VendorService;

import java.util.Arrays;
import java.util.List;

import static restful_springmvc.api.v1.controllers.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendors", hasSize(2)));
    }

    @Test
    void createVendor() throws Exception {
        //given
        VendorDto vendorDto = VendorDto.builder().id(1L).name("Ayşe").vendor_url("/api/v1/vendors/1").build();
        when(vendorService.createVendor(any(VendorDto.class))).thenReturn(vendorDto);

        //when
        mockMvc.perform(post("/api/v1/vendors")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendorDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo("Ayşe")))
                .andExpect(jsonPath("$.vendor_url", equalTo("/api/v1/vendors/1")));

    }

    @Test
    void updateVendor() throws Exception {
        //given
        VendorDto vendorDto = VendorDto.builder().id(1L).name("Ayşe").vendor_url("/api/v1/vendors/1").build();
        when(vendorService.updateVendor(anyLong(), any(VendorDto.class))).thenReturn(vendorDto);

        //when
        mockMvc.perform(put("/api/v1/vendors/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(vendorDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Ayşe")))
                .andExpect(jsonPath("$.vendor_url", equalTo("/api/v1/vendors/1")));

    }


    @Test
    void findVendorById() throws Exception {
        //given
        VendorDto vendorDto = VendorDto.builder().id(1L).name("Ayşe").vendor_url("/api/v1/vendors/1").build();
        when(vendorService.findVendorById(anyLong())).thenReturn(vendorDto);

        //when
        mockMvc.perform(get("/api/v1/vendors/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Ayşe")))
                .andExpect(jsonPath("$.vendor_url", equalTo("/api/v1/vendors/1")));

    }
}