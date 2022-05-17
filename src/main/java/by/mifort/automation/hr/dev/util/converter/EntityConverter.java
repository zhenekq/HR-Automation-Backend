package by.mifort.automation.hr.dev.util.converter;

import java.util.List;

/**
 * Dto converter to entities
 *
 * @author yauheni_vozny
 * @version 1.0
 */

public interface EntityConverter<Entity, EntityDto> {

    EntityDto convertToEntityDto(Entity entity);

    List<EntityDto> convertToListEntityDto(List<Entity> entities);

    Entity convertToEntity(EntityDto entityDto);

    List<Entity> convertToListEntity(List<EntityDto> entityDtoList);
}
