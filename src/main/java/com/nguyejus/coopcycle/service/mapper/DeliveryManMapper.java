package com.nguyejus.coopcycle.service.mapper;

import com.nguyejus.coopcycle.domain.DeliveryMan;
import com.nguyejus.coopcycle.service.dto.DeliveryManDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DeliveryMan} and its DTO {@link DeliveryManDTO}.
 */
@Mapper(componentModel = "spring")
public interface DeliveryManMapper extends EntityMapper<DeliveryManDTO, DeliveryMan> {}
