package restful_springmvc.api.v1.model;

import com.restful_springmvc.CustomerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class CustomerListDto {
    List<CustomerDto> customers;
}
