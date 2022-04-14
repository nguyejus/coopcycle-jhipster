package com.nguyejus.coopcycle.service.mapper;

import com.nguyejus.coopcycle.domain.Menu;
import com.nguyejus.coopcycle.domain.Product;
import com.nguyejus.coopcycle.service.dto.MenuDTO;
import com.nguyejus.coopcycle.service.dto.ProductDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Product} and its DTO {@link ProductDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProductMapper extends EntityMapper<ProductDTO, Product> {
    @Mapping(target = "menu", source = "menu", qualifiedByName = "menuId")
    ProductDTO toDto(Product s);

    @Named("menuId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    MenuDTO toDtoMenuId(Menu menu);
}
