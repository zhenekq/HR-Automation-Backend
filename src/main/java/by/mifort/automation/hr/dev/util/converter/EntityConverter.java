package by.mifort.automation.hr.dev.util.converter;

import java.util.List;

/**
 * Dto converter to entities
 *
 * @author yauheni_vozny
 * @version 1.0
 */

public interface EntityConverter<T, Y> {

    Y convertToEntityDto(T entity);

    List<Y> convertToListEntityDto(List<T> entities);

    T convertToEntity(Y entityDto);

    List<T> convertToListEntity(List<Y> entityDtoList);
}
