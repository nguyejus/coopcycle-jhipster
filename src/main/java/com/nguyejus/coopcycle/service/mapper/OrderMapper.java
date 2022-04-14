package com.nguyejus.coopcycle.service.mapper;

import com.nguyejus.coopcycle.domain.Cooperative;
import com.nguyejus.coopcycle.domain.Course;
import com.nguyejus.coopcycle.domain.Customer;
import com.nguyejus.coopcycle.domain.Order;
import com.nguyejus.coopcycle.service.dto.CooperativeDTO;
import com.nguyejus.coopcycle.service.dto.CourseDTO;
import com.nguyejus.coopcycle.service.dto.CustomerDTO;
import com.nguyejus.coopcycle.service.dto.OrderDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Order} and its DTO {@link OrderDTO}.
 */
@Mapper(componentModel = "spring")
public interface OrderMapper extends EntityMapper<OrderDTO, Order> {
    @Mapping(target = "course", source = "course", qualifiedByName = "courseId")
    @Mapping(target = "customer", source = "customer", qualifiedByName = "customerId")
    @Mapping(target = "cooperative", source = "cooperative", qualifiedByName = "cooperativeId")
    OrderDTO toDto(Order s);

    @Named("courseId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CourseDTO toDtoCourseId(Course course);

    @Named("customerId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CustomerDTO toDtoCustomerId(Customer customer);

    @Named("cooperativeId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CooperativeDTO toDtoCooperativeId(Cooperative cooperative);
}
