package com.velialiyev.restful_springmvc.api.v1.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDto {
    Long id;
    String firstname;
    String lastname;
    String customer_url;
}
