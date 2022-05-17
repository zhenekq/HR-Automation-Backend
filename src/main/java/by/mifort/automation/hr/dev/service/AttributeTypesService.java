package by.mifort.automation.hr.dev.service;

import by.mifort.automation.hr.dev.dto.AttributeTypesDto;
import by.mifort.automation.hr.dev.dto.FilterDto;
import by.mifort.automation.hr.dev.entity.AttributeTypes;

import java.util.List;

/**
 * Service layer for validation and business logic for AttributeTypesRepository
 *
 * @author yauheni_vozny
 * @version 1.0
 * @see by.mifort.automation.hr.dev.repository.AttributeTypesRepository
 */

public interface AttributeTypesService {

    /**
     * @param filterDto dto with pagination, sortTypes
     * @return List of all exists paginated attribute types
     * @see FilterDto
     */
    List<AttributeTypes> getAll(FilterDto filterDto);


    /**
     * Get attribute type by id
     *
     * @param id identifier of attribute type
     * */
    AttributeTypes getById(Integer id);

    /**
     * Create new attribute type in database
     *
     * @param attributeTypes body of our attribute type
     */
    AttributeTypes create(AttributeTypes attributeTypes);

    /**
     * Update exists attribute type
     *
     * @param id             attribute's type identifier
     * @param attributeTypes body of attribute's type
     */
    AttributeTypes updateById(Integer id, AttributeTypesDto attributeTypes);

    /**
     * Archive exists attribute's type
     *
     * @param id attribute's type identifier
     */
    AttributeTypes archiveById(Integer id);

}
