package com.nguyejus.coopcycle.service.mapper;

import com.nguyejus.coopcycle.domain.Customer;
import com.nguyejus.coopcycle.service.dto.CustomerDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Customer} and its DTO {@link CustomerDTO}.
 */
@Mapper(componentModel = "spring")
public interface CustomerMapper extends EntityMapper<CustomerDTO, Customer> {}
