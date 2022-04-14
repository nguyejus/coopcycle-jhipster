package com.nguyejus.coopcycle.service.mapper;

import com.nguyejus.coopcycle.domain.Course;
import com.nguyejus.coopcycle.domain.DeliveryMan;
import com.nguyejus.coopcycle.service.dto.CourseDTO;
import com.nguyejus.coopcycle.service.dto.DeliveryManDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Course} and its DTO {@link CourseDTO}.
 */
@Mapper(componentModel = "spring")
public interface CourseMapper extends EntityMapper<CourseDTO, Course> {
    @Mapping(target = "deliveryMan", source = "deliveryMan", qualifiedByName = "deliveryManId")
    CourseDTO toDto(Course s);

    @Named("deliveryManId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DeliveryManDTO toDtoDeliveryManId(DeliveryMan deliveryMan);
}
