package com.nguyejus.coopcycle.service.mapper;

import com.nguyejus.coopcycle.domain.Cooperative;
import com.nguyejus.coopcycle.domain.Menu;
import com.nguyejus.coopcycle.service.dto.CooperativeDTO;
import com.nguyejus.coopcycle.service.dto.MenuDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Menu} and its DTO {@link MenuDTO}.
 */
@Mapper(componentModel = "spring")
public interface MenuMapper extends EntityMapper<MenuDTO, Menu> {
    @Mapping(target = "cooperative", source = "cooperative", qualifiedByName = "cooperativeId")
    MenuDTO toDto(Menu s);

    @Named("cooperativeId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CooperativeDTO toDtoCooperativeId(Cooperative cooperative);
}
